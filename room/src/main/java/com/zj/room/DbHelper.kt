package com.zj.room

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.zj.room.sp.SPHelper
import kotlin.Exception

@Suppress("unused")
class DbHelper<T : RoomDatabase> private constructor(context: Context, name: String, dbId: Any, dbClass: Class<T>, migrations: Array<Migration>?) {

    private var db: T
    private var dbName = "$name:$dbId"

    companion object {
        const val SP_LAST_DB_VERSION = "get_last_db_version"
        private var mInstance: DbHelper<*>? = null

        @WorkerThread
        @Suppress("UNCHECKED_CAST")
        fun <T : RoomDatabase> get(context: Context, dbName: String, tableId: Any, dbClass: Class<T>, migrations: Array<Migration>?): DbHelper<T> {
            if (mInstance != null && mInstance?.dbName != "$dbName:$tableId") {
                mInstance = null
            }
            if (mInstance == null) {
                synchronized(DbHelper::class.java) {
                    if (mInstance == null) {
                        mInstance = DbHelper(context, dbName, tableId, dbClass, migrations)
                    }
                }
            }
            return mInstance as DbHelper<T>
        }
    }

    init {
        SPHelper.init("SP:$dbName:$dbId", context)
        val builder = Room.databaseBuilder(context, dbClass, dbName)
        builder.setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
        builder.allowMainThreadQueries()
        var needClearSp = false
        if (migrations.isNullOrEmpty()) {
            builder.fallbackToDestructiveMigration()
            needClearSp = true
        } else {
            builder.addMigrations(*migrations)
        }
        db = builder.build()
        init(needClearSp)
    }

    private fun init(needClearSp: Boolean) {
        try {
            val curDbVersion = db.openHelper.writableDatabase.version
            val last = SPHelper[SP_LAST_DB_VERSION, 0] ?: 0
            if (last != curDbVersion) {
                if (needClearSp) {
                    SPHelper.clear()
                } else {
                    println("Warming: DB Version has been changed and migrated , SP data is keep.")
                }
            }
            SPHelper.put(SP_LAST_DB_VERSION, curDbVersion)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getDb(): T {
        return db
    }

    fun clearAll() {
        SPHelper.clear()
        db.clearAllTables()
    }

    fun <N> putToSp(key: String, value: N) {
        SPHelper.put(key, value)
    }

    fun <T : Any> getFromSp(key: String, default: T): T? {
        return SPHelper[key, default]
    }
}
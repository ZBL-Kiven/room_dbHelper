package com.zj.roomDb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zj.room.DbHelper
import com.zj.roomDb.db.dao.MsgDao
import com.zj.roomDb.db.entities.MsgEntity

/**
 * 所有要存的DAO都在这里面声明，用于初始化DB
 * 注解信息参照 [Database]，不要在此注解指定 Migration ，
 * 在 [DbHelper] 初始化指定会更便于调试和自带容错。
 * exportSchema 保持 False 就行了，除非你打算查看它的配置导出表。
 */
@Database(entities = [MsgEntity::class], version = 1, exportSchema = false)
abstract class DAOs : RoomDatabase() {

    abstract fun msgDao(): MsgDao

}

package com.zj.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [MsgEntity::class], version = 1, exportSchema = false)
abstract class DAOs : RoomDatabase() {

    abstract fun msgDao(): MsgDao

}

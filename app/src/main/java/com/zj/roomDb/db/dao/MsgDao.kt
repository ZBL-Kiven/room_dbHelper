package com.zj.roomDb.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.zj.roomDb.db.entities.MsgEntity

//表的操作类，用于写所有的sql。
@Dao
interface MsgDao {

    @Query("SELECT * FROM msgEntity /*通过 @Entity(tableName = ) 注解的表名*/  WHERE id = :id")
    fun getMessageById(id: Int): MsgEntity?


}
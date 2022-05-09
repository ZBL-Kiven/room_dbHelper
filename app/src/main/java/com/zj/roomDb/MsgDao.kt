package com.zj.roomDb

import androidx.room.Dao
import androidx.room.Query

@Dao
interface MsgDao {

    @Query("SELECT * FROM msgEntity WHERE id = :id")
    fun getMessageById(id: Int): MsgEntity?


}
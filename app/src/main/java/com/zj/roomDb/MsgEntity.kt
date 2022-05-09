package com.zj.roomDb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "msgEntity")
class MsgEntity {

    @PrimaryKey val id: Int = 0

}
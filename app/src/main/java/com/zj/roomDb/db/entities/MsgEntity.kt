package com.zj.roomDb.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

//实体类，注解的表名用于Dao的Sql使用。必须有  @PrimaryKey
@Entity(tableName = "msgEntity")
class MsgEntity {

    @PrimaryKey var id: Int = 0

}
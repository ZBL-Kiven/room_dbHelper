package com.zj.roomDb

import android.content.Context
import com.zj.room.DbHelper

class Test1 {

    fun test(context: Context) {
        val db = DbHelper.get(context, "test", 100, DAOs::class.java, null)
        db.getDb().msgDao().getMessageById(10)
    }

}
package com.zj.roomDb

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zj.room.DbHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test(this)
    }

    fun test(context: Context) {
        val db = DbHelper.get(context, "test", 100, DAOs::class.java, null)
        db.getDb().msgDao().getMessageById(10)
    }

}
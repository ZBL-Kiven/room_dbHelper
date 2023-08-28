package com.zj.roomDb

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zj.room.DbHelper
import com.zj.roomDb.db.DAOs

class MainActivity : AppCompatActivity() {

    companion object {
        //通常是模块名。支持一个项目多个模块，也就是单用户数据、模块数据、和公共数据可以并存。
        private const val TABLE_NAME = "APP_MODULE_NAME"
        lateinit var appContext: Application

        // 数据库初始化，拿到的是一个代理对象，建议封装成公共方法，内部是单例，如果 DAOs版本出现升级且migrations传空，那自动清除这个ID的所有数据。
        fun getUserDb(tableId: Int): DAOs {
            return DbHelper.get(appContext, TABLE_NAME, tableId, DAOs::class.java, null).getDb()
        }
    }

    private val tableId = 1001 //随意，通常用 UserId 来区分用户表。

    override fun onCreate(savedInstanceState: Bundle?) {
        appContext = application
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test()
    }

    //使用
    private fun test() {
        getUserDb(tableId).msgDao().getMessageById(0)

        // 下面的SP也是 SharedPerformance ，但用它的好处是，数据库表名和ID都和它绑定的，数据库版本也和它绑定的，
        // 所以如果是使用这个SP，它自动根据用户信息初始化，如果数据库升级了版本并且没有指定 Migration，它自动清除数据。
        // db.getFromSp()
        // db.putToSp()
    }

}
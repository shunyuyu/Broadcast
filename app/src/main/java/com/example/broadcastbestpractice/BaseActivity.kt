package com.example.broadcastbestpractice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity(){

    private lateinit var receiver:BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //凡是继承本类的Activity均会被加入Activity管理器中
        ActivityCollector.addActivity(this)
    }

    /**
     * 处于栈顶，注册广播
     */
    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter("com.example.broadcastbestpractice.FORCS_OFFLIN")
        receiver = ForceOfflineReceiver()
        registerReceiver(receiver,intentFilter)
    }

    /**
     * 离开栈顶取消广播注册
     */
    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    inner class ForceOfflineReceiver:BroadcastReceiver(){
        override fun onReceive(context: Context, intent: Intent) {
            AlertDialog.Builder(context).apply {
                setTitle("强制下线通知")
                setMessage("账号已在别处登录，若非本人登录，请尽快修改密码")
                setCancelable(false)
                setPositiveButton("确定"){_,_->
                    //结束所有的Activity
                    ActivityCollector.finishAll()
                    //重新打开登录界面
                    val i = Intent(context,LoginActivity::class.java)
                    context.startActivity(i)
                }
                show()
            }
        }

    }
}
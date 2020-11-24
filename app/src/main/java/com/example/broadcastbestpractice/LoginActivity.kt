package com.example.broadcastbestpractice

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val prefs = getPreferences(MODE_PRIVATE)
        val isDemember = prefs.getBoolean("remember_pwd",false)
        if (isDemember){
            val account = prefs.getString("user","")
            val pwd = prefs.getString("pwd","")
            UserID.setText(account)
            UserPwd.setText(pwd)
            checkBoxPass.isChecked = true
        }
        LoginButton.setOnClickListener {
            val id = UserID.text.trim().toString()
            val pwd = UserPwd.text.trim().toString()
            if (id == "2631582891" && pwd == "123456"){
                val editor = prefs.edit()
                if (checkBoxPass.isChecked){
                    editor.putBoolean("remember_pwd",true)
                    editor.putString("user",id)
                    editor.putString("pwd",pwd)
                }else{
                    editor.clear()
                }
                editor.apply()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"QQ或者密码错误，请重试",Toast.LENGTH_LONG).show()
            }
        }

    }
}
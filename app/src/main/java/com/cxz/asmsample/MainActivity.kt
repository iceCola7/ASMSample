package com.cxz.asmsample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.tv_click

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_click.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                Log.e("TAG", "正常点击事件")
            }
        })

//        tv_click.setOnClickListener {
//            Log.e("TAG", "正常点击事件")
//        }

    }
}
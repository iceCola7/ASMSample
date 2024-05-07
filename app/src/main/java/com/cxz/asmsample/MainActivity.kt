package com.cxz.asmsample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cxz.auto_track_sdk.SensorsDataTrackViewOnClick
import kotlinx.android.synthetic.main.activity_main.tv_click
import kotlinx.android.synthetic.main.activity_main.tv_other

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_click.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.e(TAG, "正常点击事件")
            }
        })

//        tv_click.setOnClickListener {
//            Log.e("TAG", "正常点击事件")
//        }

        tv_other.setOnClickListener(this)

    }

    @SensorsDataTrackViewOnClick
    fun onXmlClick(v: View?) {
        Log.e(TAG, "onXmlClick onXmlClick")
    }

    override fun onClick(v: View?) {
        when (v?.id ?: -1) {
            R.id.tv_other -> {
                Log.e(TAG, "Other Other")
            }
        }
    }
}
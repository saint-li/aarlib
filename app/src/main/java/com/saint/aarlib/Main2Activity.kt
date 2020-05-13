package com.saint.aarlib

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.heytap.msp.push.HeytapPushManager
import com.heytap.msp.push.callback.ICallBackResultService

class Main2Activity : AppCompatActivity() {
    var tvInitResult: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        tvInitResult = findViewById(R.id.tv_init_result)
        HeytapPushManager.init(application, true)
        HeytapPushManager.register(application, "appKey", "appSecret", PushCallBack())
        HeytapPushManager.requestNotificationPermission()
    }

    fun pushStatus(view: View?) {

    }
    fun getId(view: View?) {

    }

    /************************************************************************************
     * ***************************callbacks from mcs************************************
     */
    inner class PushCallBack : ICallBackResultService {
        override fun onRegister(code: Int, s: String) {
            if (code == 0) {
                showResult("注册成功", "registerId:$s")
            } else {
                showResult("注册失败", "code=$code,msg=$s")
            }
        }

        override fun onUnRegister(code: Int) {
            if (code == 0) {
                showResult("注销成功", "code=$code")
            } else {
                showResult("注销失败", "code=$code")
            }
        }

        override fun onGetPushStatus(code: Int, status: Int) {
            if (code == 0 && status == 0) {
                showResult("Push状态正常", "code=$code,status=$status")
            } else {
                showResult("Push状态错误", "code=$code,status=$status")
            }
        }

        override fun onGetNotificationStatus(code: Int, status: Int) {
            if (code == 0 && status == 0) {
                showResult("通知状态正常", "code=$code,status=$status")
            } else {
                showResult("通知状态错误", "code=$code,status=$status")
            }
        }

        override fun onSetPushTime(code: Int, s: String) {
            showResult("SetPushTime", "code=$code,result:$s")
        }
    }

    /**
     * 此方法会将结果进行回显
     */
    private fun showResult(@Nullable tag: String, @NonNull msg: String) {
        Log.d(tag, msg)
    }
}
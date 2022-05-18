package com.gabrielsantana.smslistener

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import androidx.annotation.RequiresApi


class MessageReceiver : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context?, intent: Intent?) {
        val data = intent?.extras
        val format = intent?.getStringExtra("format")
        val pdus = data?.get("pdus") as Array<Any>?
        if (pdus != null) {
            for (i in pdus.indices) {
                val smsMessage: SmsMessage = SmsMessage.createFromPdu(pdus[i] as ByteArray?, format)
                val message = "Sender : " + smsMessage.displayOriginatingAddress
                    .toString() + "Email From: " + smsMessage.emailFrom
                    .toString() + "Emal Body: " + smsMessage.emailBody
                    .toString() + "Display message body: " + smsMessage.displayMessageBody
                    .toString() + "Time in millisecond: " + smsMessage.timestampMillis
                    .toString() + "Message: " + smsMessage.messageBody
                mListener?.messageReceived(message)
            }
        }
    }

    companion object {
        private var mListener: MessageListener? = null
        fun bindListener(listener: MessageListener?) {
            mListener = listener
        }
    }
}
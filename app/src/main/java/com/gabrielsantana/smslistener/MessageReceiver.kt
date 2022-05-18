package com.gabrielsantana.smslistener

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Telephony
import java.util.*

class MessageReceiver : BroadcastReceiver() {

    companion object {
        private var mListener: MessageListener? = null
        fun bindListener(listener: MessageListener?) {
            mListener = listener
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            for (sms in Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                mListener!!.messageReceived("Remetente: ${sms.displayOriginatingAddress}, Mensagem: ${sms.displayMessageBody}, Data: ${Date(sms.timestampMillis)}")
            }
        }
    }

}
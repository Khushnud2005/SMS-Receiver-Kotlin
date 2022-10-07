package uz.example.sms_receiver_kotlin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsMessage
import android.widget.Toast
import java.lang.Exception

class SmsReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action == "android.provider.Telephony.SMS_RECEIVED") {
            val bundle = intent!!.extras
            val msgs: Array<SmsMessage?>
            var msg_from: String?
            if (bundle != null) {
                try {
                    val pdus = bundle["pdus"] as Array<Any>?
                    msgs = arrayOfNulls(pdus!!.size)
                    for (i in msgs.indices) {
                        msgs[i] = SmsMessage.createFromPdu(pdus!![i] as ByteArray)
                        msg_from = msgs[i]!!.getOriginatingAddress()
                        val msg_body = msgs[i]!!.getMessageBody()
                        Toast.makeText(
                            context,
                            "From : $msg_from\nMessage : $msg_body",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
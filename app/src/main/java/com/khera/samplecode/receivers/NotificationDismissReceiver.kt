package com.khera.samplecode.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.khera.samplecode.utils.ConstantStrings
import com.khera.samplecode.utils.NotificationUtils.clearNotifications

class NotificationDismissReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notificationId = intent.getIntExtra(ConstantStrings.KEY_NOTIFY_ID, 0)
        clearNotifications(context, notificationId)
    }
}
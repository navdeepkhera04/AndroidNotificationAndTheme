package com.khera.samplecode.services

import android.app.IntentService
import android.content.Intent
import androidx.core.app.RemoteInput
import com.khera.samplecode.utils.ConstantStrings
import com.khera.samplecode.utils.NotificationUtils.showSimpleNotification

class NotificationReplyIntent : IntentService(NotificationReplyIntent::class.java.simpleName) {
    override fun onHandleIntent(intent: Intent?) {
        val directReply = getMessageText(intent)
        if (directReply != null) {
            showSimpleNotification(applicationContext,
                    "Reply",
                    "Received: $directReply")
        }
    }

    private fun getMessageText(intent: Intent?): CharSequence? {
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        return remoteInput?.getCharSequence(ConstantStrings.KEY_TEXT_REPLY)
    }
}
package com.khera.samplecode.utils

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import androidx.core.app.RemoteInput
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.IconCompat
import com.khera.samplecode.R
import com.khera.samplecode.activity.HomeActivity
import com.khera.samplecode.receivers.NotificationDismissReceiver
import com.khera.samplecode.services.NotificationReplyIntent
import com.khera.samplecode.utils.CommonUtils.getCircularBitmap
import com.khera.samplecode.utils.CommonUtils.getIcon
import java.util.*

object NotificationUtils {
    @SuppressLint("StaticFieldLeak")
    private var mBuilder: NotificationCompat.Builder? = null
    private const val REQUEST_CODE = 12
    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun setNotiChannel(): NotificationChannel {
        val channel = NotificationChannel(ConstantStrings.NOTIFICATION_CHANNEL_ID,
                ConstantStrings.NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
        channel.description = ConstantStrings.NOTIFICATION_CONTENT_DESCRIPTION
        channel.enableLights(true)
        return channel
    }

    private fun getNotiManager(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (isOreo) notificationManager.createNotificationChannel(setNotiChannel())
        notificationManager.notify(ConstantStrings.NOTIFICATION_ID, mBuilder!!.build())
    }

    @JvmStatic
    fun clearNotifications(context: Context, notificationId: Int) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.cancel(notificationId)
    }

    private val isOreo: Boolean
        private get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

    private fun createPendingIntent(context: Context, requestCode: Int, flags: Int, resultIntent: Intent): PendingIntent {
        return PendingIntent.getActivity(context, requestCode, resultIntent, flags)
    }

    private fun createPendingService(context: Context, requestCode: Int, flags: Int, resultIntent: Intent): PendingIntent {
        return PendingIntent.getService(context, requestCode, resultIntent, flags)
    }

    private fun createPendingBroadcast(context: Context, requestCode: Int, flags: Int, resultIntent: Intent): PendingIntent {
        return PendingIntent.getBroadcast(context, requestCode, resultIntent, flags)
    }

    private fun getIntent(context: Context, mClass: Class<*>): Intent {
        return Intent(context, mClass)
    }

    @JvmStatic
    fun showSimpleNotification(context: Context, title: String?, message: String?) {
        mBuilder = if (isOreo) NotificationCompat.Builder(context, ConstantStrings.NOTIFICATION_CHANNEL_ID) else NotificationCompat.Builder(context)
        mBuilder!!.setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
        mBuilder!!.setContentTitle(title)
        mBuilder!!.setContentText(message)
        mBuilder!!.setAutoCancel(true)
        mBuilder!!.color = ContextCompat.getColor(context, R.color.blue_A400)
        mBuilder!!.setContentIntent(createPendingIntent(context, REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT, getIntent(context, HomeActivity::class.java)))
        getNotiManager(context)
    }

    @JvmStatic
    fun showBigTextNotification(context: Context, title: String?, message: String?) {
        mBuilder = if (isOreo) NotificationCompat.Builder(context, ConstantStrings.NOTIFICATION_CHANNEL_ID) else NotificationCompat.Builder(context)
        mBuilder!!.setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
        mBuilder!!.setContentText(message)
        mBuilder!!.setStyle(NotificationCompat.BigTextStyle()
                .bigText(message)
                .setSummaryText(title))
        mBuilder!!.setAutoCancel(true)
        mBuilder!!.color = ContextCompat.getColor(context, R.color.blue_A400)
        mBuilder!!.setContentIntent(createPendingIntent(context, REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT, getIntent(context, HomeActivity::class.java)))
        getNotiManager(context)
    }

    @JvmStatic
    fun showBigPictureNotification(context: Context, title: String?, message: String?, largeIcon: Bitmap?, picture: Bitmap?) {
        mBuilder = if (isOreo) NotificationCompat.Builder(context, ConstantStrings.NOTIFICATION_CHANNEL_ID) else NotificationCompat.Builder(context)
        mBuilder!!.setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
        mBuilder!!.setContentText(message)
        mBuilder!!.setContentTitle(title)
        mBuilder!!.setStyle(NotificationCompat.BigPictureStyle()
                .setSummaryText(message)
                .setBigContentTitle(title)
                .bigLargeIcon(getCircularBitmap(largeIcon!!))
                .bigPicture(picture))
        mBuilder!!.setAutoCancel(true)
        mBuilder!!.color = ContextCompat.getColor(context, R.color.blue_A400)
        mBuilder!!.setContentIntent(createPendingIntent(context, REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT, getIntent(context, HomeActivity::class.java)))
        getNotiManager(context)
    }

    @JvmStatic
    fun showInboxNotification(context: Context, title: String?, messages: ArrayList<String?>) {
        val iStyle = NotificationCompat.InboxStyle()
        for (i in messages.indices) {
            iStyle.addLine(messages[i])
        }
        iStyle.setSummaryText("+" + messages.size + " more")
        mBuilder = if (isOreo) NotificationCompat.Builder(context, ConstantStrings.NOTIFICATION_CHANNEL_ID) else NotificationCompat.Builder(context)
        mBuilder!!.setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
        mBuilder!!.setContentTitle(title)
        mBuilder!!.setContentText(messages[messages.size - 1])
        mBuilder!!.setStyle(iStyle)
        mBuilder!!.setAutoCancel(true)
        mBuilder!!.color = ContextCompat.getColor(context, R.color.blue_A400)
        mBuilder!!.setContentIntent(createPendingIntent(context, REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT, getIntent(context, HomeActivity::class.java)))
        getNotiManager(context)
    }

    @JvmStatic
    fun showNotificationWithReply(context: Context, title: String?, message: String?) {
        mBuilder = if (isOreo) NotificationCompat.Builder(context, ConstantStrings.NOTIFICATION_CHANNEL_ID) else NotificationCompat.Builder(context)
        mBuilder!!.setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
        mBuilder!!.setContentTitle(title)
        mBuilder!!.setContentText(message)
        mBuilder!!.setAutoCancel(true)
        mBuilder!!.color = ContextCompat.getColor(context, R.color.blue_A400)
        val directReplyIntent = getIntent(context, NotificationReplyIntent::class.java)
        directReplyIntent.putExtra(ConstantStrings.KEY_NOTIFY_ID, 82)
        directReplyIntent.action = java.lang.Long.toString(System.currentTimeMillis())
        val remoteInput = RemoteInput.Builder(ConstantStrings.KEY_TEXT_REPLY)
                .setLabel("Message").build()
        val action = NotificationCompat.Action.Builder(
                R.drawable.ic_baseline_send_24, "Reply", createPendingService(context, REQUEST_CODE, PendingIntent.FLAG_CANCEL_CURRENT, directReplyIntent))
                .addRemoteInput(remoteInput).build()
        mBuilder!!.addAction(action)
        getNotiManager(context)
    }

    @JvmStatic
    fun showHeadsUpNotification(context: Context, title: String?, message: String?) {
        val buttonIntent = getIntent(context, NotificationDismissReceiver::class.java)
        buttonIntent.action = java.lang.Long.toString(System.currentTimeMillis())
        buttonIntent.putExtra(ConstantStrings.KEY_NOTIFY_ID, ConstantStrings.NOTIFICATION_ID)
        mBuilder = if (isOreo) NotificationCompat.Builder(context, ConstantStrings.NOTIFICATION_CHANNEL_ID) else NotificationCompat.Builder(context)
        mBuilder!!.setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
        mBuilder!!.setContentTitle(title)
        mBuilder!!.setContentText(message)
        mBuilder!!.setAutoCancel(true)
        mBuilder!!.priority = NotificationCompat.PRIORITY_MAX
        mBuilder!!.color = ContextCompat.getColor(context, R.color.blue_A400)
        mBuilder!!.setDefaults(NotificationCompat.DEFAULT_ALL)
        mBuilder!!.priority = NotificationCompat.PRIORITY_HIGH
        mBuilder!!.addAction(R.drawable.ic_baseline_call_24, "Answer",
                createPendingIntent(context, REQUEST_CODE, PendingIntent.FLAG_CANCEL_CURRENT, getIntent(context, HomeActivity::class.java)))
        mBuilder!!.addAction(R.drawable.ic_baseline_call_end_24, "Cancel",
                createPendingBroadcast(context, REQUEST_CODE, PendingIntent.FLAG_CANCEL_CURRENT, buttonIntent))
        getNotiManager(context)
    }

    @JvmStatic
    fun showMessageNotification(context: Context, title: String?, groupTitle: String?, largeIcon: Bitmap?) {
        val user = Person.Builder()
                .setIcon(IconCompat.createWithBitmap(getCircularBitmap(getIcon(context, R.drawable.dummy_user1))))
                .setName("khera").build()
        val reciever = Person.Builder()
                .setIcon(IconCompat.createWithBitmap(getCircularBitmap(getIcon(context, R.drawable.dummy_user2))))
                .setName("Riya").build()
        val style = NotificationCompat.MessagingStyle(user)
        style.conversationTitle = groupTitle
        style.addMessage("Hey there!", System.currentTimeMillis(), user)
        style.addMessage("Hi , long time no see", System.currentTimeMillis(), reciever)
        style.addMessage("Yeah bit busy with some cool stuff", System.currentTimeMillis(), user)
        mBuilder = if (isOreo) NotificationCompat.Builder(context, ConstantStrings.NOTIFICATION_CHANNEL_ID) else NotificationCompat.Builder(context)
        mBuilder!!.setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
        mBuilder!!.setAutoCancel(true)
        mBuilder!!.setStyle(style)
        mBuilder!!.setLargeIcon(largeIcon)
        mBuilder!!.setContentTitle(title)
        mBuilder!!.color = ContextCompat.getColor(context, R.color.blue_A400)
        mBuilder!!.setContentIntent(createPendingIntent(context, REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT, getIntent(context, HomeActivity::class.java)))
        getNotiManager(context)
    }

    @JvmStatic
    fun showCustomNotification(context: Context, title: String?, message: String?, bitmap: Bitmap?) {
        val contentView = RemoteViews(context.packageName, R.layout.custom_notification_layout)
        contentView.setTextViewText(R.id.txtTitle, title)
        contentView.setTextViewText(R.id.txtMassage, message)
        contentView.setViewVisibility(R.id.imgMsg, View.VISIBLE)
        contentView.setImageViewBitmap(R.id.imgMsg, bitmap)
        mBuilder = if (isOreo) NotificationCompat.Builder(context, ConstantStrings.NOTIFICATION_CHANNEL_ID) else NotificationCompat.Builder(context)
        mBuilder!!.setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
        mBuilder!!.setCustomBigContentView(contentView)
        mBuilder!!.setCustomContentView(contentView)
        mBuilder!!.setContent(contentView)
        mBuilder!!.setAutoCancel(true)
        mBuilder!!.setStyle(NotificationCompat.DecoratedCustomViewStyle())
        mBuilder!!.color = ContextCompat.getColor(context, R.color.blue_A400)
        mBuilder!!.setContentIntent(createPendingIntent(context, REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT, getIntent(context, HomeActivity::class.java)))
        getNotiManager(context)
    }

    @JvmStatic
    fun showProgressNotification(context: Context, title: String?, message: String?) {
        mBuilder = if (isOreo) NotificationCompat.Builder(context, ConstantStrings.NOTIFICATION_CHANNEL_ID) else NotificationCompat.Builder(context)
        mBuilder!!.setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
        mBuilder!!.setContentTitle(title)
        mBuilder!!.setContentText(message)
        mBuilder!!.setAutoCancel(true)
        mBuilder!!.setOngoing(true)
        mBuilder!!.setOnlyAlertOnce(true)
        mBuilder!!.priority = NotificationCompat.PRIORITY_LOW
        mBuilder!!.color = ContextCompat.getColor(context, R.color.blue_A400)
        mBuilder!!.setContentIntent(createPendingIntent(context, REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT, getIntent(context, HomeActivity::class.java)))
        Thread {
            var incr: Int
            incr = 0
            while (incr <= 100) {
                mBuilder!!.setProgress(100, incr, false)
                getNotiManager(context)
                try {
                    Thread.sleep((1 * 1000).toLong())
                } catch (e: InterruptedException) {
                    Log.e("TAG", "sleep failure")
                }
                incr += 5
            }
            mBuilder!!.setContentText("Download completed")
                    .setProgress(0, 0, false)
            getNotiManager(context)
        }.start()
    }
}
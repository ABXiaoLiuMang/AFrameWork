package commonsdk.ktx

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import commonsdk.BuildConfig
import commonsdk.R

/**
 * Notification
 */

fun showNotification(context: Context, channelId: String,title: String,contenttext: String, icon: Int) {
    // CHANNEL_ID：通道ID，可在类 MainActivity 外自定义。如：val CHANNEL_ID = 'msg_1'
    val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(icon)
            .setContentTitle(title)
            .setContentText(contenttext)
            // 通知优先级，可以设置为int型，范围-2至2
            .setPriority(NotificationCompat.PRIORITY_MAX)
    // 显示通知
    with(NotificationManagerCompat.from(context)) {
        notify(1, builder.build())
    }
}

fun createNotificationChannel(context: Context, channel_name: String, channel_description: String, channelId: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = channel_name
        val descriptionText = channel_description
        // 提醒式通知(横幅显示)，不过大部分需要手动授权
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelId, name, importance).apply { description = descriptionText }
        // 注册通道(频道)
        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
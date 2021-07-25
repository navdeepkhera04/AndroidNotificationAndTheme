package com.khera.samplecode.utils

import android.content.Context
import android.content.res.Configuration
import android.graphics.*
import androidx.appcompat.app.AppCompatDelegate

object CommonUtils {
    private val TAG = CommonUtils::class.java.simpleName
    @JvmStatic
    fun isDarkMode(context: Context): Boolean {
        var mode = false
        val nightModeFlags = context.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK
        when (nightModeFlags) {
            Configuration.UI_MODE_NIGHT_YES -> mode = true
            Configuration.UI_MODE_NIGHT_NO -> mode = false
            Configuration.UI_MODE_NIGHT_UNDEFINED -> mode = false
        }
        return mode
    }

    @JvmStatic
    fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    @JvmStatic
    fun enableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    @JvmStatic
    fun getIcon(context: Context, icon: Int): Bitmap {
        return BitmapFactory.decodeResource(context.resources, icon)
    }

    @JvmStatic
    fun getCircularBitmap(bitmap: Bitmap): Bitmap {
        val output: Bitmap
        val srcRect: Rect
        val dstRect: Rect
        val r: Float
        val width = bitmap.width
        val height = bitmap.height
        if (width > height) {
            output = Bitmap.createBitmap(height, height, Bitmap.Config.ARGB_8888)
            val left = (width - height) / 2
            val right = left + height
            srcRect = Rect(left, 0, right, height)
            dstRect = Rect(0, 0, height, height)
            r = (height / 2).toFloat()
        } else {
            output = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888)
            val top = (height - width) / 2
            val bottom = top + width
            srcRect = Rect(0, top, width, bottom)
            dstRect = Rect(0, 0, width, width)
            r = (width / 2).toFloat()
        }
        val canvas = Canvas(output)
        val color = -0xbdbdbe
        val paint = Paint()
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        canvas.drawCircle(r, r, r, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, srcRect, dstRect, paint)
        bitmap.recycle()
        return output
    }

}
package com.khera.samplecode.utils

import java.util.*

object ConstantStrings {
    @JvmField
    var NOTIFICATION_ID = Random().nextInt(99 - 1) + 1
    @JvmField
    var NOTIFICATION_CHANNEL_ID = "amkhera"
    @JvmField
    var NOTIFICATION_CHANNEL_NAME = "samplecode"
    @JvmField
    var NOTIFICATION_CONTENT_DESCRIPTION = "Testing All Notification Types"
    @JvmField
    var KEY_TEXT_REPLY = "key_text_reply"
    @JvmField
    var KEY_NOTIFY_ID = "key_notify_id"
    @JvmField
    var API_URL = "https://api.stackexchange.com/2.2/"
    @JvmField
    var ROOM_API_URL = "https://api.themoviedb.org/"
    @JvmField
    var BACK_DROP_BASE = "http://image.tmdb.org/t/p/w780"
    @JvmField
    var POSTER_BASE = "http://image.tmdb.org/t/p/w185"
}
package com.khera.samplecode.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.khera.samplecode.R
import com.khera.samplecode.utils.CommonUtils.getIcon
import com.khera.samplecode.utils.NotificationUtils.showBigPictureNotification
import com.khera.samplecode.utils.NotificationUtils.showBigTextNotification
import com.khera.samplecode.utils.NotificationUtils.showCustomNotification
import com.khera.samplecode.utils.NotificationUtils.showHeadsUpNotification
import com.khera.samplecode.utils.NotificationUtils.showInboxNotification
import com.khera.samplecode.utils.NotificationUtils.showMessageNotification
import com.khera.samplecode.utils.NotificationUtils.showNotificationWithReply
import com.khera.samplecode.utils.NotificationUtils.showProgressNotification
import com.khera.samplecode.utils.NotificationUtils.showSimpleNotification
import com.khera.samplecode.widgets.BoldButton
import java.util.*

class NotificationFragment : Fragment() {
    private var btnSimpleNoti: BoldButton? = null
    private var btnBigTextNoti: BoldButton? = null
    private var btnBigPictureNoti: BoldButton? = null
    private var btnWithReplyNoti: BoldButton? = null
    private var btnInboxNoti: BoldButton? = null
    private var btnMsgNoti: BoldButton? = null
    private var btnHeadsUpNoti: BoldButton? = null
    private var btnProgNoti: BoldButton? = null
    private var btnCustomNoti: BoldButton? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_notification, container, false)
        findViewById(root)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListners()
    }

    private fun findViewById(root: View) {
        btnSimpleNoti = root.findViewById(R.id.btnSimpleNoti)
        btnBigTextNoti = root.findViewById(R.id.btnBigTextNoti)
        btnBigPictureNoti = root.findViewById(R.id.btnBigPictureNoti)
        btnWithReplyNoti = root.findViewById(R.id.btnWithReplyNoti)
        btnInboxNoti = root.findViewById(R.id.btnInboxNoti)
        btnHeadsUpNoti = root.findViewById(R.id.btnHeadsUpNoti)
        btnMsgNoti = root.findViewById(R.id.btnMsgNoti)
        btnCustomNoti = root.findViewById(R.id.btnCustomNoti)
        btnProgNoti = root.findViewById(R.id.btnProgNoti)
    }

    private fun setClickListners() {
        btnSimpleNoti!!.setOnClickListener { v: View? ->
            showSimpleNotification(activity!!,
                    "Hello There!",
                    "Welcome to samplecode")
        }
        btnBigTextNoti!!.setOnClickListener { v: View? ->
            showBigTextNotification(activity!!,
                    "Important Update",
                    "Hello User , if you like our work consider donating some amount by tapping 'Buy me a coffee' in the app")
        }
        btnBigPictureNoti!!.setOnClickListener { v: View? ->
            showBigPictureNotification(activity!!,
                    "Updates",
                    "New System Update is out with lots of features",
                    getIcon(activity!!, R.drawable.header_image),
                    getIcon(activity!!, R.drawable.dummy_image))
        }
        btnInboxNoti!!.setOnClickListener { v: View? ->
            showInboxNotification(activity!!,
                    "Riya Sharma",
                    messagesList)
        }
        btnWithReplyNoti!!.setOnClickListener { v: View? ->
            showNotificationWithReply(activity!!,
                    "Riya Sharma",
                    "Hey are you there?")
        }
        btnMsgNoti!!.setOnClickListener { v: View? ->
            showMessageNotification(activity!!,
                    "Chats",
                    "Group chats",
                    getIcon(activity!!, R.drawable.dummy_user1))
        }
        btnHeadsUpNoti!!.setOnClickListener { v: View? ->
            showHeadsUpNotification(activity!!,
                    "Incoming call",
                    "Riya is calling you")
        }
        btnCustomNoti!!.setOnClickListener { v: View? ->
            showCustomNotification(activity!!,
                    "This is title",
                    "This is message",
                    getIcon(activity!!, R.drawable.dummy_image))
        }
        btnProgNoti!!.setOnClickListener { v: View? ->
            showProgressNotification(activity!!,
                    "File Download",
                    "Downloading in progress")
        }
    }

    private val messagesList: ArrayList<String?>
        private get() {
            val list = ArrayList<String?>()
            list.add("Hey there?!")
            list.add("Listen to me")
            list.add("Are you free tonight")
            list.add("Let's go for ride")
            list.add("See you soon")
            return list
        }
}
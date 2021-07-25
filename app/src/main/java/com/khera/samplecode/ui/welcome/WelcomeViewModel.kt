package com.khera.samplecode.ui.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {
    private var mTextHome: MutableLiveData<String?>? = null
    private var mTextWelcome: MutableLiveData<String?>? = null
    private val unicode = 0x1F60A
    private fun setVariables() {
        mTextHome = MutableLiveData()
        mTextWelcome = MutableLiveData()
    }

    private fun setValues() {
        mTextHome!!.value = "Swipe the menu and start exploring " + getEmojiByUnicode(unicode)
        mTextWelcome!!.value = "Welcome user ! "
    }

    val textHome: LiveData<String?>?
        get() = mTextHome
    val textWelcome: LiveData<String?>?
        get() = mTextWelcome

    fun getEmojiByUnicode(unicode: Int): String {
        return String(Character.toChars(unicode))
    }

    init {
        setVariables()
        setValues()
    }
}
package com.khera.samplecode.widgets

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import com.google.android.material.textview.MaterialTextView

class RegularTextView : MaterialTextView {
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context!!, attrs, defStyle) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init()
    }

    constructor(context: Context?) : super(context!!) {
        init()
    }

    private fun init() {
        val tf = Typeface.createFromAsset(context.assets,
                "Montserrat-Regular.ttf")
        typeface = tf
    }
}
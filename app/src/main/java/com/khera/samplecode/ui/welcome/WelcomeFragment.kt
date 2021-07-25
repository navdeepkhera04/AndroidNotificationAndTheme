package com.khera.samplecode.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.khera.samplecode.R

class WelcomeFragment : Fragment() {
    private var welcomeViewModel: WelcomeViewModel? = null
    private var txtHome: TextView? = null
    private var txtWelcome: TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        welcomeViewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_welcome, container, false)
        findViewById(root)
        setTextObservers()
        return root
    }

    private fun findViewById(root: View) {
        txtHome = root.findViewById(R.id.txtHome)
        txtWelcome = root.findViewById(R.id.txtWelcome)
    }

    private fun setTextObservers() {
        welcomeViewModel!!.textWelcome!!.observe(viewLifecycleOwner, Observer { s -> txtWelcome!!.text = s })
        welcomeViewModel!!.textHome!!.observe(viewLifecycleOwner, Observer { s -> txtHome!!.text = s })
    }
}
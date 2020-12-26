package com.surjuas.kotui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val uiTree: UiTree<*> by UiTree.Delegate(this) {
        object : UiLabel<TextView> {
            override val properties = duplicateAwareProperties(
                UiLabel.Text.Value("Hello, World")
            )

            override fun createView(context: Context) = TextView(context)
        }
    }
}
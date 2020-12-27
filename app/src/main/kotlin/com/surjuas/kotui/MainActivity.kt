package com.surjuas.kotui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val uiTree: UiTree<*> by UiTree.Delegate(this) {
        object : UiLabel<TextView> {
            override val attributes = duplicateAwareAttributes(
                UiLabel.Text.Resource(R.string.app_name)
            )

            override fun createView(context: Context) = TextView(context)
        }
    }
}
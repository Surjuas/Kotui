package com.surjuas.kotui

import android.content.Context
import android.view.View
import androidx.annotation.CallSuper

interface BasicUiEntity<V : View> : UiEntity<V> {

    fun createView(context: Context): V

    @CallSuper
    override fun build(context: Context): V {
        val view = createView(context)
        properties.forEach { it.setValue(view) }
        return view
    }

    interface Property<V : View, T> : UiEntity.Property<V, T>
}
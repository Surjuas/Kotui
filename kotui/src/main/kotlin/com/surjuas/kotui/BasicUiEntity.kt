package com.surjuas.kotui

import android.content.Context
import android.view.View
import androidx.annotation.CallSuper

interface BasicUiEntity<V : View> : UiEntity<V> {

    fun createView(context: Context): V

    @CallSuper
    override suspend fun build(context: Context): V {
        val view = createView(context)
        attributes.forEach { it.setValue(view) }
        return view
    }

    interface Attribute<V : View, T> : UiEntity.Attribute<V, T>
}
package com.surjuas.kotui

import android.content.Context

interface UiEntity<T> {

    val properties: Set<Property<T, *>>
        get() = emptySet()

    fun build(context: Context): T

    interface Property<T, R> {
        val value: R
        fun setValue(uiEntity: T)
    }
}
package com.surjuas.kotui

import android.content.Context

interface UiEntity<T> {

    val attributes: Set<Attribute<T, *>>
        get() = emptySet()

    suspend fun build(context: Context): T

    interface Attribute<T, R> {
        val value: R
        suspend fun setValue(uiEntity: T)
    }
}
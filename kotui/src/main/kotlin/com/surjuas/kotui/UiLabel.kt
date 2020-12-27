package com.surjuas.kotui

import android.widget.TextView
import androidx.annotation.StringRes

interface UiLabel<V : TextView> : BasicUiEntity<V> {

    interface Attribute<V : TextView, T> : BasicUiEntity.Attribute<V, T>

    abstract class Text<V : TextView, T> : Attribute<V, T> {

        class Resource<V : TextView>(
            @StringRes override val value: Int
        ) : Text<V, Int>() {
            override suspend fun setValue(uiEntity: V) = uiEntity.setText(value)
        }

        class Value<V : TextView>(
            override val value: String
        ) : Text<V, String>() {
            override suspend fun setValue(uiEntity: V) = uiEntity.setText(value)
        }
    }
}
package com.surjuas.kotui

import android.widget.TextView
import androidx.annotation.StringRes

interface UiLabel<V : TextView> : BasicUiEntity<V> {

    interface Property<V : TextView, T> : BasicUiEntity.Property<V, T>

    abstract class Text<V : TextView, T> : Property<V, T> {

        class Resource<V : TextView>(
            @StringRes override val value: Int
        ) : Text<V, Int>() {
            override fun setValue(uiEntity: V) = uiEntity.setText(value)
        }

        class Value<V : TextView>(
            override val value: String
        ) : Text<V, String>() {
            override fun setValue(uiEntity: V) = uiEntity.setText(value)
        }
    }
}
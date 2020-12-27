package com.surjuas.kotui

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class UiTree<T : BasicUiEntity<*>>(private val uiEntity: T) {

    suspend fun grow(context: Context) = uiEntity.build(context)

    class Delegate(
        private val activity: ComponentActivity,
        val block: () -> BasicUiEntity<*>
    ) : ReadOnlyProperty<ComponentActivity, UiTree<*>> {

        private var uiTree: UiTree<*>? = null

        init {
            activity.lifecycleScope.launchWhenCreated {
                val uiTree = UiTree(block())
                this@Delegate.uiTree = uiTree
                activity.setContentView(uiTree.grow(activity))
            }
            activity.lifecycle.addObserver(OnDestroyObserver {
                // TODO dispose uiTree
                uiTree = null
            })
        }

        override fun getValue(thisRef: ComponentActivity, property: KProperty<*>): UiTree<*> {
            uiTree?.let { return it }

            val currentState = activity.lifecycle.currentState

            if (!currentState.isAtLeast(Lifecycle.State.INITIALIZED))
                throw IllegalUiTreeAccessException(currentState)

            throw IllegalUiTreeAccessException(currentState)
        }

        class IllegalUiTreeAccessException(
            currentState: Lifecycle.State
        ) : IllegalStateException(
            "Should not attempt to get uiTree when Activity is not created yet or destroyed. Current state: ${currentState.name}"
        )
    }
}
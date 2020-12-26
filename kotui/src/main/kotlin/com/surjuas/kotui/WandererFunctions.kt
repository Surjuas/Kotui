package com.surjuas.kotui

import android.view.View
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.reflect.full.superclasses

fun interface OnCreateObserver : DefaultLifecycleObserver {

    fun doOnCreate()

    override fun onCreate(owner: LifecycleOwner) = doOnCreate()
}

fun interface OnDestroyObserver : DefaultLifecycleObserver {

    fun doOnDestroy()

    override fun onDestroy(owner: LifecycleOwner) = doOnDestroy()
}

fun <T : View> duplicateAwareProperties(
    vararg properties: BasicUiEntity.Property<T, *>
): Set<BasicUiEntity.Property<T, *>> {
    properties
        .groupingBy { property ->
            property::class.superclasses.firstOrNull { it.isAbstract }
            // TODO property must have one sealed class as superclass exception
        }
        .eachCount()
        .none { (_, count) -> count > 1 }
        .takeIf { it }
        ?: throw IllegalStateException()
    // TODO only one derivation of abstract class must exist inside property
    return properties.toSet()
}
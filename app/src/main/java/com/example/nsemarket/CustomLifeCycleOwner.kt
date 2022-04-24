package com.example.nsemarket

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

class CustomLifeCycleOwner : LifecycleOwner {
    private val mLifecycleRegistry: LifecycleRegistry
    fun stopListening() {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }

    fun startListening() {
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    override fun getLifecycle(): Lifecycle {
        Log.d(TAG, "getLifecycle: ")
        return mLifecycleRegistry
    }

    companion object {
        private const val TAG = "CustomLifeCycleOwner"
    }

    init {
        Log.d(TAG, "CustomLifeCycleOwner: ")
        mLifecycleRegistry = LifecycleRegistry(this)
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }
}
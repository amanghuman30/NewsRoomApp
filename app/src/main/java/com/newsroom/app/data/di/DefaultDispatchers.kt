package com.newsroom.app.data.di

import kotlinx.coroutines.Dispatchers

interface DefaultDispatchers {
    fun dispatcherIO() = Dispatchers.IO
    fun dispatcherMain() = Dispatchers.Main
    fun dispatcherDefault() = Dispatchers.Default
    fun dispatcherUnconfined() = Dispatchers.Unconfined
}
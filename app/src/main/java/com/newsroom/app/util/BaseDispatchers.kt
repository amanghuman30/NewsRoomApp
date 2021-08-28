package com.newsroom.app.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface BaseDispatchers {
    fun main() : CoroutineDispatcher
    fun io() : CoroutineDispatcher
    fun default() : CoroutineDispatcher
    fun unconfined() : CoroutineDispatcher
}
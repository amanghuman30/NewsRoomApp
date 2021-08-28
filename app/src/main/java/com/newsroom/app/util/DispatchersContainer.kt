package com.newsroom.app.util

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DispatchersContainer @Inject constructor() : BaseDispatchers{


    override fun main() = Dispatchers.Main

    override fun io() = Dispatchers.IO

    override fun default() = Dispatchers.Default

    override fun unconfined() = Dispatchers.Unconfined
}
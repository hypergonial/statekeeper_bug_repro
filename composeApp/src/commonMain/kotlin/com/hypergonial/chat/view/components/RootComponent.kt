package com.hypergonial.chat.view.components

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import kotlinx.serialization.serializer


interface RootComponent : BackHandlerOwner {
    val data: Value<Model>
    fun input(token: String)

    data class Model(
        val token: String? = null,
    )
}

class DefaultRootComponent(
    val ctx: ComponentContext,
) : RootComponent, ComponentContext by ctx {

    override val data = MutableValue(
        RootComponent.Model(
            token = stateKeeper.consume(key = "TOKEN", strategy = serializer<String>())
        )
    )

    init {
        stateKeeper.register(key = "TOKEN", strategy = serializer<String>()) {
            // This always gets printed, and yet, on Android, the token is not persisted.
            // On Desktop (JVM) this is fine though.
            println("Looks like it is time to save the token, which is: ${data.value.token}")
            data.value.token
        }
    }

    override fun input(token: String) {
        data.value = data.value.copy(token = token)
    }
}



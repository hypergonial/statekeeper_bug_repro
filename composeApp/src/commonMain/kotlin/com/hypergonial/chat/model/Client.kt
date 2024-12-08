package com.hypergonial.chat.model

import com.arkivanov.essenty.instancekeeper.InstanceKeeper

class Client(var token: String? = null) : InstanceKeeper.Instance {
    suspend fun login(token: String) {
        this.token = token
    }
}

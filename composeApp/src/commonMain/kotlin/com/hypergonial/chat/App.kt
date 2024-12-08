package com.hypergonial.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.hypergonial.chat.view.components.RootComponent


@Composable
fun App(root: RootComponent) {
    val state by root.data.subscribeAsState()

    Scaffold { p ->
        Column(Modifier.padding(p).fillMaxWidth()) {
            Row {
                Text("Hello, world!")
            }
            OutlinedTextField(
                value = state.token ?: "",
                onValueChange = { root.input(it) },
                label = { Text("Enter token here") },
                placeholder = { Text("It should be persisted between application starts") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}

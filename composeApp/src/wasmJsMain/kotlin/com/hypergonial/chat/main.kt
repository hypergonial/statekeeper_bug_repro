package com.hypergonial.chat

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.ComposeViewport
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop
import com.arkivanov.essenty.statekeeper.StateKeeperDispatcher
import com.hypergonial.chat.view.components.DefaultRootComponent
import kotlinx.browser.document

private val lightColorScheme = lightColorScheme(
    primary = Color(0xFF476810),
    onPrimary = Color(0xFF476810),
    primaryContainer = Color(0xFFC7F089),
    onPrimaryContainer = Color(0xFFC7F089),
)
private val darkColorScheme = darkColorScheme(
    primary = Color(0xFFACD370),
    onPrimary = Color(0xFF213600),
    primaryContainer = Color(0xFF324F00),
    onPrimaryContainer = Color(0xFF324F00),
)

/** Change the app's lifecycle based on the document's visibility. */
private fun LifecycleRegistry.attachToDocument() {
    val onVisibilityChanged = { if (document.hasFocus()) resume() else stop() }

    onVisibilityChanged()

    document.addEventListener(type = "visibilitychange", callback = { onVisibilityChanged() })
}

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit
) {

    val colorScheme = when {
        useDarkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme, content = content
    )

}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalDecomposeApi::class)
fun main() {
    val lifecycle = LifecycleRegistry()
    val stateKeeper = StateKeeperDispatcher()

    val root = DefaultRootComponent(
        ctx = DefaultComponentContext(lifecycle, stateKeeper),
    )

    lifecycle.attachToDocument()

    ComposeViewport(document.body!!) {
        AppTheme {
            App(root)
        }
    }
}

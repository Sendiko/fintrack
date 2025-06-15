package id.my.sendiko.fintrack

import androidx.compose.ui.window.ComposeUIViewController
import id.my.sendiko.fintrack.core.App
import id.my.sendiko.fintrack.core.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }
package com.github.sendiko.fintrack

import androidx.compose.ui.window.ComposeUIViewController
import com.github.sendiko.fintrack.core.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }
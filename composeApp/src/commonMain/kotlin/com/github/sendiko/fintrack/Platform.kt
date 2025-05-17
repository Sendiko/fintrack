package com.github.sendiko.fintrack

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
package com.github.sendiko.fintrack.splash.domain

import kotlinx.coroutines.flow.Flow

interface SplashRepository {
    fun getToken(): Flow<String>
}
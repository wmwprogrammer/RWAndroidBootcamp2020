package com.wmw.movieviewer.di

import com.wmw.movieviewer.di.dbModule
import com.wmw.movieviewer.di.networkModule
import com.wmw.movieviewer.di.repositoryModule
import com.wmw.movieviewer.di.viewModelModule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class ModuleTests : KoinTest {
    @Test
    fun `Test Koin`() {
        startKoin {
            modules(listOf(networkModule, viewModelModule, repositoryModule, dbModule))
        }.checkModules()

        stopKoin()
    }
}
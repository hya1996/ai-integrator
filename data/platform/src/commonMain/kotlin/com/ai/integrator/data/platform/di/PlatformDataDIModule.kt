package com.ai.integrator.data.platform.di

import com.ai.integrator.data.platform.datasource.PlatformModelLocalDataSource
import com.ai.integrator.data.platform.repository.PlatformModelRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val platformDataDIModule = module {
    singleOf(::PlatformModelLocalDataSource)
    singleOf(::PlatformModelRepository)
}
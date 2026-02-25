package com.ai.integrator.data.model.di

import com.ai.integrator.data.model.database.ModelDatabase
import com.ai.integrator.data.model.database.dao.ModelDao
import com.ai.integrator.data.model.database.getModelDatabase
import com.ai.integrator.data.model.repository.ModelRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal expect val platformDIModule: Module

val modelDataDIModule = module {
    includes(platformDIModule)

    single<ModelDatabase> { getModelDatabase(get(named("model_db_builder"))) }
    single<ModelDao> { get<ModelDatabase>().modelDao() }
    singleOf(::ModelRepository)
}
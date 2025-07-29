package com.ai.integrator.core.datastore

import com.ai.integrator.core.datastore.di.dataStoreDIModule
import com.ai.integrator.core.framework.application.ApplicationModule
import org.koin.core.context.loadKoinModules

class DataStoreModule : ApplicationModule() {
    override fun onInit() {
        loadKoinModules(dataStoreDIModule)
    }
}
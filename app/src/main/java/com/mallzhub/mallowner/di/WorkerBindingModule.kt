package com.mallzhub.mallowner.di

import com.mallzhub.mallowner.worker.DaggerWorkerFactory
import com.mallzhub.mallowner.worker.TokenRefreshWorker
import com.mallzhub.mallowner.worker.WorkerKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class WorkerBindingModule {

    /*injector for DaggerWorkerFactory*/
    @Binds
    @IntoMap
    @WorkerKey(TokenRefreshWorker::class)
    abstract fun bindTokenRefreshWorker(factory: TokenRefreshWorker.Factory):
            DaggerWorkerFactory.ChildWorkerFactory


}

package com.mallzhub.mallowner.di

import com.mallzhub.mallowner.ui.LoginActivity
import com.mallzhub.mallowner.ui.MainActivity
import com.mallzhub.mallowner.ui.SplashActivity
import com.mallzhub.mallowner.ui.barcode_reader.LiveBarcodeScanningActivity
import com.mallzhub.mallowner.ui.live_chat.LiveChatActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeLiveChatActivity(): LiveChatActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeLiveBarcodeScanningActivity(): LiveBarcodeScanningActivity
}
package com.rtchubs.talika.di

import com.rtchubs.talika.ui.LoginActivity
import com.rtchubs.talika.ui.MainActivity
import com.rtchubs.talika.ui.SplashActivity
import com.rtchubs.talika.ui.barcode_reader.LiveBarcodeScanningActivity
import com.rtchubs.talika.ui.live_chat.LiveChatActivity
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
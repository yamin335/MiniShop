package com.rtchubs.talika.di

import com.rtchubs.talika.ar_location.ARLocationFragment
import com.rtchubs.talika.nid_scan.NIDScanCameraXFragment
import com.rtchubs.talika.ui.add_payment_methods.AddBankFragment
import com.rtchubs.talika.ui.add_payment_methods.AddCardFragment
import com.rtchubs.talika.ui.add_payment_methods.AddPaymentMethodsFragment
import com.rtchubs.talika.ui.add_product.AddProductFragment
import com.rtchubs.talika.ui.cart.CartFragment
import com.rtchubs.talika.ui.chapter_list.ChapterListFragment
import com.rtchubs.talika.ui.exams.ExamsFragment
import com.rtchubs.talika.ui.gift_point.GiftPointHistoryDetailsFragment
import com.rtchubs.talika.ui.gift_point.GiftPointHistoryFragment
import com.rtchubs.talika.ui.home.*
import com.rtchubs.talika.ui.how_works.HowWorksFragment
import com.rtchubs.talika.ui.info.InfoFragment
import com.rtchubs.talika.ui.live_chat.BotFragment
import com.rtchubs.talika.ui.login.SignInFragment
import com.rtchubs.talika.ui.mall_iot.MallIOTFragmentFragment
import com.rtchubs.talika.ui.mall_iot.MyDevicesFragment
import com.rtchubs.talika.ui.terms_and_conditions.TermsAndConditionsFragment
import com.rtchubs.talika.ui.tou.TouFragment
import com.rtchubs.talika.ui.pre_on_boarding.PreOnBoardingFragment
import com.rtchubs.talika.ui.profiles.ProfilesFragment
import com.rtchubs.talika.ui.registration.RegistrationFragment
import com.rtchubs.talika.ui.settings.SettingsFragment
import com.rtchubs.talika.ui.setup.SetupFragment
import com.rtchubs.talika.ui.setup_complete.SetupCompleteFragment
import com.rtchubs.talika.ui.splash.SplashFragment
import com.rtchubs.talika.ui.video_play.LoadWebViewFragment
import com.rtchubs.talika.ui.video_play.VideoPlayFragment
import com.rtchubs.talika.ui.more.MoreFragment
import com.rtchubs.talika.ui.offer.OfferFragment
import com.rtchubs.talika.ui.order.OrderAsGuestDialogFragment
import com.rtchubs.talika.ui.order.OrderListFragment
import com.rtchubs.talika.ui.order.OrderTrackHistoryFragment
import com.rtchubs.talika.ui.otp_signin.OtpSignInFragment
import com.rtchubs.talika.ui.pin_number.PinNumberFragment
import com.rtchubs.talika.ui.shops.ShopDetailFragment
import com.rtchubs.talika.ui.shops.ShopDetailsContactUsFragment
import com.rtchubs.talika.ui.shops.ShopDetailsFragment
import com.rtchubs.talika.ui.shops.ShopDetailsProductListFragment
import com.rtchubs.talika.ui.topup.TopUpAmountFragment
import com.rtchubs.talika.ui.topup.TopUpBankCardFragment
import com.rtchubs.talika.ui.topup.TopUpMobileFragment
import com.rtchubs.talika.ui.topup.TopUpPinFragment
import com.rtchubs.talika.ui.transactions.TransactionDetailsFragment
import com.rtchubs.talika.ui.transactions.TransactionsFragment
import com.rtchubs.talika.ui.wallet.WalletFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeTermsAndConditionsFragment(): TermsAndConditionsFragment

    @ContributesAndroidInjector
    abstract fun contributeMoreBookListFragment(): MoreBookListFragment

    @ContributesAndroidInjector
    abstract fun contributeMoreShoppingMallFragment(): MoreShoppingMallFragment

    @ContributesAndroidInjector
    abstract fun contributeAllShopListFragment(): AllShopListFragment

    @ContributesAndroidInjector
    abstract fun contributeShopDetailsFragment(): ShopDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeShopDetailsProductListFragment(): ShopDetailsProductListFragment

    @ContributesAndroidInjector
    abstract fun contributeShopDetailsContactUsFragment(): ShopDetailsContactUsFragment

    @ContributesAndroidInjector
    abstract fun contributeProductListFragment(): ProductListFragment

    @ContributesAndroidInjector
    abstract fun contributeProductDetailsFragment(): ProductDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeCartFragment(): CartFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteFragment(): FavoriteFragment

    @ContributesAndroidInjector
    abstract fun contributeSignInFragment(): SignInFragment

    @ContributesAndroidInjector
    abstract fun contributeExamsFragment(): ExamsFragment

    @ContributesAndroidInjector
    abstract fun contributeInfoFragment(): InfoFragment

    @ContributesAndroidInjector
    abstract fun contributeNIDScanCameraXFragment(): NIDScanCameraXFragment

    @ContributesAndroidInjector
    abstract fun contributePreOnBoardingFragment(): PreOnBoardingFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeMoreFragment(): MoreFragment

    @ContributesAndroidInjector
    abstract fun contributeSetAFragment(): SetAFragment

    @ContributesAndroidInjector
    abstract fun contributeSetBFragment(): SetBFragment

    @ContributesAndroidInjector
    abstract fun contributeSetCFragment(): SetCFragment

    @ContributesAndroidInjector
    abstract fun contributeHome2Fragment(): Home2Fragment

    @ContributesAndroidInjector
    abstract fun contributeAddPaymentMethodsFragment(): AddPaymentMethodsFragment


    @ContributesAndroidInjector
    abstract fun contributeHowWorksFragment(): HowWorksFragment

    @ContributesAndroidInjector
    abstract fun contributeRegistrationFragment(): RegistrationFragment

    @ContributesAndroidInjector
    abstract fun contributeTouFragment(): TouFragment

    @ContributesAndroidInjector
    abstract fun contributeSetupFragment(): SetupFragment

    @ContributesAndroidInjector
    abstract fun contributeSetupCompleteFragment(): SetupCompleteFragment

    @ContributesAndroidInjector
    abstract fun contributeProfilesFragment(): ProfilesFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector
    abstract fun contributeChapterListFragment(): ChapterListFragment

    @ContributesAndroidInjector
    abstract fun contributeVideoPlayFragment(): VideoPlayFragment

    @ContributesAndroidInjector
    abstract fun contributeLoadWebViewFragment(): LoadWebViewFragment

    @ContributesAndroidInjector
    abstract fun contributeOtpSignInFragment(): OtpSignInFragment

    @ContributesAndroidInjector
    abstract fun contributePinNumberFragment(): PinNumberFragment

    @ContributesAndroidInjector
    abstract fun contributeAddBankFragment(): AddBankFragment

    @ContributesAndroidInjector
    abstract fun contributeAddCardFragment(): AddCardFragment

    @ContributesAndroidInjector
    abstract fun contributeTopUpMobileFragment(): TopUpMobileFragment

    @ContributesAndroidInjector
    abstract fun contributeTopUpAmountFragment(): TopUpAmountFragment

    @ContributesAndroidInjector
    abstract fun contributeTopUpPinFragment(): TopUpPinFragment

    @ContributesAndroidInjector
    abstract fun contributeTopUpBankCardFragment(): TopUpBankCardFragment

    @ContributesAndroidInjector
    abstract fun contributeARLocationFragment(): ARLocationFragment

    @ContributesAndroidInjector
    abstract fun contributeAddProductFragment(): AddProductFragment

    @ContributesAndroidInjector
    abstract fun contributeOfferFragment(): OfferFragment

    @ContributesAndroidInjector
    abstract fun contributeBotFragment(): BotFragment

    @ContributesAndroidInjector
    abstract fun contributeOrderListFragment(): OrderListFragment

    @ContributesAndroidInjector
    abstract fun contributeOrderTrackHistoryFragment(): OrderTrackHistoryFragment

    @ContributesAndroidInjector
    abstract fun contributeOrderAsGuestDialogFragment(): OrderAsGuestDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeTransactionsFragment(): TransactionsFragment

    @ContributesAndroidInjector
    abstract fun contributeTransactionDetailsFragment(): TransactionDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeWalletFragment(): WalletFragment

    @ContributesAndroidInjector
    abstract fun contributeGiftPointHistoryFragment(): GiftPointHistoryFragment

    @ContributesAndroidInjector
    abstract fun contributeGiftPointHistoryDetailsFragment(): GiftPointHistoryDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeShopEditFragment(): ShopEditFragment

    @ContributesAndroidInjector
    abstract fun contributeShopDetailFragment(): ShopDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeMallIOTFragmentFragment(): MallIOTFragmentFragment

    @ContributesAndroidInjector
    abstract fun contributeMyDevicesFragment(): MyDevicesFragment
}
package com.mallzhub.mallowner.di

import com.mallzhub.mallowner.ar_location.ARLocationFragment
import com.mallzhub.mallowner.nid_scan.NIDScanCameraXFragment
import com.mallzhub.mallowner.ui.add_payment_methods.AddBankFragment
import com.mallzhub.mallowner.ui.add_payment_methods.AddCardFragment
import com.mallzhub.mallowner.ui.add_payment_methods.AddPaymentMethodsFragment
import com.mallzhub.mallowner.ui.add_product.AddProductFragment
import com.mallzhub.mallowner.ui.cart.CartFragment
import com.mallzhub.mallowner.ui.chapter_list.ChapterListFragment
import com.mallzhub.mallowner.ui.exams.ExamsFragment
import com.mallzhub.mallowner.ui.gift_point.GiftPointHistoryDetailsFragment
import com.mallzhub.mallowner.ui.gift_point.GiftPointHistoryFragment
import com.mallzhub.mallowner.ui.home.*
import com.mallzhub.mallowner.ui.how_works.HowWorksFragment
import com.mallzhub.mallowner.ui.info.InfoFragment
import com.mallzhub.mallowner.ui.live_chat.BotFragment
import com.mallzhub.mallowner.ui.login.SignInFragment
import com.mallzhub.mallowner.ui.terms_and_conditions.TermsAndConditionsFragment
import com.mallzhub.mallowner.ui.tou.TouFragment
import com.mallzhub.mallowner.ui.pre_on_boarding.PreOnBoardingFragment
import com.mallzhub.mallowner.ui.profiles.ProfilesFragment
import com.mallzhub.mallowner.ui.registration.RegistrationFragment
import com.mallzhub.mallowner.ui.settings.SettingsFragment
import com.mallzhub.mallowner.ui.setup.SetupFragment
import com.mallzhub.mallowner.ui.setup_complete.SetupCompleteFragment
import com.mallzhub.mallowner.ui.splash.SplashFragment
import com.mallzhub.mallowner.ui.video_play.LoadWebViewFragment
import com.mallzhub.mallowner.ui.video_play.VideoPlayFragment
import com.mallzhub.mallowner.ui.more.MoreFragment
import com.mallzhub.mallowner.ui.offer.OfferFragment
import com.mallzhub.mallowner.ui.order.OrderAsGuestDialogFragment
import com.mallzhub.mallowner.ui.order.OrderListFragment
import com.mallzhub.mallowner.ui.order.OrderTrackHistoryFragment
import com.mallzhub.mallowner.ui.otp_signin.OtpSignInFragment
import com.mallzhub.mallowner.ui.pin_number.PinNumberFragment
import com.mallzhub.mallowner.ui.shops.ShopDetailFragment
import com.mallzhub.mallowner.ui.shops.ShopDetailsContactUsFragment
import com.mallzhub.mallowner.ui.shops.ShopDetailsFragment
import com.mallzhub.mallowner.ui.shops.ShopDetailsProductListFragment
import com.mallzhub.mallowner.ui.topup.TopUpAmountFragment
import com.mallzhub.mallowner.ui.topup.TopUpBankCardFragment
import com.mallzhub.mallowner.ui.topup.TopUpMobileFragment
import com.mallzhub.mallowner.ui.topup.TopUpPinFragment
import com.mallzhub.mallowner.ui.transactions.TransactionDetailsFragment
import com.mallzhub.mallowner.ui.transactions.TransactionsFragment
import com.mallzhub.mallowner.ui.wallet.WalletFragment
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
}
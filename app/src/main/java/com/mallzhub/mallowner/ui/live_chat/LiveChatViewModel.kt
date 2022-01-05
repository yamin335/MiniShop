package com.mallzhub.mallowner.ui.live_chat

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.mallzhub.mallowner.local_db.dao.CartDao
import com.mallzhub.mallowner.models.LiveChatMessage
import com.mallzhub.mallowner.ui.common.BaseViewModel
import javax.inject.Inject

class LiveChatViewModel @Inject constructor(
    private val application: Application,
    private val cartDao: CartDao
    ) : BaseViewModel(application) {

    val newMessage: MutableLiveData<String> = MutableLiveData()

    val chatMessages: MutableLiveData<MutableList<LiveChatMessage>> by lazy {
        MutableLiveData<MutableList<LiveChatMessage>>()
    }

}
package com.vpd.vpdmobile.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

typealias MutableLiveEvent<T> = MutableLiveData<Event<T>>
typealias LiveEvent<T> = LiveData<Event<T>>

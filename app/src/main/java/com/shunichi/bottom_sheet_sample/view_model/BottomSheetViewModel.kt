package com.shunichi.bottom_sheet_sample.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BottomSheetViewModel: ViewModel() {

    private val _title: MutableLiveData<String> = MutableLiveData("")
    val title get() = _title

    fun updateTitle(title: String) {
        _title.value = title
    }
}
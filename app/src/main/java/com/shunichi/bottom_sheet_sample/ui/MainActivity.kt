package com.shunichi.bottom_sheet_sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.shunichi.bottom_sheet_sample.R
import com.shunichi.bottom_sheet_sample.databinding.ActivityMainBinding
import com.shunichi.bottom_sheet_sample.ui.bottom_sheet.ModalBottomSheet

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.button.setOnClickListener {
            val bottomSheet = ModalBottomSheet()
            bottomSheet.show(supportFragmentManager, ModalBottomSheet.MODAL_BOTTOM_SHEET)
        }
    }
}
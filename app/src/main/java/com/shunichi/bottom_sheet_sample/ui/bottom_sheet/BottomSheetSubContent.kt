package com.shunichi.bottom_sheet_sample.ui.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.shunichi.bottom_sheet_sample.databinding.FragmentBottomSheetSubContentBinding
import com.shunichi.bottom_sheet_sample.view_model.BottomSheetViewModel

class BottomSheetSubContent : Fragment() {

    private lateinit var binding: FragmentBottomSheetSubContentBinding

    private val viewModel: BottomSheetViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetSubContentBinding.inflate(inflater, container, false)
        viewModel.updateTitle("Sub")
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
package com.shunichi.bottom_sheet_sample.ui.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.shunichi.bottom_sheet_sample.databinding.FragmentBottomSheetMainContentBinding
import com.shunichi.bottom_sheet_sample.view_model.BottomSheetViewModel

class BottomSheetMainContent : Fragment() {

    private lateinit var binding: FragmentBottomSheetMainContentBinding

    private val viewModel: BottomSheetViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetMainContentBinding.inflate(inflater, container, false)
        viewModel.updateTitle("Main")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomSheetButton.setOnClickListener {
            val nav = BottomSheetMainContentDirections.navigateToSubBottomSheet()
            findNavController().navigate(nav)
        }
    }
}
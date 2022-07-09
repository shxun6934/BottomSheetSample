package com.shunichi.bottom_sheet_sample.ui.bottom_sheet

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shunichi.bottom_sheet_sample.R
import com.shunichi.bottom_sheet_sample.databinding.FragmentBottomSheetBinding
import com.shunichi.bottom_sheet_sample.view_model.BottomSheetViewModel
import kotlin.math.roundToInt

class ModalBottomSheet : BottomSheetDialogFragment() {

    companion object {
        const val MODAL_BOTTOM_SHEET = "MODAL_BOTTOM_SHEET"
    }

    private lateinit var binding: FragmentBottomSheetBinding

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    private lateinit var bottomSheetCallback: BottomSheetBehavior.BottomSheetCallback

    private val viewModel: BottomSheetViewModel by activityViewModels()

    override fun getTheme(): Int = R.style.BottomSheetTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = requireContext()

        return super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener {
                val bottomSheet =
                    (it as? BottomSheetDialog)?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
                        ?: return@setOnShowListener
                bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

                bottomSheet.layoutParams.height = FrameLayout.LayoutParams.MATCH_PARENT

                val bottomSheetHeight = Resources.getSystem().displayMetrics.heightPixels -
                    (50 * Resources.getSystem().displayMetrics.density).roundToInt()
                bottomSheetBehavior.peekHeight = bottomSheetHeight

                bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        when (newState) {
                            BottomSheetBehavior.STATE_EXPANDED -> {
                                binding.appBar.background = AppCompatResources.getDrawable(
                                    context,
                                    R.drawable.not_rounded_dialog_primary
                                )
                            }
                            else -> {
                                binding.appBar.background = AppCompatResources.getDrawable(
                                    context,
                                    R.drawable.rounded_dialog_primary
                                )
                            }
                        }
                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {}
                }
                bottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        val activity = (requireActivity() as AppCompatActivity)
        activity.setSupportActionBar(binding.toolBar)
        activity.supportActionBar?.apply { setDisplayHomeAsUpEnabled(true) }
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.title.observe(viewLifecycleOwner) {
            binding.toolBar.title = it
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bottomSheetBehavior.removeBottomSheetCallback(bottomSheetCallback)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.close, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (currentFragment() is BottomSheetMainContent && bottomSheetBehavior.state != BottomSheetBehavior.STATE_HIDDEN) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                }
            }
            R.id.close -> {
                if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_HIDDEN) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun currentFragment(): Fragment {
        val navHostFragment = childFragmentManager.fragments.first()
        return navHostFragment.childFragmentManager.fragments.first()
    }
}
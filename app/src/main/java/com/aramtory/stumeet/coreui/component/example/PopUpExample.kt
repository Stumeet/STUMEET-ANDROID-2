package com.aramtory.stumeet.coreui.component.example

import android.widget.Toast
import com.aramtory.stumeet.R
import com.aramtory.stumeet.coreui.component.popup.StumeetPopUp
import com.aramtory.stumeet.databinding.ExampleFragmentBinding

class PopUpExample :
    com.aramtory.stumeet.coreui.base.BindingFragment<ExampleFragmentBinding>(R.layout.example_fragment) {

    override fun initView() {
        showPopup()
    }

    private fun showPopup(){
        val popup = StumeetPopUp(
            questionResId = R.string.test_popup_question,
            yesClicked = {
                Toast.makeText(requireContext(), R.string.component_popup_yes, Toast.LENGTH_SHORT).show()
            }
        )
        popup.show(parentFragmentManager, "StumeetPopUp")
    }
}
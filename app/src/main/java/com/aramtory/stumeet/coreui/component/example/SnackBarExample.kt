package com.aramtory.stumeet.coreui.component.example

import android.content.Intent
import android.widget.Toast
import com.aramtory.stumeet.R
import com.aramtory.stumeet.coreui.component.popup.StumeetPopUp
import com.aramtory.stumeet.coreui.component.snackbar.StumeetSnackBar
import com.aramtory.stumeet.databinding.ExampleFragmentBinding

class SnackBarExample :
    com.aramtory.stumeet.coreui.base.BindingFragment<ExampleFragmentBinding>(R.layout.example_fragment) {

    override fun initView() {
        showSnackBar()
    }

    private fun showSnackBar() {
        val snackBar = StumeetSnackBar(
            cautionResId = R.string.component_snackbar_test,
            marginBottom = 111,
            actionTextResId = R.string.component_snackbar_go_to_setting,
            action = {
                Intent()
            }
        )
        snackBar.show(parentFragmentManager, getString(R.string.component_snackbar_test_tag))
    }
}
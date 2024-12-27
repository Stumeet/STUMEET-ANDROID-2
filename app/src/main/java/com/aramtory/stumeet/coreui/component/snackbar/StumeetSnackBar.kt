package com.aramtory.stumeet.coreui.component.snackbar

import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import com.aramtory.stumeet.R
import com.aramtory.stumeet.coreui.fragment.dpToPx
import com.aramtory.stumeet.databinding.ComponentSnackbarBinding

class StumeetSnackBar(
    private val cautionResId: Int,
    private val marginBottom: Int = 10,
    private val actionTextResId: Int? = null,
    private val action: (() -> Unit)? = null,
) : com.aramtory.stumeet.coreui.base.BindingDialogFragment<ComponentSnackbarBinding>(R.layout.component_snackbar) {

    init {
        setStyle(STYLE_NO_FRAME, R.style.StumeetSnackBarTheme)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            // 하단에 나타나도록 설정
            val params = attributes
            params.gravity = Gravity.BOTTOM
            attributes = params

            // 마진 설정
            decorView.setPadding(dpToPx(24), 0, dpToPx(24), dpToPx(marginBottom))
        }
    }

    override fun initView() {
        with(binding) {
            // 메세지
            tvSnackbarMessage.text = getString(cautionResId)

            // 액션 설정
            if (actionTextResId != null && action != null) {
                tvSnackbarGoTo.visibility = View.VISIBLE
                tvSnackbarGoTo.text = getString(actionTextResId)
                tvSnackbarGoTo.setOnClickListener {
                    action.invoke()
                    dismiss()
                }
            } else {
                tvSnackbarGoTo.visibility = View.GONE
            }
        }

        // 종료
        Handler(Looper.getMainLooper()).postDelayed({
            dismiss()
        }, 2000)
    }

}
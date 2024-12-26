package com.aramtory.stumeet.coreui.component.popup

import android.view.View
import androidx.core.text.HtmlCompat
import com.aramtory.stumeet.R
import com.aramtory.stumeet.databinding.ComponentBasicPopupBinding

class StumeetPopUp(
    private val questionResId: Int,
    private val cautionResId: Int? = null,
    private val yesClicked: () -> Unit,
    private var noClicked: (() -> Unit)? = null,
) : com.aramtory.stumeet.coreui.base.BindingDialogFragment<ComponentBasicPopupBinding>(
    R.layout.component_basic_popup
) {
    init {
        setStyle(STYLE_NORMAL, R.style.StumeetDialogTheme)
    }

    override fun initView() {
        with(binding) {
            // <b> </b> 태그 이해를 위한 설정
            tvPopupQuestion.text = HtmlCompat.fromHtml(
                getString(questionResId),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )

            // 주의사항 텍스트 있는 경우 추가
            if (cautionResId != null) {
                tvPopupCaution.visibility = View.VISIBLE
                tvPopupCaution.text = getString(cautionResId)
            }

            // 긍정 응답 클릭 시
            tvPopupYes.setOnClickListener {
                yesClicked()
                dismiss()
            }

            // 부정 응답 클릭 시
            tvPopupNo.setOnClickListener {
                noClicked?.let { it1 -> it1() }
                dismiss()
            }
        }
    }

}
package com.valencio.on_boarding_guider

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.airbnb.lottie.utils.Logger
import com.valencio.highlighterview.CircleInRectangleView
import kotlinx.android.synthetic.main.controls_layout.*
import kotlinx.android.synthetic.main.controls_layout.view.*
import kotlinx.android.synthetic.main.walkthough_guide_layout.view.*
import kotlin.math.roundToInt

class OnBoardingDialogueBox @JvmOverloads constructor(
    private val activity: Activity,
    private val anchorViews: List<AnchorView>
) : AlertDialog(activity, R.style.Theme_AppCompat_DayNight_NoActionBar) {

    private lateinit var dialogRootView: View

    private var currentViewIndex = 0
    var openIntentCallback: OpenNextIntent? = null

    @ColorRes
    private var aroundColor: Int = R.color.transparentColor  //#cb000000

    @ColorRes
    private var highlighterColor: Int = android.R.color.white

    @ColorRes
    private var contentTintColor: Int = android.R.color.white

    @ColorRes
    private var stepsPageIndicatorTextColor: Int = android.R.color.darker_gray

    fun setAroundColor(@ColorRes aroundColor: Int): OnBoardingDialogueBox {
        this.aroundColor = aroundColor
        return this
    }

    fun setHighLighterColor(@ColorRes highlighterColor: Int): OnBoardingDialogueBox {
        this.highlighterColor = highlighterColor
        return this
    }

    fun setContentTintColor(@ColorRes contentTintColor: Int): OnBoardingDialogueBox {
        this.contentTintColor = contentTintColor
        return this
    }

    fun setStepsPageIndicatorTextColor(@ColorRes stepsPageIndicatorTextColor: Int): OnBoardingDialogueBox {
        this.stepsPageIndicatorTextColor = stepsPageIndicatorTextColor
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogRootView = layoutInflater.inflate(R.layout.walkthough_guide_layout, null)
        setContentView(dialogRootView)

        openIntentCallback = (activity as OpenNextIntent)

        window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        anchorViews.firstOrNull()?.anchorView?.highlightAView()
    }

    private fun View.highlightAView() {
        val circleRectView = CircleInRectangleView(
            activity, this, aroundColor, highlighterColor
        )

        dialogRootView.highLighterLay.addView(
            circleRectView,
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        )

        circleRectView.animateEnlarge()

        circleRectView.post {
            addControls(circleRectView)
            setUpOnClickListeners()
        }
    }

    private fun addControls(circleRectView: CircleInRectangleView) {
        val safeControlsLay = getSafeControlRegion(circleRectView)

        val controlsLay = ControlsLay(activity).apply {
            id = View.generateViewId()
            setDescriptionText(anchorViews[currentViewIndex].description)
            setPageText("${currentViewIndex + 1} of ${anchorViews.size}")
            setCloseListener { this@OnBoardingDialogueBox.dismiss() }
            setContentColorTintMode(contentTintColor)
            stepsPageIndicatorTextColor(stepsPageIndicatorTextColor)
            //setBackgroundColor(resources.getColor(R.color.transparentColor))

            setBackgroundDrawable(resources.getDrawable(R.drawable.background))  //Working
        }

        safeControlsLay.addView(
            controlsLay, ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        )

        val set = ConstraintSet()
        set.clone(safeControlsLay)
        set.connect(controlsLay.id, ConstraintSet.TOP, safeControlsLay.id, ConstraintSet.TOP)
        set.connect(controlsLay.id, ConstraintSet.BOTTOM, safeControlsLay.id, ConstraintSet.BOTTOM)
        set.applyTo(safeControlsLay)


        /////
        val closeCurrentActivity = anchorViews[currentViewIndex].closePopUp
        if (closeCurrentActivity == true) {
            this@OnBoardingDialogueBox.dismiss()
        }
        /////

        val openNextActivity = anchorViews[currentViewIndex].openNextIntentFlag
        if (openNextActivity == true) {
            Log.d("Interface Triggered", "OnBoardingAnimation")
            openIntentCallback?.openIntent(openNextActivity)
        }
    }

    private fun getSafeControlRegion(circleInRectangleView: CircleInRectangleView): ConstraintLayout {
        val topRegionRect = Rect()
        val middleRegionRect = Rect()
        val bottomRegionRect = Rect()

        dialogRootView.topGroundLay.getGlobalVisibleRect(topRegionRect)
        dialogRootView.middleGroundLay.getGlobalVisibleRect(middleRegionRect)
        dialogRootView.bottomGroundLay.getGlobalVisibleRect(bottomRegionRect)

        val anchorCoordinates = circleInRectangleView.getCentre()

        dialogRootView.topGroundLay.removeAllViews()
        dialogRootView.bottomGroundLay.removeAllViews()
        dialogRootView.middleGroundLay.removeAllViews()

        return when {
            topRegionRect.contains(
                anchorCoordinates[0].roundToInt(),
                anchorCoordinates[1].roundToInt()
            ) -> {
                dialogRootView.middleGroundLay
            }
            bottomRegionRect.contains(
                anchorCoordinates[0].roundToInt(),
                anchorCoordinates[1].roundToInt()
            ) -> {
                dialogRootView.middleGroundLay
            }
            else -> {
                dialogRootView.bottomGroundLay
            }
        }
    }

    private fun setUpOnClickListeners() {

        dialogRootView.rightIV.setOnClickListener {
            currentViewIndex++
            dialogRootView.highLighterLay.removeAllViews()
            anchorViews[currentViewIndex].anchorView.highlightAView()
        }

        dialogRootView.leftIV.setOnClickListener {
            currentViewIndex--
            dialogRootView.highLighterLay.removeAllViews()
            anchorViews[currentViewIndex].anchorView.highlightAView()
        }

        dialogRootView.leftIV.enable(currentViewIndex != 0)

        dialogRootView.rightIV.enable(currentViewIndex < anchorViews.size - 1)

        if (currentViewIndex < anchorViews.size - 1) {
            closeAnim.isEnabled = false
            closeAnim.visibility = View.GONE
        } else {
            rightAnim.isEnabled = false
            rightAnim.visibility = View.GONE
        }

    }

    private fun View.enable(enable: Boolean) {
        if (enable) {
            this.isEnabled = true
            this.visibility = View.VISIBLE
        } else {
            this.isEnabled = false
            this.visibility = View.INVISIBLE
        }
    }

    override fun onBackPressed() {
        if (leftIV.visibility == View.VISIBLE) {
            currentViewIndex--
            dialogRootView.highLighterLay.removeAllViews()
            anchorViews[currentViewIndex].anchorView.highlightAView()
        }
    }

}
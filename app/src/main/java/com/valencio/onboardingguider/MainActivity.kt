package com.valencio.onboardingguider

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import com.valencio.on_boarding_guider.AnchorView
import com.valencio.on_boarding_guider.ControlsLay
import com.valencio.on_boarding_guider.OnBoardingDialogueBox
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.controls_layout.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ConstraintLayout>(R.id.rootView).viewTreeObserver.addOnGlobalLayoutListener(
            object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    launchOnBoardingDialogueBox()
                    findViewById<ConstraintLayout>(R.id.rootView).viewTreeObserver.removeOnGlobalLayoutListener(
                        this
                    )
                }
            })
        /*findViewById<Button>(R.id.ashwini).setOnClickListener {
            launchOnBoardingDialogueBox()
        }*/
    }


    private fun launchOnBoardingDialogueBox() {
        OnBoardingDialogueBox(
            this@MainActivity,
            listOf(
                AnchorView(
                    findViewById<ImageView>(R.id.imageView),
                    "This is an imageView"
                )
            )
        ).setAroundColor(android.R.color.darker_gray)
            .setContentTintColor(android.R.color.white)
            .setHighLighterColor(android.R.color.white)
            .setStepsPageIndicatorTextColor(android.R.color.holo_red_light)
            .show()

        timerFunction()

    }

    private fun timerFunction() {

        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                //finish()
                /*OnBoardingDialogueBox(
                    this@MainActivity,
                    listOf(
                        AnchorView(
                            findViewById<TextView>(R.id.imageView),
                            "Hey there thats Valencio"
                        )
                    )
                ).dismiss()

                OnBoardingDialogueBox(
                    this@MainActivity,
                    listOf(
                        AnchorView(
                            findViewById<TextView>(R.id.valencio),
                            "Hey there thats Valencio"
                        )
                        *//*,
                        AnchorView(
                            findViewById<TextView>(R.id.valencio),
                            "Hey there thats Valencio"
                        ),
                        AnchorView(
                            findViewById<TextView>(R.id.ashwini),
                            "\"Hey there thats Ashwini"
                        ),
                        AnchorView(
                            findViewById<TextView>(R.id.shreya),
                            "Hey there thats Shreya"
                        ),
                        AnchorView(
                            findViewById<ImageView>(R.id.iv_123),
                            "IV 123 "
                        ),
                        AnchorView(
                            findViewById<ImageView>(R.id.iv_456),
                            "IV 456"
                        ),
                        AnchorView(
                            findViewById<ImageView>(R.id.iv_456),
                            "123456789"
                        )*//*
                    )
                ).setAroundColor(android.R.color.darker_gray)
                    .setContentTintColor(android.R.color.white)
                    .setHighLighterColor(android.R.color.white)
                    .setStepsPageIndicatorTextColor(android.R.color.holo_red_light)
                    .show()
*/

                val text = "Timer Done"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()

            }
        }.start()


    }

}

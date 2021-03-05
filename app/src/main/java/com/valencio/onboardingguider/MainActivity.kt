package com.valencio.onboardingguider

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.valencio.on_boarding_guider.AnchorView
import com.valencio.on_boarding_guider.OnBoardingDialogueBox

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ConstraintLayout>(R.id.rootView).viewTreeObserver.addOnGlobalLayoutListener(
            object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    launchOnBoardingDialogueBox()
                }
            })
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
                val text = "Timer Done"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()


            }
        }.start()

    }

}

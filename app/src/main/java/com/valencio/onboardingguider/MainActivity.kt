package com.valencio.onboardingguider

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.valencio.on_boarding_guider.AnchorView
import com.valencio.on_boarding_guider.OnBoardingDialogueBox
import kotlinx.android.synthetic.main.controls_layout.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerFunction()
    }

    private fun timerFunction() {

        object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                launchOnBoardingDialogueBox()

            }
        }.start()

    }

    private fun launchOnBoardingDialogueBox() {


        val intent = Intent(this, Activity2::class.java)

        OnBoardingDialogueBox(
            this@MainActivity,
            listOf(
                AnchorView(
                    findViewById<ImageView>(R.id.imageView),
                    "This is an imageView",
                    closePopUp = false,
                    openNextIntentFlag = false
                ), AnchorView(
                    findViewById<ImageView>(R.id.imageView),
                    "This is an imageView",
                    closePopUp = false,
                    openNextIntentFlag = false
                )
            )
        ).apply {
            setAroundColor(android.R.color.darker_gray)
            setContentTintColor(android.R.color.white)
            setHighLighterColor(android.R.color.white)
            setStepsPageIndicatorTextColor(android.R.color.holo_red_light)
            show()
        }


    }

}

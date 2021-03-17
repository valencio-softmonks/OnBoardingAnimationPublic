# OnBoardingAnimation 

----------
Step 1 : Add to project root build.gradle.(Project Gradle)
----------
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' } 
		}
	}
----------
Step 2 : Add the dependency.(Module Gradle)
----------
	  implementation 'com.github.valencio-softmonks:OnBoardingAnimationPublic:v1.0.3'

----------
Step 3 : Implementation in the code
----------
	OnBoardingDialogueBox(
                this@NavigationActivity,
                listOf(
                    AnchorView(
                        findViewById<ImageView>(R.id.nav_slideshow),
                        "Nav Home 3 ",
                        closePopUp = false,
                        openNextIntentFlag = false
                    ), AnchorView(
                        findViewById<ImageView>(R.id.nav_home),
                        "Nav Home Highlight - (Camera)",
                        closePopUp = false,
                        openNextIntentFlag = false
                    )
                )
            ).apply {
                setAroundColor(android.R.color.holo_red_light)
                setContentTintColor(android.R.color.white)
                setHighLighterColor(android.R.color.holo_red_light)
                setStepsPageIndicatorTextColor(android.R.color.holo_red_light)
                show()
            }
----------
Step 4 : Implement the Interface
----------
	Example: class NavigationActivity : AppCompatActivity(), OpenNextIntent {
----------
Step 5 : Override the openIntent(openNextIntent: Boolean?) method.
----------
	 override fun openIntent(openNextIntent: Boolean?) {
        Log.d("Interface Triggered", "NavigationActivity")
        if (openNextIntent == true) {
           //doSomething...
        } else {
           //doSomething...
        }
    }
----------
NOTE : Use a timer if the Views Do Not Load
----------
	private fun timerFunction() {
        object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                //call the onBoarding function here.
            }
        }.start()

    }
----------

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
	  implementation 'com.github.valencio-softmonks:OnBoardingAnimationPublic:v1.0.1'

----------
Step 3 : Implementation in the code
----------
	OnBoardingDialogueBox(
            this@MainActivity,
            listOf(
                AnchorView(
                    findViewById<TextView>(R.id.imageView),
                    "ImageView"
                ),
                AnchorView(
                    findViewById<TextView>(R.id.textView),
                    "Text View"
                )
            )
        ).setAroundColor(R.color.transparentColor)  //optional
            .setContentTintColor(android.R.color.white) //optional
            .setHighLighterColor(android.R.color.white) //optional
            .setStepsPageIndicatorTextColor(android.R.color.darker_gray) //optional
            .show() //required

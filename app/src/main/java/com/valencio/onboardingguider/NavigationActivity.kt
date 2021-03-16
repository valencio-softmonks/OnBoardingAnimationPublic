package com.valencio.onboardingguider

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.valencio.on_boarding_guider.AnchorView
import com.valencio.on_boarding_guider.OnBoardingDialogueBox
import com.valencio.on_boarding_guider.OpenNextIntent

class NavigationActivity : AppCompatActivity(), OpenNextIntent {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        timerFunction()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
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
        // var openIntentFlag: OnBoardingDialogueBox.OpenIntent? = null

        OnBoardingDialogueBox(
            this@NavigationActivity,
            listOf(
                AnchorView(
                    findViewById<ImageView>(R.id.fab),
                    "Fab Icon 1 ",
                    closePopUp = false,
                    openNextIntentFlag = false
                ), AnchorView(
                    findViewById<ImageView>(R.id.nav_gallery),
                    "Nav Home 3 ",
                    closePopUp = true,
                    openNextIntentFlag = true
                )
            )
        ).apply {
            setAroundColor(android.R.color.transparent)
            setContentTintColor(android.R.color.white)
            setHighLighterColor(android.R.color.holo_red_light)
            setStepsPageIndicatorTextColor(android.R.color.holo_red_light)
            show()
        }

    }

    override fun openIntent(openNextIntent: Boolean?) {
        Log.d("Interface Triggered", "NavigationActivity")
        if (openNextIntent == true) {
            val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
            drawerLayout.openDrawer(Gravity.LEFT)
            /////
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
            /////

        } else {
            val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
            drawerLayout.closeDrawer(Gravity.LEFT)
            //Close the Side Menu
        }
    }

}
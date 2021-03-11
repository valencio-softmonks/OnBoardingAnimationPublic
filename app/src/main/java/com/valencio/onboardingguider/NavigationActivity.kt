package com.valencio.onboardingguider

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.Menu
import android.widget.ImageView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.valencio.on_boarding_guider.AnchorView
import com.valencio.on_boarding_guider.OnBoardingDialogueBox

class NavigationActivity : AppCompatActivity() {

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


        val intent = Intent(this, Activity2::class.java)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.openDrawer(Gravity.LEFT)

        OnBoardingDialogueBox(
            this@NavigationActivity,
            listOf(
                AnchorView(
                    findViewById<ImageView>(R.id.fab),
                    "Fab Icon",
                    closePopUp = false
                ), AnchorView(
                    findViewById<ImageView>(R.id.nav_view),
                    "Nav View",
                    closePopUp = false
                ), AnchorView(
                    findViewById<ImageView>(R.id.nav_home),
                    "Nav Home",
                    closePopUp = false
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

}
package com.app.appnews

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        var anim = AnimationUtils.loadAnimation(this, R.anim.fade);
        img.animation = anim

        // Create the Handler object (on the main thread by default)
        val handler = Handler()

        // Define the code block to be executed
        val runnableCode = Runnable { // Do something here on the main thread
            startActivity(Intent(this, LoginActivity::class.java))
        }
        // Run the above code block on the main thread after 5 seconds
        handler.postDelayed(runnableCode, 4000)

    }
}
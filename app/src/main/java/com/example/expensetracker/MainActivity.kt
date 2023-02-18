package com.example.expensetracker

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txt : TextView = findViewById(R.id.txt)

        //https://stackoverflow.com/questions/4985805/set-locale-programmatically
        //https://stackoverflow.com/questions/53985529/android-loads-values-from-default-strings-file-strings-xml-after-changing-loca
        //https://developer.android.com/studio/write/translations-editor

        val locale = Locale("ar")
        Locale.setDefault(locale)
      //  val config = baseContext.resources.configuration
        /*config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)*/

        val resources: Resources = resources
        val configuration: Configuration = resources.configuration
        val displayMetrics: DisplayMetrics = resources.displayMetrics
        configuration.setLocale(locale)
        /*if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            applicationContext.createConfigurationContext(configuration)
        } else {*/
            resources.updateConfiguration(configuration, displayMetrics)
        //}

        txt.text = resources.getString(R.string.hello_world)
    }

}
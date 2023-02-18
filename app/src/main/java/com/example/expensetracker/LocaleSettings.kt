package com.example.expensetracker

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.preference.PreferenceManager
import android.view.View
import androidx.core.text.TextUtilsCompat
import androidx.core.view.ViewCompat
import java.util.*


/**
 * Created by Parthi on 13/02/23
 */
class LocaleSettings {

    companion object {
        const val LANGUAGE_ARABIC = "ar"
        const val CURRENT_LANGUAGE = "currentLanguage"
        const val LANGUAGE_ENGLISH = "en"
    }


    /**
     * Loads the current language of application
     *
     * @param context current context, pass "this" for current view context
     */
    fun loadLocal(context: Context) {
        setLocal(context,
            PreferenceManager.getDefaultSharedPreferences(context)
                .getString(Companion.CURRENT_LANGUAGE, ""))
    }

    /**
     * This fucntion sets the application language
     *
     * @param context - current context. pass "this" for current view context
     * @param lang    Language String, i.e. "en" or "ar"
     */
    fun setLocal(context: Context, lang: String?) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        context.getResources()
            .updateConfiguration(config, context.getResources().getDisplayMetrics())
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = pref.edit()
        editor.putString(Companion.CURRENT_LANGUAGE, lang)
        editor.apply()
        editor.commit()
    }

    /**
     * Use to change application language using current context
     *
     * @param context pass "this" for current view context
     */
  open  fun switchLanguage(context: Context) {
        if (getCurrentLanguage(context) == LANGUAGE_ENGLISH) setLocal(context,
            Companion.LANGUAGE_ARABIC) else setLocal(context, LANGUAGE_ENGLISH)
    }

    /**
     * Get application current active language
     *
     * @param context pass "this" for current view context
     * @return String - language string i.e. en or ar
     */
    fun getCurrentLanguage(context: Context?): String? {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getString(Companion.CURRENT_LANGUAGE, "")
    }

    fun isRTL(locale: String?): Boolean {
        return TextUtilsCompat.getLayoutDirectionFromLocale(Locale(locale)) == ViewCompat.LAYOUT_DIRECTION_RTL
    }

    fun enforceDirectionIfRTL(context: Context) {
        if (isRTL(getCurrentLanguage(context))) {
            (context as Activity).window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }
    }
}


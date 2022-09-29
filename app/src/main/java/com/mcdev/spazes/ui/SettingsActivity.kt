package com.mcdev.spazes.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.dolatkia.animatedThemeManager.AppTheme
import com.dolatkia.animatedThemeManager.ThemeActivity
import com.mcdev.spazes.R
import com.mcdev.spazes.changeStatusBarColor
import com.mcdev.spazes.databinding.ActivitySettingsBinding
import com.mcdev.spazes.makeStatusBarTransparent
import com.mcdev.spazes.theme.BaseTheme
import com.mcdev.spazes.theme.DefaultTheme
import com.mcdev.spazes.viewmodel.DatastoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : ThemeActivity() {
    lateinit var binding : ActivitySettingsBinding
    private var themeMode : AppTheme = DefaultTheme()
    private val dataStoreViewModel: DatastoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(LayoutInflater.from(this@SettingsActivity))
        setContentView(binding.root)

        makeStatusBarTransparent()

        binding.settingsBackBtn.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
            finish() // todo implement this in a better way
        }

        binding.settingsThemeBtn.setOnClickListener {
            startActivity(Intent(this@SettingsActivity, SettingsThemeActivity::class.java))
        }
    }

    override fun syncTheme(appTheme: AppTheme) {

        val tt = appTheme as BaseTheme
        binding.root.setBackgroundColor(tt.activityBgColor(this))
        binding.settingsTv.setTextColor(resources.getColor(tt.textColor(), this.theme))
        binding.settingsThemeTv.setTextColor(resources.getColor(tt.textColor(), this.theme))

    }

    override fun getStartTheme(): AppTheme {
        return themeMode
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HomeActivity::class.java))
        finish() // todo implement this in a better way
    }
}
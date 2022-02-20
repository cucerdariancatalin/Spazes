package com.mcdev.spazes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.mcdev.spazes.databinding.ActivityProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val loginViewModel: LoginViewModel by viewModels()
    private val viewModel: SpacesViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        changeStatusBarColor(R.color.white)

        val profileUrl = intent.extras?.get("profile_url")
        val displayName = intent.extras?.get("username")
        val userTwitterId = intent.extras?.get("userTwitterId")
        val userTwitterHandle = intent.extras?.get("userTwitterHandle")

        Log.d("TAG", "onCreate: profile id is $userTwitterId")
        Log.d("TAG", "onCreate: profile handle is $userTwitterHandle")
        binding.profileAvi.setImageURI(profileUrl.toString().getOriginalTwitterAvi())
        binding.displayName.text = displayName.toString()


        binding.profileBackBtn.setOnClickListener {
            finish()
        }

        binding.signOutBtn.setOnClickListener {
            AlertDialog.Builder(this@ProfileActivity)
                .setTitle(getString(R.string.logout_dialog_title))
                .setMessage(getString(R.string.logout_confirmation_message))
                .setPositiveButton(getString(R.string.yes)
                ) { _, _ -> lifecycleScope.launch { doSignOut() } }//signOut
                .setNegativeButton(getString(R.string.no)
                ) { p0, _ -> p0.dismiss() }// dismiss dialog
                .show()
        }

        binding.favouriteHostsBtn.setOnClickListener {
            Toast.makeText(this, "Coming soon...", Toast.LENGTH_SHORT).show()
        }

        binding.mySpacesBtn.setOnClickListener {
            startActivity(Intent(this, UserSpacesActivity::class.java)
                .putExtra("loadAction", LoadAction.MY_SPACES)
                .putExtra("user_twitter_id", userTwitterId.toString()))
        }
    }

    private suspend fun doSignOut() {
        val loadingDialog = LottieLoadingDialogFragment()
        val bundle = Bundle()
        bundle.putString("message", "Logging out...")
        loadingDialog.isCancelable = false
        loadingDialog.arguments = bundle
        loadingDialog.show(supportFragmentManager, "")

        delay(3000)
        loginViewModel.logout()
        finish()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}
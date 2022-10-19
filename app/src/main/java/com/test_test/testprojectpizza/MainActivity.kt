package com.test_test.testprojectpizza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.test_test.testprojectpizza.databinding.ActionBarLayoutBinding
import com.test_test.testprojectpizza.databinding.ActivityMainBinding
import com.test_test.testprojectpizza.screens.fragments.CartFragment
import com.test_test.testprojectpizza.screens.fragments.MenuFragment
import com.test_test.testprojectpizza.screens.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var actionBarBinding: ActionBarLayoutBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        actionBarBinding = ActionBarLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.placeHolder, MenuFragment.newInstance()).commit()

        setContent()
    }

    private fun setContent() {
        supportActionBar?.hide()
        binding.bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.BottomMenuMenu ->
                    supportFragmentManager.beginTransaction().replace(R.id.placeHolder, MenuFragment.newInstance()).commit()

                R.id.BottomMenuProfile ->
                    supportFragmentManager.beginTransaction().replace(R.id.placeHolder, ProfileFragment.newInstance()).commit()

                R.id.BottomMenuCart ->
                    supportFragmentManager.beginTransaction().replace(R.id.placeHolder, CartFragment.newInstance()).commit()
            }
            true
        }

        actionBarBinding.imQr.setOnClickListener {
            Toast.makeText(this, "Qr pressed!", Toast.LENGTH_SHORT).show()
        }
    }
}
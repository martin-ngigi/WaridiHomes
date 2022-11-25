package com.example.waridihomes.presentation.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.waridihomes.R
import com.example.waridihomes.presentation.ui.fragments.FirstFragment
import com.example.waridihomes.presentation.ui.fragments.SecondFragment
import com.example.waridihomes.presentation.ui.fragments.ThirdFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomBNavigationView.background=null
        bottomBNavigationView.menu.getItem(2).isEnabled = false

        //create fragment instances
        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
        val thirdFragment = ThirdFragment()

        //set current fragment as firstFragment
        setCurrentFragment(firstFragment)

        //handle click listener
        bottomBNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.mi_home -> setCurrentFragment(firstFragment)
                R.id.mi_search -> setCurrentFragment(secondFragment)
                R.id.mi_profile -> setCurrentFragment(thirdFragment)
            }
            true //returns true value
        }

    }


    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_Fragment, fragment) //replace fl_Fragment with fragment
            commit() //save changes
        }
}
package com.example.waridihomes.fragments


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.waridihomes.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_signup.setOnClickListener {
            //navigate to the signUp fragment
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)

        }

        login_button.setOnClickListener{
            //navigate to the home fragment
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

}
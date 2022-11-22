package com.example.waridihomes.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.waridihomes.R
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register_signin.setOnClickListener {
            //navigate to the login fragment
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }


    }

}
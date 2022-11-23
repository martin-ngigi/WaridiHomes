package com.example.waridihomes.presentation.ui.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.waridihomes.R
import com.example.waridihomes.data.model.modelrequest.LoginRequest
import com.example.waridihomes.data.util.Utils.validateLoginRequest
import com.example.waridihomes.databinding.FragmentLoginBinding
import com.example.waridihomes.presentation.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
//class LoginFragment : Fragment(R.layout.fragment_login) { //also acceptable
class LoginFragment : Fragment() {

    val viewModel: LoginViewModel by viewModels()
    private lateinit var loginBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the layout for this fragmnet
        return  inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
        login_signup.setOnClickListener {
            //navigate to the signUp fragment
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)

        }

        login_button.setOnClickListener{
            //navigate to the home fragment
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        */

        loginBinding = FragmentLoginBinding.bind(view)

        // handle loginButton click events
        loginBinding.loginButton.setOnClickListener{
            val email = loginBinding.loginUsername.editableText.toString()
            val password = loginBinding.loginPassword.editableText.toString()

            /**
             * invoke data validation method
             */
            val result = validateLoginRequest(email, password)

            if (result.successful){
                loginBinding.loginProgress.loadingProgress.visibility = View.VISIBLE
                loginBinding.loginButton.isEnabled = false

                val loginRequest = LoginRequest(email, password)

                viewModel.login(loginRequest= loginRequest)


                viewModel.successful.observe(viewLifecycleOwner){successful ->
                    if (successful==true){
                        Log.i("AuthUseCase", "2. onViewCreated: Loggin was successful... ")

                        loginBinding.loginProgress.loadingProgress.visibility = View.GONE
                        loginBinding.loginButton.isEnabled = true
                        //println("DATA SENT: $loginRequest")
                        //navigate to home fragment
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        viewModel.navigateToPage()
                    }
                    else if( successful == false){


                        loginBinding.loginProgress.loadingProgress.visibility = View.GONE
                        loginBinding.loginButton.isEnabled = true
                        Snackbar.make(loginBinding.loginButton, "Loginn Error: ${viewModel.error.value}", Snackbar.LENGTH_SHORT).show()
                        viewModel.navigateToPage()
                    }
                }



            }
            else{
                loginBinding.loginProgress.loadingProgress.visibility = View.GONE
                loginBinding.loginButton.isEnabled = true
                Snackbar.make(loginBinding.loginButton, "Error: ${result.error}", Snackbar.LENGTH_SHORT).show()
            }
        }

        //navigate to signup fragment on button click
        loginBinding.loginSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

}
package com.example.waridihomes.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.waridihomes.R
import com.example.waridihomes.data.model.modelrequest.UserRequest
import com.example.waridihomes.data.util.Utils
import com.example.waridihomes.data.util.Utils.validateRegisterRequest
import com.example.waridihomes.databinding.FragmentSignUpBinding
import com.example.waridihomes.presentation.viewmodel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_sign_up.*

@AndroidEntryPoint
//class SignUpFragment : Fragment(R.layout.fragment_sign_up) { //This can also be used
class SignUpFragment : Fragment() {

    val viewModel: RegisterViewModel by viewModels()
    private  lateinit var  signUpBinding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUpBinding = FragmentSignUpBinding.bind(view)

        /**
        register_signin.setOnClickListener {
            //navigate to the login fragment
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        */

        /**
         * handle registerButton click listener
         */
        signUpBinding.registerButton.setOnClickListener{
            /**
             * get data from UI
             */
            val fname = signUpBinding.fName.editableText.toString()
            val lname = signUpBinding.lName.editableText.toString()
            val email = signUpBinding.email.editableText.toString()
            val phone = signUpBinding.phone.editableText.toString()
            val password = signUpBinding.registerPassword.editableText.toString()

            val result = validateRegisterRequest(fname, lname,email, phone, password)

            if (result.successful){
                signUpBinding.loginProgress.loadingProgress.visibility = View.VISIBLE
                signUpBinding.registerButton.isEnabled = false

                /**
                 *
                @SerializedName("email")
                val email: String,
                @SerializedName("first_name")
                val firstName: String,
                @SerializedName("last_name")
                val lastName: String,
                @SerializedName("password")
                val password: String,
                @SerializedName("phone")
                val phone: String,
                @SerializedName("user_type")
                val userType: String,
                @SerializedName("username")
                val username: String
                 */
                val userRequest = UserRequest(email=email,
                                            firstName = fname,
                                            lastName = lname,
                                            password=password,
                                            phone=phone,
                                            userType = "C",
                                            username = email)

                viewModel.registerUser(userRequest = userRequest)

                viewModel.successful.observe(viewLifecycleOwner) {successful ->
                    if (successful == true){
                        signUpBinding.loginProgress.loadingProgress.visibility= View.VISIBLE
                        signUpBinding.registerButton.isEnabled = true
                        /**
                         * navigate to login fragment
                         */
                        findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
                        viewModel.navigateToPage()
                    }
                    else{
                        signUpBinding.loginProgress.loadingProgress.visibility = View.VISIBLE
                        signUpBinding.registerButton.isEnabled=true
                        Snackbar.make(signUpBinding.registerButton, "${viewModel.error.value}", Snackbar.LENGTH_SHORT).show()
                        viewModel.navigateToPage()
                    }
                }
            }
            else{
                Snackbar.make(signUpBinding.registerButton, "Error: ${result.error}", Snackbar.LENGTH_SHORT).show()
            }
        }

        signUpBinding.registerSignin.setOnClickListener {
            //navigate to login page
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }


    }

}
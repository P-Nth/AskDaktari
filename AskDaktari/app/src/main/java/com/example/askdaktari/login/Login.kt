package com.example.askdaktari.login

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.askdaktari.auth.Authentication
import kotlinx.coroutines.launch


class Login(
    private val auth: Authentication = Authentication()
):ViewModel() {
    val currentUser = auth.currentUser

    val hasUser: Boolean
        get() = auth.hasUser()

    var loginUiState by mutableStateOf(LoginUiStates())
        private set
//    private var loginUiState by mutableStateOf(LoginUiStates())


    fun onUserNameChange(email: String) {
        loginUiState = loginUiState.copy(email = email)
    }
    fun onPasswordChange(password: String) {
        loginUiState = loginUiState.copy(password = password)
    }
    fun onEmailSignup(email: String) {
        loginUiState = loginUiState.copy(emailSignup = email)
    }
    fun onPasswordSignup(password: String) {
        loginUiState = loginUiState.copy(passwordSignup = password)
    }
    fun onConfirmPass(password: String) {
        loginUiState = loginUiState.copy(confirmPass = password)
    }

    private fun validateLogins() =
        loginUiState.email.isNotBlank() && loginUiState.password.isNotBlank()

    private fun validateRegister() =
        loginUiState.emailSignup.isNotBlank() && loginUiState.passwordSignup.isNotBlank() && loginUiState.confirmPass.isNotBlank()

    fun createUser(context: Context) = viewModelScope.launch {
        try {
            if(!validateRegister()) {
                throw IllegalArgumentException("Email and password cannot be empty")
            }
            loginUiState = loginUiState.copy(isLoading = true)
            if (loginUiState.passwordSignup != loginUiState.confirmPass) {
                throw IllegalArgumentException("Passwords don't match!")
            }
            loginUiState = loginUiState.copy(signupError = null)
            auth.createUser(
                loginUiState.emailSignup,
                loginUiState.passwordSignup
            ) { isSuccessful ->
                if (isSuccessful) {
                    Toast.makeText(context, "Account Created Successfully", Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                } else {
                    Toast.makeText(
                        context,
                        "Successful Login",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }
            }
        }catch (_e:Exception) {
            loginUiState = loginUiState.copy(signupError = _e.localizedMessage)
            _e.printStackTrace()
        }finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }
    }

    fun loginUser(context: Context) = viewModelScope.launch {
        try {
            if(!validateLogins()) {
                throw IllegalArgumentException("Email and password cannot be empty")
            }
            loginUiState = loginUiState.copy(isLoading = true)
            loginUiState = loginUiState.copy(loginError = null)
            auth.login(
                loginUiState.email,
                loginUiState.password
            ) { isSuccessful ->
                loginUiState = if (isSuccessful) {
                    Toast.makeText(context, "Logged in Successfully", Toast.LENGTH_SHORT).show()
                    loginUiState.copy(isSuccessLogin = true)
                } else {
                    Toast.makeText(
                        context,
                        "Login Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState.copy(isSuccessLogin = false)
                }
            }
        }catch (_e:Exception) {
            loginUiState = loginUiState.copy(loginError = _e.localizedMessage)
            _e.printStackTrace()
        }finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }
    }

}

data class LoginUiStates(
    val email:String = "",
    val password:String = "",
    val emailSignup:String = "",
    val passwordSignup:String = "",
    val confirmPass:String = "",
    val isLoading:Boolean = false,
    val isSuccessLogin:Boolean = false,
    val loginError:String? = null,
    val signupError:String? = null,
)
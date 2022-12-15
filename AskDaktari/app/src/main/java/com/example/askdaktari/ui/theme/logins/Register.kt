package com.example.askdaktari.ui.theme.logins

import android.util.Patterns
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import com.example.askdaktari.login.Login
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.askdaktari.ui.theme.*
import com.example.askdaktari.ui.theme.extra.LoadingBubbles

@Composable
fun Register(
    login: Login? = null,
    onNavToHomeScreen:() -> Unit,
    onNavToLogin:() -> Unit,
) {

    val logo = painterResource(com.example.askdaktari.R.drawable.logo)

    val loginUiState = login?.loginUiState
    val isError = loginUiState?.signupError != null
    val context = LocalContext.current

    val emailValue = remember { mutableStateOf("") }
    val passValue = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val dataEntry = remember { mutableStateOf(false) }
    val passVisibility = remember { mutableStateOf(false) }
    val repassVisibility = remember { mutableStateOf(false) }
    val isPasswordValid by derivedStateOf { passValue.value.length > 5 }
    val isEmailValid by derivedStateOf { Patterns.EMAIL_ADDRESS.matcher(emailValue.value).matches() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 30.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.25f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = logo,
                    contentDescription = "logo",
                    modifier = Modifier
                        .fillMaxHeight(.6f)
                        .fillMaxWidth(.6f)
                        .background(Color.White)

                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
                    .clip(RoundedCornerShape(5.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Register",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    ),
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.padding(10.dp))
                if (isError) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Red100)
                            .padding(5.dp),
                        text = loginUiState?.signupError ?: "Please try again later!",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            letterSpacing = 1.sp,
                            color = Red,
                        ),
                        fontSize = 15.sp
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    OutlinedTextField(
                        value = loginUiState?.emailSignup ?: emailValue.value,
                        onValueChange = {
                            login?.onEmailSignup(it)
                            emailValue.value = it
                            dataEntry.value = true
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = if (emailValue.value.isEmpty()) Gray else {
                                if(isEmailValid) Gray else Red
                            },
                            focusedBorderColor = if (emailValue.value.isEmpty()) Blue500 else {
                                if(isEmailValid) Blue500 else Red
                            },
                        ),
                        trailingIcon = {
                            if(dataEntry.value) {
                                Icon(
                                    imageVector = if (emailValue.value.isEmpty()) return@OutlinedTextField else {
                                        if(isEmailValid) Icons.Filled.CheckCircle else Icons.Filled.Close
                                    },
                                    contentDescription = "email validity",
                                    tint = if (isEmailValid) Blue500 else Red
                                )
                            }
                        },
                        label = { Text(text = "Email") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                                dataEntry.value = false }
                        ),
                        placeholder = { Text(text = "Email Address") },

                        //check email validity
                        isError = isError
                    )
                    OutlinedTextField(
                        value = loginUiState?.passwordSignup ?: passValue.value,
                        onValueChange = {
                            login?.onPasswordSignup(it)
                            passValue.value = it
                            dataEntry.value = true
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = if (passValue.value.isEmpty()) Gray else {
                                if(isPasswordValid) Gray else Red
                            },
                            focusedBorderColor = if (passValue.value.isEmpty()) Blue500 else {
                                if(isPasswordValid) Blue500 else Red
                            },
                        ),
                        trailingIcon = {
                            IconButton(onClick = { passVisibility.value = !passVisibility.value }) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = com.example.askdaktari.R.drawable.password_eye),
                                    contentDescription = "password eye",
                                    tint = if (passVisibility.value) Purple500 else Gray
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Password") },
                        placeholder = { Text(text = "Password") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        ),
                        visualTransformation = if (passVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),

                        //check pass validity
                        isError = isError
                    )
                    OutlinedTextField(
                        value = loginUiState?.confirmPass ?: passValue.value,
                        onValueChange = {
                            login?.onConfirmPass(it)
                            passValue.value = it
                            dataEntry.value = true
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = if (passValue.value.isEmpty()) Gray else {
                                if(isPasswordValid) Gray else Red
                            },
                            focusedBorderColor = if (passValue.value.isEmpty()) Blue500 else {
                                if(isPasswordValid) Blue500 else Red
                            },
                        ),
                        trailingIcon = {
                            IconButton(onClick = { repassVisibility.value = !repassVisibility.value }) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = com.example.askdaktari.R.drawable.password_eye),
                                    contentDescription = "password eye",
                                    tint = if (repassVisibility.value) Purple500 else Gray
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Re-Password") },
                        placeholder = { Text(text = "Re-Password") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        ),
                        visualTransformation = if (repassVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),

                        //check pass validity
                        isError = isError
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        onClick = { login?.createUser(context) }
                    ) {
                        if (loginUiState?.isLoading == true) LoadingBubbles() else {
                            Text(
                                text = "Register",
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 20.sp,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(20.dp))
                    Text(
                        modifier = Modifier.clickable(onClick = {onNavToLogin.invoke()}),
                        text = "Have an account? Login"
                    )
                }
                Spacer(modifier = Modifier.padding(20.dp))
            }
        }
        LaunchedEffect(key1 = login?.hasUser) {
            if (login?.hasUser == true) {
                onNavToLogin.invoke()
            }
        }
    }
}

@Preview
@Composable
fun Prev3() {
    Register(onNavToHomeScreen = { /*TODO*/ }) {
        
    }
}
package com.example.askdaktari.ui.theme.extra

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.compose.ui.text.TextStyle
import com.google.accompanist.permissions.*
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.LifecycleEventObserver
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun Permission() {
    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_MEDIA_LOCATION,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CAMERA
        )
    )
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                if(event == Lifecycle.Event.ON_START) {
                    permissionState.launchMultiplePermissionRequest()
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
        }
    )
    ManagePermissions(
        appPermissions = permissionState,
       // deniedContent = { shouldShowRationale ->
           // PermissionDeniedContent(
              //  rationaleMessage = rationaleMessage,
              //  shouldShowRationale = shouldShowRationale
           // ) { permissionState.launchPermissionRequest() }
       // }
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ManagePermissions(
    appPermissions: MultiplePermissionsState,
    // deniedContent: @Composable (Boolean) -> Unit,
    // content: @Composable () -> Unit
) {
    Box(modifier = Modifier) {
        Column(modifier = Modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            appPermissions.permissions.forEach { appPermission ->
                when(appPermission.permission) {
                    Manifest.permission.ACCESS_FINE_LOCATION -> {
                        when {
                            appPermission.status.isGranted -> {
                               // PermissionContent(
                                   // rationaleMessage = "This App requires camera permissions for video calls",
                                   // shouldShowRationale =  false
                               // )
                            }
                            appPermission.status.shouldShowRationale -> {
                                PermissionContent(
                                    rationaleMessage = "This App requires camera permissions for video calls",
                                    shouldShowRationale =  appPermission.status.shouldShowRationale
                                ) { appPermission.launchPermissionRequest() }
                            }
                            appPermission.status.isDeniedRecursively() -> {

                            }
                        }
                    }
                    Manifest.permission.ACCESS_MEDIA_LOCATION -> {
                        when {
                            appPermission.status.isGranted -> {

                            }
                            appPermission.status.shouldShowRationale -> {
                                PermissionContent(
                                    rationaleMessage = "This App requires camera permissions for video calls",
                                    shouldShowRationale =  appPermission.status.shouldShowRationale
                                ) { appPermission.launchPermissionRequest() }
                            }
                            appPermission.status.isDeniedRecursively() -> {

                            }
                        }
                    }
                    Manifest.permission.CALL_PHONE -> {
                        when {
                            appPermission.status.isGranted -> {

                            }
                            appPermission.status.shouldShowRationale -> {
                                PermissionContent(
                                    rationaleMessage = "This App requires camera permissions for video calls",
                                    shouldShowRationale =  appPermission.status.shouldShowRationale
                                ) { appPermission.launchPermissionRequest() }
                            }
                            appPermission.status.isDeniedRecursively() -> {

                            }
                        }
                    }
                    Manifest.permission.CAMERA -> {
                        when {
                            appPermission.status.isGranted -> {

                            }
                            appPermission.status.shouldShowRationale -> {
                                PermissionContent(
                                    rationaleMessage = "This App requires camera permissions for video calls",
                                    shouldShowRationale =  appPermission.status.shouldShowRationale
                                ){ appPermission.launchPermissionRequest() }
                            }
                            appPermission.status.isDeniedRecursively() -> {

                            }
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalPermissionsApi
@Composable
fun PermissionContent(
    rationaleMessage: String,
    shouldShowRationale: Boolean,
    onRequestPermission: () -> Unit
) {
    Box(modifier = Modifier) {
        if (shouldShowRationale) {

            AlertDialog(
                onDismissRequest = {},
                title = {
                    Text(
                        text = "Permission Request",
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.h6.fontSize,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                text = {
                    Text(rationaleMessage)
                },
                confirmButton = {
                    Button(onClick = onRequestPermission) {
                        Text("Give Permission")
                    }
                }
            )

        } else {
            Content(onClick = onRequestPermission)
        }
    }
}

@Composable
fun Content(showButton: Boolean = true, onClick: () -> Unit) {
    if (showButton) {
        val enableLocation = remember { mutableStateOf(true) }
        if (enableLocation.value) {
            Text(text = "DO THIS")
        }
    }
}
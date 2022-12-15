package com.example.askdaktari.ui.theme.extra

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
fun PermissionStatus.isDeniedRecursively(): Boolean {
    return !shouldShowRationale && !isGranted
}
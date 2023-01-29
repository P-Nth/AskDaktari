package com.example.askdaktari.ui.theme.clinics

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import com.example.askdaktari.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.*
import com.google.android.gms.maps.model.*
import com.example.askdaktari.home.location.ViewMap
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource

@Composable
fun MapHolder(navController: NavHostController) {
    Box() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MapScreen()
        }
    }
}

@Composable
fun MapScreen(
    viewModel: ViewMap = viewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val currLocation = LatLng(-1.2157893,36.8877352)
    val uiSettings = remember { MapUiSettings(zoomControlsEnabled = false)}
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currLocation, 10f)
    }
    Box(modifier = Modifier) {
        Scaffold(
            modifier = Modifier.fillMaxHeight(.919f),
            scaffoldState = scaffoldState,
            floatingActionButton = {
                FloatingActionButton(modifier = Modifier.padding(0.dp, 50.dp), onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = if (viewModel.state.isFallOutMap) {
                            Icons.Default.ToggleOff
                        } else Icons.Default.ToggleOn, contentDescription = "Toggle Fallout"
                    )
                }
            }
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxWidth(),
                uiSettings = uiSettings,
                properties = viewModel.state.properties,
                cameraPositionState = cameraPositionState,
            ) {
                Marker(
                    state = MarkerState(position = currLocation),
                    draggable = true,
                    title = "Roymatt Supermarket",
                    snippet = "Marker in this location",
                    icon = fromResource(R.drawable.pin)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Prevz(navController: NavHostController = rememberNavController()) {
    MapHolder(navController = navController)
}
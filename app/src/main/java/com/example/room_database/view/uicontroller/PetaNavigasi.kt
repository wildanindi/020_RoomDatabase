package com.example.room_database.view.uicontroller


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.room_database.view.DetailSiswaScreen
import com.example.room_database.view.EditSiswaScreen
import com.example.room_database.view.EntrySiswaScreen
import com.example.room_database.view.HomeScreen
import com.example.room_database.view.route.DestinasiDetailSiswa
import com.example.room_database.view.route.DestinasiDetailSiswa.itemIdArg
import com.example.room_database.view.route.DestinasiEditSiswa
import com.example.room_database.view.route.DestinasiHome
import com.example.room_database.view.route.DestinasiEntry

@Composable
fun SiswaApp(modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()
    HostNavigasi(navController = navController, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },

                navigateToItemUpdate = {
                    navController.navigate("${DestinasiDetailSiswa.route}/${it}")
                }
            )

        }
        composable(DestinasiEntry.route) {
            EntrySiswaScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(route = DestinasiDetailSiswa.routeWithArgs,
            arguments = listOf(navArgument(name = itemIdArg) {
                type = NavType.IntType
            })
        ){
            DetailSiswaScreen(
                // Pastikan parameter ini sudah di-uncomment di DetailSiswaScreen
                navigateToEditItem = { idSiswa ->
                    navController.navigate("${DestinasiEditSiswa.route}/$idSiswa")
                },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = DestinasiEditSiswa.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEditSiswa.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            EditSiswaScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
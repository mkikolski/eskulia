package pl.mkikolski.mojacodziennatabletka.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.mkikolski.mojacodziennatabletka.data.FirestoreRepository
import pl.mkikolski.mojacodziennatabletka.data.UserViewModel
import pl.mkikolski.mojacodziennatabletka.data.ViewModelFactory
import pl.mkikolski.mojacodziennatabletka.ui.views.AllBlogPostsView
import pl.mkikolski.mojacodziennatabletka.ui.views.AllMedicinesView
import pl.mkikolski.mojacodziennatabletka.ui.views.BaseMedicineAddView
import pl.mkikolski.mojacodziennatabletka.ui.views.ChatDetailView
import pl.mkikolski.mojacodziennatabletka.ui.views.ChatView
import pl.mkikolski.mojacodziennatabletka.ui.views.MainView
import pl.mkikolski.mojacodziennatabletka.ui.views.SearchMedicineView

/**
 * Composable function that sets up the navigation graph for the home screen.
 */
@Composable
fun HomeNavigation() {
    val navController = rememberNavController()
    val repository = remember {FirestoreRepository()}
    val sharedViewModel: UserViewModel = viewModel(factory = ViewModelFactory(repository))

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            MainView(sharedViewModel, navController)
        }

        composable("medicines") {
            AllMedicinesView(sharedViewModel, navController)
        }

        composable("blog") {
            AllBlogPostsView(sharedViewModel, navController)
        }

        composable("base_add") {
            BaseMedicineAddView(sharedViewModel, navController)
        }

        composable("search_medicine") {
            SearchMedicineView(sharedViewModel, navController)
        }

        composable("chat") {
            ChatView(sharedViewModel, navController)
        }

        composable("chat_detail/{chatId}") { backStackEntry ->
            ChatDetailView(sharedViewModel, navController, backStackEntry.arguments?.getString("chatId") ?: "")
        }
    }
}
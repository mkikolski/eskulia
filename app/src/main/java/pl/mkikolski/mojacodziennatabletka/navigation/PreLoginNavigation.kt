package pl.mkikolski.mojacodziennatabletka.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.firebase.auth.FirebaseAuth
import pl.mkikolski.mojacodziennatabletka.data.UserRegistrationData
import pl.mkikolski.mojacodziennatabletka.ui.views.GreetingStepper
import pl.mkikolski.mojacodziennatabletka.ui.views.GreetingView
import pl.mkikolski.mojacodziennatabletka.ui.views.LoginView
import pl.mkikolski.mojacodziennatabletka.ui.views.PremiumPurchaseView
import pl.mkikolski.mojacodziennatabletka.ui.views.ProfileCompletedView
import pl.mkikolski.mojacodziennatabletka.ui.views.RegisterCompletionView
import pl.mkikolski.mojacodziennatabletka.ui.views.RegisterView

//TODO: Add animations to navigation
/**
 * Composable function that sets up the navigation graph for the pre-login screens.
 *
 * @param authProvider The Firebase authentication provider.
 */
@Composable
fun PreLoginNavigation(authProvider: FirebaseAuth) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "welcome_view"
    ) {
        composable("welcome_view") {
            GreetingView(navController = navController)
        }
        composable("welcome_stepper_view") {
            GreetingStepper(navController = navController)
        }
        composable("login_view") {
            LoginView(navController = navController)
        }

        composable("register_view") {
            RegisterView(navController = navController)
        }

        composable<UserRegistrationData> {backStackEntry ->
            val registrationData = backStackEntry.toRoute<UserRegistrationData>()
            RegisterCompletionView(navController = navController, registrationData = registrationData, authProvider = authProvider)
        }

        composable("premium_purchase_view") {
            PremiumPurchaseView(navController = navController)
        }
    }
}
package pl.mkikolski.mojacodziennatabletka

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import pl.mkikolski.mojacodziennatabletka.navigation.HomeNavigation
import pl.mkikolski.mojacodziennatabletka.ui.theme.PillAssistantTheme
import pl.mkikolski.mojacodziennatabletka.ui.views.MainView

/**
 * HomeActivity is the main activity for the home screen of the application.
 * It handles user authentication and sets the content view with the theme and navigation.
 */
class HomeActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    /**
     * Called when the activity is starting.
     * Initializes Firebase authentication and sets the content view with the theme and navigation.
     * If the user is not authenticated, it redirects to the MainActivity.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        auth = Firebase.auth

        if (auth.currentUser == null) {
            this.startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }

        setContent {
            PillAssistantTheme {
                HomeNavigation()
            }
        }
    }
}

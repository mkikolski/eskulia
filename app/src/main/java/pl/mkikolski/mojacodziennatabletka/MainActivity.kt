package pl.mkikolski.mojacodziennatabletka

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import pl.mkikolski.mojacodziennatabletka.navigation.PreLoginNavigation
import pl.mkikolski.mojacodziennatabletka.network.ScanApi
import pl.mkikolski.mojacodziennatabletka.ui.theme.PillAssistantTheme

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var medicineApi: ScanApi


    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth

        super.onCreate(savedInstanceState)
        setContent {
            PillAssistantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    PreLoginNavigation(auth)
                }
            }
        }

        medicineApi = ScanApi(this)
        medicineApi.getScanData("12345",
            listener = { scanData ->
                if (scanData != null) {
                    // Handle successful deserialization
                    Log.d("Scan Data", "Found drug: ${scanData.drug?.name}")
                } else {
                    // Handle deserialization failure
                    Log.e("Scan Data Error", "Failed to deserialize")
                }
            },
            errorListener = { error ->
                Log.e("Scan Data Error", error.toString())
            }
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PillAssistantTheme {
        Greeting("Android")
    }
}
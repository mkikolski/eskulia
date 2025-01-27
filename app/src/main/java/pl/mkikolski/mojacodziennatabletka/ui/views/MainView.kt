package pl.mkikolski.mojacodziennatabletka.ui.views

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import pl.mkikolski.mojacodziennatabletka.data.UserViewModel
import pl.mkikolski.mojacodziennatabletka.ui.components.BlogPostCard
import pl.mkikolski.mojacodziennatabletka.ui.components.CustomNavBar
import pl.mkikolski.mojacodziennatabletka.ui.components.MedicationCard
import pl.mkikolski.mojacodziennatabletka.ui.components.SkippedDose
import pl.mkikolski.mojacodziennatabletka.ui.components.Title
import pl.mkikolski.mojacodziennatabletka.ui.components.UpcomingDose
import pl.mkikolski.mojacodziennatabletka.ui.components.UserCard
import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueActive

@Composable
fun MainView(
    viewModel: UserViewModel,
    navController: NavController
) {
    val user by viewModel.userState.collectAsState()
    val medications by viewModel.userMedicationsState.collectAsState()
    val uid = Firebase.auth.uid ?: ""

    LaunchedEffect(Unit) {
        viewModel.getUser(uid)
        Log.d("MainView", "User: $uid")
    }
    if (user == null) {
        Text("Loading...") //TODO: Add loading screen/animation
    } else {
        CustomNavBar() {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                UserCard("John Doe")
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Title("Your medications", fontSize = 20.sp, textAlign = TextAlign.Start)
                    Spacer(modifier = Modifier.weight(1f))
                    Text("See all", fontSize = 18.sp, textAlign = TextAlign.End, color = BlueActive, textDecoration = TextDecoration.Underline)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    MedicationCard("Medicine 1", "active substance 1", "30 mg")
                    Spacer(modifier = Modifier.height(4.dp))
                    MedicationCard("Medicine 1", "active substance 1", "30 mg")
                    Spacer(modifier = Modifier.height(4.dp))
                    MedicationCard("Medicine 1", "active substance 1", "30 mg")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Title("Upcoming notifications", fontSize = 20.sp, textAlign = TextAlign.Start)
                    Spacer(modifier = Modifier.weight(1f))
                    Text("See all", fontSize = 18.sp, textAlign = TextAlign.End, color = BlueActive, textDecoration = TextDecoration.Underline)
                    Spacer(modifier = Modifier.height(8.dp))
                }
                UpcomingDose()
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Title("Skipped doses", fontSize = 20.sp, textAlign = TextAlign.Start)
                    Spacer(modifier = Modifier.weight(1f))
                    Text("See all", fontSize = 18.sp, textAlign = TextAlign.End, color = BlueActive, textDecoration = TextDecoration.Underline)
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
                SkippedDose()
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Title("Educational resources", fontSize = 20.sp, textAlign = TextAlign.Start)
                    Spacer(modifier = Modifier.weight(1f))
                    Text("See all", fontSize = 18.sp, textAlign = TextAlign.End, color = BlueActive, textDecoration = TextDecoration.Underline)
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
                BlogPostCard()
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

//@Preview
//@Composable
//fun MainViewPreview() {
//    MainView()
//}
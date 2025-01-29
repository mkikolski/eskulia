package pl.mkikolski.mojacodziennatabletka.ui.views

import android.util.Log
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavHostController
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
import pl.mkikolski.mojacodziennatabletka.ui.theme.DarkGrayInactive

@Composable
fun MainView(
    viewModel: UserViewModel,
    navController: NavHostController
) {
    val user by viewModel.userState.collectAsState()
    val medications by viewModel.userMedicationsState.collectAsState()
    val blogPosts by viewModel.blogPosts.collectAsState()
    val uid = Firebase.auth.uid ?: ""

    LaunchedEffect(Unit) {
        viewModel.getUser(uid)
        Log.d("MainView", "User: $uid")
    }
    if (user == null) {
        Text("Loading...") //TODO: Add loading screen/animation
    } else {
        CustomNavBar(navController) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                UserCard(user!!.username)
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
                    if (medications.isEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("No medications added yet", fontSize = 18.sp, textAlign = TextAlign.Center, color = DarkGrayInactive)
                        Spacer(modifier = Modifier.height(16.dp))
                    } else {
                        medications.forEach {
                            MedicationCard(it.name, it.activeSubstance, it.dose)
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
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
                    Text("See all", fontSize = 18.sp, textAlign = TextAlign.End, color = BlueActive, textDecoration = TextDecoration.Underline, modifier = Modifier.clickable { navController.navigate("blog") })
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (blogPosts.isEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("No blog posts available", fontSize = 18.sp, textAlign = TextAlign.Center, color = DarkGrayInactive)
                    Spacer(modifier = Modifier.height(16.dp))
                } else {
                    blogPosts[0].let {
                        BlogPostCard(it.title, it.author, it.date, it.content, it.imageUrl, it.tags)
                    }
                }
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
package pl.mkikolski.mojacodziennatabletka.ui.views

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.data.UserRegistrationData
import pl.mkikolski.mojacodziennatabletka.data.UserRegistrationDataSaver
import pl.mkikolski.mojacodziennatabletka.ui.components.ProgressBar
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledIconButtonBackground
import pl.mkikolski.mojacodziennatabletka.ui.components.TextNavButton
import pl.mkikolski.mojacodziennatabletka.ui.models.TextIconContainer
import pl.mkikolski.mojacodziennatabletka.ui.theme.PillAssistantTheme

//TODO: Refactor the avatar selection view to use the new state management
@Composable
fun RegisterCompletionView(navController: NavController, registrationData: UserRegistrationData, authProvider: FirebaseAuth) {
    var step = rememberSaveable { mutableStateOf(0) }
    val allSteps = 5

    var registrationDataState = rememberSaveable(stateSaver = UserRegistrationDataSaver) { mutableStateOf(registrationData) }
    var selectedReasons = remember { mutableStateListOf<TextIconContainer>() }
    val coroutineScope = rememberCoroutineScope()

    val setAge = { age: Int ->
        Log.d("RegisterCompletionView", registrationDataState.value.toString())
        registrationDataState.value = registrationDataState.value.copy(age = age)
    }

    val setReasons = { reason: TextIconContainer ->
        Log.d("RegisterCompletionView", registrationDataState.value.toString())
        Log.d("RegisterCompletionView", selectedReasons.toString())
        if (selectedReasons.contains(reason)) {
            selectedReasons.remove(reason)
        } else {
            selectedReasons.add(reason)
        }
        val reasons = selectedReasons.map { it.text }
        registrationDataState.value = registrationDataState.value.copy(reasons = reasons)
    }

    val setUsesBuiltinAvatar = { usesBuiltinAvatar: Boolean ->
        registrationDataState.value = registrationDataState.value.copy(usesBuiltinAvatar = usesBuiltinAvatar)
    }

    val setAvatarUrl = { avatarUrl: String ->
        registrationDataState.value = registrationDataState.value.copy(avatarUrl = avatarUrl)
    }

    val createProfileInFirebase = {
        Log.d("RegisterCompletionView", registrationDataState.value.toString())
    }

    val setUserName = { username: String ->
        registrationDataState.value = registrationDataState.value.copy(username = username)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            StyledIconButtonBackground(
                icon = R.drawable.baseline_arrow_back_24,
                size = 64.dp,
                colorEnabled = Color(0xFFD3D3D3),
                contentColor = Color.Black,
                onClick = {
                    if (step.value > 0) {
                        step.value -= 1
                    } else {
                        navController.navigate("register_view")
                    }
                }
            )

            Spacer(modifier = Modifier.weight(.1f))

            ProgressBar(
                modifier = Modifier
                    .weight(1f)
                    .height(10.dp),
                width = 300.dp,
                backgroundColor = Color(0xFFF3F3F3),
                foregroundColor = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF0020EE),
                        Color(0xFF9C27B0)
                    )
                ),
                percent = (step.value + 1) * 100 / allSteps
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        AnimatedContent(targetState = step) { targetState ->
            when (targetState.value) {
                0 -> {
                    AgeFormView(registrationDataState.value.age, {
                        step.value += 1
                    }, setAge)
                }

                1 -> { //TODO: Add username validation against whole userbase
                    SetUserNameView(registrationDataState.value.username, {
                        step.value += 1
                    }, setUserName)
                }

                2 -> {
                    ReasonsFormView(selectedReasons, {
                        step.value += 1
                    }, setReasons)
                }

                3 -> { //TODO: Add state edition to AvatarSelectFormView
                    AvatarSelectFormView({
                        step.value += 1
                    })
                }

                4 -> {
                    PrivacyPolicyFormView({
                        step.value += 1
                    })
                }

                5 -> {
                    ProfileCompletedView {
                        coroutineScope.launch {
                            Log.d("STARTED", "STARTED")
                            val uid = registrationDataState.value.createFirebaseUser(authProvider)
                            registrationDataState.value.createEmptyUserData(Firebase.firestore, uid!!)
                            Log.d("FINISHED", "FINISHED")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun RegisterCompletionViewPreview() {
    PillAssistantTheme {
        Surface {
            RegisterCompletionView(navController = rememberNavController(), registrationData = UserRegistrationData("blank", "blank"), authProvider = FirebaseAuth.getInstance())
        }
    }
}
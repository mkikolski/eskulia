package pl.mkikolski.mojacodziennatabletka.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Facebook
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledButtonFullWidth
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledDivider
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledOutlinedIconButtonBackground
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledTextField
import pl.mkikolski.mojacodziennatabletka.ui.components.Title
import pl.mkikolski.mojacodziennatabletka.ui.theme.PillAssistantTheme
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily

@Composable
fun RegisterView(
    navController: NavController
) {
    var email = rememberSaveable { mutableStateOf("") }
    var password = rememberSaveable { mutableStateOf("") }
    var passwordRepeat = rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .padding(top = 64.dp)
            )
            Title(text = "Sign up for free", fontSize = 36.sp, modifier = Modifier.zIndex(2f).background(
                Color.Transparent))
            Spacer(modifier = Modifier.height(16.dp))
            StyledTextField(
                label = "E-mail address",
                value = email.value,
                onValueChange = {it -> email.value = it},
                validator = {it ->
                    !(it.length > 6 && it.contains(".") && !it.contains("@"))
                },
                errorMessage = "Invalid e-mail address",
                icon = R.drawable.baseline_alternate_email_24,
                placeholder = "Enter e-mail",
                isPassword = false
            )
            Spacer(modifier = Modifier.height(8.dp))
            StyledTextField(
                label = "Password",
                value = password.value,
                onValueChange = {it -> password.value = it},
                validator = {it ->
                    true
//                    (it.contains("[A-Z]".toRegex()) && it.length >= 8 && it.contains("[0-9]".toRegex()))
                },
                errorMessage = "Password must be at least 8 characters and contain\n- uppercase letter\n- a number",
                icon = R.drawable.baseline_fingerprint_24,
                placeholder = "Enter password",
                isPassword = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            StyledTextField(
                label = "Password confirmation",
                value = passwordRepeat.value,
                onValueChange = {it -> passwordRepeat.value = it},
                validator = {it ->
                    true
//                    (it.contains("[A-Z]".toRegex()) && it.length >= 8 && it.contains("[0-9]".toRegex()))
                },
                errorMessage = "Passwords don't match",
                icon = R.drawable.baseline_fingerprint_24,
                placeholder = "Repeat password",
                isPassword = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            StyledButtonFullWidth(
                "Continue",
                onClick = {},
                leadingIcon = null,
                trailingIcon = R.drawable.baseline_arrow_forward_24
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = "Already have an account? ",
                    fontSize = 16.sp,
                    fontFamily = jakartaFontFamily
                )
                Text(
                    text = "Sign in",
                    fontSize = 16.sp,
                    fontFamily = jakartaFontFamily,
                    color = Color(0xFF0020EE),
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
fun RegisterViewPreview() {
    PillAssistantTheme {
        Surface {
            RegisterView(navController = rememberNavController())
        }
    }
}
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
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.GMobiledata
import androidx.compose.material.icons.outlined.Facebook
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledButtonFullWidth
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledDivider
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledIconButtonBackground
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledOutlinedIconButtonBackground
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledTextField
import pl.mkikolski.mojacodziennatabletka.ui.components.Title
import pl.mkikolski.mojacodziennatabletka.ui.theme.PillAssistantTheme
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily


@Composable
fun LoginView(

) {
    var email = rememberSaveable { mutableStateOf("")}
    var password = rememberSaveable { mutableStateOf("") }

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
            Title(text = "Log in to\n\nPill assistant", fontSize = 36.sp, modifier = Modifier.zIndex(2f).background(Color.Transparent))
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
            Spacer(modifier = Modifier.height(16.dp))
            StyledButtonFullWidth(
                "Sign in",
                onClick = {},
                leadingIcon = null,
                trailingIcon = R.drawable.baseline_arrow_forward_24
            )
            Spacer(modifier = Modifier.height(16.dp))
            StyledDivider("or", modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                StyledOutlinedIconButtonBackground(
                    R.drawable.google_icon,
                    onClick = {},
                    size = 72.dp,
                    colorEnabled = Color(0xFFD3D3D3),
                    colorDisabled = Color(0xFFF3A3A3)
                )

                StyledOutlinedIconButtonBackground(
                    Icons.Outlined.Facebook,
                    onClick = {},
                    size = 72.dp,
                    colorEnabled = Color(0xFFD3D3D3),
                    colorDisabled = Color(0xFFF3A3A3)
                )
            }
            Spacer(Modifier.height(32.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = "Don't have an account? ",
                    fontSize = 16.sp,
                    fontFamily = jakartaFontFamily
                )
                Text(
                    text = "Sign up",
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
fun LoginViewPreview() {
    PillAssistantTheme {
        Surface {
            LoginView()
        }
    }
}
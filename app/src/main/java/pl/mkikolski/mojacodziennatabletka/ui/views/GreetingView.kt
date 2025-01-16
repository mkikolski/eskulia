package pl.mkikolski.mojacodziennatabletka.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import pl.mkikolski.mojacodziennatabletka.ui.components.Title
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily

@Composable
fun GreetingView(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
//            .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(96.dp)
                    .padding(top = 48.dp)
            )
            Title(text = "Welcome in\nPill Assistant!", fontSize = 36.sp, modifier = Modifier.zIndex(2f).background(Color.Transparent))
            Image(
                painter = painterResource(id = R.drawable.cover_art),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
//                .offset(y = (-64).dp)
                    .zIndex(1f)
                    .fillMaxWidth()
//                .height(1400.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp) // Adjust the height to control the fade area
                .align(Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.White, Color.Transparent),
                        startY = Float.POSITIVE_INFINITY,
                        endY = 0f
                    )
                )
                .zIndex(2f)
        )
        Column(
            modifier = Modifier.padding(start = 48.dp, end = 48.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-100).dp)
                .zIndex(9999f)
        ) {
            StyledButtonFullWidth(text = "Get started", onClick = {navController.navigate("welcome_stepper_view")}, leadingIcon = null, trailingIcon = R.drawable.baseline_arrow_forward_24, enabledColor = Color.Black, disabledColor = Color.Gray, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 16.dp))
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
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { navController.navigate("login_view") }
                )
            }
        }

    }

}

@Preview
@Composable
fun GreetingViewPreview() {
    Surface {
        GreetingView(navController = rememberNavController())
    }
}
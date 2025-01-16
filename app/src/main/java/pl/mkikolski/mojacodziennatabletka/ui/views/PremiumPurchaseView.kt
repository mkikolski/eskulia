package pl.mkikolski.mojacodziennatabletka.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.ui.components.BipolarSwitch
import pl.mkikolski.mojacodziennatabletka.ui.components.SelectBox
import pl.mkikolski.mojacodziennatabletka.ui.components.SingularSelect
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledButtonFullWidth
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledIconButtonBackground
import pl.mkikolski.mojacodziennatabletka.ui.components.Title
import pl.mkikolski.mojacodziennatabletka.ui.theme.BackgroundColor
import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueActive
import pl.mkikolski.mojacodziennatabletka.ui.theme.DarkGrayInactive
import pl.mkikolski.mojacodziennatabletka.ui.theme.LightGrayInactive
import pl.mkikolski.mojacodziennatabletka.ui.theme.PillAssistantTheme
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily

//TODO: Redo switch to be MaterialTheme.shapes.medium instead of stock and have square thumper
//TODO: Add "no thank you" button
//TODO: Add followup screen with payment options and rules
@Composable
fun PremiumPurchaseView(
    navController: NavController
) {
    var selectedYearly = rememberSaveable { mutableStateOf(false) }
    var selectedOption = rememberSaveable { mutableStateOf("Pro") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                painter = painterResource(id = R.drawable.cover_5),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
            )
            Spacer(modifier = Modifier.height(18.dp))
            Title(
                text = "Eskulia Pro",
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Get rid of ads, unlock all features and support the development of the application.",
                fontSize = 18.sp,
                fontFamily = jakartaFontFamily,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row{
                Icon(
                    painter = painterResource(id = R.drawable.baseline_signal_cellular_4_bar_24),
                    contentDescription = null,
                    tint = BlueActive,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Unlimited AI tokens",
                    fontSize = 14.sp,
                    fontFamily = jakartaFontFamily,
                    color = DarkGrayInactive
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    painter = painterResource(id = R.drawable.baseline_star_24),
                    contentDescription = null,
                    tint = BlueActive,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "No ads",
                    fontSize = 14.sp,
                    fontFamily = jakartaFontFamily,
                    color = DarkGrayInactive
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            BipolarSwitch(
                "Monthly",
                "Yearly",
                selectedYearly.value,
                onSwitch = {
                    selectedYearly.value = !selectedYearly.value
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            SingularSelect(
                listOf("Pro", "User+"),
                listOf("Best value", null),
                listOf("29.99€/yr", "19.99€/yr"),
                selected = selectedOption.value,
                onSelected = {
                    selectedOption.value = it
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            StyledButtonFullWidth(
                text = "Go pro",
                onClick = {},
                disabled = false,
                trailingIcon = R.drawable.baseline_star_24,
                leadingIcon = null
            )
        }
    }
}

@Preview
@Composable
fun PremiumPurchaseViewPreview() {
    PillAssistantTheme {
        Surface(Modifier.background(BackgroundColor)) {
            PremiumPurchaseView(navController = rememberNavController())
        }
    }
}
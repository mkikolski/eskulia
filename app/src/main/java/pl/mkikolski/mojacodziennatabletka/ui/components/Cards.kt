package pl.mkikolski.mojacodziennatabletka.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ChipDefaults
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.ui.theme.AmbientShadowLight
import pl.mkikolski.mojacodziennatabletka.ui.theme.BackgroundColor
import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueActive
import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueTintColorMatrix
import pl.mkikolski.mojacodziennatabletka.ui.theme.DarkGrayInactive
import pl.mkikolski.mojacodziennatabletka.ui.theme.LightGrayInactive
import pl.mkikolski.mojacodziennatabletka.ui.theme.PillAssistantTheme
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily

@Composable
fun TopCutCard(
    cornerRadius: Dp = 16.dp,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
//            .background(Color.White, shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = cornerRadius, bottomEnd = cornerRadius))
//            .clip(RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = cornerRadius, bottomEnd = cornerRadius))
            .shadow(
                30.dp,
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = cornerRadius,
                    bottomEnd = cornerRadius
                ),
                spotColor = Color.Transparent,
                ambientColor = AmbientShadowLight
            )
            .padding(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = cornerRadius, bottomEnd = cornerRadius),
    ) {
        content()
    }
}

@Composable
fun HeaderUserCard(
    title: String
) {
    TopCutCard(cornerRadius = 36.dp) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.avatar_1),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(32.dp),
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix(BlueTintColorMatrix))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Title(text = title, fontSize = 24.sp, textAlign = TextAlign.Start)
                Spacer(modifier = Modifier.weight(1f))
                StyledIconButtonBackground(
                    icon = R.drawable.baseline_add_24,
                    onClick = {},
                    size = 64.dp,
                    colorEnabled = BlueActive,
                    contentColor = Color.White
                )
//                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun ChatHeader(title: String, modifier: Modifier = Modifier) {
    TopCutCard(cornerRadius = 36.dp, modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                StyledIconButtonBackground(
                    icon = R.drawable.baseline_arrow_back_24,
                    onClick = {},
                    size = 56.dp,
                    colorEnabled = LightGrayInactive,
                    contentColor = Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Title(text = title, fontSize = 24.sp, textAlign = TextAlign.Start)
            }
        }
    }
}

@Composable
fun UserCardContent(
    username: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar_1),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(64.dp),
                colorFilter = ColorFilter.colorMatrix(ColorMatrix(BlueTintColorMatrix))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Title(text = "Hi, ${username}!", fontSize = 22.sp, textAlign = TextAlign.Start)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "Eskulia Basic user", fontSize = 14.sp, color = Color.Gray, fontFamily = jakartaFontFamily)
            }
            Spacer(modifier = Modifier.weight(1f))
            StyledOutlinedIconButtonBackground(
                icon = R.drawable.baseline_notifications_24,
                onClick = {},
                size = 56.dp,
                colorEnabled = Color.Gray
            )
        }
    }
}

@Composable
fun MedicationCard(
    medicationName: String,
    medicationActiveSubstance: String,
    medicationDosage: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .shadow(
                30.dp,
                MaterialTheme.shapes.medium,
                spotColor = Color.Transparent,
                ambientColor = AmbientShadowLight
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(LightGrayInactive)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_medication_24),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    tint = DarkGrayInactive
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Title(text = medicationName, fontSize = 18.sp, textAlign = TextAlign.Start)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "${medicationName}, ${medicationDosage}", fontSize = 14.sp, color = Color.Gray, fontFamily = jakartaFontFamily)
            }
            Spacer(modifier = Modifier.weight(1f))
            StyledOutlinedIconButtonBackground(
                icon = R.drawable.baseline_edit_note_24,
                onClick = {},
                size = 56.dp,
                colorEnabled = DarkGrayInactive
            )
        }
    }
}

@Composable
fun BlogPostCard() {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .background(Color.Transparent)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .background(Color.White)
            ) {
                AsyncImage(
                    model = "https://images.pexels.com/photos/40568/medical-appointment-doctor-healthcare-40568.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                    contentDescription = "Blog post image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                MaterialTheme.shapes.medium.topStart,
                                MaterialTheme.shapes.medium.topEnd,
                                CornerSize(0.dp),
                                CornerSize(0.dp)
                            )
                        )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column {
                    Title(
                        text = "Safe usage of statins",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_person_24),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = DarkGrayInactive
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Dr. John Doe",
                            fontSize = 16.sp,
                            color = DarkGrayInactive,
                            fontFamily = jakartaFontFamily
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = DarkGrayInactive
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "11.12.2024",
                            fontSize = 16.sp,
                            color = DarkGrayInactive,
                            fontFamily = jakartaFontFamily
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Statins are a class of drugs often prescribed by doctors to help lower cholesterol levels in the blood. By lowering the levels, they help prevent heart attacks and stroke. Studies show that, in certain people, statins reduce the risk of heart attack, stroke, and even death from heart disease by about 25-35%. Statins are effective, but they are not for everyone. Some people appear to benefit more from statins than others. Your doctor can determine if you are at risk for heart disease and if statins are a good option for you.".substring(
                            0..80
                        ) + "...",
                        fontSize = 16.sp,
                        color = DarkGrayInactive,
                        fontFamily = jakartaFontFamily,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
            TextNavButton(
                text = "More",
                onClick = {},
                disabled = false,
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.BottomEnd)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(
                        RoundedCornerShape(
                            MaterialTheme.shapes.medium.topStart,
                            MaterialTheme.shapes.medium.topEnd,
                            CornerSize(0.dp),
                            CornerSize(0.dp),
                        )
                    )
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.4f),
                                Color.Transparent
                            )
                        ),
                        shape = RoundedCornerShape(
                            MaterialTheme.shapes.medium.topStart,
                            MaterialTheme.shapes.medium.topEnd,
                            CornerSize(0.dp),
                            CornerSize(0.dp),
                        )
                    )
                    .align(Alignment.TopCenter)
            )
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopStart)
            ) {
                AssistChip(
                    label = {
                        Text(
                            text = "Health",
                            fontSize = 14.sp,
                            fontFamily = jakartaFontFamily
                        )
                    },
                    onClick = {},
                    modifier = Modifier.padding(4.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = BlueActive,
                        labelColor = Color.White
                    )
                )
                AssistChip(
                    label = {
                        Text(
                            text = "Cholesterol",
                            fontSize = 14.sp,
                            fontFamily = jakartaFontFamily
                        )
                    },
                    onClick = {},
                    modifier = Modifier.padding(4.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = Color.White,
                        labelColor = DarkGrayInactive
                    )
                )
            }
        }
    }
}

@Composable
fun UserCard(
    username: String
) {
    TopCutCard(cornerRadius = 48.dp) {
        UserCardContent(username = username)
    }
}

@Preview
@Composable
fun BlogPostCardPreview() {
    PillAssistantTheme {
        Surface(
            Modifier
                .background(BackgroundColor)
        ) {
            BlogPostCard()
        }
    }
}

@Preview
@Composable
fun TopCutCardPreview(){
    PillAssistantTheme {
        Surface(
            Modifier
                .background(BackgroundColor)
                .padding(0.dp)) {
            TopCutCard(cornerRadius = 50.dp) {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Preview
@Composable
fun UserCardContentPreview(){
    PillAssistantTheme {
        Surface(
            Modifier
                .background(BackgroundColor)
                .padding(0.dp)) {
            UserCardContent(username = "John Doe")
        }
    }
}

@Preview
@Composable
fun UserCardPreview(){
    PillAssistantTheme {
        Surface(Modifier.background(BackgroundColor)) {
            TopCutCard(cornerRadius = 48.dp) {
                UserCardContent(username = "John Doe")
            }
        }
    }
}
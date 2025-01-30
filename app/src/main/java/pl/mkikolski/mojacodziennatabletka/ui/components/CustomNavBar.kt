package pl.mkikolski.mojacodziennatabletka.ui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pl.mkikolski.mojacodziennatabletka.ui.theme.AmbientShadowLight
import pl.mkikolski.mojacodziennatabletka.ui.theme.BackgroundColor
import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueActive
import pl.mkikolski.mojacodziennatabletka.ui.theme.DarkGrayInactive
import pl.mkikolski.mojacodziennatabletka.ui.theme.PillAssistantTheme


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CustomNavBar(
    navController: NavHostController = rememberNavController(),
    screen: @Composable (PaddingValues) -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(64.dp)
                    .clip(RoundedCornerShape(18.dp, 18.dp, 0.dp, 0.dp))
                    .shadow(60.dp, MaterialTheme.shapes.medium, spotColor = Color.Transparent, ambientColor = AmbientShadowLight),
                cutoutShape = RoundedCornerShape(18.dp, 18.dp, 18.dp, 18.dp),
                backgroundColor = Color.White,
                elevation = 22.dp
            ) {
                BottomNavigation(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .fillMaxWidth(),
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp
                ) {
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Home",
                                modifier = Modifier.width(35.dp).height(35.dp)
                            )
                        },
                        selected = true,
                        onClick = { navController.navigate("home") },
                        selectedContentColor = BlueActive,
                        unselectedContentColor = DarkGrayInactive
                    )
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Medication,
                                contentDescription = "Medication list",
                                modifier = Modifier.width(35.dp).height(35.dp)
                            )
                        },
                        selected = false,
                        onClick = { navController.navigate("medicines") },
                        selectedContentColor = BlueActive,
                        unselectedContentColor = DarkGrayInactive
                    )
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Medication,
                                contentDescription = "Medication list",
                                tint = Color.Transparent,
                                modifier = Modifier.width(35.dp).height(35.dp)
                            )
                        },
                        selectedContentColor = Color.Transparent,
                        unselectedContentColor = Color.Transparent,
                        selected = false,
                        onClick = {}
                    )
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.ChatBubble,
                                contentDescription = "AI Chat",
                                modifier = Modifier.width(35.dp).height(35.dp)
                            )
                        },
                        selected = false,
                        onClick = { navController.navigate("chat") },
                        selectedContentColor = BlueActive,
                        unselectedContentColor = DarkGrayInactive
                    )
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Settings",
                                modifier = Modifier.width(35.dp).height(35.dp)
                            )
                        },
                        selected = false,
                        onClick = { Log.d("CustomNavBar", "Settings clicked") },
                        selectedContentColor = BlueActive,
                        unselectedContentColor = DarkGrayInactive
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        backgroundColor = BackgroundColor,
        floatingActionButton = {
            FloatingActionButton(
                shape = MaterialTheme.shapes.medium,
                onClick = {},
                contentColor = Color.White,
                modifier = Modifier.width(56.dp).height(56.dp),
                backgroundColor = BlueActive,
            ) {
                Icon(imageVector = Icons.Filled.CameraAlt, contentDescription = "Add icon")
            }
        },
        content = screen
    )
}

@SuppressLint("RememberReturnType")
@Composable
fun NavbarShape(): Shape {
    return GenericShape { size, _ ->
        val path = Path()

        // Canvas dimensions
        val width = size.width
        val height = size.height

        // Normalized vertices (-1 to 1)
        val vertices = listOf(
            Pair(-1f, 0f),  // Start at the left bottom
            Pair(-1f, 1f),  // Top-left corner
            Pair(-0.8f, 1f), // Smooth transition
            Pair(-0.3f, 1f), // Transition towards the middle
            Pair(-0.2f, 0.8f), // Left of the FAB cutout
            Pair(-0.1f, 0f),  // Bottom-left of FAB cutout
            Pair(0.1f, 0f),   // Bottom-right of FAB cutout
            Pair(0.2f, 0.8f), // Right of the FAB cutout
            Pair(0.3f, 1f),   // Transition back to the main path
            Pair(0.8f, 1f),   // Right upper edge
            Pair(1f, 1f),     // Top-right corner
            Pair(1f, 0f),     // Right bottom
            Pair(1f, -0.5f),  // Bottom right extra
            Pair(-1f, -0.5f)  // Bottom left extra
        )

        // Scale and flip Y-axis (to match the canvas coordinate system)
        val scaledVertices = vertices.map { (x, y) ->
            Pair(
                (x + 1) / 2 * width,  // Scale X: [-1, 1] -> [0, width]
                (1 - y) / 2 * height  // Scale Y: [-1, 1] -> [0, height]
            )
        }

        // Start the path
        val (startX, startY) = scaledVertices.first()
        path.moveTo(startX, startY)

        // Draw the shape with smooth curves
        for (i in 1 until scaledVertices.size) {
            val (currentX, currentY) = scaledVertices[i]
            val (prevX, prevY) = scaledVertices[i - 1]

            // Add a curve or line based on position
            if (i in listOf(4, 7)) { // Smooth curves at FAB cutout edges
                path.quadraticTo(
                    (prevX + currentX) / 2, (prevY + currentY) / 2, // Control point (midpoint)
                    currentX, currentY // End point
                )
            } else {
                path.lineTo(currentX, currentY)
            }
        }

        // Close the path to complete the shape
        path.close()

        // Add path to the shape
        addPath(path)
    }
}


@Preview
@Composable
fun CustomNavBarPreview() {
    PillAssistantTheme {
        Surface {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                CustomNavBar(){
                    Text("Content")
                }
            }

        }
    }
}
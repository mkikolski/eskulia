package pl.mkikolski.mojacodziennatabletka.ui.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.data.UserViewModel
import pl.mkikolski.mojacodziennatabletka.ui.components.CustomNavBar
import pl.mkikolski.mojacodziennatabletka.ui.components.MarkdownScrollableColumn
import pl.mkikolski.mojacodziennatabletka.ui.components.MedicationCard
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledButtonFullWidth
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledIconButtonBackground
import pl.mkikolski.mojacodziennatabletka.ui.components.StyledTextField
import pl.mkikolski.mojacodziennatabletka.ui.components.Title
import pl.mkikolski.mojacodziennatabletka.ui.theme.AmbientShadowLight
import pl.mkikolski.mojacodziennatabletka.ui.theme.BackgroundColor
import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueActive
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//TODO: Add validation
//TODO: Add option to pass arguments from other "add medicine" screens via navigation
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun BaseMedicineAddView(
    viewModel: UserViewModel,
    navController: NavHostController
) {
    val medications by viewModel.userMedicationsState.collectAsState()

    var medicationName by rememberSaveable { mutableStateOf("") }
    var activeSubstance by rememberSaveable { mutableStateOf("") }
    var medicationDose by rememberSaveable { mutableStateOf("") }
    var remainingDoses by rememberSaveable { mutableStateOf(0) }
    var totalDoses by rememberSaveable { mutableStateOf(0) }
    var sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    var coroutineScope = rememberCoroutineScope()
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let { convertMillisToDate(it) } ?: ""
    var expiryDate by rememberSaveable { mutableStateOf("12.12.2024") }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
            ) {
                DatePicker(
                    state = datePickerState,
                    modifier = Modifier.fillMaxWidth(),
                    colors = DatePickerDefaults.colors(
                        containerColor = Color.White,
                    )
                )
                Spacer(modifier = Modifier.height(32.dp))
                StyledButtonFullWidth(
                    text = "Select date",
                    onClick = {
                        coroutineScope.launch {
                            sheetState.hide()
                        }
                    },
                    modifier = Modifier.padding(top = 32.dp, bottom = 16.dp).align(Alignment.BottomCenter),
                    leadingIcon = null,
                    trailingIcon = R.drawable.baseline_arrow_forward_24,
                )
            }
        },
        sheetShape = MaterialTheme.shapes.medium,
        sheetBackgroundColor = Color.White
    ) {

        CustomNavBar(navController) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .padding(0.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomStart = 16.dp,
                                bottomEnd = 16.dp
                            )
                        )
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomStart = 16.dp,
                                bottomEnd = 16.dp
                            )
                        )
                        .shadow(
                            16.dp,
                            RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomStart = 16.dp,
                                bottomEnd = 16.dp
                            ),
                            spotColor = Color.Transparent,
                            ambientColor = AmbientShadowLight
                        )
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    StyledIconButtonBackground(
                        icon = R.drawable.baseline_arrow_back_24,
                        size = 72.dp,
                        colorEnabled = Color(0xFFD3D3D3),
                        contentColor = Color.Black,
                        onClick = {
                            navController.navigate("medicines")
                        }
                    )
                    Title("Add medication", fontSize = 20.sp, textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    StyledTextField(
                        label = "Medication name",
                        value = medicationName,
                        onValueChange = { it -> medicationName = it },
                        validator = { it ->
                            true
//                    (it.contains("[A-Z]".toRegex()) && it.length >= 8 && it.contains("[0-9]".toRegex()))
                        },
                        errorMessage = "",
                        icon = R.drawable.baseline_medication_24,
                        placeholder = "Enter medication name",
                        isPassword = false
                    )
//                Spacer(modifier = Modifier.height(8.dp))
                    StyledTextField(
                        label = "Active substance",
                        value = activeSubstance,
                        onValueChange = { it -> activeSubstance = it },
                        validator = { it ->
                            true
//                    (it.contains("[A-Z]".toRegex()) && it.length >= 8 && it.contains("[0-9]".toRegex()))
                        },
                        errorMessage = "",
                        icon = R.drawable.baseline_medication_24,
                        placeholder = "Enter active substance",
                        isPassword = false
                    )
                    StyledTextField(
                        label = "Dose",
                        value = medicationDose,
                        onValueChange = { it -> medicationDose = it },
                        validator = { it ->
                            true
//                    (it.contains("[A-Z]".toRegex()) && it.length >= 8 && it.contains("[0-9]".toRegex()))
                        },
                        errorMessage = "",
                        icon = R.drawable.baseline_medication_24, //TODO: change icon
                        placeholder = "Enter active substance content in dose",
                        isPassword = false
                    )

                    StyledTextField( //TODO: Change to number picker
                        label = "Remaining doses",
                        value = remainingDoses.toString(),
                        onValueChange = { it -> remainingDoses = it.toIntOrNull() ?: 0 },
                        validator = { it ->
                            true
//                    (it.contains("[A-Z]".toRegex()) && it.length >= 8 && it.contains("[0-9]".toRegex()))
                        },
                        errorMessage = "",
                        icon = R.drawable.baseline_medication_24, //TODO: change icon
                        placeholder = "How many doses are left?",
                        isPassword = false,
                        isNumeric = true
                    )
                    StyledTextField(
                        label = "Total doses",
                        value = totalDoses.toString(),
                        onValueChange = { it -> totalDoses = it.toIntOrNull() ?: 0 },
                        validator = { it ->
                            true
//                    (it.contains("[A-Z]".toRegex()) && it.length >= 8 && it.contains("[0-9]".toRegex()))
                        },
                        errorMessage = "",
                        icon = R.drawable.baseline_medication_24, //TODO: change icon
                        placeholder = "How many doses are in total?",
                        isPassword = false,
                        isNumeric = true
                    )
                    StyledTextField( //TODO: Add working date picker
                        label = "Expiry date",
                        value = selectedDate,
                        onValueChange = { },
                        validator = { it ->
                            true
//                    (it.contains("[A-Z]".toRegex()) && it.length >= 8 && it.contains("[0-9]".toRegex()))
                        },
                        errorMessage = "",
                        icon = R.drawable.baseline_calendar_month_24,
                        placeholder = "Select expiry date",
                        isPassword = false,
                        readOnly = true,
                        modifier = Modifier
                            .clickable {
                                coroutineScope.launch {
                                    Log.d("BaseMedicineAddView", "Clicked on date picker")
                                    sheetState.show()
                                }
                            }
                    )

                    StyledButtonFullWidth(
                        "Add medication",
                        onClick = {
//                        viewModel.addMedication(
//                            medicationName,
//                            activeSubstance,
//                            medicationDose,
//                            remainingDoses,
//                            totalDoses,
//                            expiryDate
//                        )
                            navController.navigate("home")
                        },
                        leadingIcon = null,
                        trailingIcon = R.drawable.baseline_arrow_forward_24
                    )

                }
            }
        }
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}
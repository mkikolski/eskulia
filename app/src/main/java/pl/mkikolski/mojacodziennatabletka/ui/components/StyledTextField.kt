package pl.mkikolski.mojacodziennatabletka.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.ui.theme.PillAssistantTheme
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.unit.Dp
import pl.mkikolski.mojacodziennatabletka.ui.theme.AmbientShadowLight
import pl.mkikolski.mojacodziennatabletka.ui.theme.BackgroundColor
import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueActive
import pl.mkikolski.mojacodziennatabletka.ui.theme.DarkGrayInactive
import pl.mkikolski.mojacodziennatabletka.ui.theme.LightRedError
import pl.mkikolski.mojacodziennatabletka.ui.theme.RedError

@Composable
fun StyledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    validator: (String) -> Boolean, // returns false if input is invalid
    errorMessage: String, // error message to display below the field
    icon: Int,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    isPassword: Boolean = false, // set to true if it's a field that should be masked,
    paddingTop: Dp = 8.dp,
    paddingBottom: Dp = 8.dp,
    paddingLeft: Dp = 16.dp,
    paddingRight: Dp = 16.dp,
    isNumeric: Boolean = false
    ) {
    var isFocused = remember{ mutableStateOf(false) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(value = value,
        onValueChange = onValueChange,
        label = { Text(
            label,
            fontFamily = jakartaFontFamily,
            fontWeight = FontWeight.Bold,
        ) },
        placeholder = { Text(
            placeholder,
            fontFamily = jakartaFontFamily,
            fontWeight = FontWeight.Normal,
        ) },
        singleLine = true,
        textStyle = TextStyle(
            fontFamily = jakartaFontFamily,
            fontWeight = FontWeight.Normal,
        ),
        leadingIcon = { if (icon != 0) Icon(imageVector = ImageVector.vectorResource(icon), contentDescription = null)},
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = BlueActive,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLeadingIconColor = BlueActive,
            focusedLabelColor = BlueActive,
            unfocusedLabelColor = DarkGrayInactive,
            errorLabelColor = RedError,
            errorIndicatorColor = RedError,
            errorContainerColor = LightRedError,
        ),
        isError = !validator(value),
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) KeyboardType.Password else if (isNumeric) KeyboardType.Number else KeyboardType.Text
        ),
        visualTransformation = if (!passwordVisible && isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Please provide localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(imageVector  = image, description)
                }
            }
        },
        modifier = modifier
            .padding(
                start = paddingLeft,
                top = paddingTop,
                end = paddingRight,
                bottom = paddingBottom
            )
            .shadow(
                30.dp,
                MaterialTheme.shapes.medium,
                spotColor = Color.Transparent,
                ambientColor = AmbientShadowLight
            )
            .fillMaxWidth()
            .onFocusChanged {
                isFocused.value = it.hasFocus
                Log.d("StyledTextField", "isFocused: ${isFocused.value}")
            },
        shape = MaterialTheme.shapes.medium
    )
}

@Composable
fun StyledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    validator: (String) -> Boolean, // returns false if input is invalid
    errorMessage: String, // error message to display below the field
    icon: ImageVector,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    isPassword: Boolean = false, // set to true if it's a field that should be masked,
    paddingTop: Dp = 8.dp,
    paddingBottom: Dp = 8.dp,
    paddingLeft: Dp = 16.dp,
    paddingRight: Dp = 16.dp,
    isNumeric: Boolean = false
) {
    var isFocused = remember{ mutableStateOf(false) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(value = value,
        onValueChange = onValueChange,
        label = { Text(
            label,
            fontFamily = jakartaFontFamily,
            fontWeight = FontWeight.Bold,
        ) },
        placeholder = { Text(
            placeholder,
            fontFamily = jakartaFontFamily,
            fontWeight = FontWeight.Normal,
        ) },
        singleLine = true,
        textStyle = TextStyle(
            fontFamily = jakartaFontFamily,
            fontWeight = FontWeight.Normal,
        ),
        leadingIcon = { Icon(icon, null) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = BlueActive,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLeadingIconColor = BlueActive,
            focusedLabelColor = BlueActive,
            unfocusedLabelColor = DarkGrayInactive,
            errorLabelColor = RedError,
            errorIndicatorColor = RedError,
            errorContainerColor = LightRedError,
        ),
        isError = !validator(value),
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) KeyboardType.Password else if (isNumeric) KeyboardType.Number else KeyboardType.Text
        ),
        visualTransformation = if (!passwordVisible && isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Please provide localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(imageVector  = image, description)
                }
            }
        },
        modifier = modifier
            .padding(
                start = paddingLeft,
                top = paddingTop,
                end = paddingRight,
                bottom = paddingBottom
            )
            .shadow(
                30.dp,
                MaterialTheme.shapes.medium,
                spotColor = Color.Transparent,
                ambientColor = AmbientShadowLight
            )
            .fillMaxWidth()
            .onFocusChanged {
                isFocused.value = it.hasFocus
                Log.d("StyledTextField", "isFocused: ${isFocused.value}")
            },
        shape = MaterialTheme.shapes.medium
    )
}

@Preview
@Composable
fun StyledTextFieldPreview() {
    PillAssistantTheme {
        Surface (
           color = BackgroundColor
        ) {
            var value = remember{ mutableStateOf("")}

            fun validator(checkedText: String) : Boolean {
                return !checkedText.contains("@")
            }
            Column {
                StyledTextField(
                    label = "Label",
                    value = value.value,
                    onValueChange = {value.value = it},
                    validator = { validator(value.value) },
                    errorMessage = "Error",
                    icon = R.drawable.ic_android_black_24dp,
                    placeholder = "Placeholder"
                )

                StyledTextField(
                    label = "Label",
                    value = value.value,
                    onValueChange = {value.value = it},
                    validator = { validator(value.value) },
                    errorMessage = "Error",
                    icon = R.drawable.ic_android_black_24dp,
                    placeholder = "Placeholder",
                    isPassword = true
                )
            }
        }
    }
}
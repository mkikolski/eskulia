package pl.mkikolski.mojacodziennatabletka.ui.components

import androidx.annotation.Dimension
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mkikolski.mojacodziennatabletka.R
import pl.mkikolski.mojacodziennatabletka.ui.theme.BackgroundColor
import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueActive
import pl.mkikolski.mojacodziennatabletka.ui.theme.BlueInactive
import pl.mkikolski.mojacodziennatabletka.ui.theme.DarkGrayInactive
import pl.mkikolski.mojacodziennatabletka.ui.theme.LightGrayInactive
import pl.mkikolski.mojacodziennatabletka.ui.theme.PillAssistantTheme
import pl.mkikolski.mojacodziennatabletka.ui.theme.jakartaFontFamily
import java.util.Locale

/**
 * A composable function that displays a switch with two labels.
 *
 * @param label1 The label for the first option.
 * @param label2 The label for the second option.
 * @param selected A boolean indicating if the second option is selected.
 * @param onSwitch The callback to be invoked when the switch is toggled.
 */
@Composable
fun BipolarSwitch(
    label1: String,
    label2: String,
    selected: Boolean,
    onSwitch: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = label1,
            fontFamily = jakartaFontFamily,
            color = if (!selected) BlueActive else DarkGrayInactive,
            fontWeight = if (!selected) FontWeight.Bold else FontWeight.Normal,
            textAlign = TextAlign.End,
            fontSize = 18.sp,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .clickable { onSwitch(false) }
        )
        Switch(
            checked = selected,
            onCheckedChange = { onSwitch(it) },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = BlueActive,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = LightGrayInactive
            ),
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )
        Text(
            text = label2,
            fontFamily = jakartaFontFamily,
            color = if (selected) BlueActive else DarkGrayInactive,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            fontSize = 18.sp,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .clickable { onSwitch(false) }
        )
    }
}


/**
 * A composable function that displays a text navigation button.
 *
 * @param text The text to be displayed on the button.
 * @param onClick The callback to be invoked when the button is clicked.
 * @param disabled A boolean indicating if the button is disabled.
 * @param modifier The modifier to be applied to the button.
 */
@Composable
fun TextNavButton(
    text: String,
    onClick: () -> Unit,
    disabled: Boolean,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = !disabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            contentColor = BlueActive,
            disabledContentColor = DarkGrayInactive
        ),
        modifier = modifier
            .width(IntrinsicSize.Min)
            .height(IntrinsicSize.Min)
    ) {
        Text(
            text = text.uppercase(Locale.getDefault()),
            fontFamily = jakartaFontFamily,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
        )
    }
}

/**
 * A composable function that displays a full-width styled button.
 *
 * @param text The text to be displayed on the button.
 * @param onClick The callback to be invoked when the button is clicked.
 * @param leadingIcon The resource ID of the leading icon, or null if there is no leading icon.
 * @param trailingIcon The resource ID of the trailing icon, or null if there is no trailing icon.
 * @param modifier The modifier to be applied to the button.
 * @param disabled A boolean indicating if the button is disabled.
 * @param enabledColor The color of the button when enabled.
 * @param disabledColor The color of the button when disabled.
 */
@Composable
fun StyledButtonFullWidth(
    text: String,
    onClick: () -> Unit,
    leadingIcon: Int?, // pass R.drawable.xxx or null if there shouldn't be an icon
    trailingIcon: Int?,
    modifier: Modifier = Modifier,
    disabled: Boolean = false,
    enabledColor: Color = BlueActive,
    disabledColor: Color = BlueInactive
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = enabledColor,
            disabledContainerColor = disabledColor,
            contentColor = Color.White,
            disabledContentColor = LightGrayInactive
        ),
        shape = MaterialTheme.shapes.medium,
        enabled = !disabled
    ) {
        if (leadingIcon != null) {
            Icon(imageVector = ImageVector.vectorResource(leadingIcon),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Text(
            text,
            fontFamily = jakartaFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(8.dp)
        )
        if (trailingIcon != null) {
            Icon(imageVector = ImageVector.vectorResource(trailingIcon),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

/**
 * A composable function that displays a full-width styled outlined button.
 *
 * @param text The text to be displayed on the button.
 * @param onClick The callback to be invoked when the button is clicked.
 * @param leadingIcon The resource ID of the leading icon, or null if there is no leading icon.
 * @param trailingIcon The resource ID of the trailing icon, or null if there is no trailing icon.
 * @param modifier The modifier to be applied to the button.
 * @param disabled A boolean indicating if the button is disabled.
 */
@Composable
fun StyledOutlineButtonFullWidth(
    text: String,
    onClick: () -> Unit,
    leadingIcon: Int?, // pass R.drawable.xxx or null if there shouldn't be an icon
    trailingIcon: Int?,
    modifier: Modifier = Modifier,
    disabled: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp, end = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color(0x3E0020EE),
            contentColor = Color(0xFF0020EE),
            disabledContentColor = Color(0xFF4C5696)
        ),
        border = BorderStroke(2.dp, if (!disabled) Color(0xFF0020EE) else Color(0xFF4C5696)),
        shape = MaterialTheme.shapes.medium,
        enabled = !disabled
    ) {
        if (leadingIcon != null) {
            Icon(imageVector = ImageVector.vectorResource(leadingIcon),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Text(
            text,
            fontFamily = jakartaFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(8.dp)
        )
        if (trailingIcon != null) {
            Icon(imageVector = ImageVector.vectorResource(trailingIcon),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

/**
 * A composable function that displays a styled icon button with a background.
 *
 * @param icon The resource ID of the icon to be displayed.
 * @param onClick The callback to be invoked when the button is clicked.
 * @param disabled A boolean indicating if the button is disabled.
 * @param size The size of the button.
 * @param colorEnabled The color of the button when enabled.
 * @param colorDisabled The color of the button when disabled.
 * @param contentColor The color of the icon when the button is enabled.
 * @param disabledContentColor The color of the icon when the button is disabled.
 * @param modifier The modifier to be applied to the button.
 */
@Composable
fun StyledIconButtonBackground(
    icon: Int,
    onClick: () -> Unit,
    disabled: Boolean = false,
    size: Dp,
    colorEnabled: Color = BlueActive,
    colorDisabled: Color = BlueInactive,
    contentColor: Color = Color.White,
    disabledContentColor: Color = LightGrayInactive,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(size)
            .padding(8.dp)
            .aspectRatio(1f)
            .clip(MaterialTheme.shapes.medium)
            .background(if (disabled) colorDisabled else colorEnabled)
            .padding(2.dp),
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = contentColor,
            disabledContentColor = disabledContentColor
        ),
        enabled = !disabled
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null,
            modifier = Modifier.size(size),
            tint = contentColor
        )
    }
}

/**
 * A composable function that displays a styled icon button with a background.
 *
 * @param icon The ImageVector of the icon to be displayed.
 * @param onClick The callback to be invoked when the button is clicked.
 * @param disabled A boolean indicating if the button is disabled.
 * @param size The size of the button.
 * @param colorEnabled The color of the button when enabled.
 * @param colorDisabled The color of the button when disabled.
 * @param contentColor The color of the icon when the button is enabled.
 * @param disabledContentColor The color of the icon when the button is disabled.
 * @param modifier The modifier to be applied to the button.
 */
@Composable
fun StyledIconButtonBackground(
    icon: ImageVector,
    onClick: () -> Unit,
    disabled: Boolean = false,
    size: Dp,
    colorEnabled: Color = BlueActive,
    colorDisabled: Color = BlueInactive,
    contentColor: Color = Color.White,
    disabledContentColor: Color = LightGrayInactive,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(size)
            .padding(8.dp)
            .aspectRatio(1f)
            .clip(MaterialTheme.shapes.medium)
            .background(if (disabled) colorDisabled else colorEnabled)
            .padding(2.dp),
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = contentColor,
            disabledContentColor = disabledContentColor
        ),
        enabled = !disabled
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(size),
            tint = contentColor
        )
    }
}

/**
 * A composable function that displays a styled outlined icon button with a background.
 *
 * @param icon The resource ID of the icon to be displayed.
 * @param onClick The callback to be invoked when the button is clicked.
 * @param disabled A boolean indicating if the button is disabled.
 * @param size The size of the button.
 * @param colorEnabled The color of the button when enabled.
 * @param colorDisabled The color of the button when disabled.
 * @param modifier The modifier to be applied to the button.
 */
@Composable
fun StyledOutlinedIconButtonBackground(
    icon: Int,
    onClick: () -> Unit,
    disabled: Boolean = false,
    size: Dp,
    colorEnabled: Color = BlueActive,
    colorDisabled: Color = BlueInactive,
    modifier: Modifier = Modifier
) {
    OutlinedIconButton(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .size(size)
            .padding(8.dp)
            .aspectRatio(1f)
            .clip(MaterialTheme.shapes.medium)
            .background(Color.Transparent),
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = colorEnabled,
            disabledContentColor = colorDisabled,
        ),
        border = BorderStroke(1.dp, if (disabled) colorDisabled else colorEnabled),
        enabled = !disabled
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null,
            modifier = Modifier.size(size).padding(8.dp),
            tint = colorEnabled
        )
    }
}

/**
 * A composable function that displays a styled outlined icon button with a background.
 *
 * @param icon The ImageVector of the icon to be displayed.
 * @param onClick The callback to be invoked when the button is clicked.
 * @param disabled A boolean indicating if the button is disabled.
 * @param size The size of the button.
 * @param colorEnabled The color of the button when enabled.
 * @param colorDisabled The color of the button when disabled.
 * @param modifier The modifier to be applied to the button.
 */
@Composable
fun StyledOutlinedIconButtonBackground(
    icon: ImageVector,
    onClick: () -> Unit,
    disabled: Boolean = false,
    size: Dp,
    colorEnabled: Color = BlueActive,
    colorDisabled: Color = BlueInactive,
    modifier: Modifier = Modifier
) {
    OutlinedIconButton(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .size(size)
            .padding(8.dp)
            .aspectRatio(1f)
            .clip(MaterialTheme.shapes.medium)
            .background(Color.Transparent),
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = colorEnabled,
            disabledContentColor = colorDisabled,
        ),
        border = BorderStroke(1.dp, if (disabled) colorDisabled else colorEnabled),
        enabled = !disabled
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(size).padding(8.dp),
            tint = colorEnabled
        )
    }
}

@Preview
@Composable
fun StyledButtonFullWidthPreview() {
    PillAssistantTheme {
        Surface (
            color = BackgroundColor
        ) {
            Column {
                StyledButtonFullWidth(
                    text = "Text",
                    onClick = { },
                    leadingIcon = R.drawable.ic_android_black_24dp,
                    trailingIcon = R.drawable.ic_android_black_24dp,
                    disabled = false
                )

                StyledOutlineButtonFullWidth(
                    text = "Text",
                    onClick = { },
                    leadingIcon = R.drawable.ic_android_black_24dp,
                    trailingIcon = R.drawable.ic_android_black_24dp,
                    disabled = true
                )

                StyledIconButtonBackground(icon = R.drawable.ic_android_black_24dp, onClick = {}, size = 72.dp, disabled = false)
                TextNavButton(text = "Skip", onClick = {}, disabled = true)
                TextNavButton(text = "Next", onClick = {}, disabled = false)
            }

        }
    }
}
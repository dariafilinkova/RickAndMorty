package com.example.rickandmorty.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R

@Composable
fun ArrowDropDownUp(
    isClicked: MutableState<Boolean>
) {
    val RippleRadius = 24.dp
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    Icon(
        painter = if (!isClicked.value) painterResource(id = R.drawable.baseline_arrow_drop_down_24) else painterResource(
            id = R.drawable.baseline_arrow_drop_up_24
        ),
        contentDescription = "Arrow drop down",
        modifier = Modifier.clickable(
            interactionSource = interactionSource,
            indication = rememberRipple(bounded = false, radius = RippleRadius),
            onClick = { isClicked.value = !isClicked.value })
    )
}
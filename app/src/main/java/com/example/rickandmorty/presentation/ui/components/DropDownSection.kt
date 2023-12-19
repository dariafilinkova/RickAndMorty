package com.example.rickandmorty.presentation.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.utils.ArrowDropDownUp

@Composable
fun DropDownSection(
    isClickedSection: MutableState<Boolean>,
    name: String,
    function: @Composable () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .border(
                1.dp, Color.Black,
                CutCornerShape(12.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(name, modifier = Modifier.padding(16.dp))
        Spacer(modifier = Modifier.weight(1f))
        ArrowDropDownUp(isClicked = isClickedSection)
    }
    if (isClickedSection.value) {
        function()
    }
}
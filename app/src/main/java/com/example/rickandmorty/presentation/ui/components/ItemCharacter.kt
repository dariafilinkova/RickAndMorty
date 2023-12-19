package com.example.rickandmorty.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.rickandmorty.domain.model.ItemCharacter
import com.example.rickandmorty.utils.defineColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemCharacter(
    itemCharacter: ItemCharacter,
    onItemClick: () -> Unit,
) {
    val showShimmer = remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .height(270.dp),
            shape = CutCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            onClick = {onItemClick()}
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = itemCharacter.imageUrl,
                    contentDescription = "item image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(160.dp)
                        .fillMaxWidth()
                        .background(
                            shimmerBrush(
                                targetValue = 1300f,
                                showShimmer = showShimmer.value
                            )
                        ),
                    onSuccess = {
                        showShimmer.value = false
                    },
                )
                Text(
                    text = itemCharacter.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(10.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Circle(color = defineColor(itemCharacter.status))
                    Text(
                        text = itemCharacter.status,
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }
                Text(
                    text = itemCharacter.gender,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(6.dp),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray
                )
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun ItemCharacterPreview() {
//    ItemCharacter(
//        ItemCharacter(
//            "Alien Spa",
//            "Dead",
//            "Male",
//            "https://rickandmortyapi.com/api/character/avatar/361.jpeg"
//        )
//    )
}
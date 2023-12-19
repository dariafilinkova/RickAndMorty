package com.example.rickandmorty.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.palette.graphics.Palette
import coil.request.ImageRequest
import coil.transform.Transformation
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.palette.BitmapPalette


@Composable
fun CharacterDetailImage(
    imageUrl: String,
    modifier: Modifier,
    transformations: List<Transformation> = emptyList(),
    bitPallette: (Palette) -> Unit = {}
) {
    CoilImage(
        imageRequest =
        ImageRequest
            .Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .transformations(transformations)
            .build(),
        alignment = Alignment.Center,
        loading = {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .placeholder(
                        visible = true,

                        highlight = PlaceholderHighlight.shimmer(),
                    )
            ) {
                val indicator = createRef()
                CircularProgressIndicator(
                    modifier = Modifier.constrainAs(indicator) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            }
        },
        circularReveal = CircularReveal(
            duration = 300,
        ),
        modifier = modifier,
        bitmapPalette = BitmapPalette { pallette ->
            bitPallette(pallette)
        },
    )
}
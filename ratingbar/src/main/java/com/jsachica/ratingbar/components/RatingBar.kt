package com.jsachica.ratingbar.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview

private const val maxRating = 10f

@Composable
fun RatingBar(rating: Float, modifier: Modifier = Modifier, stars: Int = 5) {
    val ratingPerStar = maxRating / stars
    var remainingRating = rating

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(end = 8.dp),
            text = "$rating",
            style = TextStyle(
                color = Color.DarkGray,
                fontSize = 12.sp
            )
        )
        for (i in 1 .. stars) {
            val starRating = when {
                remainingRating == 0f -> {
                    0f
                }
                remainingRating >= ratingPerStar -> {
                    remainingRating -= ratingPerStar
                    1f
                }
                else -> {
                    val fraction = remainingRating / ratingPerStar
                    remainingRating = 0f
                    fraction
                }
            }
            RatingStar(
                fraction = starRating,
                modifier = Modifier
                    .padding(end = if (i != stars) 2.dp else 0.dp)
                    .size(10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyRatingBarPreview() {
    RatingBar(rating = 0f)
}

@Preview(showBackground = true)
@Composable
fun PartialRatingBarPreview() {
    RatingBar(rating = 5.2f)
}

@Preview(showBackground = true)
@Composable
fun FullRatingBarPreview() {
    RatingBar(rating = 10f)
}

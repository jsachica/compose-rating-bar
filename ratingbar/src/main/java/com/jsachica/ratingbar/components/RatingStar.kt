package com.jsachica.ratingbar.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

import com.jsachica.ratingbar.extensions.addStar
import com.jsachica.ratingbar.shapes.FractionalRectangleShape

private const val strokeWidth = 1f

@Composable
fun RatingStar(
    @FloatRange(from = 0.0, to = 1.0) fraction: Float,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        FilledStar(fraction)
        EmptyStar(fraction)
    }
}

@Composable
private fun FilledStar(fraction: Float) = Canvas(
    modifier = Modifier
        .fillMaxSize()
        .clip(FractionalRectangleShape(0f, fraction))
) {
    val path = Path().addStar(size)

    drawPath(path, color = Color(0xFFFF7A32), style = Fill) // Filled Star
    drawPath(path, color = Color(0XFFFF630F), style = Stroke(width = strokeWidth)) // Border
}

@Composable
private fun EmptyStar(fraction: Float) = Canvas(
    modifier = Modifier
        .fillMaxSize()
        .clip(FractionalRectangleShape(fraction, 1f))
) {
    val path = Path().addStar(size)

    drawPath(path, color = Color.Gray, style = Stroke(width = strokeWidth)) // Border
}

@Preview(showBackground = true)
@Composable
fun EmptyRatingStarPreview() {
    RatingStar(fraction = 0f, modifier = Modifier.size(20.dp))
}

@Preview(showBackground = true)
@Composable
fun PartialRatingStarPreview() {
    RatingStar(fraction = 0.7f, modifier = Modifier.size(20.dp))
}

@Preview(showBackground = true)
@Composable
fun FullRatingStarPreview() {
    RatingStar(fraction = 1f, modifier = Modifier.size(20.dp))
}

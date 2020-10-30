package com.jsachica.ratingbar.shapes

import androidx.annotation.FloatRange
import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density

@Stable
class FractionalRectangleShape(
    @FloatRange(from = 0.0, to = 1.0) private val startFraction: Float,
    @FloatRange(from = 0.0, to = 1.0) private val endFraction: Float
): Shape {
    override fun createOutline(size: Size, density: Density) =
        // The use of coerceAtMost is here to ensure that the rect never has zero width or height
        // which would cause it to be drawn entirely.
        // This is necessary until the fix for issue
        // https://issuetracker.google.com/issues/171492099 is released.
        Outline.Rectangle(Rect(
            left = (startFraction * size.width).coerceAtMost(size.width - 1f),
            top = 0f,
            right = (endFraction * size.width).coerceAtLeast(1f),
            bottom = size.height
        ))
}

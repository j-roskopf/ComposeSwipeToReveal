package com.joetr.swipetoreveal

import androidx.compose.ui.unit.Dp

/**
 * Parameters used to tweak the slide to reveal animation
 *
 * @param swipeToRevealAnimationDurationMs - amount in milliseconds for the animation duration
 * @param cardOffset - offset for the card to be revealed to. Generally this is the same width as the action content below
 */
data class SwipeToRevealParameters(
    val swipeToRevealAnimationDurationMs: Int,
    val cardOffset: Dp,
)
package com.jetapptech.halfwarenote.util.ShakingEffect

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.coroutines.handleCoroutineException

class ShakingState(
    private val strength : Strength,
    private val directions : Directions
) {

    val xPosition = Animatable(0f)
    var animate   = false

    suspend fun shake(repeat : Int = 15 , animationDuration : Int = 50){
        val shakeAnimationSpec : AnimationSpec<Float> = tween(animationDuration)

        if (animate){
            cancelShake()
            return
        }

        animate = true
        when(directions){
            Directions.LEFT            -> shakeToLeft(repeat , shakeAnimationSpec)
            Directions.RIGHT           -> shakeToRight(repeat , shakeAnimationSpec)
            Directions.LEFT_THEN_RIGHT -> shakeToLeftThenRight(repeat , shakeAnimationSpec)
            Directions.RIGHT_THEN_LEFT -> shakeToRightTHenLeft(repeat , shakeAnimationSpec)
        }
    }

    suspend fun cancelShake() {
        val shakeAnimationSpec : AnimationSpec<Float> = tween(5)
        xPosition.animateTo(0f , shakeAnimationSpec)
        animate = false


    }

    private suspend fun shakeToLeftThenRight(repeat : Int = 15 , shakeAnimationSpec: AnimationSpec<Float>) {
        repeat(repeat){
            if(!animate){
                animate = true
                return@repeat
            }
            xPosition.animateTo(-strength.value , shakeAnimationSpec)
            xPosition.animateTo(0f , shakeAnimationSpec)
            xPosition.animateTo(strength.value , shakeAnimationSpec)
            xPosition.animateTo(0f , shakeAnimationSpec)
        }
    }

    private suspend fun shakeToRightTHenLeft(repeat : Int = 15 ,shakeAnimationSpec: AnimationSpec<Float>) {
        repeat(repeat){
            if(!animate){
                animate = true
                return@repeat
            }
            xPosition.animateTo(strength.value , shakeAnimationSpec)
            xPosition.animateTo(0f , shakeAnimationSpec)
            xPosition.animateTo(-strength.value , shakeAnimationSpec)
            xPosition.animateTo(0f , shakeAnimationSpec)
        }
    }

    private suspend fun shakeToRight(repeat : Int = 15 ,shakeAnimationSpec: AnimationSpec<Float>) {
        repeat(repeat){
            if(!animate){
                animate = true
                return@repeat
            }
            xPosition.animateTo(strength.value , shakeAnimationSpec)
            xPosition.animateTo(0f , shakeAnimationSpec)
        }
    }

    private suspend fun shakeToLeft(repeat : Int = 15 ,shakeAnimationSpec: AnimationSpec<Float>) {
        repeat(repeat){
            if(!animate){
                animate = true
                return@repeat
            }
            xPosition.animateTo(-strength.value , shakeAnimationSpec)
            xPosition.animateTo(0f , shakeAnimationSpec)
        }
    }

    sealed class Strength(val value : Float){
        data object Norma    : Strength(17f)
        data object String : Strength(40f)
        data class  Custom(val strength : Float) : Strength(strength)
    }

    enum class Directions{
        LEFT, RIGHT , LEFT_THEN_RIGHT , RIGHT_THEN_LEFT
    }

}


@Composable
fun rememberShackingState(
    strength: ShakingState.Strength = ShakingState.Strength.String,
    directions : ShakingState.Directions = ShakingState.Directions.RIGHT_THEN_LEFT
) : ShakingState {
    return remember {
        ShakingState(strength , directions)
    }
}


fun Modifier.shakable(
    state : ShakingState
):Modifier{
    return graphicsLayer {
        translationX = state.xPosition.value
    }
}
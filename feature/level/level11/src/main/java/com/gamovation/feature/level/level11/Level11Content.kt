package com.gamovation.feature.level.level11

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.gamovation.core.domain.level.LevelScreenState
import com.gamovation.core.ui.Dimensions
import com.gamovation.core.ui.animation.DrawAnimation
import com.gamovation.core.ui.clickableNoRipple

@Composable
fun Level11Content(modifier: Modifier = Modifier, onLevelAction: (LevelScreenState) -> Unit) {
    val onClickLambda = remember {
        {
            onLevelAction(
                LevelScreenState.UserWrongChoice(
                    com.gamovation.core.domain.R.string.event_level_11_wrong
                )
            )
        }
    }
    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (
            topContent, dogRef, mewkaRef, murkaRef, kilenRef, cowRef
        ) = createRefs()

        val topGuideline = createGuidelineFromTop(0.2f)
        Level11TopContent(
            modifier = Modifier.constrainAs(topContent) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(parent.top)
                bottom.linkTo(topGuideline)
                centerHorizontallyTo(parent)
            },
            onClick = {
                onLevelAction(
                    LevelScreenState.UserCorrectChoice(
                        com.gamovation.core.domain.R.string.event_level_11_finished
                    )
                )
            }
        )

        val verticalOptionsGuideline = createVerticalChain(
            dogRef,
            mewkaRef,
            murkaRef,
            kilenRef,
            cowRef,
            chainStyle = ChainStyle.Packed
        )

        constrain(verticalOptionsGuideline) {
            top.linkTo(topGuideline, margin = Dimensions.Padding.Small.value)
            bottom.linkTo(parent.bottom)
        }

        DrawAnimation(
            modifier = Modifier.constrainAs(
                dogRef
            ) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                centerHorizontallyTo(parent)
            },
            appearOrder = 0
        ) {
            Card(
                border = BorderStroke(4.dp, Color.White),
                shape = Dimensions.RoundedShape.Large.value,
                colors = CardDefaults.cardColors(Color.Transparent)
            ) {
                Text(
                    text = stringResource(R.string.l11_dog),
                    modifier = Modifier
                        .clickableNoRipple(onClick = onClickLambda)
                        .padding(Dimensions.Padding.Small.value),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        DrawAnimation(
            modifier = Modifier.constrainAs(
                mewkaRef
            ) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                centerHorizontallyTo(parent)
            },
            appearOrder = 1
        ) {
            Card(
                border = BorderStroke(4.dp, Color.White),
                shape = Dimensions.RoundedShape.Large.value,
                colors = CardDefaults.cardColors(Color.Transparent)
            ) {
                Text(
                    text = stringResource(R.string.l11_mewka),
                    modifier = Modifier
                        .clickableNoRipple(onClick = onClickLambda)
                        .padding(Dimensions.Padding.Small.value),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        DrawAnimation(
            modifier = Modifier.constrainAs(
                murkaRef
            ) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                centerHorizontallyTo(parent)
            },
            appearOrder = 2
        ) {
            Card(
                border = BorderStroke(4.dp, Color.White),
                shape = Dimensions.RoundedShape.Large.value,
                colors = CardDefaults.cardColors(Color.Transparent)
            ) {
                Text(
                    text = stringResource(R.string.l11_murka),
                    modifier = Modifier
                        .clickableNoRipple(onClick = onClickLambda)
                        .padding(Dimensions.Padding.Small.value),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        DrawAnimation(
            modifier = Modifier.constrainAs(
                kilenRef
            ) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                centerHorizontallyTo(parent)
            },
            appearOrder = 3
        ) {
            Card(
                border = BorderStroke(4.dp, Color.White),
                shape = Dimensions.RoundedShape.Large.value,
                colors = CardDefaults.cardColors(Color.Transparent)
            ) {
                Text(
                    text = stringResource(R.string.l11_kilen),
                    modifier = Modifier
                        .clickableNoRipple(onClick = onClickLambda)
                        .padding(Dimensions.Padding.Small.value),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        DrawAnimation(
            modifier = Modifier.constrainAs(
                cowRef
            ) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                centerHorizontallyTo(parent)
            },
            appearOrder = 4
        ) {
            Card(
                border = BorderStroke(4.dp, Color.White),
                shape = Dimensions.RoundedShape.Large.value,
                colors = CardDefaults.cardColors(Color.Transparent)
            ) {
                Text(
                    text = stringResource(R.string.l11_cow),
                    modifier = Modifier
                        .clickableNoRipple(onClick = onClickLambda)
                        .padding(Dimensions.Padding.Small.value),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

@Composable
internal fun Level11TopContent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    var isCorrect by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = stringResource(R.string.l11_what_are_the_names_of_baby),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.width(Dimensions.Padding.ExtraSmall.value))
        Text(
            text = stringResource(R.string.l11_cats),
            modifier = Modifier.clickable(onClick = {
                isCorrect = true
                onClick()
            }),
            style = MaterialTheme.typography.titleLarge,
            textDecoration = if (isCorrect) TextDecoration.Underline else TextDecoration.None
        )
    }
}

package com.conboi.feature.level.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.conboi.core.database.model.LevelData
import com.conboi.core.domain.level.LevelScreenState
import com.conboi.core.ui.Dimensions
import com.conboi.feature.level.all.level_1.Level1Content
import com.conboi.feature.level.all.level_10.Level10Content
import com.conboi.feature.level.all.level_11.Level11Content
import com.conboi.feature.level.all.level_12.Level12Content
import com.conboi.feature.level.all.level_13.Level13Content
import com.conboi.feature.level.all.level_14.Level14Content
import com.conboi.feature.level.all.level_15.Level15Content
import com.conboi.feature.level.all.level_16.Level16Content
import com.conboi.feature.level.all.level_17.Level17Content
import com.conboi.feature.level.all.level_18.Level18Content
import com.conboi.feature.level.all.level_19.Level19Content
import com.conboi.feature.level.all.level_2.Level2Content
import com.conboi.feature.level.all.level_20.Level20Content
import com.conboi.feature.level.all.level_3.Level3Content
import com.conboi.feature.level.all.level_4.Level4Content
import com.conboi.feature.level.all.level_5.Level5Content
import com.conboi.feature.level.all.level_6.Level6Content
import com.conboi.feature.level.all.level_7.Level7Content
import com.conboi.feature.level.all.level_8.Level8Content
import com.conboi.feature.level.all.level_9.Level9Content

@Composable
fun LevelBody(level: LevelData, onLevelScreenAction: (LevelScreenState) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (level.title.isNotBlank()) {
            Title(text = level.title)
            Spacer(modifier = Modifier.height(Dimensions.Padding.ExtraLarge.value))
        }


        when (level.id) {
            1 -> Level1Content(onLevelAction = onLevelScreenAction)
            2 -> Level2Content(onLevelAction = onLevelScreenAction)
            3 -> Level3Content(onLevelAction = onLevelScreenAction)
            4 -> Level4Content(onLevelAction = onLevelScreenAction)
            5 -> Level5Content(onLevelAction = onLevelScreenAction)
            6 -> Level6Content(onLevelAction = onLevelScreenAction)
            7 -> Level7Content(onLevelAction = onLevelScreenAction)
            8 -> Level8Content(onLevelAction = onLevelScreenAction)
            9 -> Level9Content(onLevelAction = onLevelScreenAction)
            10 -> Level10Content(onLevelAction = onLevelScreenAction)
            11 -> Level11Content(onLevelAction = onLevelScreenAction)
            12 -> Level12Content(onLevelAction = onLevelScreenAction)
            13 -> Level13Content(onLevelAction = onLevelScreenAction)
            14 -> Level14Content(onLevelAction = onLevelScreenAction)
            15 -> Level15Content(onLevelAction = onLevelScreenAction)
            16 -> Level16Content(onLevelAction = onLevelScreenAction)
            17 -> Level17Content(onLevelAction = onLevelScreenAction)
            18 -> Level18Content(onLevelAction = onLevelScreenAction)
            19 -> Level19Content(onLevelAction = onLevelScreenAction)
            20 -> Level20Content(onLevelAction = onLevelScreenAction)
            //                        21 -> Level21Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        22 -> Level22Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        23 -> Level23Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        24 -> Level24Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        25 -> Level25Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        26 -> Level26Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        27 -> Level27Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        28 -> Level28Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        29 -> Level29Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        30 -> Level30Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        31 -> Level31Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        32 -> Level32Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        33 -> Level33Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        34 -> Level34Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        35 -> Level35Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        36 -> Level36Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        37 -> Level37Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        38 -> Level38Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        39 -> Level39Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        40 -> Level40Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        41 -> Level41Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        42 -> Level42Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        43 -> Level43Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        44 -> Level44Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        45 -> Level45Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        46 -> Level46Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        47 -> Level47Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        48 -> Level48Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        49 -> Level49Content(modifier = modifier, onLevelAction = onLevelUIAction)
            //                        50 -> Level50Content(modifier = modifier, onLevelAction = onLevelUIAction)
            else -> {}
        }
    }
}
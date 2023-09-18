package com.android.ash.charactersheet.gui.main.charactercreator.screen

import android.graphics.Bitmap
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.theme.D20CharacterSheetTheme
import com.d20charactersheet.framework.boc.model.Ability
import com.d20charactersheet.framework.boc.model.Race
import com.d20charactersheet.framework.boc.model.Size


@Composable
fun RaceScreen(
    race: Race,
    raceList: List<Race>,
    getBitmap: (Int) -> Bitmap,
    onRaceChange: (Race) -> Unit,

    onNavigateToNext: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxHeight()) {
            Scaffold(
                topBar = {
                    TopBarTitle(
                        title = stringResource(R.string.race_screen_title),
                        progress = 1
                    )
                },

                content = {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(bottom = it.calculateBottomPadding())
                    ) {
                        RaceList(
                            selectedRace = race,
                            raceList = raceList,
                            onRaceChange = onRaceChange,
                            getBitmap = getBitmap
                        )
                    }

                },

                bottomBar = {
                    BottomBarNavigation(
                        showPrevious = false,
                        showNext = true,
                        showCreate = false,
                        onNavigateToNext = onNavigateToNext
                    )
                }
            )
        }
    }
}


@Preview
@Composable
fun RaceScreenPreview() {
    D20CharacterSheetTheme {
        val raceList = listOf(
            Race().apply {
                id = 1
                name = "firstRace"
                size = Size.MEDIUM
                baseLandSpeed = 30
                abilities = listOf(
                    Ability().apply { name = "Resist Sleep" },
                    Ability().apply { name = "Ability Adjustment (+2 Dex, -2 Con)" },
                    Ability().apply { name = "Weapon Proficiency (Sword and Bow)" },
                    Ability().apply { name = "Skill Bonus (Listen, Search, Spot" },
                    Ability().apply { name = "Low-Light Vision" }
                )
            },
            Race().apply {
                id = 2
                name = "secondRace"
                size = Size.SMALL
                baseLandSpeed = 20
                abilities = listOf(
                    Ability().apply { name = "Resist Sleep" },
                    Ability().apply { name = "Ability Adjustment (+2 Dex, -2 Con)" },
                    Ability().apply { name = "Weapon Proficiency (Sword and Bow)" },
                    Ability().apply { name = "Skill Bonus (Listen, Search, Spot" },
                    Ability().apply { name = "Low-Light Vision" }
                )
            }
        )
        RaceScreen(
            race = raceList[0],
            raceList = raceList,
            getBitmap = { Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888) },
            onRaceChange = { }
        ) { }
    }
}


@Composable
fun RaceList(
    selectedRace: Race,
    raceList: List<Race>,
    onRaceChange: (Race) -> Unit,
    getBitmap: (Int) -> Bitmap
) {
    Column {
        for (race in raceList) {
            RaceCard(
                race = race,
                selected = (selectedRace == race),
                getBitmap = getBitmap,
                onRaceChange = onRaceChange
            )
            Divider(color = Color.Black)
        }
    }
}


@Composable
fun RaceCard(
    modifier: Modifier = Modifier,
    race: Race,
    selected: Boolean,
    getBitmap: (Int) -> Bitmap,
    onRaceChange: (Race) -> Unit
) {
    val backgroundColor by animateColorAsState(
        if (selected) MaterialTheme.colors.primary else Color.Transparent
    )
    val abilityScoreIncrease = remember { getAbilityScoreIncrease(race.abilities) }
    val imageBitmap = remember { getBitmap(race.imageId).asImageBitmap() }
    Row(
        modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .clickable(onClick = { onRaceChange(race) })
            .padding(16.dp)
    ) {
        Image(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colors.onSurface.copy(alpha = 0.2f)),
            bitmap = imageBitmap,
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(race.name, fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(abilityScoreIncrease, style = MaterialTheme.typography.body2)
                Text("Size: ${race.size}", style = MaterialTheme.typography.body2)
                Text("Speed: ${race.baseLandSpeed}", style = MaterialTheme.typography.body2)
            }
        }
    }

}

fun getAbilityScoreIncrease(abilities: List<Ability>): String {
    val ability = abilities.firstOrNull { it.name.startsWith("Ability Score Increase") }
    return ability?.description ?: ""
}


@Preview
@Composable
fun RaceCardPreview() {
    D20CharacterSheetTheme {
        val abilities = listOf(
            Ability().apply { name = "Resist Sleep" },
            Ability().apply { name = "Ability Adjustment (+2 Dex, -2 Con)" },
            Ability().apply { name = "Weapon Proficiency (Sword and Bow)" },
            Ability().apply { name = "Skill Bonus (Listen, Search, Spot" },
            Ability().apply { name = "Low-Light Vision" },
        )
        RaceCard(
            race = Race().apply {
                name = "myRace"
                size = Size.MEDIUM
                baseLandSpeed = 30
                this.abilities = abilities
            },
            selected = false,
            getBitmap = { Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888) },
            onRaceChange = { }
        )
    }
}


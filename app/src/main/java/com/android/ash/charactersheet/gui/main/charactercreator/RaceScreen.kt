package com.android.ash.charactersheet.gui.main.charactercreator

import android.graphics.Bitmap
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
                        title = stringResource(R.string.race_title),
                        progress = 1
                    )
                },

                content = {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(bottom = 80.dp)
                    ) {
                        RaceList(
                            race = race,
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
fun TopBarTitle(
    title: String,
    progress: Int
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            title = { Text(title) }
        )
        LinearProgressIndicator(
            progress = progress / 3f,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        )
    }
}

@Preview
@Composable
fun TopBarTitlePreview() {
    D20CharacterSheetTheme {
        TopBarTitle(
            title = "myTitle",
            progress = 1
        )
    }
}

@Composable
fun BottomBarNavigation(
    showPrevious: Boolean = false,
    showNext: Boolean = false,
    showCreate: Boolean = false,

    onNavigateToPrevious: () -> Unit = { },
    onNavigateToNext: () -> Unit = { },
    onCreateCharacter: () -> Unit = { }
) {
    Surface(
        elevation = 3.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (showPrevious) {
                    OutlinedButton(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        onClick = onNavigateToPrevious
                    ) {
                        Text(stringResource(R.string.character_creator_previous_button))
                    }
                }
                if (showNext) {
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        onClick = onNavigateToNext
                    ) {
                        Text(stringResource(R.string.character_creator_next_button))
                    }
                }
            }
            if (showCreate) {
                if (!showNext) {
                    Button(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        onClick = onCreateCharacter
                    ) {
                        Text(stringResource(R.string.character_creator_create_button))
                    }
                } else {
                    OutlinedButton(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        onClick = onCreateCharacter
                    ) {
                        Text(stringResource(R.string.character_creator_create_button))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BottomBarNavigationPreview() {
    D20CharacterSheetTheme {
        BottomBarNavigation(
            showPrevious = true,
            showNext = true,
            showCreate = false,
            onNavigateToPrevious = { },
            onNavigateToNext = { }
        ) { }
    }
}

@Composable
fun RaceList(
    modifier: Modifier = Modifier,
    race: Race,
    raceList: List<Race>,
    onRaceChange: (Race) -> Unit,
    getBitmap: (Int) -> Bitmap
) {
    Column(modifier = modifier) {
        for (currentRace in raceList) {
            RaceCard(race = currentRace, selected = (race == currentRace), getBitmap = getBitmap, onRaceChange = onRaceChange)
            Divider(color = Color.Black)
        }
    }

//    LazyColumn(modifier = modifier) {
//        items(items = raceList) { race ->
//            RaceCard(assetId = R.drawable.race_dwarf, race = race)
//            Divider(color = Color.Black)
//        }
//    }
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
        Surface(
            modifier = Modifier.size(100.dp),
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            Image(
                bitmap = imageBitmap,
                modifier = modifier,
                contentDescription = null
            )
        }
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
        RaceCard(
            race = Race().apply {
                name = "myRace"
                size = Size.MEDIUM
                baseLandSpeed = 30
                abilities = listOf(
                    Ability().apply { name = "Resist Sleep" },
                    Ability().apply { name = "Ability Adjustment (+2 Dex, -2 Con)" },
                    Ability().apply { name = "Weapon Proficiency (Sword and Bow)" },
                    Ability().apply { name = "Skill Bonus (Listen, Search, Spot" },
                    Ability().apply { name = "Low-Light Vision" },
                )
            },
            selected = false,
            onRaceChange = { },
            getBitmap = { Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888) },
        )
    }
}


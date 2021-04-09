package com.android.ash.charactersheet.gui.main.charactercreator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.ash.charactersheet.R

@Composable
fun RaceAndClassScreen(
    name: String,
    player: String,
    race: String,
    raceList: List<String>,
    clazz: String,
    classList: List<String>,
    sex: String,
    sexList: List<String>,
    alignment: String,
    alignmentList: List<String>,

    onChangeName: (String) -> Unit,
    onChangePlayer: (String) -> Unit,
    onRaceChange: (String) -> Unit,
    onSexChange: (String) -> Unit,
    onAlignmentChange: (String) -> Unit,
    onClassChange: (String) -> Unit,
    onNavigateToRaceAndClassFragment: () -> Unit,
    onCreateCharacter: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxHeight()) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(id = R.string.race_and_class_title)) }
                    )
                },

                content = {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(bottom = 50.dp)
                    ) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = onChangeName,
                            label = { Text(stringResource(id = R.string.create_name_label)) },
                            maxLines = 1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )

                        OutlinedTextField(
                            value = player,
                            onValueChange = onChangePlayer,
                            label = { Text(stringResource(id = R.string.create_player_label)) },
                            maxLines = 1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )

                        StringDropdownMenu(
                            labelResourceId = R.string.create_race_label,
                            name = race,
                            nameList = raceList,
                            onNameChange = onRaceChange
                        )

                        StringDropdownMenu(
                            labelResourceId = R.string.create_class_label,
                            name = clazz,
                            nameList = classList,
                            onNameChange = onClassChange
                        )

                        StringDropdownMenu(
                            labelResourceId = R.string.create_sex_label,
                            name = sex,
                            nameList = sexList,
                            onNameChange = onSexChange
                        )

                        StringDropdownMenu(
                            labelResourceId = R.string.create_alignment_label,
                            name = alignment,
                            nameList = alignmentList,
                            onNameChange = onAlignmentChange
                        )
                    }
                },

                bottomBar = {
                    Surface(
                        elevation = 3.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Button(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp),
                                onClick = onNavigateToRaceAndClassFragment
                            ) {
                                Text(stringResource(R.string.race_and_class_navigate_next_button))
                            }

                            Button(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp), onClick = onCreateCharacter
                            ) {
                                Text(stringResource(R.string.character_create_button))
                            }
                        }
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun RaceAndClassScreenPreview() {
    MaterialTheme {
        RaceAndClassScreen(
            name = "myName",
            player = "myPlayer",
            race = "Human",
            raceList = listOf("Human", "Dwarf"),
            sexList = listOf("Male", "Female"),
            sex = "Male",
            alignmentList = listOf("Lawfull Good", "Neutral Good"),
            alignment = "Lafull Good",
            clazz = "Fighter",
            classList = listOf("Fighter", "Wizard"),
            onChangeName = {},
            onChangePlayer = {},
            onRaceChange = {},
            onSexChange = {},
            onAlignmentChange = {},
            onClassChange = {},
            onNavigateToRaceAndClassFragment = {},
            onCreateCharacter = {}
        )
    }
}


@Composable
fun StringDropdownMenu(
    labelResourceId: Int,
    name: String,
    nameList: List<String>,
    onNameChange: (String) -> Unit
) {
    val isOpen = remember { mutableStateOf(false) }

    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { },
                label = { Text(stringResource(labelResourceId)) },
                modifier = Modifier.fillMaxWidth()
            )

            DropdownMenu(
                expanded = isOpen.value,
                onDismissRequest = { isOpen.value = false }
            ) {
                nameList.forEach {
                    DropdownMenuItem(
                        onClick = {
                            isOpen.value = false
                            onNameChange(it)
                        }
                    ) {
                        Text(it)
                    }
                }
            }
        }
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .padding(10.dp)
                .clickable(
                    onClick = { isOpen.value = true }
                )
        )
    }

}


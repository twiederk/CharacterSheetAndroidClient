package com.android.ash.charactersheet.gui.main.charactercreator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.theme.D20CharacterSheetTheme
import com.d20charactersheet.framework.boc.model.*

@Composable
fun EquipmentScreen(
    starterPackBoxViewModels: List<StarterPackBoxViewModel>,
    onNavigateToPrevious: () -> Unit,
    onCreateCharacter: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxHeight()) {
            Scaffold(
                topBar = {
                    TopBarTitle(
                        title = stringResource(R.string.equipment_title),
                        progress = 4
                    )
                },

                content = {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(bottom = 80.dp)
                    ) {
                        if (starterPackBoxViewModels.isEmpty()) {
                            EmptyEquipmentMessage()
                        } else {
                            StarterPackBoxCards(starterPackBoxViewModels)
                        }
                    }
                },

                bottomBar = {
                    BottomBarNavigation(
                        showPrevious = true,
                        showNext = false,
                        showCreate = true,

                        onNavigateToPrevious = onNavigateToPrevious,
                        onCreateCharacter = onCreateCharacter
                    )
                }
            )
        }
    }
}

@Composable
private fun EmptyEquipmentMessage() {
    Text(
        text = stringResource(R.string.equipment_missing_message),
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
private fun StarterPackBoxCards(starterPackBoxViewModels: List<StarterPackBoxViewModel>) {
    for (starterPackBoxViewModel in starterPackBoxViewModels) {
        val starterPackBoxOption by starterPackBoxViewModel.starterPackBoxOption.observeAsState(
            starterPackBoxViewModel.initialStarterPackBoxOption
        )
        StarterPackBoxCard(
            starterPackBox = starterPackBoxViewModel.starterPackBox,
            starterPackBoxOption = starterPackBoxOption,
            onNameChange = {
                starterPackBoxViewModel.onStarterPackBoxOptionChange(it)
            }
        )
    }
}


@Preview
@Composable
fun EquipmentScreenPreview() {
    D20CharacterSheetTheme {
        EquipmentScreen(
            starterPackBoxViewModels = listOf(
                StarterPackBoxViewModel(getStarterPackBox("Armor")),
                StarterPackBoxViewModel(getStarterPackBox("Primary Hand")),
                StarterPackBoxViewModel(getStarterPackBox("Secondary Hand")),
                StarterPackBoxViewModel(getStarterPackBox("Ranged Weapon")),
                StarterPackBoxViewModel(getStarterPackBox("Equipment Packs"))
            ),
            onNavigateToPrevious = { }
        ) { }
    }

}

@Preview
@Composable
fun EmptyEquipmentScreenPreview() {
    D20CharacterSheetTheme {
        EquipmentScreen(
            starterPackBoxViewModels = listOf(),
            onNavigateToPrevious = { }
        ) { }
    }

}

@Composable
fun StarterPackBoxCard(
    starterPackBox: StarterPackBox,
    starterPackBoxOption: StarterPackBoxOption,
    onNameChange: (StarterPackBoxOption) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.TopStart)
        ) {
            OutlinedTextField(
                value = starterPackBoxOption.getTitle(),
                onValueChange = { },
                label = { Text(starterPackBox.name) },
                modifier = Modifier.fillMaxWidth()
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                starterPackBox.options.forEach {
                    DropdownMenuItem(
                        onClick = {
                            onNameChange(it)
                            expanded = false
                        }
                    ) {
                        Text(it.getTitle(), fontWeight = FontWeight.Bold)
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Transparent)
                    .clickable(
                        onClick = { expanded = true }
                    )
            )
        }
    }


}

@Preview
@Composable
fun StarterPackBoxCardPreview() {
    val viewModel = StarterPackBoxViewModel(getStarterPackBox("Armor"))
    D20CharacterSheetTheme {
        StarterPackBoxCard(
            starterPackBox = viewModel.starterPackBox,
            starterPackBoxOption = viewModel.starterPackBoxOption.value!!,
            onNameChange = { viewModel.onStarterPackBoxOptionChange(it) }
        )
    }
}

private fun getStarterPackBox(title: String): StarterPackBox {
    val starterPackBox = StarterPackBox(name = title)
    starterPackBox.add(
        StarterPackBoxItemOption().apply {
            add(ItemGroup().apply {
                item = Armor().apply { name = "1st Armor" }
                number = 1
            })
        })

    starterPackBox.add(StarterPackBoxItemOption().apply {
        add(ItemGroup().apply {
            item = Armor().apply { name = "2nd Armor" }
            number = 1
        })
    })
    return starterPackBox
}


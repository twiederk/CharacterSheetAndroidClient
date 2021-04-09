package com.android.ash.charactersheet.gui.main.charactercreator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.ash.charactersheet.R

@Composable
fun AbilityScoresScreen(
    onRollDice: () -> Unit,
    onNavigateToRaceAndClassFragment: () -> Unit,
    onCreateCharacter: () -> Unit,
    vararg attributes: AttributeRowData
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxHeight()) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(id = R.string.ability_scores_title)) }
                    )
                },

                content = {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(bottom = 50.dp)
                    ) {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            onClick = onRollDice
                        ) {
                            Text(stringResource(R.string.ability_scores_roll_dice))
                        }
                        for (attributeRowData in attributes) {
                            AttributeRow(attributeRowData)
                        }
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
                                Text(stringResource(R.string.ability_scores_navigate_previous_button))
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
fun AbilityScoresScreenPreview() {
    MaterialTheme {
        AbilityScoresScreen(
            onRollDice = { },
            onNavigateToRaceAndClassFragment = { },
            onCreateCharacter = { },
            AttributeRowData(
                attributeLabel = R.string.attribute_strength,
                attributeValue = 10,
                attributeModifier = "0",
                onIncrease = {},
                onDecrease = {},
            ),
            AttributeRowData(
                attributeLabel = R.string.attribute_dexterity,
                attributeValue = 12,
                attributeModifier = "+1",
                onIncrease = {},
                onDecrease = {},
            )
        )
    }
}

@Composable
fun AttributeRow(
    attributeRowData: AttributeRowData,
) {
    Row(Modifier.fillMaxWidth()) {
        AttributeLabel(attributeRowData.attributeLabel)
        AttributeIncrease(attributeRowData.onIncrease)
        AttributeValue(attributeRowData.attributeValue)
        AttributeDecrease(attributeRowData.onDecrease)
        AttributeModifier(attributeRowData.attributeModifier)
    }
}

@Preview
@Composable
fun AttributeRowPreview() {
    MaterialTheme {
        AttributeRow(
            AttributeRowData(
                attributeLabel = R.string.attribute_strength,
                attributeValue = 12,
                attributeModifier = "+1",
                onIncrease = {},
                onDecrease = {},
            )
        )
    }
}

@Composable
fun AttributeLabel(labelResourceId: Int) {
    Text(
        modifier = Modifier
            .width(120.dp)
            .padding(8.dp),
        text = stringResource(labelResourceId)
    )
}

@Composable
fun AttributeIncrease(onIncreaseAttribute: () -> Unit) {
    Button(
        modifier = Modifier.padding(8.dp),
        onClick = onIncreaseAttribute
    ) {
        Text("+")
    }
}

@Composable
fun AttributeValue(attributeValue: Int) {
    Text(
        modifier = Modifier
            .width(40.dp)
            .padding(8.dp),
        textAlign = TextAlign.End,
        text = attributeValue.toString()
    )
}

@Composable
fun AttributeDecrease(onDecreaseAttribute: () -> Unit) {
    Button(
        modifier = Modifier.padding(8.dp),
        onClick = onDecreaseAttribute
    ) {
        Text("-")
    }
}


@Composable
fun AttributeModifier(abilityModifier: String) {
    Text(
        modifier = Modifier
            .width(50.dp)
            .padding(8.dp),
        textAlign = TextAlign.End,
        text = abilityModifier
    )
}

class AttributeRowData(
    val attributeLabel: Int,
    val attributeValue: Int,
    val attributeModifier: String,
    val onIncrease: () -> Unit,
    val onDecrease: () -> Unit,
)
package com.android.ash.charactersheet.gui.main.charactercreator.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

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

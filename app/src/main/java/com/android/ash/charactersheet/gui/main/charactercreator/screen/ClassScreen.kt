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
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.gui.theme.D20CharacterSheetTheme
import com.d20charactersheet.framework.boc.model.BaseAttackBonus
import com.d20charactersheet.framework.boc.model.CharacterClass
import com.d20charactersheet.framework.boc.model.Die
import com.d20charactersheet.framework.boc.model.Save
import java.util.EnumSet


@Composable
fun ClassScreen(
    clazz: CharacterClass,
    classList: List<CharacterClass>,
    gameSystemType: GameSystemType,

    getBitmap: (Int) -> Bitmap,
    getDisplaySaves: (EnumSet<Save>) -> String,
    onClassChange: (CharacterClass) -> Unit,
    onNavigateToPrevious: () -> Unit,
    onNavigateToNext: () -> Unit,
    onCreateCharacter: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxHeight()) {
            Scaffold(
                topBar = {
                    TopBarTitle(
                        title = stringResource(R.string.class_screen_title),
                        progress = 2
                    )
                },

                content = {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(bottom = it.calculateBottomPadding())
                    ) {

                        ClassList(
                            classSelected = clazz,
                            classList = classList,
                            gameSystemType = gameSystemType,
                            onClassChange = onClassChange,
                            getBitmap = getBitmap,
                            getDisplaySaves = getDisplaySaves,
                        )

                    }
                },

                bottomBar = {
                    BottomBarNavigation(
                        showPrevious = true,
                        showNext = true,
                        showCreate = true,
                        onNavigateToPrevious = onNavigateToPrevious,
                        onNavigateToNext = onNavigateToNext,
                        onCreateCharacter = onCreateCharacter
                    )
                }
            )
        }
    }
}


@Composable
fun ClassList(
    classSelected: CharacterClass,
    classList: List<CharacterClass>,
    gameSystemType: GameSystemType,
    onClassChange: (CharacterClass) -> Unit,
    getBitmap: (Int) -> Bitmap,
    getDisplaySaves: (EnumSet<Save>) -> String
) {
    Column {
        for (clazz in classList) {
            ClassCard(
                clazz = clazz,
                selected = (classSelected == clazz),
                gameSystemTyp = gameSystemType,
                getBitmap = getBitmap,
                getDisplaySaves = getDisplaySaves,
                onClassChange = onClassChange
            )
            Divider(color = Color.Black)
        }
    }
}


@Preview
@Composable
fun ClassScreenPreview() {
    val classFighter = CharacterClass().apply {
        id = 1
        name = "Fighter"
        hitDie = Die.D10
        saves = EnumSet.of(Save.STRENGTH, Save.CONSTITUTION)
    }
    val classWizard = CharacterClass().apply {
        id = 2
        name = "Wizard"
        hitDie = Die.D4
        saves = EnumSet.of(Save.INTELLIGENCE, Save.WISDOM)
    }

    D20CharacterSheetTheme {
        ClassScreen(
            clazz = classFighter,
            classList = listOf(classFighter, classWizard),
            gameSystemType = GameSystemType.DND5E,
            getBitmap = { Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888) },
            getDisplaySaves = { "" },
            onClassChange = {},
            onNavigateToPrevious = {},
            onNavigateToNext = {},
            onCreateCharacter = {})
    }
}


@Composable
fun ClassCard(
    clazz: CharacterClass,
    selected: Boolean,
    gameSystemTyp: GameSystemType,
    getBitmap: (Int) -> Bitmap,
    getDisplaySaves: (EnumSet<Save>) -> String,
    onClassChange: (CharacterClass) -> Unit
) {
    val backgroundColor by animateColorAsState(
        if (selected) MaterialTheme.colors.primary else Color.Transparent,
        label = "SelectedClassAnimation"
    )
    val imageBitmap = remember { getBitmap(clazz.imageId).asImageBitmap() }
    val displaySaves = remember { getDisplaySaves(clazz.saves) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .clickable(onClick = { onClassChange(clazz) })
            .padding(16.dp)
    ) {
        Image(
            modifier = Modifier
                .background(backgroundColor)
                .size(100.dp)
                .clip(RoundedCornerShape(10.dp)),
            bitmap = imageBitmap,
            contentDescription = null
        )

        ClassInfo(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically),
            clazz = clazz,
            saves = displaySaves,
            gameSystemType = gameSystemTyp,
        )
    }

}


@Composable
fun ClassInfo(
    modifier: Modifier,
    clazz: CharacterClass,
    gameSystemType: GameSystemType,
    saves: String,
) {
    Column(
        modifier = modifier
    ) {
        Text(clazz.name, fontWeight = FontWeight.Bold)
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                stringResource(id = R.string.class_screen_hit_die, clazz.hitDie),
                style = MaterialTheme.typography.body2
            )
            Text(
                stringResource(id = R.string.class_screen_saves, saves),
                style = MaterialTheme.typography.body2
            )
            if (gameSystemType != GameSystemType.DND5E) {
                Text(
                    stringResource(
                        id = R.string.class_screen_base_attack_bonus, clazz.baseAttackBonus
                    ),
                    style = MaterialTheme.typography.body2
                )
                Text(
                    stringResource(
                        id = R.string.class_screen_skill_points_per_level, clazz.skillPointsPerLevel
                    ),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }

}


@Preview(
    name = "DnDv35 class",
    backgroundColor = 0xFFFFFF,
    showBackground = true
)
@Composable
fun ClassCardPreview() {
    D20CharacterSheetTheme {
        ClassCard(
            clazz = CharacterClass().apply {
                name = "myDnDv35Class"
                hitDie = Die.D10
                saves = EnumSet.of(Save.STRENGTH, Save.CONSTITUTION)
                baseAttackBonus = BaseAttackBonus.GOOD
            },
            selected = false,
            gameSystemTyp = GameSystemType.DNDV35,
            getBitmap = { Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888) },
            getDisplaySaves = { "Strength, Constitution" },
            onClassChange = { }
        )
    }
}

@Preview(
    name = "DnDv5e class",
    backgroundColor = 0xFFFFFF,
    showBackground = true
)
@Composable
fun DnD5eClassCardPreview() {
    D20CharacterSheetTheme {
        ClassCard(
            clazz = CharacterClass().apply {
                name = "myDnD5eClass"
                hitDie = Die.D10
                saves = EnumSet.of(Save.STRENGTH, Save.CONSTITUTION)
                baseAttackBonus = BaseAttackBonus.GOOD
            },
            selected = false,
            gameSystemTyp = GameSystemType.DND5E,
            getBitmap = { Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888) },
            getDisplaySaves = { "Strength, Constitution" },
            onClassChange = { }
        )
    }
}

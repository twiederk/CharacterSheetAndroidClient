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
import java.util.*


@Composable
fun ClassScreen(
    clazz: CharacterClass,
    classList: List<CharacterClass>,
    gameSystemType: GameSystemType,

    getBitmap: (Int) -> Bitmap,
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
                            .padding(bottom = 80.dp)
                    ) {

                        ClassList(
                            clazz = clazz,
                            classList = classList,
                            gameSystemType = gameSystemType,
                            onClassChange = onClassChange,
                            getBitmap = getBitmap,
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
    modifier: Modifier = Modifier,
    clazz: CharacterClass,
    classList: List<CharacterClass>,
    gameSystemType: GameSystemType,
    onClassChange: (CharacterClass) -> Unit,
    getBitmap: (Int) -> Bitmap
) {
    Column(modifier = modifier) {
        for (currentClass in classList) {
            ClassCard(
                clazz = currentClass,
                selected = (clazz == currentClass),
                gameSystemTyp = gameSystemType,
                getBitmap = getBitmap,
                onClassChange = onClassChange
            )
            Divider(color = Color.Black)
        }
    }
}


@Preview
@Composable
fun ClassScreenPreview() {
    D20CharacterSheetTheme {
        ClassScreen(
            clazz = CharacterClass().apply { id = 1; name = "Fighter" },
            classList = listOf(
                CharacterClass().apply { id = 1; name = "Fighter"; hitDie = Die.D10 },
                CharacterClass().apply { id = 2; name = "Wizard"; hitDie = Die.D4 }),
            gameSystemType = GameSystemType.DND5E,
            getBitmap = { Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888) },
            onClassChange = {},
            onNavigateToPrevious = {},
            onNavigateToNext = {},
        ) {}
    }
}


@Composable
fun ClassCard(
    clazz: CharacterClass,
    selected: Boolean,
    gameSystemTyp: GameSystemType,
    getBitmap: (Int) -> Bitmap,
    onClassChange: (CharacterClass) -> Unit
) {
    val backgroundColor by animateColorAsState(
        if (selected) MaterialTheme.colors.primary else Color.Transparent
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .clickable(onClick = { onClassChange(clazz) })
            .padding(16.dp)
    ) {
        ClassImage(
            modifier = Modifier
                .background(backgroundColor),
            clazz = clazz,
            getBitmap = getBitmap
        )
        ClassInfo(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically),
            clazz = clazz,
            gameSystemType = gameSystemTyp
        )
    }

}

@Composable
fun ClassImage(
    modifier: Modifier = Modifier,
    clazz: CharacterClass,
    getBitmap: (Int) -> Bitmap
) {
    val imageBitmap = remember { getBitmap(clazz.imageId).asImageBitmap() }
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
}


@Composable
fun ClassInfo(
    modifier: Modifier,
    clazz: CharacterClass,
    gameSystemType: GameSystemType,
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
            if (gameSystemType != GameSystemType.DND5E) {
                Text(
                    stringResource(id = R.string.class_screen_saves, clazz.saves),
                    style = MaterialTheme.typography.body2
                )
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


@Preview
@Composable
fun ClassCardPreview() {
    D20CharacterSheetTheme {
        ClassCard(
            clazz = CharacterClass().apply {
                name = "myClass"
                hitDie = Die.D10
                saves = EnumSet.of(Save.FORTITUDE)
                baseAttackBonus = BaseAttackBonus.GOOD
            },
            selected = false,
            gameSystemTyp = GameSystemType.DNDV35,
            getBitmap = { Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888) },
        ) { }
    }
}

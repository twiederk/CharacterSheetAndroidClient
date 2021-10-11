package com.android.ash.charactersheet.boc.model;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.ImageResource;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.ImageResources;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.RawScriptResource;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.ScriptResource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Enumeration of supported GameSystems.
 */
public enum GameSystemType {

    DNDV35(1, //
            "DnD v.3.5", //
            "dndv35_db",
            Arrays.asList(
                    new RawScriptResource(R.raw.create_database), //
                    new RawScriptResource(R.raw.dndv35_phb_data), //
                    new RawScriptResource(R.raw.dndv35_phb_spell), //
                    new RawScriptResource(R.raw.dndv35_phb_character) //
            ), //
            createUpdateScriptMap(new int[]{59, 72, 73},
                    new ScriptResource[]{
                            new RawScriptResource(R.raw.dndv35_upgrade_59_to_60),
                            new RawScriptResource(R.raw.dndv35_upgrade_72_to_73),
                            new RawScriptResource(R.raw.dndv35_upgrade_73_to_74)
                    }),
            createUpdateImageMap(new int[]{72},
                    new ImageResources[]{
                            new ImageResources(new ImageResource[]{
                                    new ImageResource(4, R.drawable.dwarf_female_bg),
                                    new ImageResource(5, R.drawable.dwarf_female_hunter_bg),
                                    new ImageResource(6, R.drawable.dwarf_male_bg),
                                    new ImageResource(7, R.drawable.dwarf_male_warrior_bg),
                                    new ImageResource(8, R.drawable.elf_female_bg),
                                    new ImageResource(9, R.drawable.elf_female_druid_bg),
                                    new ImageResource(10, R.drawable.elf_male_bg),
                                    new ImageResource(11, R.drawable.elf_male_thief_bg),
                                    new ImageResource(12, R.drawable.gnome_female_bg),
                                    new ImageResource(13, R.drawable.gnome_female_engineer_bg),
                                    new ImageResource(14, R.drawable.gnome_male_bg),
                                    new ImageResource(15, R.drawable.gnome_male_wizard_bg),
                                    new ImageResource(16, R.drawable.halfelf_female_bg),
                                    new ImageResource(17, R.drawable.halfelf_male_bg),
                                    new ImageResource(18, R.drawable.halfelf_male_warlock_bg),
                                    new ImageResource(19, R.drawable.halfelf_priest_female_bg),
                                    new ImageResource(20, R.drawable.human_female_bg),
                                    new ImageResource(21, R.drawable.human_female_wizard_bg),
                                    new ImageResource(22, R.drawable.human_male_bg),
                                    new ImageResource(23, R.drawable.human_male_paladin_bg),
                                    new ImageResource(24, R.drawable.lizardmen_female_bg),
                                    new ImageResource(25, R.drawable.lizardmen_male_bg),
                                    new ImageResource(26, R.drawable.lizzardmen_warrior_male1_bg),
                                    new ImageResource(27, R.drawable.lizzardmen_warrior_male_bg),
                                    new ImageResource(28, R.drawable.lizzardmen_witch_female_bg),
                                    new ImageResource(29, R.drawable.orc_female_bg),
                                    new ImageResource(30, R.drawable.orc_female_shaman_bg),
                                    new ImageResource(31, R.drawable.orc_male_barbarian_bg),
                                    new ImageResource(32, R.drawable.orc_male_bg),
                                    new ImageResource(33, R.drawable.ratmen_female_bg),
                                    new ImageResource(34, R.drawable.ratmen_female_necro_bg),
                                    new ImageResource(35, R.drawable.ratmen_male_assasin_bg),
                                    new ImageResource(36, R.drawable.ratmen_male_bg),
                                    new ImageResource(37, R.drawable.undead_female_warrior_bg),
                                    new ImageResource(38, R.drawable.undead_male_dark_priest_bg),
                                    new ImageResource(39, R.drawable.undead_necromancer_bg),
                            })
                    })
    ),

    PATHFINDER(2, //
            "Pathfinder", //
            "pathfinder_db", //
            Arrays.asList( //
                    new RawScriptResource(R.raw.create_database), //
                    new RawScriptResource(R.raw.pathfinder_crb_data), //
                    new RawScriptResource(R.raw.pathfinder_crb_spell), //
                    new RawScriptResource(R.raw.pathfinder_crb_character), //
                    new RawScriptResource(R.raw.pathfinder_apg_data), //
                    new RawScriptResource(R.raw.pathfinder_apg_spell), //
                    new RawScriptResource(R.raw.pathfinder_arg_data) //
            ), //
            createUpdateScriptMap(
                    new int[]{59, 63, 72, 73},
                    new ScriptResource[]{
                            new RawScriptResource(R.raw.pathfinder_upgrade_59_to_60), //
                            new RawScriptResource(R.raw.pathfinder_upgrade_63_to_64), //
                            new RawScriptResource(R.raw.pathfinder_upgrade_72_to_73), //
                            new RawScriptResource(R.raw.pathfinder_upgrade_73_to_74) //
                    }),
            createUpdateImageMap(new int[]{72},
                    new ImageResources[]{
                            new ImageResources(new ImageResource[]{
                                    new ImageResource(4, R.drawable.dwarf_female_bg),
                                    new ImageResource(5, R.drawable.dwarf_female_hunter_bg),
                                    new ImageResource(6, R.drawable.dwarf_male_bg),
                                    new ImageResource(7, R.drawable.dwarf_male_warrior_bg),
                                    new ImageResource(8, R.drawable.elf_female_bg),
                                    new ImageResource(9, R.drawable.elf_female_druid_bg),
                                    new ImageResource(10, R.drawable.elf_male_bg),
                                    new ImageResource(11, R.drawable.elf_male_thief_bg),
                                    new ImageResource(12, R.drawable.gnome_female_bg),
                                    new ImageResource(13, R.drawable.gnome_female_engineer_bg),
                                    new ImageResource(14, R.drawable.gnome_male_bg),
                                    new ImageResource(15, R.drawable.gnome_male_wizard_bg),
                                    new ImageResource(16, R.drawable.halfelf_female_bg),
                                    new ImageResource(17, R.drawable.halfelf_male_bg),
                                    new ImageResource(18, R.drawable.halfelf_male_warlock_bg),
                                    new ImageResource(19, R.drawable.halfelf_priest_female_bg),
                                    new ImageResource(20, R.drawable.human_female_bg),
                                    new ImageResource(21, R.drawable.human_female_wizard_bg),
                                    new ImageResource(22, R.drawable.human_male_bg),
                                    new ImageResource(23, R.drawable.human_male_paladin_bg),
                                    new ImageResource(24, R.drawable.lizardmen_female_bg),
                                    new ImageResource(25, R.drawable.lizardmen_male_bg),
                                    new ImageResource(26, R.drawable.lizzardmen_warrior_male1_bg),
                                    new ImageResource(27, R.drawable.lizzardmen_warrior_male_bg),
                                    new ImageResource(28, R.drawable.lizzardmen_witch_female_bg),
                                    new ImageResource(29, R.drawable.orc_female_bg),
                                    new ImageResource(30, R.drawable.orc_female_shaman_bg),
                                    new ImageResource(31, R.drawable.orc_male_barbarian_bg),
                                    new ImageResource(32, R.drawable.orc_male_bg),
                                    new ImageResource(33, R.drawable.ratmen_female_bg),
                                    new ImageResource(34, R.drawable.ratmen_female_necro_bg),
                                    new ImageResource(35, R.drawable.ratmen_male_assasin_bg),
                                    new ImageResource(36, R.drawable.ratmen_male_bg),
                                    new ImageResource(37, R.drawable.undead_female_warrior_bg),
                                    new ImageResource(38, R.drawable.undead_male_dark_priest_bg),
                                    new ImageResource(39, R.drawable.undead_necromancer_bg),
                            })
                    })

    ),


    DND5E(3, //
            "DnD 5e", //
            "dnd5e_db", //
            Arrays.asList( //
                    new RawScriptResource(R.raw.create_database), //
                    new RawScriptResource(R.raw.dnd5e_phb_data), //
                    new RawScriptResource(R.raw.dnd5e_phb_character) //
            ), //
            createUpdateScriptMap(
                    new int[]{69, 70, 71, 72, 73, 75},
                    new ScriptResource[]{
                            new RawScriptResource(R.raw.dnd5e_upgrade_69_to_70), //
                            new RawScriptResource(R.raw.dnd5e_upgrade_70_to_71), //
                            new RawScriptResource(R.raw.dnd5e_upgrade_71_to_72), //
                            new RawScriptResource(R.raw.dnd5e_upgrade_72_to_73), //
                            new RawScriptResource(R.raw.dnd5e_upgrade_73_to_74), //
                            new RawScriptResource(R.raw.dnd5e_upgrade_75_to_76), //
                    }),
            createUpdateImageMap(new int[]{72},
                    new ImageResources[]{
                            new ImageResources(new ImageResource[]{
                                    new ImageResource(4, R.drawable.dwarf_female_bg),
                                    new ImageResource(5, R.drawable.dwarf_female_hunter_bg),
                                    new ImageResource(6, R.drawable.dwarf_male_bg),
                                    new ImageResource(7, R.drawable.dwarf_male_warrior_bg),
                                    new ImageResource(8, R.drawable.elf_female_bg),
                                    new ImageResource(9, R.drawable.elf_female_druid_bg),
                                    new ImageResource(10, R.drawable.elf_male_bg),
                                    new ImageResource(11, R.drawable.elf_male_thief_bg),
                                    new ImageResource(12, R.drawable.gnome_female_bg),
                                    new ImageResource(13, R.drawable.gnome_female_engineer_bg),
                                    new ImageResource(14, R.drawable.gnome_male_bg),
                                    new ImageResource(15, R.drawable.gnome_male_wizard_bg),
                                    new ImageResource(16, R.drawable.halfelf_female_bg),
                                    new ImageResource(17, R.drawable.halfelf_male_bg),
                                    new ImageResource(18, R.drawable.halfelf_male_warlock_bg),
                                    new ImageResource(19, R.drawable.halfelf_priest_female_bg),
                                    new ImageResource(20, R.drawable.human_female_bg),
                                    new ImageResource(21, R.drawable.human_female_wizard_bg),
                                    new ImageResource(22, R.drawable.human_male_bg),
                                    new ImageResource(23, R.drawable.human_male_paladin_bg),
                                    new ImageResource(24, R.drawable.lizardmen_female_bg),
                                    new ImageResource(25, R.drawable.lizardmen_male_bg),
                                    new ImageResource(26, R.drawable.lizzardmen_warrior_male1_bg),
                                    new ImageResource(27, R.drawable.lizzardmen_warrior_male_bg),
                                    new ImageResource(28, R.drawable.lizzardmen_witch_female_bg),
                                    new ImageResource(29, R.drawable.orc_female_bg),
                                    new ImageResource(30, R.drawable.orc_female_shaman_bg),
                                    new ImageResource(31, R.drawable.orc_male_barbarian_bg),
                                    new ImageResource(32, R.drawable.orc_male_bg),
                                    new ImageResource(33, R.drawable.ratmen_female_bg),
                                    new ImageResource(34, R.drawable.ratmen_female_necro_bg),
                                    new ImageResource(35, R.drawable.ratmen_male_assasin_bg),
                                    new ImageResource(36, R.drawable.ratmen_male_bg),
                                    new ImageResource(37, R.drawable.undead_female_warrior_bg),
                                    new ImageResource(38, R.drawable.undead_male_dark_priest_bg),
                                    new ImageResource(39, R.drawable.undead_necromancer_bg),
                            })
                    })

    );

    private static Map<Integer, ScriptResource> createUpdateScriptMap(final int[] key, final ScriptResource[] value) {
        Map<Integer, ScriptResource> updateScriptMap = new HashMap<>();
        for (int i = 0; i < key.length; i++) {
            updateScriptMap.put(key[i], value[i]);
        }
        return updateScriptMap;
    }

    private static Map<Integer, ImageResources> createUpdateImageMap(int[] key, ImageResources[] value) {
        Map<Integer, ImageResources> updateImageMap = new HashMap<>();
        for (int i = 0; i < key.length; i++) {
            updateImageMap.put(key[i], value[i]);
        }
        return updateImageMap;
    }


    private final int id;
    private final String name;
    private final String databaseName;
    private final List<ScriptResource> createScriptResources;
    private final Map<Integer, ScriptResource> updateScriptResources;
    private final Map<Integer, ImageResources> updateImageResources;
    private final int[] images;

    GameSystemType(final int id, final String name, final String databaseName,
                   List<ScriptResource> createScriptResources,
                   Map<Integer, ScriptResource> updateScriptResources,
                   Map<Integer, ImageResources> updateImageResources) {
        this.id = id;
        this.name = name;
        this.databaseName = databaseName;
        this.createScriptResources = createScriptResources;
        this.updateScriptResources = updateScriptResources;
        this.updateImageResources = updateImageResources;
        this.images = new int[]{ //
                R.drawable.char_default, // default image
                R.drawable.char_default_face, // default thumbnail
                R.drawable.char_belvador, // Belvador image
                R.drawable.char_belvador_face, // Belvador thumbnail

                // race images
                R.drawable.dwarf_female_bg,
                R.drawable.dwarf_female_hunter_bg,
                R.drawable.dwarf_male_bg,
                R.drawable.dwarf_male_warrior_bg,
                R.drawable.elf_female_bg,
                R.drawable.elf_female_druid_bg,
                R.drawable.elf_male_bg,
                R.drawable.elf_male_thief_bg,
                R.drawable.gnome_female_bg,
                R.drawable.gnome_female_engineer_bg,
                R.drawable.gnome_male_bg,
                R.drawable.gnome_male_wizard_bg,
                R.drawable.halfelf_female_bg,
                R.drawable.halfelf_male_bg,
                R.drawable.halfelf_male_warlock_bg,
                R.drawable.halfelf_priest_female_bg,
                R.drawable.human_female_bg,
                R.drawable.human_female_wizard_bg,
                R.drawable.human_male_bg,
                R.drawable.human_male_paladin_bg,
                R.drawable.lizardmen_female_bg,
                R.drawable.lizardmen_male_bg,
                R.drawable.lizzardmen_warrior_male1_bg,
                R.drawable.lizzardmen_warrior_male_bg,
                R.drawable.lizzardmen_witch_female_bg,
                R.drawable.orc_female_bg,
                R.drawable.orc_female_shaman_bg,
                R.drawable.orc_male_barbarian_bg,
                R.drawable.orc_male_bg,
                R.drawable.ratmen_female_bg,
                R.drawable.ratmen_female_necro_bg,
                R.drawable.ratmen_male_assasin_bg,
                R.drawable.ratmen_male_bg,
                R.drawable.undead_female_warrior_bg,
                R.drawable.undead_male_dark_priest_bg,
                R.drawable.undead_necromancer_bg,
        };
    }

    /**
     * Returns the id of the game system.
     *
     * @return The id of the game system.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the game system.
     *
     * @return The name of the game system.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the name of the database used by the game system.
     *
     * @return The name of the database used by the game system.
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * Returns the resource ids of the images used by the game system.
     *
     * @return The resource ids of the images used by the game system.
     */
    public int[] getImages() {
        return images;
    }

    /**
     * Returns list of scripts to create database and fill with data.
     *
     * @return list of scripts to create database and fill with data.
     */
    public List<ScriptResource> getCreateScriptResources() {
        return createScriptResources;
    }

    /**
     * Returns version and script to update to this version
     *
     * @return Map containing versions with according update script
     */
    public Map<Integer, ? extends ScriptResource> getUpdateScriptResources() {
        return updateScriptResources;
    }


    public Map<Integer, ImageResources> getUpdateImageResources() {
        return updateImageResources;
    }
}

package com.android.ash.charactersheet.boc.model;

import com.android.ash.charactersheet.R;

import androidx.annotation.NonNull;

/**
 * Enumeration of supported GameSystems.
 */
public enum GameSystemType {

    /** Dungeons & Dragons v.3.5 */
    DNDV35(DnDv35.ID, DnDv35.NAME, DnDv35.DATABASE_NAME, DnDv35.CREATE_SCRIPTS, DnDv35.UPDATE_SCRIPTS, DnDv35.IMAGES),

    /** Pathfinder */
    PATHFINDER(Pathfinder.ID, Pathfinder.NAME, Pathfinder.DATABASE_NAME, Pathfinder.CREATE_SCRIPTS,
            Pathfinder.UPDATE_SCRIPTS, Pathfinder.IMAGES);

    private static final int NO_UPDATE_SCRIPT = 0;

    private final int id;
    private final String name;
    private final String databaseName;
    private final int[] createScripts;
    private final int[][] updateScripts;
    private final int[] images;

    GameSystemType(final int id, final String name, final String databaseName, final int[] createScripts,
                   final int[][] updateScripts, final int[] images) {
        this.id = id;
        this.name = name;
        this.databaseName = databaseName;
        this.createScripts = createScripts;
        this.updateScripts = updateScripts;
        this.images = images;
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
     * Returns the resource ids of scripts to create and fill the database of the game system. The scripts will be
     * executed in the given order.
     * 
     * @return The resource ids of scripts to create and fill the database of the game system.
     */
    public int[] getCreateScripts() {
        return createScripts;
    }

    /**
     * Returns the resource ids of scripts to update the database of the game system.
     * 
     * @return The resource ids of scripts to update the database of the game system.
     */
    public int[][] getUpdateScripts() {
        return updateScripts;
    }

    /**
     * Returns the resource ids of the images used by the game system.
     * 
     * @return The resource ids of the images used by the game system.
     */
    public int[] getImages() {
        return images;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    private interface DnDv35 {

        int ID = 1;

        String NAME = "Dungeons & Dragons v.3.5";

        String DATABASE_NAME = "dndv35_db";

        int[] CREATE_SCRIPTS = { //
        // scripts
                R.raw.create_database, //
                R.raw.dndv35_phb_data, //
                R.raw.dndv35_phb_spell, //
                R.raw.dndv35_phb_character, //
        };

        int[][] UPDATE_SCRIPTS = { //
        //
                { R.raw.dndv35_upgrade_01_to_02, }, //
                { R.raw.dndv35_upgrade_02_to_03, }, //
                { R.raw.dndv35_upgrade_03_to_04, }, //
                { R.raw.dndv35_upgrade_04_to_05, }, //
                { R.raw.dndv35_upgrade_05_to_06, }, //
                { R.raw.dndv35_upgrade_06_to_07, }, //
                { R.raw.dndv35_upgrade_07_to_08, }, //
                { R.raw.dndv35_upgrade_08_to_09, }, //
                { R.raw.dndv35_upgrade_09_to_10, }, //
                { R.raw.dndv35_upgrade_10_to_11, }, //
                { R.raw.dndv35_upgrade_11_to_12, }, //
                { R.raw.dndv35_upgrade_12_to_13, }, //
                { R.raw.dndv35_upgrade_13_to_14, }, //
                { R.raw.dndv35_upgrade_14_to_15, }, //
                { R.raw.dndv35_upgrade_15_to_16, }, //
                { R.raw.dndv35_upgrade_16_to_17, }, //
                { R.raw.dndv35_upgrade_17_to_18, }, //
                { R.raw.dndv35_upgrade_18_to_19, }, //
                { R.raw.dndv35_upgrade_19_to_20, }, //
                { R.raw.dndv35_upgrade_20_to_21, }, //
                { R.raw.dndv35_upgrade_21_to_22, }, //
                { R.raw.dndv35_upgrade_22_to_23, }, //
                { R.raw.dndv35_upgrade_23_to_24, }, //
                { R.raw.dndv35_upgrade_24_to_25, }, //
                { R.raw.dndv35_upgrade_25_to_26, }, //
                { R.raw.dndv35_upgrade_26_to_27, }, //
                { R.raw.dndv35_upgrade_27_to_28, }, //
                { R.raw.dndv35_upgrade_28_to_29, }, //
                { R.raw.dndv35_upgrade_29_to_30, }, //
                { R.raw.dndv35_upgrade_30_to_31, }, //
                { R.raw.dndv35_upgrade_31_to_32, }, //
                { R.raw.dndv35_upgrade_32_to_33, }, //
                { R.raw.dndv35_upgrade_33_to_34, }, //
                { R.raw.dndv35_upgrade_34_to_35, }, //
                { R.raw.dndv35_upgrade_35_to_36, }, //
                { R.raw.dndv35_upgrade_36_to_37, }, //
                { R.raw.dndv35_upgrade_37_to_38, }, //
                { R.raw.dndv35_upgrade_38_to_39, }, //
                { R.raw.dndv35_upgrade_39_to_40, }, //
                { R.raw.dndv35_upgrade_40_to_41, }, //
                { R.raw.dndv35_upgrade_41_to_42, }, //
                { R.raw.dndv35_upgrade_42_to_43, }, //
                {R.raw.dndv35_upgrade_43_to_44,}, //
                {R.raw.dndv35_upgrade_44_to_45,}, //
                {R.raw.dndv35_upgrade_45_to_46,}, //
                {R.raw.dndv35_upgrade_46_to_47,}, //
                {R.raw.dndv35_upgrade_47_to_48,}, //
                {R.raw.dndv35_upgrade_48_to_49,}, //
                {R.raw.dndv35_upgrade_49_to_50,}, //
                {R.raw.dndv35_upgrade_50_to_51,}, //
                {R.raw.dndv35_upgrade_51_to_52,}, //
                {R.raw.dndv35_upgrade_52_to_53,}, //
                {R.raw.dndv35_upgrade_53_to_54,}, //
                {R.raw.dndv35_upgrade_54_to_55,}, //
                {R.raw.dndv35_upgrade_55_to_56,}, //
                {R.raw.dndv35_upgrade_56_to_57,}, //
                {R.raw.dndv35_upgrade_57_to_58,}, //
                {R.raw.dndv35_upgrade_58_to_59,}, //
                {R.raw.dndv35_upgrade_59_to_60,}, //
                {R.raw.dndv35_upgrade_60_to_61,}, //
        };

        int[] IMAGES = { //
        // image resource id, face resource id
                R.drawable.char_default, R.drawable.char_default_face, // Default
                R.drawable.char_belvador, R.drawable.char_belvador_face, // Belvador
        };
    }

    private interface Pathfinder {

        int ID = 2;

        String NAME = "Pathfinder";

        String DATABASE_NAME = "pathfinder_db";

        int[] CREATE_SCRIPTS = { //
        // create scripts
                R.raw.create_database, //
                R.raw.pathfinder_crb_data, //
                R.raw.pathfinder_crb_spell, //
                R.raw.pathfinder_crb_character, //
                R.raw.pathfinder_apg_data, //
                R.raw.pathfinder_apg_spell, //
        };

        int[] IMAGES = { //
        // image resource id, face resource id
                R.drawable.char_default, R.drawable.char_default_face, // Default
                R.drawable.char_belvador, R.drawable.char_belvador_face // Belvador
        };

        int[][] UPDATE_SCRIPTS = {//
                // update scripts
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { NO_UPDATE_SCRIPT }, //
                { R.raw.pathfinder_upgrade_01_to_27, },//
                { R.raw.pathfinder_upgrade_27_to_28, },//
                { R.raw.pathfinder_upgrade_28_to_29, },//
                { R.raw.pathfinder_upgrade_29_to_30, },//
                { R.raw.pathfinder_upgrade_30_to_31, },//
                { R.raw.pathfinder_upgrade_31_to_32, },//
                { R.raw.pathfinder_upgrade_32_to_33, R.raw.pathfinder_upgrade_32_to_33_apg_data,
                        R.raw.pathfinder_apg_spell, },//
                { R.raw.pathfinder_upgrade_33_to_34, },//
                { R.raw.pathfinder_upgrade_34_to_35, },//
                { R.raw.pathfinder_upgrade_35_to_36, },//
                { R.raw.pathfinder_upgrade_36_to_37, },//
                { R.raw.pathfinder_upgrade_37_to_38, },//
                { R.raw.pathfinder_upgrade_38_to_39, },//
                { R.raw.pathfinder_upgrade_39_to_40, },//
                { R.raw.pathfinder_upgrade_40_to_41, },//
                { R.raw.pathfinder_upgrade_41_to_42, },//
                { R.raw.pathfinder_upgrade_42_to_43, },//
                {R.raw.pathfinder_upgrade_43_to_44,},//
                {R.raw.pathfinder_upgrade_44_to_45,},//
                {R.raw.pathfinder_upgrade_45_to_46,},//
                {R.raw.pathfinder_upgrade_46_to_47,},//
                {R.raw.pathfinder_upgrade_47_to_48,},//
                {R.raw.pathfinder_upgrade_48_to_49,},//
                {R.raw.pathfinder_upgrade_49_to_50,},//
                {R.raw.pathfinder_upgrade_50_to_51,},//
                {R.raw.pathfinder_upgrade_51_to_52,},//
                {R.raw.pathfinder_upgrade_52_to_53,},//
                {R.raw.pathfinder_upgrade_53_to_54,},//
                {R.raw.pathfinder_upgrade_54_to_55,},//
                {R.raw.pathfinder_upgrade_55_to_56,},//
                {R.raw.pathfinder_upgrade_56_to_57,},//
                {R.raw.pathfinder_upgrade_57_to_58,},//
                {R.raw.pathfinder_upgrade_58_to_59,},//
                {R.raw.pathfinder_upgrade_59_to_60,},//
                {R.raw.pathfinder_upgrade_60_to_61,},//
        };

    }
}

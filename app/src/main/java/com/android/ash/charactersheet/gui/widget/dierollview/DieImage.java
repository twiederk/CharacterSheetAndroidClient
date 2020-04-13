package com.android.ash.charactersheet.gui.widget.dierollview;

import com.android.ash.charactersheet.R;

/**
 * Contains all die images used by DieRollView. The images are stored in a three dimensional array [Die][Color][digit]
 */
interface DieImage {

    /**
     * The images are stored in a three dimensional array [Die][Color][digit]
     */
    int[][][] IMAGES = new int[][][]{
            // D2
            {
            // BLUE
            { R.drawable.d2_blue_01, R.drawable.d2_blue_02, }, },

            // D3
            {
            // BLUE
            { R.drawable.d3_blue_01, R.drawable.d3_blue_02, R.drawable.d3_blue_03, }, },

            // D4
            {
            // BLUE
            { R.drawable.d4_blue_01, R.drawable.d4_blue_02, R.drawable.d4_blue_03, R.drawable.d4_blue_04, }, },

            // D6
            {
            // BLUE
            { R.drawable.d6_blue_01, R.drawable.d6_blue_02, R.drawable.d6_blue_03, R.drawable.d6_blue_04,
                    R.drawable.d6_blue_05, R.drawable.d6_blue_06, }, },

            // D8
            {
            // BLUE
            { R.drawable.d8_blue_01, R.drawable.d8_blue_02, R.drawable.d8_blue_03, R.drawable.d8_blue_04,
                    R.drawable.d8_blue_05, R.drawable.d8_blue_06, R.drawable.d8_blue_07, R.drawable.d8_blue_08, }, },

            // D10
            {
            // BLUE
            { R.drawable.d10_blue_01, R.drawable.d10_blue_02, R.drawable.d10_blue_03, R.drawable.d10_blue_04,
                    R.drawable.d10_blue_05, R.drawable.d10_blue_06, R.drawable.d10_blue_07, R.drawable.d10_blue_08,
                    R.drawable.d10_blue_09, R.drawable.d10_blue_10, }, },

            // D12
            {
            // BLUE
            { R.drawable.d12_blue_01, R.drawable.d12_blue_02, R.drawable.d12_blue_03, R.drawable.d12_blue_04,
                    R.drawable.d12_blue_05, R.drawable.d12_blue_06, R.drawable.d12_blue_07, R.drawable.d12_blue_08,
                    R.drawable.d12_blue_09, R.drawable.d12_blue_10, R.drawable.d12_blue_11, R.drawable.d12_blue_12, }, },

            // D20
            {
                    // BLUE
                    { R.drawable.d20_blue_01, R.drawable.d20_blue_02, R.drawable.d20_blue_03, R.drawable.d20_blue_04,
                            R.drawable.d20_blue_05, R.drawable.d20_blue_06, R.drawable.d20_blue_07,
                            R.drawable.d20_blue_08, R.drawable.d20_blue_09, R.drawable.d20_blue_10,
                            R.drawable.d20_blue_11, R.drawable.d20_blue_12, R.drawable.d20_blue_13,
                            R.drawable.d20_blue_14, R.drawable.d20_blue_15, R.drawable.d20_blue_16,
                            R.drawable.d20_blue_17, R.drawable.d20_blue_18, R.drawable.d20_blue_19,
                            R.drawable.d20_blue_20 },

                    // RED
                    { R.drawable.d20_red_01, R.drawable.d20_red_02, R.drawable.d20_red_03, R.drawable.d20_red_04,
                            R.drawable.d20_red_05, R.drawable.d20_red_06, R.drawable.d20_red_07, R.drawable.d20_red_08,
                            R.drawable.d20_red_09, R.drawable.d20_red_10, R.drawable.d20_red_11, R.drawable.d20_red_12,
                            R.drawable.d20_red_13, R.drawable.d20_red_14, R.drawable.d20_red_15, R.drawable.d20_red_16,
                            R.drawable.d20_red_17, R.drawable.d20_red_18, R.drawable.d20_red_19, R.drawable.d20_red_20 },

                    // WHITE
                    { R.drawable.d20_white_01, }, },

            // D100
            {
            // BLUE
            { R.drawable.d100_blue_001, R.drawable.d100_blue_002, R.drawable.d100_blue_003, R.drawable.d100_blue_004,
                    R.drawable.d100_blue_005, R.drawable.d100_blue_006, R.drawable.d100_blue_007,
                    R.drawable.d100_blue_008, R.drawable.d100_blue_009, R.drawable.d100_blue_010,
                    R.drawable.d100_blue_011, R.drawable.d100_blue_012, R.drawable.d100_blue_013,
                    R.drawable.d100_blue_014, R.drawable.d100_blue_015, R.drawable.d100_blue_016,
                    R.drawable.d100_blue_017, R.drawable.d100_blue_018, R.drawable.d100_blue_019,
                    R.drawable.d100_blue_020, R.drawable.d100_blue_021, R.drawable.d100_blue_022,
                    R.drawable.d100_blue_023, R.drawable.d100_blue_024, R.drawable.d100_blue_025,
                    R.drawable.d100_blue_026, R.drawable.d100_blue_027, R.drawable.d100_blue_028,
                    R.drawable.d100_blue_029, R.drawable.d100_blue_030, R.drawable.d100_blue_031,
                    R.drawable.d100_blue_032, R.drawable.d100_blue_033, R.drawable.d100_blue_034,
                    R.drawable.d100_blue_035, R.drawable.d100_blue_036, R.drawable.d100_blue_037,
                    R.drawable.d100_blue_038, R.drawable.d100_blue_039, R.drawable.d100_blue_040,
                    R.drawable.d100_blue_041, R.drawable.d100_blue_042, R.drawable.d100_blue_043,
                    R.drawable.d100_blue_044, R.drawable.d100_blue_045, R.drawable.d100_blue_046,
                    R.drawable.d100_blue_047, R.drawable.d100_blue_048, R.drawable.d100_blue_049,
                    R.drawable.d100_blue_050, R.drawable.d100_blue_051, R.drawable.d100_blue_052,
                    R.drawable.d100_blue_053, R.drawable.d100_blue_054, R.drawable.d100_blue_055,
                    R.drawable.d100_blue_056, R.drawable.d100_blue_057, R.drawable.d100_blue_058,
                    R.drawable.d100_blue_059, R.drawable.d100_blue_060, R.drawable.d100_blue_061,
                    R.drawable.d100_blue_062, R.drawable.d100_blue_063, R.drawable.d100_blue_064,
                    R.drawable.d100_blue_065, R.drawable.d100_blue_066, R.drawable.d100_blue_067,
                    R.drawable.d100_blue_068, R.drawable.d100_blue_069, R.drawable.d100_blue_070,
                    R.drawable.d100_blue_071, R.drawable.d100_blue_072, R.drawable.d100_blue_073,
                    R.drawable.d100_blue_074, R.drawable.d100_blue_075, R.drawable.d100_blue_076,
                    R.drawable.d100_blue_077, R.drawable.d100_blue_078, R.drawable.d100_blue_079,
                    R.drawable.d100_blue_080, R.drawable.d100_blue_081, R.drawable.d100_blue_082,
                    R.drawable.d100_blue_083, R.drawable.d100_blue_084, R.drawable.d100_blue_085,
                    R.drawable.d100_blue_086, R.drawable.d100_blue_087, R.drawable.d100_blue_088,
                    R.drawable.d100_blue_089, R.drawable.d100_blue_090, R.drawable.d100_blue_091,
                    R.drawable.d100_blue_092, R.drawable.d100_blue_093, R.drawable.d100_blue_094,
                    R.drawable.d100_blue_095, R.drawable.d100_blue_096, R.drawable.d100_blue_097,
                    R.drawable.d100_blue_098, R.drawable.d100_blue_099, R.drawable.d100_blue_100 }, },

    };

}

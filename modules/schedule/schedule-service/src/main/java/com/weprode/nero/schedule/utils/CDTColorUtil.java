package com.weprode.nero.schedule.utils;

public class CDTColorUtil {

    private CDTColorUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getRandomColor() {
        int colorIndex = (int)(Math.random() * (mainColorsPool.length + additionalColorsPool.length));
        return getNewColor(colorIndex);
    }

    // Pick main colors first, then additional
    public static String getNewColor(int nbExistingColors) {

        if (nbExistingColors < mainColorsPool.length) {
            return mainColorsPool[nbExistingColors % mainColorsPool.length];
        } else {
            return additionalColorsPool[nbExistingColors % additionalColorsPool.length];
        }
    }

    private static final String[] mainColorsPool = new String[]{"#D20113","#3CB57D","#177F45","#E37C75","#259CE2","#4353B3","#F0512A","#7B87C9","#8D29A8","#616161","#F3BE39"};
    private static final String[] additionalColorsPool = new String[]{
            "#EA4335",
            "#4285F4",
            "#FBBC05",
            "#34A853",
            "#311B92",
            "#827717",
            "#F57F17",
            "#FF9792",
            "#C1E1E5",
            "#5D4037",
            "#FFCCBC",
            "#CBADFC",
            "#90A4AE",
            "#FBD199",
            "#2196F3",
            "#388E3C",
            "#F7ABEA",
            "#FBC02D",
            "#512DA8",
            "#94EECB",
            "#F7981D",
            "#0D47A1",
            "#BF360C",
            "#00796B",
            "#F9B256",
            "#CFD8DC",
            "#795548",
            "#CDDC39",
            "#455A64",
            "#E64A19",
            "#2196F3",
            "#9575CD",
            "#E8B4C7",
            "#A1887F",
            "#E7711B",
            "#AFE892",
            "#3E2723",
            "#73B2D9",
            "#E8AC84",
            "#607D8B",
            "#673AB7",
            "#D1C4E9",
            "#92E8A7",
            "#1976D2",
            "#FF5722",
            "#FF8A65",
            "#64B5F6",
            "#004D40",
            "#AFB42B",
            "#DCE775",
            "#81C784",
            "#4DB6AC",
            "#263238",
            "#009688"
    };

}

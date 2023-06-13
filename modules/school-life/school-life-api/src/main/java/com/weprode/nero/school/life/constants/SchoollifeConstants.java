package com.weprode.nero.school.life.constants;

public class SchoollifeConstants {

    private SchoollifeConstants() {
        throw new IllegalStateException("Constants class");
    }

    public static final int TYPE_RENVOI = 1;
    public static final int TYPE_RETENUE = 2;
    public static final int TYPE_TRAVAUX = 3;
    public static final int TYPE_DEPANNAGE = 4;
    public static final int TYPE_ETUDE = 5;

    public static final String RENVOI = "Renvoi";
    public static final String RETENUE = "Retenue";
    public static final String EPREUVE = "Travaux \u00e0 refaire";
    public static final String DEPANNAGE = "D\u00e9pannage";
    public static final String ETUDE = "Cercle d'\u00e9tude";

    public static final String RENVOI_COLOR = "#FF0000";
    public static final String RETENUE_COLOR = "#EDA12A";
    public static final String EPREUVE_COLOR = "#3694B7";
    public static final String DEPANNAGE_COLOR = "#8763CA";
    public static final String ETUDE_COLOR = "#32AC71";

    public static final int RENVOI_STATUS_CREATED = 0;
    public static final int RENVOI_STATUS_CLOSED = 1;
}

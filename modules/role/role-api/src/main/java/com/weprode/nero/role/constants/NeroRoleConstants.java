package com.weprode.nero.role.constants;

public class NeroRoleConstants {
	private NeroRoleConstants() {
		throw new IllegalStateException("Constants class");
	}

	public static final String ORGANIZATION_USER = "Organization User";
	// School admin
	public static final String GROUP_ADMIN = "Group_admin";
	public static final String COLLECTIVITY_ADMIN = "Collectivity_admin";
	public static final String GAR_ADMIN = "GAR_admin";
	public static final String ENT_ADMIN = "ENT_admin";
	public static final String SITE_ADMIN = "Site_admin";
	public static final String NEWS_ADMIN = "News_admin";

	public static final String MAIN_TEACHER = "Maitre de classe";
	public static final String EXTERNAL = "External";
	public static final String INSPECTOR = "Inspecteur";

	// SCONET National roles
	public static final String NATIONAL_1  = "National_1";	// Elève
	public static final String NATIONAL_2  = "National_2";	// Responsable légal
	public static final String NATIONAL_3  = "National_3";	// Enseignant.te
	public static final String NATIONAL_4  = "National_4";	// Personnel de direction

	public static final String NATIONAL_5  = "National_5";	// Personnel de vie scolaire
	public static final String NATIONAL_6  = "National_6";	// Personnel technique
	public static final String NATIONAL_7  = "National_7";	// Personnel académique
	public static final String NATIONAL_8  = "National_8";	// Personnel d'inspection académique / Inspecteur
	public static final String NATIONAL_9  = "National_9";	// Personnel de collectivité territoriale

	public static final String NATIONAL_20 = "National_20";	// Personnel administratif
	public static final String NATIONAL_21 = "National_21";	// Assistant.te  d'éducation
	public static final String NATIONAL_22 = "National_22";	// Assistant.te étranger
	public static final String NATIONAL_23 = "National_23";	// Personnel de direction
	public static final String NATIONAL_24 = "National_24";	// Enseignant.te documentaliste
	public static final String NATIONAL_25 = "National_25";	// Personnel de vie scolaire (Education)
	public static final String NATIONAL_26 = "National_26";	// Enseignement
	public static final String NATIONAL_27 = "National_27";	// Personnel de laboratoire
	public static final String NATIONAL_28 = "National_28";	// Personnel médico-social
	public static final String NATIONAL_29 = "National_29";	// Orientation

	public static final String NATIONAL_ELV = "National_elv"; // Elève
	public static final String NATIONAL_TUT = "National_tut"; // Responsable légal
	public static final String NATIONAL_ENS = "National_ens"; // Enseignant.te
	public static final String NATIONAL_DOC = "National_doc"; // Documentaliste
	public static final String NATIONAL_DIR = "National_dir"; // Personnel de direction
	public static final String NATIONAL_EVS = "National_evs"; // Personnel de vie scolaire
	public static final String NATIONAL_ETA = "National_eta"; // Personnel administratif
	public static final String NATIONAL_ACA = "National_aca"; // Personnel académique
	public static final String NATIONAL_COL = "National_col"; // Personnel de la collectivité territoriale

	// Custom national
	
	public static final String DIRECTION_INCLUSIVE = "Directeurs\u00B7trices";
	public static final String DOYEN = "Doyen";
	public static final String DOYEN_INCLUSIVE = "Doyens\u00B7ennes";

	public static final String STUDENT_INCLUSIVE = "El\u00E8ves";
	public static final String PARENT_INCLUSIVE = "Responsables l\u00e9gaux";
	public static final String TEACHER_INCLUSIVE = "Enseignants\u00B7tes";
	public static final String MAIN_TEACHER_INCLUSIVE = "Ma\u00EEtres de classe";
	public static final String PERSONAL_INCLUSIVE = "Personnels";

	public static final String CONSEILLER_ORIENTATION = "Conseiller d'orientation";
	public static final String CONSEILLER_ORIENTATION_INCLUSIVE = "Conseillers\u00B7\u00E8res d\u0027orientation";
	public static final String CONSEILLER_SOCIAL = "Conseiller social";
    public static final String CONSEILLER_SOCIAL_INCLUSIVE = "Conseillers\u00B7\u00E8res sociaux\u00b7ales";
	public static final String INFIRMIERE = "Infirmiere";
	public static final String INFIRMIERE_INCLUSIVE = "Infirmiers\u00B7\u00E8res";
	public static final String PSYCHOLOGUE = "Psychologue";
	public static final String PSYCHOLOGUE_INCLUSIVE = "Psychologues";

	public static final String ASSISTANT_TECHNIQUE = "Assistant technique";
	public static final String ASSISTANT_TECHNIQUE_INCLUSIVE = "Assistants\u00B7tes techniques";
	public static final String CAISSIER_COMPTABLE = "Caissier comptable";
	public static final String CAISSIER_COMPTABLE_INCLUSIVE = "Caissiers\u00B7\u00E8res comptables";
	public static final String BIBLIOTHECAIRE = "Bibliothecaire";
	public static final String BIBLIOTHECAIRE_INCLUSIVE = "Biblioth\u00e9caires";
	public static final String SECRETAIRE = "Secretaire";
	public static final String SECRETAIRE_INCLUSIVE = "Secr\u00e9taires";

	public static final String BETA_TESTER = "Beta-testeur";

	public static final String[] PERSONAL_ROLES = {
		NATIONAL_4, DOYEN, CONSEILLER_ORIENTATION, CONSEILLER_SOCIAL, 
		INFIRMIERE, PSYCHOLOGUE, ASSISTANT_TECHNIQUE, CAISSIER_COMPTABLE, 
		BIBLIOTHECAIRE, SECRETAIRE, NATIONAL_6
	};
	
	public static final String[] PAT_ROLES = {
		ASSISTANT_TECHNIQUE, CAISSIER_COMPTABLE, BIBLIOTHECAIRE, SECRETAIRE
	};

	public static final String[] EMPS_ROLES = {
		CONSEILLER_ORIENTATION, CONSEILLER_SOCIAL,INFIRMIERE, PSYCHOLOGUE
	};
	
	public static final String[] ROLES_LIST = {
		NATIONAL_1, NATIONAL_2, NATIONAL_3, NATIONAL_4, NATIONAL_5, NATIONAL_6, NATIONAL_7, 
		DOYEN, CONSEILLER_ORIENTATION, CONSEILLER_SOCIAL, INFIRMIERE, PSYCHOLOGUE, 
		ASSISTANT_TECHNIQUE, CAISSIER_COMPTABLE, BIBLIOTHECAIRE, SECRETAIRE, NATIONAL_6
	};
}

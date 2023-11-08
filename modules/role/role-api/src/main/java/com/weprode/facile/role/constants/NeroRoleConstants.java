package com.weprode.facile.role.constants;

public class NeroRoleConstants {
	private NeroRoleConstants() {
		throw new IllegalStateException("Constants class");
	}

	public static final String STUDENT = "Student";
	public static final String STUDENT_INCLUSIVE = "El\u00E8ves";

	public static final String RELATIVE = "Relative";
	public static final String PARENT_INCLUSIVE = "Responsables l\u00e9gaux";

	public static final String TEACHER = "Teacher";
	public static final String TEACHER_INCLUSIVE = "Enseignants\u00B7tes";

	public static final String MAIN_TEACHER = "Maitre de classe";
	public static final String MAIN_TEACHER_INCLUSIVE = "Ma\u00EEtres de classe";

	public static final String DIRECTION = "Direction";
	public static final String DIRECTION_INCLUSIVE = "Directeurs\u00B7trices";

	public static final String DOYEN = "Doyen";
	public static final String DOYEN_INCLUSIVE = "Doyens\u00B7ennes";

	public static final String PERSONAL = "Personnel";
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

	public static final String SCHOOL_ADMIN = "School_admin";
	public static final String ENT_ADMIN = "ENT_admin";

	// Used for manual affectation
	public static final String ORGANIZATION_USER = "Organization User";

	public static final String[] PERSONAL_ROLES = {
			DIRECTION, DOYEN, CONSEILLER_ORIENTATION, CONSEILLER_SOCIAL,
		INFIRMIERE, PSYCHOLOGUE, ASSISTANT_TECHNIQUE, CAISSIER_COMPTABLE, 
		BIBLIOTHECAIRE, SECRETAIRE
	};
	
}

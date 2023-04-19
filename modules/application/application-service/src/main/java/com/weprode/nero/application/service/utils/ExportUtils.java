package com.weprode.nero.application.service.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.weprode.nero.application.model.Application;
import com.weprode.nero.application.service.ApplicationLocalServiceUtil;
import com.weprode.nero.commons.constants.JSONConstants;
import com.weprode.nero.organization.service.OrgMappingLocalServiceUtil;
import com.weprode.nero.organization.service.UserOrgsLocalServiceUtil;
import com.weprode.nero.role.service.RoleUtilsLocalServiceUtil;
import com.weprode.nero.schedule.model.CDTSession;
import com.weprode.nero.schedule.model.ScheduleConfiguration;
import com.weprode.nero.schedule.service.CDTSessionLocalServiceUtil;
import com.weprode.nero.schedule.service.ScheduleConfigurationLocalServiceUtil;
import com.weprode.nero.user.model.LDAPMapping;
import com.weprode.nero.user.model.UserContact;
import com.weprode.nero.user.service.LDAPMappingLocalServiceUtil;
import com.weprode.nero.user.service.UserContactLocalServiceUtil;
import org.jsoup.internal.StringUtil;

import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class ExportUtils {

    private ExportUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final Log logger = LogFactoryUtil.getLog(ExportUtils.class.getName());

    private static final String ELEVE = "eleve";
    private static final String PARENT = "parent";
    private static final String PROF = "prof";
    private static final String NOM = "nom";
    private static final String PRENOM = "prenom";
    private static final String DIVISION = "division";
    private static final String ENT_PERSON_JOINTURE = "entPersonJointure";
    private static final String IDENTIFIANT = "identifiant";
    private static final String EMAIL = "email";

    public static String exportFile(long userId, long applicationId, long schoolId, String roleName) throws PortalException, SystemException {
        JSONObject resultExport = JSONFactoryUtil.createJSONObject();

        User currUser = UserLocalServiceUtil.getUser(userId);
        Application application = ApplicationLocalServiceUtil.getById(applicationId);
        String typeExport = application.getApplicationName();

        SimpleDateFormat nameFileDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String file;

        Calendar today = Calendar.getInstance();

        String fileExtension = ".csv";

        logger.info("Start export for application " + typeExport + ", schoolId " + schoolId + " and role " + roleName);

        List<User> listUser = UserLocalServiceUtil.getOrganizationUsers(schoolId);
        ResourceBundle messages = ResourceBundle.getBundle("content.Language", currUser.getLocale());

        String nfdNormalizedString = Normalizer.normalize(typeExport, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String normalizedTypeExport =  pattern.matcher(nfdNormalizedString).replaceAll(StringPool.BLANK);

        if (typeExport.equalsIgnoreCase("SACoche")
                || normalizedTypeExport.equalsIgnoreCase("Gepi")) {
            file = exportSacoche(roleName, listUser, messages);

        } else if (typeExport.equalsIgnoreCase("Paraschool")) {
            file = exportParaschool(roleName, listUser, messages);

        } else if (typeExport.equalsIgnoreCase("Cerise Pro Tertiaire") || typeExport.equalsIgnoreCase("CPro STI")) {
            file = exportCerisePro(roleName, listUser, messages);

        } else if (typeExport.equalsIgnoreCase("Moodle")) {
            file = exportMoodle(roleName, listUser, messages);

        } else if (typeExport.equalsIgnoreCase("E-sidoc")) {
            file = exportEsidoc(roleName, listUser);
            fileExtension = ".xml";
        } else if (typeExport.equalsIgnoreCase("Pearltrees")) {
            file = exportPearlTrees(roleName, listUser, messages, schoolId);

        } else if (typeExport.equalsIgnoreCase("GRR")) {
            file = exportGRR(roleName, listUser, messages, schoolId);

        } else {
            resultExport.put(JSONConstants.SUCCESS, false);
            resultExport.put(JSONConstants.MESSAGE, "Ce service n'a pas de type d'export");
            return resultExport.toString();
        }
        String type = roleName.equals("other") ? "ressource" : roleName;
        String filename = "Export_" + typeExport + "_" + type + "_" + nameFileDateFormat.format(today.getTime()) + fileExtension;

        byte[] b = file.getBytes();

        try {
            // TODO Messaging
            /* String fileEntryParams = CasierDepositLocalServiceUtil.sendReportToDropbox(currUser, b, filename);

            String hrefPDF = "<a href=\"" + PropsUtil.get(NeroSystemProperties.PORTAL_URL)
                    + "/c/document_library/get_file?" + fileEntryParams
                    + "\" target=\\'_blank\\'> <br /> <br />" + filename + "</a> ";

            resultExport.put(JSONConstants.MESSAGE, messages.getString("export_format_txt_depose") + hrefPDF);*/
            resultExport.put(JSONConstants.SUCCESS, true);

            logger.info("End export for application " + typeExport + ", schoolId " + schoolId + " and role " + roleName);
            return resultExport.toString();

        } catch (Exception e) {
            logger.error("Error when sending export file to dropbox", e);
        }


        resultExport.put(JSONConstants.SUCCESS, false);
        return resultExport.toString();

    }

    private static String exportSacoche(String userRole, List<User> userList, ResourceBundle messages) throws SystemException {
        StringBuilder file = new StringBuilder();

        SimpleDateFormat classicDateFormat = new SimpleDateFormat(JSONConstants.FRENCH_FORMAT);

        // Nom* ; Prénom ; Date de naissance ; Sexe ; Division* ; Login ; ( * -> obligatoire)
        file.append(messages.getString(NOM)).append(";").append(messages.getString(PRENOM)).append(";")
                .append(messages.getString("date-de-naissance"))
                .append(";").append(messages.getString("sexe")).append(";")
                .append(messages.getString(DIVISION)).append(";").append(messages.getString("login")).append(";\n");

        if (userRole.equals(ELEVE)) {
            for (User user : userList) {
                try {
                    if (user.isActive() && RoleUtilsLocalServiceUtil.isStudent(user)) {
                        String division = UserOrgsLocalServiceUtil.getStudentClassName(user);
                        String bday = classicDateFormat.format(user.getBirthday());
                        file.append(user.getLastName()).append(";").append(user.getFirstName()).append(";").append(bday).append(";").append(user.getJobTitle()).append(";").append(division).append(";").append(user.getScreenName()).append(";\n");
                    }
                } catch (Exception e) {
                    logger.error("SACoche : Error exporting student "+user.getUserId(), e);
                }
            }

        } else if (userRole.equalsIgnoreCase(PARENT)) {
            for (User user : userList) {
                try {
                    String bday = classicDateFormat.format(user.getBirthday());
                    if (user.isActive() && RoleUtilsLocalServiceUtil.isParent(user)) {
                        String division = "";
                        file.append(user.getLastName()).append(";").append(user.getFirstName()).append(";").append(bday).append(";")
                                .append(user.getJobTitle()).append(";").append(division).append(";").append(user.getScreenName()).append(";\n");
                    }
                } catch (Exception e) {
                    logger.error("SACoche : Error exporting parent "+user.getUserId(), e);
                }

            }

        } else if (userRole.equalsIgnoreCase(PROF)) {
            for (User user : userList) {
                try {
                    String bday = classicDateFormat.format(user.getBirthday());
                    if (user.isActive() && (RoleUtilsLocalServiceUtil.isTeacher(user) || RoleUtilsLocalServiceUtil.isPersonal(user))) {
                        file.append(user.getLastName()).append(";").append(user.getFirstName()).append(";").append(bday).append(";")
                                .append(user.getJobTitle()).append(";;").append(user.getScreenName()).append(";\n");
                    }
                } catch (Exception e) {
                    logger.error("SACoche : Error exporting teacher "+user.getUserId(), e);
                }
            }
        }

        return file.toString();
    }

    private static String exportParaschool(String userRole, List<User> userList, ResourceBundle messages) throws SystemException {
        StringBuilder file = new StringBuilder();

        if (userRole.equalsIgnoreCase(ELEVE)) {
            // -- Nom* ; Prénom ; Division* ; ENTPersonJointure ; --
            file.append(messages.getString(NOM)).append(";").append(messages.getString(PRENOM)).append(";")
                    .append(messages.getString(DIVISION)).append(";").append(messages.getString(ENT_PERSON_JOINTURE))
                    .append(";\n");

            for (User user : userList) {
                try {
                    if (user.isActive() && RoleUtilsLocalServiceUtil.isStudent(user)) {
                        String division = UserOrgsLocalServiceUtil.getStudentClassName(user);
                        String jointure = LDAPMappingLocalServiceUtil.getUserJointure(user.getUserId());
                        file.append(user.getLastName()).append(";").append(user.getFirstName()).append(";").append(division)
                                .append(";").append(jointure).append(";\n");
                    }

                } catch (Exception e) {
                    logger.error("Paraschool : Error exporting student "+user.getUserId(), e);
                }
            }

        } else if (userRole.equalsIgnoreCase(PARENT)) {
            // -- Nom* ; Prénom ; Division* ; ENTPersonJointure ; --
            file.append(messages.getString(NOM)).append(";").append(messages.getString(PRENOM)).append(";")
                    .append(messages.getString(DIVISION)).append(";")
                    .append(messages.getString(ENT_PERSON_JOINTURE)).append(";\n");

            for (User user : userList) {
                try {
                    if (user.isActive() && RoleUtilsLocalServiceUtil.isParent(user)) {
                        String division = "";
                        String jointure = LDAPMappingLocalServiceUtil.getUserJointure(user.getUserId());
                        file.append(user.getLastName()).append(";").append(user.getFirstName()).append(";")
                                .append(division).append(";").append(jointure).append(";\n");
                    }

                } catch (Exception e) {
                    logger.error("Paraschool : Error exporting parent "+user.getUserId()+" for Paraschool", e);
                }
            }

        } else if (userRole.equalsIgnoreCase(PROF)) {
            // -- Nom* ; Prénom ; ENTPersonJointure; --

            file.append(messages.getString(NOM)).append(";").append(messages.getString(PRENOM)).append(";")
                    .append(messages.getString(ENT_PERSON_JOINTURE)).append(";\n");

            for (User user : userList) {
                try {
                    if (user.isActive() && (RoleUtilsLocalServiceUtil.isTeacher(user) || RoleUtilsLocalServiceUtil.isPersonal(user))) {
                        String jointure = LDAPMappingLocalServiceUtil.getUserJointure(user.getUserId());
                        file.append(user.getLastName()).append(";").append(user.getFirstName()).append(";")
                                .append(jointure).append(";\n");
                    }
                } catch (Exception e) {
                    logger.error("Paraschool : Error exporting teacher "+user.getUserId()+" for Paraschool", e);
                }
            }
        }

        return file.toString();
    }

    private static String exportCerisePro(String userRole, List<User> userList, ResourceBundle messages) throws SystemException {
        StringBuilder file = new StringBuilder();

        if (userRole.equalsIgnoreCase(ELEVE)) {
            // uid ; ENTEleveStructRattachId
            file.append(messages.getString(IDENTIFIANT)).append(";").append(messages.getString("sconet")).append(";\n");

            for (User user : userList) {
                try {
                    if (user.isActive() && RoleUtilsLocalServiceUtil.isStudent(user)) {
                        String sconet = LDAPMappingLocalServiceUtil.getUserStructRattachId(user.getUserId());
                        LDAPMapping ldapMapping = LDAPMappingLocalServiceUtil.fetchLDAPMapping(user.getUserId());
                        String uid = ldapMapping.getUID();

                        // Append the user's data
                        file.append(uid).append(";").append(sconet).append(";\n");
                    }
                } catch (Exception e) {
                    logger.error("Cerise Pro : Error exporting student "+user.getUserId(), e);
                }
            }

        } else if (userRole.equalsIgnoreCase(PROF)) {
            // -- uid ; nom ; prenom ; --
            file.append(messages.getString(IDENTIFIANT)).append(";").append(messages.getString(NOM)).append(";").append(messages.getString(PRENOM)).append(";\n");

            for (User user : userList) {
                try {
                    if (user.isActive() && (RoleUtilsLocalServiceUtil.isTeacher(user) || RoleUtilsLocalServiceUtil.isPersonal(user))) {
                        LDAPMapping ldapMapping = LDAPMappingLocalServiceUtil.fetchLDAPMapping(user.getUserId());
                        String uid = ldapMapping.getUID();
                        file.append(uid).append(";").append(user.getLastName()).append(";").append(user.getFirstName()).append(";\n");
                    }
                } catch (Exception e) {
                    logger.error("Cerise Pro : Error exporting teacher "+user.getUserId(), e);
                }
            }
        }

        return file.toString();
    }


    private static String exportMoodle(String userRole, List<User> listUser, ResourceBundle messages) throws SystemException {
        StringBuilder file = new StringBuilder();

        if (userRole.equalsIgnoreCase(ELEVE)) {
            // -- screenname ; firstname ; lastname ; email ; cohort1 ; role1 ; --
            // cohort1 -> classe, role1 ->student
            // No email for students

            file.append(messages.getString("username")).append(";").append(messages.getString("firstname"))
                    .append(";").append(messages.getString("lastname")).append(";").append(messages.getString(EMAIL))
                    .append(";").append(messages.getString("cohort1")).append(";").append(messages.getString("role1")).append("\n");

            for (User user : listUser) {

                try {
                    if (user.isActive() && RoleUtilsLocalServiceUtil.isStudent(user)) {

                        String division = UserOrgsLocalServiceUtil.getStudentClassName(user);
                        file.append(user.getScreenName()).append(";").append(user.getFirstName()).append(";")
                                .append(user.getLastName()).append(";;;").append(division).append(";student\n");
                    }
                } catch (Exception e) {
                    logger.error("Moodle : Error exporting student "+user.getUserId(), e);
                }
            }

        } else if (userRole.equalsIgnoreCase(PROF)) {
            // -- username;firstname;lastname;email;cohort1;role1; --
            // cohort1 -> PROFS, role1 -> editingteacher

            file.append(messages.getString("username")).append(";").append(messages.getString("firstname"))
                    .append(";").append(messages.getString("lastname")).append(";").append(messages.getString(EMAIL))
                    .append(";").append(messages.getString("cohort1")).append(";").append(messages.getString("role1"))
                    .append("\n");

            for (User user : listUser) {
                try {
                    if (user.isActive() && (RoleUtilsLocalServiceUtil.isTeacher(user) || RoleUtilsLocalServiceUtil.isPersonal(user))) {
                        file.append(user.getScreenName()).append(";").append(user.getFirstName()).append(";")
                                .append(user.getLastName()).append(";").append(user.getEmailAddress())
                                .append(";PROFS;editingteacher\n");
                    }
                } catch (Exception e) {
                    logger.error("Moodle : Error exporting teacher "+user.getUserId(), e);
                }
            }
        }

        return file.toString();
    }

    private static String exportEsidoc(String userRole, List<User> userList) throws SystemException {
        StringBuilder file = new StringBuilder();

        final String fileHeader = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" + "<FICHES_XML>\n";
        final String fileFooter = "</FICHES_XML>\n";

        if (userRole.equalsIgnoreCase(ELEVE)) {
            // EMPRUNTEUR_M* : contient <Nom> <Prénom>
            // ADRESSE_M : adresse
            // CODE_POSTAL_M : code postal
            // VILLE_M : ville
            // TEL_M : son téléphone (optionnel)
            // MEL_M : email (optionnel)
            // STATUT_M_M : indique si c'est un eleve ou un professeur
            // CLASSE_M : classe
            // MOT_DE_PASSE_M* : mot de passe
            // IDENTITE_ENT_M* : clé permettant d'identifier
            // l'utilisateur
            file.append(fileHeader);

            for (User user : userList) {
                try {
                    if (user.isActive() && RoleUtilsLocalServiceUtil.isStudent(user)) {
                        file.append(getEsidocBorrower(user, "El\u00E8ve", true));
                    }
                } catch (Exception e) {
                    logger.error("E-sidoc : Error exporting student "+user.getUserId(), e);
                }
            }
            file.append(fileFooter);

        } else if (userRole.equalsIgnoreCase(PARENT)) {
            // EXPORT e-sidoc
            // EMPRUNTEUR_M* : contient <Nom> <Prénom>
            // ADRESSE_M : adresse
            // CODE_POSTAL_M : code postal
            // VILLE_M : ville
            // TEL_M : son téléphone (optionnel)
            // MEL_M : email (optionnel)
            // STATUT_M_M : indique si c'est un eleve ou un professeur
            // CLASSE_M : classe
            // MOT_DE_PASSE_M* : mot de passe
            // IDENTITE_ENT_M* : clé permettant d'identifier
            // l'utilisateur
            file.append(fileHeader);

            for (User user : userList) {
                try {
                    if (user.isActive() && RoleUtilsLocalServiceUtil.isParent(user)) {
                        file.append(getEsidocBorrower(user, "Parent", true));
                    }

                } catch (Exception e) {
                    logger.error("E-sidoc : Error exporting parent "+user.getUserId(), e);
                }
            }
            file.append(fileFooter);

        } else if (userRole.equalsIgnoreCase(PROF)) {
            // EMPRUNTEUR_M* : contient <Nom> <Prénom>
            // ADRESSE_M : adresse
            // CODE_POSTAL_M : code postal
            // VILLE_M : ville
            // TEL_M : son téléphone (optionnel)
            // MEL_M : email (optionnel)
            // STATUT_M_M : indique si c'est un eleve ou un professeur
            // CLASSE_M : classe
            // MOT_DE_PASSE_M* : mot de passe
            // IDENTITE_ENT_M* : clé permettant d'identifier
            // l'utilisateur
            file.append(fileHeader);

            for (User user : userList) {
                try {
                    if (user.isActive() && (RoleUtilsLocalServiceUtil.isTeacher(user) || RoleUtilsLocalServiceUtil.isPersonal(user))) {
                        file.append(getEsidocBorrower(user, "Enseignant", false));
                    }

                } catch (Exception e) {
                    logger.error("erreur", e);
                }
            }
            file.append(fileFooter);
        }

        return file.toString();
    }

    private static String getEsidocBorrower(User user, String status, boolean hasDivision) {
        StringBuilder borrower = new StringBuilder();

        String division = StringPool.BLANK;
        String jointure = LDAPMappingLocalServiceUtil.getUserJointure(user.getUserId());

        String address = StringPool.BLANK;
        String zipCode = StringPool.BLANK;
        String city = StringPool.BLANK;
        UserContact userContact = UserContactLocalServiceUtil.getUserContactByUserId(user.getUserId());
        if (userContact != null) {
            String fullAddress = userContact.getAddress();
            int lastDollarIndex = fullAddress.lastIndexOf(StringPool.DOLLAR);
            address = fullAddress.substring(0, lastDollarIndex);
            String cityCode = fullAddress.substring(lastDollarIndex + 1);
            int spaceIndex = cityCode.indexOf(" ");
            zipCode = cityCode.substring(0, spaceIndex);
            city = cityCode.substring(spaceIndex + 1);
        }
        String mail = user.getEmailAddress();

        borrower.append("<EMPRUNTEURS>\n");
        borrower.append("<EMPRUNTEUR_M>").append(user.getLastName()).append(" ").append(user.getFirstName()).append("</EMPRUNTEUR_M>\n");
        borrower.append(address.isEmpty() ? StringPool.BLANK : "<ADRESSE_M>" + address + "</ADRESSE_M>\n");
        borrower.append(zipCode.isEmpty() ? StringPool.BLANK : "<CODE_POSTAL_M>" + zipCode + "</CODE_POSTAL_M>\n");
        borrower.append(city.isEmpty() ? StringPool.BLANK : "<VILLE_M>" + city + "</VILLE_M>\n");
        borrower.append(mail.isEmpty() ? StringPool.BLANK : "<MEL_M>" + mail + "</MEL_M>\n");
        borrower.append("<STATUT_M_M>").append(status).append("</STATUT_M_M>\n");
        borrower.append("<TYPE_EMPRUNTEUR_M>Usager</TYPE_EMPRUNTEUR_M>\n");
        if (hasDivision) {
            borrower.append(division.isEmpty() ? StringPool.BLANK : "<CLASSE_M>" + division + "</CLASSE_M>\n");
        }
        borrower.append("<MOT_DE_PASSE_M>").append(generatePassword(8)).append("</MOT_DE_PASSE_M>\n");
        borrower.append(jointure.isEmpty() ? StringPool.BLANK : "<IDENTITE_ENT_M>" + jointure + "</IDENTITE_ENT_M>\n");
        borrower.append("</EMPRUNTEURS>\n");

        return borrower.toString();
    }

    private static String exportPearlTrees(String userRole, List<User> userList, ResourceBundle messages, Long etabId) throws PortalException, SystemException {
        StringBuilder file = new StringBuilder();

        Organization currentSchool = OrganizationLocalServiceUtil.getOrganization(etabId);
        List<Role> roles = new ArrayList<>();
        try {

            if (userRole.equalsIgnoreCase(ELEVE)) {
                roles.add(RoleUtilsLocalServiceUtil.getStudentRole());
            } else if (userRole.equalsIgnoreCase(PARENT)) {
                roles.add(RoleUtilsLocalServiceUtil.getParentRole());
            } else if (userRole.equalsIgnoreCase(PROF)) {
                roles.add(RoleUtilsLocalServiceUtil.getTeacherRole());
                roles.add(RoleUtilsLocalServiceUtil.getDirectionRole());
                roles.add(RoleUtilsLocalServiceUtil.getSchoolLifeRole());
                roles.add(RoleUtilsLocalServiceUtil.getAdministrativeRole());
                roles.add(RoleUtilsLocalServiceUtil.getAcademicRole());
            }
        } catch (Exception e) {
            logger.error(e);
        }

        if (!roles.isEmpty()) {
            // -- uid ; Nom ; Prenom ; Email ; classe ; RNE ; --
            file.append(messages.getString("uid")).append(";").append(messages.getString(NOM)).append(";")
                    .append(messages.getString(PRENOM)).append(";").append(messages.getString(EMAIL))
                    .append(";").append(messages.getString("class")).append(";").append(messages.getString("RNE"))
                    .append(";\n");

            for (User user : userList) {
                try {
                    if (user.isActive() && !Collections.disjoint(user.getRoles(), roles)) {

                        List<String> classes = new ArrayList<>();
                        List<Organization> userClasses = UserOrgsLocalServiceUtil.getUserClasses(user, false);
                        for (Organization userClass : userClasses) {
                            if (userClass.getOrganizationId() != etabId) {
                                classes.add(userClass.getName());
                            }
                        }

                        file.append(user.getScreenName()).append(";").append(user.getLastName()).append(";")
                                .append(user.getFirstName()).append(";").append(user.getEmailAddress()).append(";")
                                .append(StringUtil.join(classes, ",")).append(";")
                                .append(OrgMappingLocalServiceUtil.getOrganizationStrutUAI(currentSchool)).append(";\n");
                    }
                } catch (Exception e) {
                    logger.error("Pearltrees : Error exporting user "+user.getFullName(), e);
                }
            }

        }

        return file.toString();
    }

    private static String exportGRR(String userRole, List<User> userList, ResourceBundle messages, Long etabId) throws SystemException {
        StringBuilder file = new StringBuilder();

        List<Role> roles = new ArrayList<>();
        try {

            if (userRole.equalsIgnoreCase(ELEVE)) {
                roles.add(RoleUtilsLocalServiceUtil.getStudentRole());
            } else if (userRole.equalsIgnoreCase(PARENT)) {
                roles.add(RoleUtilsLocalServiceUtil.getParentRole());
            } else if (userRole.equalsIgnoreCase(PROF)) {
                roles.add(RoleUtilsLocalServiceUtil.getTeacherRole());
                roles.add(RoleUtilsLocalServiceUtil.getDirectionRole());
                roles.add(RoleUtilsLocalServiceUtil.getSchoolLifeRole());
                roles.add(RoleUtilsLocalServiceUtil.getAdministrativeRole());
                roles.add(RoleUtilsLocalServiceUtil.getAcademicRole());
            }
        } catch (Exception e) {
            logger.error(e);
        }

        if (!roles.isEmpty()) {
            // -- Identifiant ; Nom ; Prenom ; Mot de passe ; Adresse email ; Type d'utilisateur ; Statut ; Type d'authentification ; --
            file.append(messages.getString(IDENTIFIANT)).append(";").append(messages.getString(NOM))
                    .append(";").append(messages.getString(PRENOM)).append(";")
                    .append(messages.getString("password")).append(";").append(messages.getString("email_address"))
                    .append(";").append(messages.getString("user_type")).append(";")
                    .append(messages.getString("status")).append(";").append(messages.getString("auth_type"))
                    .append(";\n");

            // Type d'utilisateur : "visiteur", "utilisateur", "administrateur"
            // Statut : "actif" ou "inactif"
            // Type d'authentification : "local" ou "ext"

            for (User user : userList) {
                try {
                    if (user.isActive() && !Collections.disjoint(user.getRoles(), roles)) {
                        String email = user.getEmailAddress();
                        if (email.isEmpty()) {
                            email = "-";
                        }

                        String userType = "utilisateur";
                        if (RoleUtilsLocalServiceUtil.isDirectionMember(user) || RoleUtilsLocalServiceUtil.isSchoolAdmin(user, etabId)) {
                            userType = "administrateur";
                        }
                        file.append(user.getScreenName()).append(";").append(user.getLastName()).append(";")
                                .append(user.getFirstName()).append(";;").append(email).append(";").append(userType)
                                .append(";actif;ext\n");
                    }
                } catch (Exception e) {
                    logger.error("GRR : Error exporting user "+user.getFullName(), e);
                }
            }

        } else if (userRole.equalsIgnoreCase("other")) {
            // date du jour; heure de début; heure de fin; ressource; description; type
            //file.append(messages.getString("date_jour")+ ";" +messages.getString("heure_debut") + ";" + messages.getString("heure_fin")
            // 	+ ";" + messages.getString("ressource") + ";" + messages.getString("description") + ";" + messages.getString("type") + "\n");

            ScheduleConfiguration conf = ScheduleConfigurationLocalServiceUtil.getSchoolConfiguration(etabId);

            List<CDTSession> sessions = CDTSessionLocalServiceUtil.getSchoolSessions(etabId,
                    conf.getStartSessionsDate(), conf.getEndSessionsDate());
            logger.info("GRR resource export : "+sessions.size()+" sessions for schoolId = " + etabId);

            for (CDTSession session : sessions) {
                if (!session.getRoom().isEmpty()) {
                    SimpleDateFormat dayFormat = new SimpleDateFormat(JSONConstants.ENGLISH_FORMAT);
                    SimpleDateFormat hourFormat = new SimpleDateFormat("HH'h'mm");

                    // Type A = lesson
                    file.append(dayFormat.format(new Date()) + ";" + hourFormat.format(session.getSessionStart()) + ";" +
                            hourFormat.format(session.getSessionEnd()) + ";"+session.getRoom()+";"+ session.getTitle() +";A\n");
                }
            }
        }

        return file.toString();
    }

    private static String generatePassword(int size) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        StringBuilder pwd = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            pwd.append(alphabet.charAt(rand.nextInt(alphabet.length())));
        }

        return pwd.toString();
    }

}

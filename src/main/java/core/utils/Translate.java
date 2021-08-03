package core.utils;

import salesforce.config.ConfigEnvVar;

import java.util.ResourceBundle;

public class Translate {

    private static final String I18_FILE_PATH = "internationalization/i18NOpportunity";

    public static String translateField(final String key) {
        return ResourceBundle.getBundle(I18_FILE_PATH, ConfigEnvVar.getInstance().getLanguage()).getString(key);
    }
}

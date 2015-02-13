package org.vetech.lrms.core.web.json.bean;

/**
 * Created by nessy on 06/02/15.
 */
public class LocaleBean {
    int localesID = 0;
    String localeName = "";
    String localeCode = "";

    public int getLocalesID() {
        return localesID;
    }

    public void setLocalesID(int localesID) {
        this.localesID = localesID;
    }

    public String getLocaleName() {
        return localeName;
    }

    public void setLocaleName(String localeName) {
        this.localeName = localeName;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }
}

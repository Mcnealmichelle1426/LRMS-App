package org.vetech.lrms.core.web.json.bean;

/**
 * Created by nessy on 06/02/15.
 */
public class MiscExpBean {
    int miscID = 0;
    String miscName = "";
    String description = "";
    int amount = 0;
    boolean active = false;

    public int getMiscID() {
        return miscID;
    }

    public void setMiscID(int miscID) {
        this.miscID = miscID;
    }

    public String getMiscName() {
        return miscName;
    }

    public void setMiscName(String miscName) {
        this.miscName = miscName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

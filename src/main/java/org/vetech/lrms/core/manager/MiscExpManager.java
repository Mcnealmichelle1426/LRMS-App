package org.vetech.lrms.core.manager;

import org.vetech.lrms.core.system.DatabaseManager;
import org.vetech.lrms.core.system.IdGen;
import org.vetech.lrms.core.web.json.bean.*;
import org.vetech.lrms.core.web.json.response.RestPayload;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by nessy on 06/02/15.
 */
public class MiscExpManager {
    DatabaseManager databaseManager = DatabaseManager.getInstance();
    IdGen idGen = new IdGen();

    public MiscExpBean create(MiscExpBean MiscExpBean) {
        int miscExpID = databaseManager.insertReturnID("Insert into misc_exp (uuid) values (" + idGen.UuIDGen() + ")");

        return getMiscExp(miscExpID);
    }

    public MiscExpBean getMiscExp(int miscExpID) {
        MiscExpBean miscExpBean = new MiscExpBean();

        try {
            ResultSet miscExpRS = databaseManager.getData("select * from misc_exp where misc_ID =" + miscExpID + "");
            while (miscExpRS.next()) {
                /**
                 * This will get the MiscExp_ID of a specific column from the resultSet.
                 */
                int miscExpDataID = miscExpRS.getInt("misc_ID");

                /**
                 * This sets the initial json object for the miscellaneous expenditure list
                 */
                miscExpBean.setMiscID(miscExpDataID);

                String miscName = miscExpRS.getString("misc_Name");
                String description = miscExpRS.getString("description");
                int amount = miscExpRS.getInt("amount");
                String status = miscExpRS.getString("status");

                miscExpBean.setMiscName(miscName);
                miscExpBean.setDescription(description);
                miscExpBean.setAmount(amount);
                miscExpBean.setActive(false);

            }
        } catch (SQLException e) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, e);
        }

        return miscExpBean;
    }


    public RestPayload getMiscExps(int page, int startIndex, int maxRecords, boolean showAll, String active) {
        RestPayload restPayload = new RestPayload();

        restPayload.setPage(page);
        restPayload.setMaxItems(maxRecords);

        List<MiscExpBean> formattedMiscExps = new ArrayList<>();

        try {
            ResultSet miscExpRS = databaseManager.getData("select * from misc_exp");
            while (miscExpRS.next()) {
                MiscExpBean miscExpBean = new MiscExpBean();
                /**
                 * This will get the MiscExp_ID of a specific column from the resultSet.
                 */
                int miscExpDataID = miscExpRS.getInt("misc_ID");

                /**
                 * This sets the initial json object for the miscellaneous expenditure list
                 */
                miscExpBean.setMiscID(miscExpDataID);

                String miscName = miscExpRS.getString("misc_Name");
                String description = miscExpRS.getString("description");
                int amount = miscExpRS.getInt("amount");
                String status = miscExpRS.getString("status");

                miscExpBean.setMiscName(miscName);
                miscExpBean.setDescription(description);
                miscExpBean.setAmount(amount);
                miscExpBean.setActive(false);

            }
        }catch (SQLException e) {
            Logger.getLogger(MiscExpManager.class.getName()).log(Level.SEVERE, null, e);
        }

        restPayload.setInlineCount(formattedMiscExps.size());
        restPayload.setResults(formattedMiscExps);

        return restPayload;


    }

    public static void main(String args[]) {
        MiscExpManager miscExpManager = new MiscExpManager();

        Object miscExp = miscExpManager.getMiscExp(1);

        System.out.println(miscExp);
    }
}
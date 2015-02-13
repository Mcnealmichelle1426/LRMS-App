package org.vetech.lrms.core.manager;

import org.vetech.lrms.core.hibernate.models.EntityConfig;
import org.vetech.lrms.core.hibernate.models.Location;
import org.vetech.lrms.core.system.*;
import org.vetech.lrms.core.web.json.bean.EntityConfigBean;
import org.vetech.lrms.core.web.json.bean.LocationBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alex on 2/6/15.
 */
public class EntityConfigManager {
	HibernateUtilHelper hibernateUtilHelper = new HibernateUtilHelper();
	CRUD crud = new CRUD(hibernateUtilHelper.getMerchandisingHU());
	DatabaseManager databaseManager = DatabaseManager.getInstance();
	IdGen idGen = new IdGen();

	public EntityConfigBean getEntityByID(int id) {
		EntityConfigBean entityConfigBean = new EntityConfigBean();

		try {
			ResultSet entityRS = databaseManager.getData("select * from entity_Config where entity_ID = "+id+"");
			while (entityRS.next()) {
//				int locID = entityRS.getInt("location_ID");
				entityConfigBean.setEntityName(entityRS.getString("entity_Name"));
				entityConfigBean.setPostalAddress(entityRS.getString("postal_Address"));
				entityConfigBean.setPostalCode(entityRS.getInt("postal_Code"));
				entityConfigBean.setEntityEmail(entityRS.getString("entity_Email"));
				entityConfigBean.setPhoneNo(entityRS.getString("phone_NO"));
				entityConfigBean.setNhifNo(entityRS.getInt("nhifNo"));
				entityConfigBean.setNssfNo(entityRS.getInt("nssfNo"));
				entityConfigBean.setVatNo(entityRS.getInt("vatNO"));
				entityConfigBean.setPinNo(entityRS.getInt("pinNo"));

//				ResultSet locationRS = databaseManager.getData("select * from location where location_ID = "+locID+"");
//				LocationBean locationBean = new LocationBean();
//				while (locationRS.next()) {
//					locationBean.setLocationID(locationRS.getInt("location_ID"));
//					locationBean.setLocationName(locationRS.getString("location_Name"));
//				}

				entityConfigBean.setLocationName(entityRS.getString("location_Name"));
			}
		} catch (SQLException e) {
			Logger.getLogger(EntityConfigManager.class.getName()).log(Level.SEVERE, null,e);
		}

		return entityConfigBean;
	}

	public EntityConfigBean createEntity(EntityConfigBean entityConfigBean) {

		int entityID = databaseManager.insertReturnID("insert into entity_Config (entity_Name, postal_Address, postal_Code, location_Name, entity_Email, phone_NO, nhifNo, nssfNo, vatNO, pinNo, uuid) values ('"+entityConfigBean.getEntityName()+"', '"+entityConfigBean.getPostalAddress()+"', '"+entityConfigBean.getPostalCode()+"', '"+entityConfigBean.getLocationName()+"', '"+entityConfigBean.getEntityEmail()+"', '"+entityConfigBean.getPhoneNo()+"', '"+entityConfigBean.getNhifNo()+"', '"+entityConfigBean.getNssfNo()+"', '"+entityConfigBean.getVatNo()+"', '"+entityConfigBean.getPinNo()+"','"+idGen.UuIDGen()+"')");

		return getEntityByID(entityID);
	}

	public List<EntityConfigBean> getEntities() {
		List<EntityConfigBean> formattedEntities = new ArrayList<>();

		EntityConfigBean entityConfigBean = new EntityConfigBean();

		try {
			ResultSet entityRS = databaseManager.getData("select * from entity_Config");
			while (entityRS.next()) {
				entityConfigBean.setEntityName(entityRS.getString("entity_Name"));
				entityConfigBean.setPostalAddress(entityRS.getString("postal_Address"));
				entityConfigBean.setPostalCode(entityRS.getInt("postal_Code"));
				entityConfigBean.setEntityEmail(entityRS.getString("entity_Email"));
				entityConfigBean.setPhoneNo(entityRS.getString("phone_NO"));

//				int locID = entityRS.getInt("location_ID");
//
//				ResultSet locationRS = databaseManager.getData("select * from location where location_ID = "+locID+"");
//				LocationBean locationBean = new LocationBean();
//				while (locationRS.next()) {
//					locationBean.setLocationID(locationRS.getInt("location_ID"));
//					locationBean.setLocationName(locationRS.getString("location_Name"));
//				}
				entityConfigBean.setLocationName("location_Name");

				formattedEntities.add(entityConfigBean);
			}
		} catch (SQLException e) {
			Logger.getLogger(EntityConfigManager.class.getName()).log(Level.SEVERE, null,e);
		}

		return formattedEntities;
	}
}

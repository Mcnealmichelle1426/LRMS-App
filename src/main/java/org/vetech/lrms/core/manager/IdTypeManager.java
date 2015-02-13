package org.vetech.lrms.core.manager;

import org.vetech.lrms.core.hibernate.models.IdentificationType;
import org.vetech.lrms.core.system.*;
import org.vetech.lrms.core.web.json.bean.IdTypeBean;
import org.vetech.lrms.core.web.json.response.RestPayload;
import org.vetech.lrms.core.web.json.response.SearchPayLoad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 2/5/15.
 */
public class IdTypeManager {
	HibernateUtilHelper hibernateUtilHelper = new HibernateUtilHelper();
	CRUD crud = new CRUD(hibernateUtilHelper.getMerchandisingHU());
	DatabaseManager databaseManager = DatabaseManager.getInstance();
	IdGen idGen = new IdGen();

	public IdTypeBean createIdType(IdTypeBean idTypeBean) {
		IdentificationType identificationType = new IdentificationType();

		identificationType.setTypeId(idGen.randomIdGen());
		identificationType.setTypeName(idTypeBean.getTypeName());
		identificationType.setTypeCode(idTypeBean.getTypeCode());
		identificationType.setActive(idTypeBean.isActive());
		identificationType.setUuid(idGen.UuIDGen());

		return getIdTypeByKey((int)crud.save(identificationType));
	}

	public IdTypeBean getIdTypeByKey(int id) {
		@SuppressWarnings("unchecked") List<IdentificationType> existing = crud.findByPrimaryKey(id, "from IdentificationType where active = true and typeId = :pk");

		IdTypeBean idTypeBean = null;
		if (existing.size() > 0) {
			IdentificationType identificationType = existing.get(0);

			idTypeBean = new IdTypeBean();

			idTypeBean.setTypeID(identificationType.getTypeId());
			idTypeBean.setTypeName(identificationType.getTypeName());
			idTypeBean.setTypeCode(identificationType.getTypeCode());
			idTypeBean.setActive(identificationType.getActive());
		}

		return idTypeBean;
	}

	public SearchPayLoad searchId(String searchKey) {
		SearchPayLoad searchPayLoad = new SearchPayLoad();

		IdTypeBean idTypeBean = new IdTypeBean();

		@SuppressWarnings("unchecked") List<Object> existing = crud.getObject("From IdentificationType where typeName like '%"+searchKey+"%'");

		List<IdTypeBean> formattedResult = new ArrayList<>();

		for (Object typeObject : existing) {
			IdentificationType identificationType = (IdentificationType)typeObject;

			idTypeBean.setTypeID(identificationType.getTypeId());
			idTypeBean.setTypeName(identificationType.getTypeName());
			idTypeBean.setTypeCode(identificationType.getTypeCode());
			idTypeBean.setActive(identificationType.getActive());

			formattedResult.add(idTypeBean);
		}

		searchPayLoad.setResults(formattedResult);

		return searchPayLoad;
	}

	public List<IdTypeBean> getIdTypes() {
//		@SuppressWarnings("unchecked") List<Object> idTypeList = crud.getObject("From IdentificationType where active = true");

		List<IdTypeBean> typeList = new ArrayList<>();

//		for (Object typeObject : idTypeList) {
//			IdentificationType type = (IdentificationType)typeObject;
//
//			IdTypeBean idTypeBean = new IdTypeBean();
//			idTypeBean.setTypeID(type.getTypeId());
//			idTypeBean.setTypeName(type.getTypeName());
//			idTypeBean.setTypeCode(type.getTypeCode());
//			idTypeBean.setActive(type.getActive());
//
//			typeList.add(idTypeBean);
//		}

		try {
			ResultSet idTypeRS = databaseManager.getData("select * from identification_Type where active = true");
			while (idTypeRS.next()) {
				IdTypeBean idTypeBean = new IdTypeBean();

				idTypeBean.setTypeID(idTypeRS.getInt("type_ID"));
				idTypeBean.setTypeName(idTypeRS.getString("type_Name"));
				idTypeBean.setTypeCode(idTypeRS.getString("type_Code"));
				idTypeBean.setActive(idTypeRS.getBoolean("active"));

				typeList.add(idTypeBean);
			}
		} catch (SQLException e) {

		}


		return typeList;
	}

	public RestPayload getIdTypes(int page, int startIndex, int maxRecords, boolean showAll, String active) {
		RestPayload restPayload = new RestPayload();
		restPayload.setPage(page);
		restPayload.setMaxItems(maxRecords);

		String query = "";

		if (showAll) {
			query = "from IdentificationType";
		} else {
			if (active.equalsIgnoreCase("true")) {
				query = "from IdentificationType where active = true";
			} else {
				query = "from IdentificationType where active = false";
			}
		}

		List<Object> idTypeObjectList  =crud.getObject(query,startIndex,maxRecords);

		List<IdTypeBean> typeList = new ArrayList<>();

		for (Object typeObject : idTypeObjectList) {
			IdentificationType type = (IdentificationType)typeObject;

			IdTypeBean idTypeBean = new IdTypeBean();
			idTypeBean.setTypeID(type.getTypeId());
			idTypeBean.setTypeName(type.getTypeName());
			idTypeBean.setTypeCode(type.getTypeCode());
			idTypeBean.setActive(type.getActive());

			typeList.add(idTypeBean);
		}
		restPayload.setInlineCount(typeList.size());
		restPayload.setResults(typeList);

		return restPayload;
	}
}

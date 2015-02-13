package org.vetech.lrms.core.manager;

import org.vetech.lrms.core.hibernate.models.CaseClasses;
import org.vetech.lrms.core.system.CRUD;
import org.vetech.lrms.core.system.HibernateUtilHelper;
import org.vetech.lrms.core.system.IdGen;
import org.vetech.lrms.core.web.json.bean.CaseClassBean;
import org.vetech.lrms.core.web.json.response.RestPayload;
import org.vetech.lrms.core.web.json.response.SearchPayLoad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 2/3/15.
 */
public class CaseClassManager {
	HibernateUtilHelper hibernateUtilHelper = new HibernateUtilHelper();
	CRUD crud = new CRUD(hibernateUtilHelper.getMerchandisingHU());
	IdGen idGen = new IdGen();

	public RestPayload getCaseClasses(int page, int startIndex, int maxRecords, boolean showAll, String active) {
		RestPayload restPayload = new RestPayload();
		restPayload.setPage(page);
		restPayload.setMaxItems(maxRecords);

		String query = "";

		if (showAll) {
			query = "from CaseClasses";
		} else {
			if (active.equalsIgnoreCase("true")) {
				query = "from CaseClasses where active = true";
			} else {
				query = "from CaseClasses where active = false";
			}
		}

		List<Object> caseClassList = crud.getObject(query, startIndex -1, maxRecords);

		List<CaseClassBean> formattedCaseClasses = new ArrayList<>();

		for (Object caseClassesObject : caseClassList) {
			CaseClasses caseClasses = (CaseClasses) caseClassesObject;

			CaseClassBean caseClassBean = new CaseClassBean();

			caseClassBean.setId(caseClasses.getCaseId());
			caseClassBean.setCode(caseClasses.getCaseCode());
			caseClassBean.setName(caseClasses.getCaseName());
			caseClassBean.setDescription(caseClasses.getDescription());
			caseClassBean.setActive(caseClasses.getActive());
			caseClassBean.setIfRoot(caseClasses.getIfRoot());

			formattedCaseClasses.add(caseClassBean);
		}
		restPayload.setResults(formattedCaseClasses);
		restPayload.setInlineCount(formattedCaseClasses.size());

		return restPayload;
	}

	public SearchPayLoad getAutoCaseClass(String searchKey) {
		SearchPayLoad searchPayLoad = new SearchPayLoad();

		CaseClassBean caseClassBean = new CaseClassBean();

			@SuppressWarnings("unchecked")
			List<Object> existing = crud.getObject("From CaseClasses where caseName like '%" + searchKey + "%' or caseCode like '%" + searchKey + "%'");

			List<CaseClassBean> formattedResult = new ArrayList<>();

			for (Object cases : existing) {
				CaseClasses caseClasses = (CaseClasses) cases;

				caseClassBean.setId(caseClasses.getCaseId());
				caseClassBean.setCode(caseClasses.getCaseCode());
				caseClassBean.setName(caseClasses.getCaseName());
				caseClassBean.setDescription(caseClasses.getDescription());
				caseClassBean.setActive(caseClasses.getActive());
				caseClassBean.setIfRoot(caseClasses.getIfRoot());

				formattedResult.add(caseClassBean);
			}
			searchPayLoad.setResults(formattedResult);

		return searchPayLoad;
	}

	public CaseClassBean getCaseClass(String code) {
		@SuppressWarnings("unchecked")
		List<CaseClasses> existing = crud.findByPrimaryKey(code, "from CaseClasses where caseCode = :pk");

		CaseClassBean caseClassBean = null;

		if (existing.size() > 0) {
			CaseClasses caseClasses = existing.get(0);

			caseClassBean = new CaseClassBean();

			caseClassBean.setId(caseClasses.getCaseId());
			caseClassBean.setCode(caseClasses.getCaseCode());
			caseClassBean.setName(caseClasses.getCaseName());
			caseClassBean.setDescription(caseClasses.getDescription());
			caseClassBean.setIfRoot(caseClasses.getIfRoot());
			caseClassBean.setActive(caseClasses.getActive());
		}
		return caseClassBean;
 	}

	public CaseClassBean createCaseClass(CaseClassBean caseClassBean) {
		CaseClasses caseClasses = new CaseClasses();

		caseClassBean.setId(idGen.randomIdGen());
		caseClasses.setCaseCode(caseClassBean.getCode());
		caseClasses.setCaseName(caseClassBean.getName());
		caseClasses.setDescription(caseClassBean.getDescription());
		caseClasses.setActive(caseClassBean.isActive());
		caseClasses.setIfRoot(caseClassBean.isIfRoot());
		caseClasses.setUuid(idGen.UuIDGen());

		return getCaseClass((String) crud.save(caseClasses));
	}

	public CaseClassBean updateCaseClass(CaseClassBean caseClassBean) {
		CaseClasses caseClasses = new CaseClasses();

		caseClassBean.setId(caseClasses.getCaseId());
		caseClasses.setCaseName(caseClassBean.getName());
		caseClasses.setCaseCode(caseClassBean.getCode());
		caseClasses.setDescription(caseClassBean.getDescription());
		caseClasses.setIfRoot(caseClassBean.isIfRoot());
		caseClasses.setActive(caseClassBean.isActive());

		boolean status = crud.saveOrUpdate(caseClasses);

		return getCaseClass(caseClassBean.getCode());
	}

//	public Ca
}

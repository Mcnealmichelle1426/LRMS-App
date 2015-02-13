package org.vetech.lrms.core.manager;

import org.vetech.lrms.core.system.*;

/**
 * Created by alex on 2/6/15.
 */
public class InstructionManager {
	HibernateUtilHelper hibernateUtilHelper = new HibernateUtilHelper();
	CRUD crud = new CRUD(hibernateUtilHelper.getMerchandisingHU());
	DatabaseManager databaseManager = DatabaseManager.getInstance();
	IdGen idGen = new IdGen();

}

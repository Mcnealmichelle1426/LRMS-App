package org.vetech.lrms.core.system;

import java.util.Random;
import java.util.UUID;

/**
 * Created by alex on 2/3/15.
 */
public class IdGen {

	public String UuIDGen() {
		String uuid = UUID.randomUUID().toString();

		return uuid;
	}

	public Integer randomIdGen() {
		Random randomID = new Random();

		int systemID = randomID.nextInt(100000);

		return systemID;
	}

	public static void main(String args[]) {
		IdGen idGen = new IdGen();

		String id = idGen.UuIDGen();

		int randomId = idGen.randomIdGen();

		System.out.println(randomId);

		System.out.println(id);
	}
}

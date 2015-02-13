package org.vetech.lrms.core.web.json.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexkimani on 2/2/15.
 */
public class SearchPayLoad {
	public List getResults() {
		return results;
	}

	public void setResults(List results) {
		this.results = results;
	}

	List results = new ArrayList();
}

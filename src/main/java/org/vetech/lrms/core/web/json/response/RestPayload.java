/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.vetech.lrms.core.web.json.response;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kimani
 */
public class RestPayload
{
    int maxItems = 12;
    int page = 1;
    int inlineCount = 0;
    List results = new ArrayList();

    public int getMaxItems()
    {
        return maxItems;
    }

    public void setMaxItems(int maxItems)
    {
        this.maxItems = maxItems;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getInlineCount()
    {
        return inlineCount;
    }

    public void setInlineCount(int inlineCount)
    {
        this.inlineCount = inlineCount;
    }

    public List getResults()
    {
        return results;
    }

    public void setResults(List results)
    {
        this.results = results;
    }
}

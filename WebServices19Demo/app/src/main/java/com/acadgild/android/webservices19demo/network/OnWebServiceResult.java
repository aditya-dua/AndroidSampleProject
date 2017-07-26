package com.acadgild.android.webservices19demo.network;

import com.acadgild.android.webservices19demo.utils.CommonUtilities;

/**
 * Created by AdityaDua on 12/07/17.
 */

public interface OnWebServiceResult {

    public void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type);
}

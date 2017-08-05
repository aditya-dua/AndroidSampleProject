package com.adityadua.webservices18demo.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.adityadua.webservices18demo.utils.CommonUtilities;
import com.adityadua.webservices18demo.utils.OnWebServiceResult;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**￼
 * Created by AdityaDua on 05/08/17.
 */

public class CallAddr extends AsyncTask<String,Void,String> {
    Context context;
    String result="";
    FormEncodingBuilder formBody;
    String url;
    OnWebServiceResult resultlistener;
    CommonUtilities.SERVICE_TYPE service_type;
    Request request;

    public Request getRequest(){
        return request;

    }

    public CallAddr(String url, Context context, FormEncodingBuilder formBody, OnWebServiceResult resultlistener, CommonUtilities.SERVICE_TYPE service_type) {
        this.url = url;
        this.context = context;
        this.formBody = formBody;
        this.resultlistener = resultlistener;
        this.service_type = service_type;
    }

    // Not a UIThread method
    @Override
    protected String doInBackground(String... params) {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(120, TimeUnit.SECONDS);
        client.setReadTimeout(120,TimeUnit.SECONDS);

        RequestBody body = formBody.build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try{
            Response response = client.newCall(request).execute();
            if(!response.isSuccessful()){
                result= response.toString();
            }
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i("URL is ::",url);
        Log.i("Response is ::",result);
    }
}

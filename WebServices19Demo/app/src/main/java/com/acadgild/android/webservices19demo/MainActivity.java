package com.acadgild.android.webservices19demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.acadgild.android.webservices19demo.adaptor.DataAdaptor;
import com.acadgild.android.webservices19demo.models.DataHandler;
import com.acadgild.android.webservices19demo.network.CallAddr;
import com.acadgild.android.webservices19demo.network.NetworkStatus;
import com.acadgild.android.webservices19demo.network.OnWebServiceResult;
import com.acadgild.android.webservices19demo.utils.CommonUtilities;
import com.squareup.okhttp.FormEncodingBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnWebServiceResult{

    String url = "http://api.themoviedb.org/3/movie/tt0816692/credits?api_key=8496be0b2149805afa458ab8ec27560c";
    List<DataHandler> model = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        hitRequest();
    }
    private void hitRequest(){
        FormEncodingBuilder parameters = new FormEncodingBuilder();
        parameters.add("page","1");

        if(NetworkStatus.getInstance(this).isConnectedToInternet()){
            CallAddr call = new CallAddr(this,url,parameters, CommonUtilities.SERVICE_TYPE.GET_DATA,this);
            call.execute();
        }else
        {
            Toast.makeText(this,"No Network ! You are offline.",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type) {
        Log.i("Response IS::","type ="+type+"XML is ::"+result);

        try{
            JSONObject obj= new JSONObject(result);
            JSONArray arr=obj.getJSONArray("cast");
            for(int i=0;i<arr.length();i++){
                JSONObject jsonObject=arr.getJSONObject(i);
                DataHandler data= new DataHandler();
                data.setCast_id(jsonObject.getInt("cast_id"));
                data.setOrder(jsonObject.getInt("order"));
                data.setName(jsonObject.getString("name"));
                data.setCharacter(jsonObject.getString("character"));
                model.add(data);
            }
            DataAdaptor adaptor = new DataAdaptor(this,model);
            recyclerView.setAdapter(adaptor);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

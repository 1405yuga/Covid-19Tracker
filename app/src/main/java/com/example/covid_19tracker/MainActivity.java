package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcv;
    TextView tv;
    LinearLayoutManager layoutManager;
    List<Model> casesList;
    myAdapter adapter;
    private static final String CovidURL="https://data.covid19india.org/state_district_wise.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv=findViewById(R.id.rv);
        tv=findViewById(R.id.text1);
        tv.setText("Below is the sample box to view covid cases. Click on down arrow to get started.");
        getData();

        setData();

    }

    private void getData() {
        casesList=new ArrayList<>();
        RequestQueue rq= Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObject=new JsonObjectRequest(Request.Method.GET, CovidURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Iterator states= response.keys();
                        while(states.hasNext()){
                            //getting statenames
                            String state=(String)states.next();
                            try {
                                JSONObject obj=response.getJSONObject(state).getJSONObject("districtData");
                                Iterator districts= obj.keys();
                                while(districts.hasNext()){
                                    //getting districtnames
                                    String district=(String)districts.next();
                                    System.out.println("ganesh "+district);
                                    JSONObject obj1=obj.getJSONObject(district);
                                    String active=obj1.getString("active");
                                    String deceased= obj1.getString("deceased");
                                    String confirm= obj1.getString("confirmed");
                                    String recover= obj1.getString("recovered");
                                    Model m =new Model(state,district,active,confirm,deceased,recover);
                                    casesList.add(m);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        rq.add(jsonObject);

        casesList.add(new Model("Sample-State name","District name","active cases will be displayed","confirmed cases will be displayed","deceased cases will be displayed","recovered cases will be displayed"));
    }

    private void setData() {
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcv.setLayoutManager(layoutManager);
        adapter=new myAdapter(casesList);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
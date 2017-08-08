package syaiful.finalpro.englishcourse.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import syaiful.finalpro.englishcourse.R;
import syaiful.finalpro.englishcourse.adapter.AdapterExample;
import syaiful.finalpro.englishcourse.adapter.AdapterFormula;
import syaiful.finalpro.englishcourse.config.Config;
import syaiful.finalpro.englishcourse.custom.CustomItemClickListener;

public class Example extends AppCompatActivity implements CustomItemClickListener{
    private RecyclerView recyclerview;
    private Toolbar toolbar;
    //volley
    private com.android.volley.RequestQueue requestQueue;
    private StringRequest stringRequest;
    ArrayList<HashMap<String, String>> list_data;
    private Config config = new Config();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        //setting layout recycler
        recyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(llm);

        GETDATA();
        setSupportActionBar(toolbar);
        //action bar back press
        ActionBar actionBar = getSupportActionBar();
        if (actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    public void GETDATA(){
        Intent in = getIntent();
        String id = in.getStringExtra(config.TAG_ID_FRM);

        requestQueue    = Volley.newRequestQueue(getApplicationContext());
        list_data   = new ArrayList<HashMap<String, String>>();
        stringRequest   = new StringRequest(Request.Method.GET, config.URL_EXAMPLE + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response: ", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //JSONArray jsonArray = jsonObject.getJSONArray(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("content");
                    for (int a = 0; a < jsonArray.length(); a++) {


                        //add new lines
                        String id="",title="", subexample1="",subexample2="";
                        //JSONObject json = jsonArray.getJSONObject(a);
                        JSONObject json = jsonArray.getJSONObject(a);
                        id  = json.getString(config.TAG_ID_EXAM);
                        title = json.getString(config.TAG_TITLE_EXAM);
                        subexample1 = json.getString(config.TAG_SUBEXAMPLE1);
                        subexample2 = json.getString(config.TAG_SUBEXAMPLE2);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(config.TAG_ID_EXAM, id);
                        map.put(config.TAG_TITLE_EXAM, title);
                        map.put(config.TAG_SUBEXAMPLE1, subexample1);
                        map.put(config.TAG_SUBEXAMPLE2, subexample2);
                        list_data.add(map);
                        AdapterExample adapter = new AdapterExample(getApplicationContext(), list_data);
                        recyclerview.setAdapter(adapter);
                        adapter.setClickListener(Example.this);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);


    }
    //back press methode
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}

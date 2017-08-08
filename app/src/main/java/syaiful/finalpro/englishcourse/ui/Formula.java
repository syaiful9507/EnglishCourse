package syaiful.finalpro.englishcourse.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import syaiful.finalpro.englishcourse.R;
import syaiful.finalpro.englishcourse.adapter.AdapterDetailCourse;
import syaiful.finalpro.englishcourse.adapter.AdapterFormula;
import syaiful.finalpro.englishcourse.config.Config;
import syaiful.finalpro.englishcourse.custom.CustomItemClickListener;
import syaiful.finalpro.englishcourse.fragments.ListCourseFragments;

public class Formula extends AppCompatActivity  implements CustomItemClickListener{

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
        setContentView(R.layout.activity_formula);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        setSupportActionBar(toolbar);
        //action bar back press
        ActionBar actionBar = getSupportActionBar();
        if (actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Klik Rumus jika ingin melihat contoh", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //setting layout recycler

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(llm);

        GETDATA();





    }

    public void GETDATA(){
        Intent in = getIntent();
        String id = in.getStringExtra(config.TAG_ID_CATEGORY);

        requestQueue    = Volley.newRequestQueue(getApplicationContext());
        list_data   = new ArrayList<HashMap<String, String>>();
        stringRequest   = new StringRequest(Request.Method.GET, config.URL_FORMULA + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response: ", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //JSONArray jsonArray = jsonObject.getJSONArray(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("content");
                    for (int a = 0; a < jsonArray.length(); a++) {


                        //add new lines
                        String id="",title="", sub1="",sub2="",sub3="";
                        //JSONObject json = jsonArray.getJSONObject(a);
                        JSONObject json = jsonArray.getJSONObject(a);
                        id  = json.getString(config.TAG_ID_FRM);
                        title = json.getString(config.TAG_TITLE_FRM);
                        sub1 = json.getString(config.TAG_SUB1);
                        sub2 = json.getString(config.TAG_SUB2);
                        sub3 = json.getString(config.TAG_SUB3);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(config.TAG_ID_FRM, id);
                        map.put(config.TAG_TITLE_FRM, title);
                        map.put(config.TAG_SUB1, sub1);
                        map.put(config.TAG_SUB2, sub2);
                        map.put(config.TAG_SUB3, sub3);
                        list_data.add(map);
                        AdapterFormula adapter = new AdapterFormula(getApplicationContext(), list_data);
                        recyclerview.setAdapter(adapter);
                        adapter.setClickListener(Formula.this);
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

        HashMap<String , String > list = list_data.get(position);

        Intent in = new Intent(getApplicationContext(), Example.class);
        in.putExtra(config.TAG_ID_FRM, list.get(config.TAG_ID_FRM));
        startActivity(in);


    }
}

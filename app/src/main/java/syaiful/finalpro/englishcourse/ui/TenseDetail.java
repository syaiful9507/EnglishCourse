package syaiful.finalpro.englishcourse.ui;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
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
import syaiful.finalpro.englishcourse.config.Config;
import syaiful.finalpro.englishcourse.fragments.ListCourseFragments;

public class TenseDetail extends AppCompatActivity {
    private RecyclerView recyclerview;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    //volley
    private com.android.volley.RequestQueue requestQueue;
    private StringRequest stringRequest;

    ArrayList<HashMap<String, String>> list_data;
    private Config config = new Config();
    ImageView imageView;
    FloatingActionButton FormulaFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tense_detail);

        FormulaFAB  = (FloatingActionButton) findViewById(R.id.formulaFAB);
        imageView   = (ImageView) findViewById(R.id.backdrop);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.maincollapsing);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setting layout recycler
        recyclerview = (RecyclerView) findViewById(R.id.recyclerView);
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

        FormulaFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = getIntent();
                String id = in.getStringExtra(config.TAG_ID_CATEGORY);
                Intent intent = new Intent(getApplicationContext(), Formula.class);
                intent.putExtra(config.TAG_ID_CATEGORY, id);
                startActivity(intent);

            }
        });
    }


    public void GETDATA(){
        Intent in = getIntent();
        String id = in.getStringExtra(config.TAG_ID_CATEGORY);

        requestQueue    = Volley.newRequestQueue(getApplicationContext());
        list_data   = new ArrayList<HashMap<String, String>>();
        stringRequest   = new StringRequest(Request.Method.GET, config.URL_DETAIL_TENSE + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response: ", response);
                String title = null, image = null;
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //JSONArray jsonArray = jsonObject.getJSONArray(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("content");
                    for (int a = 0; a < jsonArray.length(); a++) {
                        String content = "";
                        String subtitle = "";
                        title = "";
                        image = "";
                        //JSONObject json = jsonArray.getJSONObject(a);
                        JSONObject json = jsonArray.getJSONObject(a);
                        content = json.getString(config.TAG_CONTENT);
                        subtitle = json.getString(config.TAG_SUBTITLE);
                        title = json.getString(config.TAG_TITLE);
                        image = json.getString(config.TAG_IMAGE);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(config.TAG_SUBTITLE, subtitle);
                        map.put(config.TAG_CONTENT, content);
                        list_data.add(map);
                        AdapterDetailCourse adapter = new AdapterDetailCourse(getApplicationContext(), list_data);
                        recyclerview.setAdapter(adapter);
                        //adapter.setClickListener(CourseDetail.this);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                collapsingToolbarLayout.setTitle(title);
                Glide.with(getApplicationContext())
                        .load(Config.IMAGE + image)
                        .placeholder(R.drawable.enlish)
                        .into(imageView);

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
            navigateUpTo(new Intent(this, ListCourseFragments.class));
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}

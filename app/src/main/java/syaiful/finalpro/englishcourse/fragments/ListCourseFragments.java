package syaiful.finalpro.englishcourse.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import syaiful.finalpro.englishcourse.adapter.AdapterList;
import syaiful.finalpro.englishcourse.config.Config;
import syaiful.finalpro.englishcourse.custom.CustomItemClickListener;
import syaiful.finalpro.englishcourse.ui.ContentCourse;
import syaiful.finalpro.englishcourse.ui.CourseDetail;

public class ListCourseFragments extends Fragment implements CustomItemClickListener{
    private RecyclerView recyclerview;
    private com.android.volley.RequestQueue requestQueue;
    private StringRequest stringRequest;
    ArrayList<HashMap<String, String>> list_data;
    private Config config = new Config();

    public ListCourseFragments(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_course_fragments, container, false);

        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplication());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        getdata();
        return view;
    }



    public void getdata(){

        requestQueue    = Volley.newRequestQueue(getActivity().getApplicationContext());
        list_data   = new ArrayList<HashMap<String, String>>();
        stringRequest   = new StringRequest(Request.Method.GET, config.URL_LISTCOURSE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response: ", response);
                try {
                    //JSONObject jsonObject = new JSONObject(response);
                    //JSONArray jsonArray = jsonObject.getJSONArray(response);
                    JSONArray jsonArray = new JSONArray(response);
                    for (int a = 0; a < jsonArray.length(); a++) {
                        //JSONObject json = jsonArray.getJSONObject(a);
                        JSONObject json = jsonArray.getJSONObject(a);
                        String id_ct = json.getString(config.TAG_ID_CATEGORY);
                        String title = json.getString(config.TAG_TITLE);
                        String image      = json.getString(config.TAG_IMAGE);
                        final HashMap<String, String> map = new HashMap<String, String>();

                        map.put(config.TAG_ID_CATEGORY, id_ct);
                        map.put(config.TAG_TITLE, title);
                        map.put(config.TAG_IMAGE, image);

                        /*map.put("id", json.getString("id"));
                        map.put("subjects", json.getString("subjects"));
                        map.put("content", json.getString("content"));*/

                        list_data.add(map);
                        AdapterList adapter = new AdapterList(getActivity(), list_data);
                        recyclerview.setAdapter(adapter);
                        adapter.setClickListener(ListCourseFragments.this);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);


    }

    @Override
    public void onItemClick(View view, int position) {
        HashMap<String , String > list = list_data.get(position);
        Intent in = new Intent(getActivity(), CourseDetail.class);
        in.putExtra(config.TAG_ID_CATEGORY, list.get(config.TAG_ID_CATEGORY));
        in.putExtra(config.TAG_TITLE, list.get(config.TAG_TITLE));
        in.putExtra(config.TAG_IMAGE, list.get(config.TAG_IMAGE));
        startActivity(in);

    }
}

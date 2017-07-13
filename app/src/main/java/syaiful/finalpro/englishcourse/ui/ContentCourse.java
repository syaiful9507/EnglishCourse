package syaiful.finalpro.englishcourse.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import syaiful.finalpro.englishcourse.R;
import syaiful.finalpro.englishcourse.config.Config;
public class ContentCourse extends AppCompatActivity {

    TextView txtid, txtTitle, txtContent;
    public String id_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_course);
        Intent intent = getIntent();


        txtid       = (TextView) findViewById(R.id.txtid);
        txtTitle    = (TextView) findViewById(R.id.txtTitle);
        txtContent  = (TextView) findViewById(R.id.txtContent);
        txtid.setText(intent.getStringExtra(Config.TAG_ID_CATEGORY));
        id_ = txtid.getText().toString();
        GETDATA();
    }

    private void GETDATA(){
        String url = Config.URL_LISTCONTENT+ txtid.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ContentCourse.this, error.getMessage().toString(), Toast.LENGTH_SHORT ).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){
        String title ="", content = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("content");
            JSONObject collegeData = result.getJSONObject(0);
            title = collegeData.getString(Config.TAG_TITLE);
            content = collegeData.getString(Config.TAG_CONTENT);

        } catch (JSONException e){
            e.printStackTrace();
        }
        txtTitle.setText(title);
        txtContent.setText(content);
    }
}

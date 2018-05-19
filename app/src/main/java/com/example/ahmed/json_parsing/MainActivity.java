package com.example.ahmed.json_parsing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    MyAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    Button btn_show;
    JsonArrayRequest jsonArrayRequest;
    String ser_url = "https://ahmed-atia.000webhostapp.com/show.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_show = (Button) findViewById(R.id.button_show);
        final ArrayList<ListItem> items = new ArrayList<>();

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(MainActivity.this);
        }
        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, ser_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int i = 0;
                items.clear();
                while (i < response.length()) {
                    try {
                        JSONObject jo = response.getJSONObject(i);
                        items.add(new ListItem(jo.getString("Name"), jo.getString("Email"), jo.getString("Phone")));
                        i++;
                        Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "error ...", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });


        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
                layoutManager = new LinearLayoutManager(MainActivity.this);

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);

                adapter = new MyAdapter(items, this);
                recyclerView.setAdapter(adapter);
                requestQueue.add(jsonArrayRequest);
            }
        });

    }
}

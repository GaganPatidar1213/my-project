package com.example.newsy;

// BlogActivity.java
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import java.util.List;

public class BlogActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BlogAdapter blogAdapter;
    private List<blog> blogList;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogs);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        blogList = new ArrayList<>();
        blogAdapter = new BlogAdapter(blogList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(blogAdapter);

        fetchBlogs();
    }

    private void fetchBlogs() {
        progressBar.setVisibility(View.VISIBLE);
        String url = "https://newsdata.io/api/1/news?apikey=pub_583628e575da1bf2fc79bb61e50f6380b7ad0&q=article&country=in&language=hi"; // Replace with your actual API endpoint

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressBar.setVisibility(View.GONE);
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject blogObject = response.getJSONObject(i);
                                String title = blogObject.getString("title");
                                String content = blogObject.getString("content");
                                String author = blogObject.getString("author");

                                blogList.add(new blog(title, content, author));
                            }
                            blogAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        error.printStackTrace();
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
    }
}


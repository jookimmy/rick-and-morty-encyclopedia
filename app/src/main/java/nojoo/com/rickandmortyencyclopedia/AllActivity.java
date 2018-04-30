package nojoo.com.rickandmortyencyclopedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllActivity extends AppCompatActivity implements OnItemClickListener {
    private static final String TAG = "AllActivity";
    private static RequestQueue requestQueue;
    GridView gridView;
    CharactersAdapter charactersAdapter;
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> imgurl = new ArrayList<String>();
    ArrayList<Item> data = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_all);
        GridView gridView = findViewById(R.id.gridview);

        for (int i = 1; i <= 5; i++) {
            startAPICall(Integer.toString(i));
        }
        initView();
        fillData();
        setDataAdapter();
    }
    private void setDataAdapter() {
        charactersAdapter = new CharactersAdapter(this, R.layout.item_gridview, data);
        gridView.setAdapter(charactersAdapter);
    }
    private void initView() {
        gridView = (GridView)findViewById(R.id.gridview);
        gridView.setOnItemClickListener(this);
    }
    private void fillData() {
        for (int i = 1; i <= 5; i++) {
            startAPICall(Integer.toString(i));
        }
        for (int i = 0; i < name.size(); i++) {
            data.add(new Item(name.get(i), getDrawable(R.drawable.ic_launcher_background)));
        }
    }

    public void onItemClick(final AdapterView<?> arg0, final View view, final int position, final long id)
    {
    }
    void startAPICall(final String page) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://rickandmortyapi.com/api/character/?page=" + page,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                JSONArray results = response.getJSONArray("results");
                                for (int i = 0; i < results.length(); i++) {
                                    name.add(results.getJSONObject(i).getString("name"));
                                    imgurl.add(results.getJSONObject(i).getString("image"));
                                }
                                Log.d(TAG, response.toString());
                            } catch (JSONException ignored) { }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

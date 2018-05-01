package nojoo.com.rickandmortyencyclopedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

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

public class MaleGender extends AppCompatActivity implements OnItemClickListener {
    private static final String TAG = "MaleGender";
    private static RequestQueue requestQueue;
    GridView gridView;
    CharactersAdapter charactersAdapter;
    ArrayList<Item> data = new ArrayList<Item>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male);
        requestQueue = Volley.newRequestQueue(this);
        GridView gridView = findViewById(R.id.gridview);

        initView();
        for (int i = 1; i <= 17; i++) {
            startAPICall(i);

        }
    }

    private void setDataAdapter() {
        charactersAdapter = new CharactersAdapter(this, R.layout.item_gridview, data);
        gridView.setAdapter(charactersAdapter);
    }

    private void initView() {
        gridView = (GridView) findViewById(R.id.grid_male);
        gridView.setOnItemClickListener(this);
    }

    public void onItemClick(final AdapterView<?> arg0, final View view, final int position, final long id) {
        startActivity(new Intent(MaleGender.this, CharacterCalled.class));
    }


    void startAPICall(int page) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://rickandmortyapi.com/api/character/?page=" + Integer.toString(page) + "&gender=male",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                JSONArray results = response.getJSONArray("results");
                                for (int j = 0; j < results.length(); j++) {
                                    data.add(new Item(results.getJSONObject(j).getString("name"), results.getJSONObject(j).getString("image"), results.getJSONObject(j).getString("id")));
                                }
                                setDataAdapter();
                                Log.d(TAG, data.toString());
                            } catch (JSONException ignored) {
                            }
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

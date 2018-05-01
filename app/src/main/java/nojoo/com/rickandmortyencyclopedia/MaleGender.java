package nojoo.com.rickandmortyencyclopedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MaleGender extends AppCompatActivity {
    private static final String TAG = "GenderActivity";
    private static RequestQueue requestQueue;
    CharactersAdapter charactersAdapter;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> imgurl = new ArrayList<>();
    ArrayList<String> status = new ArrayList<>();
    ArrayList<String> species = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male);
        requestQueue = Volley.newRequestQueue(this);
        startAPICall();

    }
    void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://rickandmortyapi.com/api/character/?gender=male",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                JSONArray results = response.getJSONArray("results");
                                for (int i = 0; i < results.length(); i++) {
                                    name.add(results.getJSONObject(i).getString("name"));
                                    status.add(results.getJSONObject(i).getString("status"));
                                    species.add(results.getJSONObject(i).getString("species"));
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

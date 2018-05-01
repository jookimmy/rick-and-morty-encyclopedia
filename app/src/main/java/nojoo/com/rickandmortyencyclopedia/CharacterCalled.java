package nojoo.com.rickandmortyencyclopedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CharacterCalled extends AppCompatActivity{
    private static final String TAG = "Character Called";
    private static RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_calledchar);
        String id = getIntent().getStringExtra("CHAR_ID");
        startAPICall(id);


    }
    private void startAPICall(String id) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://rickandmortyapi.com/api/character/" + id,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                Log.d(TAG, response.toString());
                                String name = "Name: " + response.getString("name");
                                String status = "Status: " + response.getString("status");
                                String species = "Species: " + response.getString("species");
                                String location = "Location: " + response.getJSONObject("location").getString("name");
                                String gender = "Gender: " + response.getString("gender");
                                String imgurl = response.getString("image");
                                ((TextView) findViewById(R.id.name)).setText(name);
                                ((TextView) findViewById(R.id.status)).setText(status);
                                ((TextView) findViewById(R.id.species)).setText(species);
                                ((TextView) findViewById(R.id.location)).setText(location);
                                ((TextView) findViewById(R.id.gender)).setText(gender);
                                Picasso.get().load(imgurl).into((ImageView) findViewById(R.id.charImage));
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

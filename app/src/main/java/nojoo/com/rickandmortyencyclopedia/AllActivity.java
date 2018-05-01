package nojoo.com.rickandmortyencyclopedia;

import android.content.Intent;
import android.media.MediaPlayer;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllActivity extends AppCompatActivity implements OnItemClickListener {
    private static final String TAG = "AllActivity";
    private static RequestQueue requestQueue;
    GridView gridView;
    CharactersAdapter charactersAdapter;
    ArrayList<Item> data = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_all);

        MediaPlayer schwifty = MediaPlayer.create(AllActivity.this, R.raw.whatyougot);
        schwifty.start();


        initView();
        fillData();
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
        for (int i = 1; i <= 436; i++) {
            startAPICall(i);
        }
    }

    public void onItemClick(final AdapterView<?> arg0, final View view, final int position, final long id)
    {
        String charID = ((TextView) view.findViewById(R.id.item_id)).getText().toString();
        startActivity(new Intent(AllActivity.this, CharacterCalled.class).putExtra("CHAR_ID", charID));
    }
    private void startAPICall(int id) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://rickandmortyapi.com/api/character/" + id,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                data.add(new Item(response.getString("name"), response.getString("image"), response.getString("id")));
                            } catch (JSONException ignored) { }
                            setDataAdapter();
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

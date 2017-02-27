package tech.inception.postdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import tech.inception.postdata.volley.AppController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void postData(View v)
    {
        EditText name_et = (EditText) findViewById(R.id.name_et);
        String name = name_et.getText().toString();

        EditText pass_et = (EditText) findViewById(R.id.pass_et);
        String pass = pass_et.getText().toString();

        JSONObject json_data = new JSONObject();
        try {
            json_data.put("name" , name);
            json_data.put("pass" , pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobjreq = new JsonObjectRequest("http://192.168.0.15/postData.php",
               json_data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getString("r").equals("done"))
                    {
                        Toast.makeText(MainActivity.this ,"user registered successfully",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this ,"error",Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println(error);
            }
        });

        jobjreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 3 ,2));

        AppController ap = new AppController(MainActivity.this);
        ap.addToRequestQueue(jobjreq);
    }
}

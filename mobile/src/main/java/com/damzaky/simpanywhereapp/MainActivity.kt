package com.damzaky.simpanywhereapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import org.json.JSONObject
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException




class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = HomeAdapter(myDataset)

        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
//------------------------------------------------------
        val textView = findViewById<TextView>(R.id.jsont)
        val queue = Volley.newRequestQueue(this)
        val url = "http://httpbin.org/get?param1=hello"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener {
                    response ->
//                textView.text = "Response: %s".format(response)//.toString())
                try {
//                    val station1 = response.getJSONObject("0").getJSONArray("1").getJSONObject(0)
//                    val stationName = station1.getString("args")
                    val test = response.getJSONObject("args").getString("param1")
                    textView.text = "test: ${test.toString()}"//+test.toString()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

//                @Override
//                public void onResponse(String response) {
//                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//                        JSONArray jsonArray = jsonObject.getJSONArray("data");
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject jo = jsonArray.getJSONObject(i);
//                            // Do you fancy stuff
//                            // Example: String gifUrl = jo.getString("url");
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )

// Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest)

//        val queue = Volley.newRequestQueue(this)
//
//        val url = "http://httpbin.org/get?param1=hello"
//
//// prepare the Request
//        val getRequest = object:JsonObjectRequest(
//            Request.Method.GET, url, null,
//            object : Response.Listener<JSONObject> {
//                override fun onResponse(response: JSONObject) {
//                    // display response
////                    Log.d("Response", response.toString())
//                }
//            },
//            object : Response.ErrorListener {
//                override fun onErrorResponse(error: VolleyError) {
////                    Log.d("Error.Response")
//                }
//            }
//        )
//
//// add it to the RequestQueue
//        queue.add(getRequest)
//
        //-------------------------------------------------------------------
        val fab: View = findViewById(R.id.floatingActionButton)
        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
//                .show()
            val intent = Intent(this, Simpan::class.java).apply {
//                putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
        }
    }
}

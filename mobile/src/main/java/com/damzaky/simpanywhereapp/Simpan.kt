package com.damzaky.simpanywhereapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class Simpan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simpan)

        val textView = findViewById<TextView>(R.id.text)
// ...

// Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://simpanywhere.appspot.com/post"
        val submit = findViewById<Button>(R.id.submit)
// Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                textView.text = "Response is: ${response.substring(0, 500)}"
            },
            Response.ErrorListener { textView.text = "That didn't work!" })
        submit.setOnClickListener { view ->
            queue.add(stringRequest)
        }
// Add the request to the RequestQueue.
//        queue.add(stringRequest)
    }
}

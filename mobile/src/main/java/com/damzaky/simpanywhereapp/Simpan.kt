package com.damzaky.simpanywhereapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.AuthFailureError



class Simpan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simpan)

        val textView = findViewById<TextView>(R.id.text)
        val queue = Volley.newRequestQueue(this)
        val url = "https://simpanywhere.appspot.com/post"
        val submit = findViewById<Button>(R.id.submit)
        val password = findViewById<EditText>(R.id.password)
        val data = findViewById<EditText>(R.id.data)

        val stringRequest = object:StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                textView.text = "Response is: ${response}"
            },
            Response.ErrorListener { textView.text = "Hmm something is wrong!" }) {

            override fun getBodyContentType(): String {
                return "application/x-www-form-urlencoded; charset=UTF-8"
            }

            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["data"] = data.getText().toString().trim()
                params["pass"] = password.getText().toString().trim()
                return params
            }
        }
        submit.setOnClickListener { view ->
            queue.add(stringRequest)
        }
    }
}

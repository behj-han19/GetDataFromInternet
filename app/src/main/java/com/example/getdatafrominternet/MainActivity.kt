package com.example.getdatafrominternet

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.w3c.dom.Text
import java.net.ResponseCache

class MainActivity : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnget = findViewById<Button>(R.id.btnget)

        btnget.setOnClickListener {
            val rq: RequestQueue = Volley.newRequestQueue(this)

            val objRequest = JsonArrayRequest(
                Request.Method.GET,
                "http://demo.onmyfinger.com/home/getAll",
                null,
                {
                    // try to read the response
                        response ->
                    try {
                        var nameList: StringBuffer = StringBuffer()

                        for (i in 0 until response.length()) {
                            val objStudent: JSONObject = response.getJSONObject(i)
                            nameList.append(objStudent.getString("name") + "\n")
                        }

                        findViewById<TextView>(R.id.tvresult).setText(nameList)

                    } catch (e: JSONException) {
                        findViewById<TextView>(R.id.tvresult).setText(e.message)
                    }
                },
                { error ->
                    findViewById<TextView>(R.id.tvresult).setText(error.message)
                }
            )

            rq?.add(objRequest)
        }

        val tfid = findViewById<TextView>(R.id.tfid)
        val btnsearch = findViewById<Button>(R.id.btnsearch)

        btnsearch.setOnClickListener {
            val rq: RequestQueue = Volley.newRequestQueue(this)

//            Toast.makeText(baseContext,"Please enter student Id",Toast.LENGTH_SHORT).show()
                val objRequest = JsonArrayRequest(
                    Request.Method.GET,
                    "http://demo.onmyfinger.com/home/getAll",
                    null,
                    {
                        // try to read the response
                            response ->
                        try {
                            var idList: StringBuffer = StringBuffer()
                            var nameList: StringBuffer = StringBuffer()
                            var ProgrammeList: StringBuffer = StringBuffer()

                            for (i in 0 until response.length()) {
                                val objid: JSONObject = response.getJSONObject(i)
                                val objStudent: JSONObject = response.getJSONObject(i)
                                val objProgramme: JSONObject = response.getJSONObject(i)


                                if (tfid.text.contains(objid.getString("id"))) {
                                    // Toast.makeText(baseContext, "Found", Toast.LENGTH_SHORT).show()
                                    findViewById<TextView>(R.id.tfname).text = objid.getString("name")
                                    findViewById<TextView>(R.id.tfprogramme).text = objProgramme.getString("programme")
                                }

                                // idList.append(objid.getString("id") + "\n")
                                nameList.append(objStudent.getString("name") + "\n")
                                ProgrammeList.append(objProgramme.getString("programme") + "\n")


                            }

//                            findViewById<TextView>(R.id.tvresult).setText(idList)
//                            findViewById<TextView>(R.id.tvresult).setText(nameList)
//                            findViewById<TextView>(R.id.tvresult).setText(ProgrammeList)

                        } catch (e: JSONException) {
                            findViewById<TextView>(R.id.tvresult).setText(e.message)
                        }
                    },
                    { error ->
                        findViewById<TextView>(R.id.tvresult).setText(error.message)
                    }
                )
            rq?.add(objRequest)
        }

        val btnadd = findViewById<Button>(R.id.btnadd)
        btnadd.setOnClickListener {
            val rq: RequestQueue = Volley.newRequestQueue(this)

            val id = findViewById<TextView>(R.id.tfid).text.toString()
            val name = findViewById<TextView>(R.id.tfname).text.toString()
            val programme = findViewById<TextView>(R.id.tfprogramme).text.toString()

            val objRequest = JsonArrayRequest(
                Request.Method.GET,
                "http://demo.onmyfinger.com/home/Add?id="
                        + id +"&name="+ name + "&programme=" + programme,
                null,
                {
                    // try to read the response
                        response ->
                    try {
                        Toast.makeText(this,"Record Added",Toast.LENGTH_SHORT).show()

                    } catch (e: JSONException) {
                        findViewById<TextView>(R.id.tvresult).setText(e.message)
                    }
                },
                { error ->
                    findViewById<TextView>(R.id.tvresult).setText(error.message)
                }
            )

            rq?.add(objRequest)
        }
    }
}
package com.example.myjsondemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    private lateinit var jsonListView : ListView
    private var viewStringArr = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jsonListView = findViewById(R.id.json_list)

        readJson()
    }

    private fun readJson()
    {
        val jsonString: String?

        try {
            val inputStream: InputStream = assets.open("test.json")
            jsonString = inputStream.bufferedReader().use { it.readText() }

            val jsonArr = JSONArray(jsonString)

            for (i in 0 until jsonArr.length()) {
                val jsonObj = jsonArr.getJSONObject(i)
                viewStringArr.add("name:" + jsonObj.getString("name") + "  age:" + jsonObj.getString("age"))
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, viewStringArr)
            jsonListView.adapter = adapter

        } catch (e : IOException) {

        }
    }
}
package org.mp.a1004ansariclass1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.net.ResponseCache

class MainActivity : AppCompatActivity() {

    val contactList = ArrayList<UserData>()
    val url = "https://api.androidhive.info/contacts/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById<RecyclerView>(R.id.recycler_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //link string processor
        var stringReq = StringRequest(Request.Method.POST,"https://api.androidhive.info/contacts/",
            Response.Listener {
                //create jsonObject since the first thing in the
                //json file is the json object '{}'
                var jsonObject = JSONObject(it)

                //the "contacts" in this example includes a json array
                //so this array object is that contacts. get array
                var jsonArrayContact = jsonObject.getJSONArray("contacts")

                //parsing the json array
                for(i in 0 until(jsonArrayContact.length())){

                    //Getting the contact object inside in the array (object inside array inside object)
                    var contact = jsonArrayContact.getJSONObject(i)

                    //and get that object's
                    var name = contact.getString("name")
                    var id = contact.getString("id")
                    var gender = contact.getString("gender")

                    //Add this to the contactList ArrayList
                    contactList.add(UserData(id, name, gender))
                }

                //Use the adapter on the contactList to bind and display it
                val adapter = UserAdapter(contactList)
                recyclerView.adapter = adapter

            }, Response.ErrorListener {  } )

        //Create new Volley request
        var reqQueue = Volley.newRequestQueue(this)
        //add the processor's result into it
        reqQueue.add(stringReq)
    }

}

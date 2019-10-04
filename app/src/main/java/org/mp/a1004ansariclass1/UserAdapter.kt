package org.mp.a1004ansariclass1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(var userList: ArrayList<UserData>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_unit, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var myData : UserData = userList.get(position)
        holder?.textId.text = myData.id
        holder?.textName.text = myData.name
        holder?.textGender.text = myData.gender
    }

}

class ViewHolder(view: View): RecyclerView.ViewHolder(view){
    val textId = view.findViewById<TextView>(R.id.text_id)
    val textName = view.findViewById<TextView>(R.id.text_name)
    val textGender = view.findViewById<TextView>(R.id.text_gender)
}
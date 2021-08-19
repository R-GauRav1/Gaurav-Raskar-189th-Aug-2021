package com.example.shreeganesha

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket_item.*
import kotlinx.android.synthetic.main.ticket_item.view.*

class MainActivity : AppCompatActivity() {

    var listitems = ArrayList<ListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //add dummy data
        listitems.add(ListItem(1, "SUNDAY"))
        listitems.add(ListItem(2, "MONDAY"))
        listitems.add(ListItem(3, "TUESDAY"))
        listitems.add(ListItem(4, "THUSDAY"))
        listitems.add(ListItem(5, "FRIDAY"))
        listitems.add(ListItem(4, "SATURDAY"))

        var myadapter = MyListItemAdapter(this, listitems)
        list_view.adapter = myadapter


    }


    inner class MyListItemAdapter : BaseAdapter {
        var listitemsAdapter = ArrayList<ListItem>()
        var context: Context? = null

        constructor(context: Context, listitemsAdapter: ArrayList<ListItem>) : super() {
            this.listitemsAdapter = listitemsAdapter
            this.context = context

        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


            var myview = layoutInflater.inflate(R.layout.ticket_item, null)
            var myitemlist = listitemsAdapter[position]
            myview.palyer_name.text = myitemlist.name

            //  myview.palyer_name.setOnClickListener {

            //}
            val selctionArgs = arrayOf(myitemlist.id.toString())
            myview.playlist_add.setOnClickListener {
                //var dbManager = DbManager(this.context!!)
                //dbManager.Delete("ID = ?",selctionArgs)
                //loadQuery("%")
                var player_intent = Intent(this.context, PlayerActivity::class.java)
                player_intent.putExtra("name", myitemlist.name)
                context!!.startActivity(player_intent)

            }
            return myview
        }

        override fun getItem(position: Int): Any {
            return listitemsAdapter[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listitemsAdapter.size
        }

    }
}

package com.example.shreeganesha

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_add_person.view.*
import kotlinx.android.synthetic.main.activity_player.*
import kotlinx.android.synthetic.main.playlist_item.view.*

class PlayerActivity : AppCompatActivity() {

    var listitems =  ArrayList<ListItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        val bundle: Bundle? = intent.extras!!
        val name = bundle!!.getString("name")

        tv_name.text = name
        var myadapter = MyListItemAdapter(this,listitems)
        list_view1.adapter = myadapter

        loadQuery("%")

    }
    override fun onResume() {
       loadQuery("%")
       super.onResume()
   }

       fun loadQuery(name : String){
          val dbManager = DbManager(this)
          val projection =  arrayOf("ID","Name")
          val selctionArgs = arrayOf(name)
          val Cursor = dbManager.Query(projection,"name like ?",selctionArgs,"name")
          listitems.clear()
          if (Cursor.moveToFirst()){
              do {
                  val ID = Cursor.getInt(Cursor.getColumnIndex("ID"))
                  val Name = Cursor.getString(Cursor.getColumnIndex("Name"))
                  listitems.add(ListItem(ID,Name))

              }while (Cursor.moveToNext())

          }
          var myadapter = MyListItemAdapter(this,listitems)
          list_view1.adapter = myadapter

      }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item!= null){
            when(item.itemId){
                R.id.add_person ->{
                    
                    var intent = Intent(this,AddPerson::class.java)
                    startActivity(intent)
                }

            }

        }
        return super.onOptionsItemSelected(item)
    }
    
    inner class MyListItemAdapter : BaseAdapter {
        var listitemsAdapter = ArrayList<ListItem>()

        var context: Context? = null

        constructor(context: Context, listitemsAdapter: ArrayList<ListItem>) : super() {
            this.listitemsAdapter = listitemsAdapter
            this.context = context

        }


        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


            var myview = layoutInflater.inflate(R.layout.playlist_item, null)
            var myitemlist = listitemsAdapter[position]

            myview.palyer_name1.text = myitemlist.name

            //  myview.palyer_name.setOnClickListener {

            //}
            val selctionArgs = arrayOf(myitemlist.id.toString())
            myview.player_delet.setOnClickListener {
                var dbManager1 = DbManager(this.context!!)
                dbManager1.Delete("ID = ?",selctionArgs)
                loadQuery("%")

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

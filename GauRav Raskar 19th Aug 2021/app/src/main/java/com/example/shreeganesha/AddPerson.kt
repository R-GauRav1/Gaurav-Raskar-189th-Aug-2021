package com.example.shreeganesha

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_person.*

class AddPerson : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_person)


    }
     fun buAdd(view : View){


        var dbman = DbManager(this )
        var values =ContentValues()

        values.put("Name",et_name.text.toString())

        var ID = dbman.insert(values)

        if (ID  > 0){
            Toast.makeText(this,"Task is Added",Toast.LENGTH_LONG).show()
            finish()
        }else{
            Toast.makeText(this,"Please enter Some Task",Toast.LENGTH_LONG).show()
        }
    }
}


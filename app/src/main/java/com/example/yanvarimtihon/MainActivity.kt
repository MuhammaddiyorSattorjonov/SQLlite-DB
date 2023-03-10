package com.example.yanvarimtihon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.yanvarimtihon.DB.MyDbHelper
import com.example.yanvarimtihon.adapters.RvAction
import com.example.yanvarimtihon.adapters.RvAdapter
import com.example.yanvarimtihon.databinding.ActivityMainBinding
import com.example.yanvarimtihon.databinding.DialogAddBinding
import com.example.yanvarimtihon.databinding.DialogDeleteBinding
import com.example.yanvarimtihon.models.MyClass
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(),RvAction {
    lateinit var adapter:RvAdapter
    lateinit var myDBHelper:MyDbHelper
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myDBHelper = MyDbHelper(binding.root.context)
        adapter = RvAdapter(myDBHelper.getAllNotes() as ArrayList,this,this)
        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy>0){
                    binding.plus.hide()
                }else{
                    binding.plus.show()
                }
            }
        })
        binding.rv.adapter = adapter

        binding.plus.setOnClickListener{
            val dialog = AlertDialog.Builder(this).create()
            val itemDialog = DialogAddBinding.inflate(layoutInflater)
            itemDialog.btnClose.setOnClickListener {
                dialog.cancel()
            }
            itemDialog.btnSave.setOnClickListener {
                val name =itemDialog.name.text.toString().trim()
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)
                val date = ""+day+"."+month+"."+year

                val myKurs = MyClass(name,date,0)

                myDBHelper.addNote(myKurs)
                adapter.list.add(myKurs)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Kurs Saved", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }
            dialog?.setView(itemDialog.root)
            dialog?.show()
        }
    }

    override fun delete(myContact: MyClass, position: Int, imageView: ImageView) {
        val dialog = AlertDialog.Builder(this).create()
        val itemDialogdelete = DialogDeleteBinding.inflate(layoutInflater)
        itemDialogdelete.btnClose.setOnClickListener {
            dialog?.cancel()
        }
        itemDialogdelete.btnSave.setOnClickListener {
            myDBHelper.deleteNotes(myContact)
            adapter.list.remove(myContact)
            adapter.notifyItemRemoved(position)
            Toast.makeText(this, "O'chirildi", Toast.LENGTH_SHORT).show()
            dialog?.cancel()
        }
        dialog?.setView(itemDialogdelete.root)
        dialog?.show()
    }

    override fun box(position: Int, imageView: CheckBox) {

    }

}

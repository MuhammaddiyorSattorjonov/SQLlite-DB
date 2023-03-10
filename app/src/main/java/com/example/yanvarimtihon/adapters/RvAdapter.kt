package com.example.yanvarimtihon.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import android.widget.RadioGroup.OnCheckedChangeListener
import androidx.recyclerview.widget.RecyclerView
import com.example.yanvarimtihon.DB.MyDbHelper
import com.example.yanvarimtihon.databinding.ItemRvBinding
import com.example.yanvarimtihon.models.MyClass

class RvAdapter(val list:ArrayList<MyClass>,val rvAction: RvAction,val context: Context): RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(val itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(myDbClass: MyClass, position: Int) {

            itemRvBinding.name.text = myDbClass.note
            itemRvBinding.date.text = myDbClass.date
            itemRvBinding.checkbox.setOnCheckedChangeListener(object: OnCheckedChangeListener,
                CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

                }

                override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                    if (isChecked){
                        Toast.makeText(context, "Bajarildi", Toast.LENGTH_SHORT).show()
                        itemRvBinding.checkbox.isChecked = true
                    }else{
                        Toast.makeText(context, "Otmen bo'ldi", Toast.LENGTH_SHORT).show()
                        itemRvBinding.checkbox.isChecked = false
                    }

                }


            })

            itemRvBinding.imageMenu.setOnClickListener {
                rvAction.delete(myDbClass, position, itemRvBinding.imageMenu)


            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}

interface RvAction{
    fun delete(myContact: MyClass, position: Int, imageView: ImageView)
    fun box(position: Int,imageView: CheckBox)

}
package example.com.ejercicioroom.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import example.com.ejercicioroom.MainActivity
import example.com.ejercicioroom.R
import example.com.ejercicioroom.activities.PedidosActivity
import example.com.ejercicioroom.model.Item


class CustomRecyclerAdapter(
    val itemList: ArrayList<Item>,
    private val actividad: PedidosActivity
) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MiViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.custom_item_list,
            parent,
            false
        )
        val viewHolder = MiViewHolder(v)
        viewHolder.actividad = actividad
        return viewHolder
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MiViewHolder, position: Int, payloads: MutableList<Any>) {
        holder.bindItems(itemList[position])
    }


    class MiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val campo1: TextView
        val campo2: TextView
        lateinit var actividad: PedidosActivity

        init {

            campo1 = itemView.findViewById(R.id.campo1)
            campo2 = itemView.findViewById(R.id.campo2) as TextView
        }

        fun bindItems(item: Item) {

            val campo1 = itemView.findViewById<TextView>(R.id.campo1)
            val campo2 = itemView.findViewById(R.id.campo2) as TextView

            campo1.text = item.campo1
            campo2.text = item.campo2

            campo1.setOnClickListener{actividad.clickProduct("Nombre: "+campo1.text.toString() +" / " + "Precio: " + campo2.text.toString())}


        }
    }

    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        holder.bindItems(itemList[position])
    }
}

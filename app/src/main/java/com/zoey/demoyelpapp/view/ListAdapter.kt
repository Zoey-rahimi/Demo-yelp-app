package com.zoey.demoyelpapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zoey.demoyelpapp.R
import com.zoey.demoyelpapp.model.Business
import kotlinx.android.synthetic.main.business_item.view.*

class ListAdapter(private val businessList: ArrayList<Business>) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.business_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = businessList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.view.nameTextView.text = businessList[position].name
        holder.view.addressTextView.text = businessList[position].location?.displayAddress.toString()
            .replace("[", "")
            .replace("]", "")
        holder.view.reviewCountTextView.text = businessList[position].reviewCount.toString()
    }

    class ListViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun updateBusinessList(list: List<Business>) {
        businessList.clear()
        businessList.addAll(list)
        notifyDataSetChanged()
    }
}
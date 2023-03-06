package com.example.mornhouse.presentation

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.mornhouse.R
import com.example.mornhouse.database.NumberEntity
import java.util.*

class NumbersRvAdapter(
    val mItemClickListener: ItemClickListener,
) : RecyclerView.Adapter<NumbersRvAdapter.NumbersViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(position: String)
    }

    inner class NumbersViewHolder(numberItemView: View) : RecyclerView.ViewHolder(numberItemView) {
        val queryTextView = numberItemView.findViewById<TextView>(R.id.queryNumber)
        val numberFronApiTextView = numberItemView.findViewById<TextView>(R.id.numberFromApi)
        val descriptionOfNumberTextView =
            numberItemView.findViewById<TextView>(R.id.descriptionOfNumber)

        init {
            numberItemView.setOnClickListener {
                allDataFromDb.get(position).description.let {
                    mItemClickListener.onItemClick(it)
                }

            }
        }

    }

    private val allDataFromDb = mutableListOf<NumberEntity>()

    fun addAllNumberstoList(list: List<NumberEntity>) {
        //val list = listToSort.reversed()
        allDataFromDb.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumbersViewHolder {
        val numberItemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_view_one_item_layout,
                parent,
                false
            )
        return NumbersViewHolder(numberItemView)
    }

    override fun getItemCount(): Int {
        return allDataFromDb.size
    }

    override fun onBindViewHolder(holder: NumbersViewHolder, position: Int) {
        holder.queryTextView.text = allDataFromDb[position].id.toString()
        holder.numberFronApiTextView.text = allDataFromDb[position].number
        holder.descriptionOfNumberTextView.text = allDataFromDb[position].description


    }
}
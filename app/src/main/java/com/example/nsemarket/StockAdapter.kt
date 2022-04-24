package com.example.nsemarket

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nsemarket.StockAdapter.StockHolder
import com.example.nsemarket.database.tables.Stock
import com.example.nsemarket.models.Datum
import java.util.*

class StockAdapter : RecyclerView.Adapter<StockHolder>() {
    private val remoteStocks: List<Datum> = ArrayList()
    private var stocks: List<Stock>? = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.stock_item, parent, false)
        return StockHolder(itemView)
    }

    override fun onBindViewHolder(holder: StockHolder, position: Int) {
//        Datum currentRemoteStock = remoteStocks.get(position);
        val currentStock = stocks!![position]
        holder.textViewName.text = currentStock.symbol
        holder.textViewChangePercentage.text = String.format("%.2f", currentStock.pChange)
        if (currentStock.change < 0) {
            holder.textViewChange.setTextColor(holder.view.resources.getColor(R.color.design_default_color_error))
            holder.textViewChangePercentageLabel.setTextColor(holder.view.resources.getColor(R.color.design_default_color_error))
            holder.textViewChange.text =  String.format("%.2f", currentStock.change)
        } else {
            holder.textViewChange.setTextColor(holder.view.resources.getColor(R.color.greenColor))
            holder.textViewChangePercentageLabel.setTextColor(holder.view.resources.getColor(R.color.greenColor))
            holder.textViewChange.text = String.format("+%s", currentStock.change)
        }
        holder.textViewLtp.text = currentStock.lastPrice.toString()
    }

    override fun getItemCount(): Int {
        return stocks!!.size
    }

    fun setStocks(stocks: List<Stock>) {
        this.stocks = stocks
        Log.d(TAG, "setStocks: $stocks")
        if (this.stocks != null) {
            this.stocks?.sortedWith(PChangeSorter())
        }
        notifyDataSetChanged()
    }

    inner class StockHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textViewLtp: TextView
        val textViewChangePercentage: TextView
        val textViewName: TextView
        val textViewChange: TextView
        val textViewChangePercentageLabel: TextView
        private val defaultColor: Int

        init {
            textViewName = view.findViewById(R.id.text_view_name)
            textViewChangePercentage = view.findViewById(R.id.text_view_change_percentage)
            textViewChange = view.findViewById(R.id.text_view_change)
            textViewLtp = view.findViewById(R.id.text_view_ltp)
            textViewChangePercentageLabel = view.findViewById(R.id.text_view_change_percentage_label)
            defaultColor = textViewChange.currentTextColor
        }
    }

    companion object {
        private const val TAG = "StockAdapter"
    }
}
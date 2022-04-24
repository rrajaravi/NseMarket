package com.example.nsemarket

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.nsemarket.database.tables.Stock
import com.example.nsemarket.viewmodel.StockViewModel

class StockFragment : Fragment() {
    private val stockAdapter = StockAdapter()
    private lateinit var textViewName: TextView
    private lateinit var textViewChange: TextView
    private lateinit var textViewChangePercentage: TextView
    private lateinit var textViewChangePercentageLabel: TextView
    private lateinit var textViewLtp: TextView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val stockType = arguments!!.getString(Constants.STOCK_TYPE_INTENT, Constants.NIFTY50)

        Log.e(TAG, "onActivityCreated: - came to this")
        val stockViewModel: StockViewModel = ViewModelProvider(this).get(StockViewModel::class.java)

        Log.e(TAG, "onActivityCreated: - passed this")
        if (stockType == Constants.NIFTY50) {
            Log.d(TAG, "onViewCreated: - observe nifty50")
            stockViewModel.nifty50.observe(viewLifecycleOwner, indexStockObserver)
            stockViewModel.allNifty50Stocks.observe(viewLifecycleOwner, allStocksObserver)
        } else if (stockType == Constants.NIFTYBANK) {
            Log.d(TAG, "onViewCreated: - observe niftybank")
            stockViewModel.niftyBank.observe(viewLifecycleOwner, indexStockObserver)
            stockViewModel.allNiftyBankStocks.observe(viewLifecycleOwner, allStocksObserver)
        } else if (stockType == Constants.NIFTYIT) {
            Log.d(TAG, "onViewCreated: observe nifty it")
            stockViewModel.getIndexStock(Constants.NIFTYIT).observe(viewLifecycleOwner, indexStockObserver)
            stockViewModel.allNiftyITStocks.observe(viewLifecycleOwner, allStocksObserver)
        } else {
            throw RuntimeException("Unsupported stock type: $stockType")
        }
        Log.d(TAG, "onCreate: $stockType")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView: ")
        val view = inflater.inflate(R.layout.fragment_stock, container, false)
        textViewName = view.findViewById(R.id.text_view_name)
        textViewChange = view.findViewById(R.id.text_view_change)
        textViewChangePercentage = view.findViewById(R.id.text_view_change_percentage)
        textViewChangePercentageLabel = view.findViewById(R.id.text_view_change_percentage_label)
        textViewLtp = view.findViewById(R.id.text_view_ltp)
        val stockRefreshLayout: SwipeRefreshLayout = view.findViewById(R.id.stock_refresh_layout)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = stockAdapter
        stockRefreshLayout.setOnRefreshListener {
            stockRefreshLayout.isRefreshing = true
            val intent = Intent(context, NSEPullService::class.java)
            intent.putExtra(Constants.STOCK_TYPE_INTENT, Constants.NIFTYBANK)
            context!!.startService(intent)
            val intent1 = Intent(context, NSEPullService::class.java)
            intent1.putExtra(Constants.STOCK_TYPE_INTENT, Constants.NIFTY50)
            context!!.startService(intent1)
            val intent2 = Intent(context, NSEPullService::class.java)
            intent2.putExtra(Constants.STOCK_TYPE_INTENT, Constants.NIFTYIT)
            context!!.startService(intent2)
            stockRefreshLayout.isRefreshing = false
        }
        return view
    }


    var allStocksObserver: Observer<List<Stock>> = Observer<List<Stock>> { stocks ->
        Log.d(TAG, "onChanged: - all stocks ")
        stockAdapter.setStocks(stocks)
    }

    var indexStockObserver = Observer<Stock?> { stock ->
        Log.d(TAG, "onChanged: - index stock changed for " + stock?.symbol)
        if (stock != null) {
            textViewName.setText(stock.symbol)
            textViewChange.text = String.format("%.2f", stock.change)
            if (stock.change < 0) {
                textViewChange.setTextColor(resources.getColor(R.color.design_default_color_error))
                textViewChangePercentageLabel.setTextColor(resources.getColor(R.color.design_default_color_error))
                textViewChangePercentage.setText(String.format("%.2f", stock.pChange))
            } else {
                textViewChange.setTextColor(resources.getColor(R.color.greenColor))
                textViewChangePercentageLabel.setTextColor(resources.getColor(R.color.greenColor))
                textViewChange.text = String.format("%.2f", stock.change)
            }
            textViewLtp.setText(stock.lastPrice.toString())
        }
    }

    companion object {
        private const val TAG = "StockFragment"
    }
}
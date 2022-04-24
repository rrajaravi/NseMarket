package com.example.nsemarket

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var fragmentHome: Fragment
    private lateinit var fragmentNiftyBank: Fragment
    private lateinit var fragmentNiftyIT: Fragment
    private var viewPager: ViewPager? = null
    private var prevMenuItem: MenuItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById<View>(R.id.viewpager) as ViewPager
        supportActionBar!!.setIcon(R.drawable.nsemarket_logo_symbol)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)
        if (savedInstanceState == null) {
            setupStockViewPager(viewPager)
    }
        viewPager!!.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                Log.d(TAG, "onPageScrolled: ")
            }

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null) {
                    prevMenuItem!!.isChecked = false
                } else {
                    bottomNav.menu.getItem(0).isChecked = false
                }
                Log.d("page", "onPageSelected: $position")
                bottomNav.menu.getItem(position).isChecked = true
                prevMenuItem = bottomNav.menu.getItem(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                Log.d(TAG, "onPageScrollStateChanged: ")
            }
        })
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_nifty_it -> viewPager!!.currentItem = 2
            R.id.nav_banknifty -> viewPager!!.currentItem = 1
            R.id.nav_nifty50 -> viewPager!!.currentItem = 0
        }
        false
    }

    private fun setupStockViewPager(viewPager: ViewPager?) {
        val viewPagerAdapter = StockPagerAdapter(supportFragmentManager)
        val bundle1 = Bundle()
        fragmentHome = StockFragment()
        bundle1.putString(Constants.STOCK_TYPE_INTENT, Constants.NIFTY50)
        fragmentHome.setArguments(bundle1)
        val bundle2 = Bundle()
        fragmentNiftyBank = StockFragment()
        bundle2.putString(Constants.STOCK_TYPE_INTENT, Constants.NIFTYBANK)
        fragmentNiftyBank.setArguments(bundle2)
        //
        val bundle3 = Bundle()
        fragmentNiftyIT = StockFragment()
        bundle3.putString(Constants.STOCK_TYPE_INTENT, Constants.NIFTYIT)
        fragmentNiftyIT.setArguments(bundle3)
        viewPagerAdapter.addFragment(fragmentHome)
        viewPagerAdapter.addFragment(fragmentNiftyBank)
        viewPagerAdapter.addFragment(fragmentNiftyIT)
        viewPager!!.adapter = viewPagerAdapter
        Log.d(TAG, "setupStockViewPager: - fragment count: " + viewPagerAdapter.count)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
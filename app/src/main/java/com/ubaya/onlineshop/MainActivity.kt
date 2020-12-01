package com.ubaya.onlineshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //Array list fragment
    val fragments:ArrayList<Fragment> = ArrayList()
    var testsaja = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragments.add(HomeFragment())
        fragments.add(CartFragment())
        fragments.add(OrderHistoryFragment())
        fragments.add(ProfileFragment())

        viewPager.adapter = Adapter(this, fragments)

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val menu = arrayOf(R.id.itemHome, R.id.itemCart, R.id.itemOrderHistory, R.id.itemProfile)
                bottomNav.selectedItemId=menu[position]

                bottomNav.setOnNavigationItemSelectedListener {
                    if(it.itemId == R.id.itemHome) {
                        viewPager.currentItem = 0
                    } else if(it.itemId == R.id.itemCart) {
                        viewPager.currentItem = 1
                    } else if(it.itemId == R.id.itemOrderHistory) {
                        viewPager.currentItem = 2
                    } else {
                        viewPager.currentItem = 3
                    }
                    true
                }

            }
        })

    }
}
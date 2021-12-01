package com.app.appnews

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PageAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 6
    }

    override fun getItem(position: Int): Fragment {
        when(position) {

            0 -> {
                return Business()
            }
            1 -> {
                return Sports()
            }
            2 -> {
                return Health()
            }
            3 -> {
                return Entertainment()
            }
            4 -> {
                return TechFragment()
            }
            else -> {
                return Science()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {

            0 -> {
                return "Business"
            }
            1 -> {
                return "Sports"
            }
            2 -> {
                return "Health"
            }
            3 -> {
                return "Entertainment"
            }
            4 -> {
                return "Technology"
            }
            5 -> {
                return "Science"
            }
        }
        return super.getPageTitle(position)
    }

}
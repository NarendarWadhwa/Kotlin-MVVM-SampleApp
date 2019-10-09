package com.example.findme.user_list

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(supportFragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabs = listOf("Users", "Favourites")
    private val fragmentList = listOf(UserListFragment(), UserFavsFragment())

    override fun getItem(position: Int) = fragmentList[position]
    override fun getCount() = tabs.size
    override fun getPageTitle(position: Int) = tabs[position]
}
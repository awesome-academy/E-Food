package com.sun.efood.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.addFragment(
    layoutId: Int,
    fragment: Fragment,
    tag: String? = null
) {
    beginTransaction()
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        .add(layoutId, fragment, tag)
        .addToBackStack(tag)
        .commit()
}

fun FragmentManager.replaceFragment(
    layoutId: Int,
    fragment: Fragment,
    tag: String? = null
) {
    beginTransaction()
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        .replace(layoutId, fragment, tag)
        .addToBackStack(tag)
        .commit()
}

fun FragmentManager.removeFragment(
    fragment: Fragment
) {
    popBackStack()
    beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        .remove(fragment)
        .commit()
}

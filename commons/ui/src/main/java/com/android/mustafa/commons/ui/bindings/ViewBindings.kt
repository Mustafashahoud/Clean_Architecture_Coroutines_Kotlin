package com.android.mustafa.commons.ui.bindings

import android.view.View
import android.view.View.*

///**
// * Simplification to check and setup view as visible.
// */
//var View.visible
//    get() = visibility == VISIBLE
//    set(value) {
//        visibility = if (value) VISIBLE else GONE
//    }
//
///**
// * Simplification to check and setup view as gone.
// */
//var View.gone
//    get() = visibility == GONE
//    set(value) {
//        visibility = if (value) GONE else VISIBLE
//    }
//
///**
// * Simplification to check and setup view as invisible.
// */
//var View.invisible
//    get() = visibility == INVISIBLE
//    set(value) {
//        visibility = if (value) INVISIBLE else VISIBLE
//    }
//
//fun showHide(view: View, show: Boolean) {
//    view.visibility = if (show) VISIBLE else View.INVISIBLE
//}

fun View.show() {
    visibility = VISIBLE
}

fun View.hide() {
    visibility = GONE
}


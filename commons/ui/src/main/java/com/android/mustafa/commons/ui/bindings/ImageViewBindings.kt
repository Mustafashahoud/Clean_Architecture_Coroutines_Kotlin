package com.android.mustafa.commons.ui.bindings

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.load

/**
 * Set image loaded from url.
 *
 * @param url Image url to download and set as drawable.
 */
fun ImageView.load(url: String?) {
    load(url) {
        crossfade(true)
    }
}

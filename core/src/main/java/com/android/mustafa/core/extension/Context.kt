package com.android.mustafa.core.extension

import android.content.Context
import android.net.ConnectivityManager

val Context.connectivityManager: ConnectivityManager get() =
    this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
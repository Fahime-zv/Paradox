package com.fahimezv.paradoxcat.util

import android.app.Activity
import android.widget.Toast
import androidx.annotation.StringRes


fun Activity.showMessage(@StringRes messageString: Int) {
    Toast.makeText(this, messageString, Toast.LENGTH_SHORT).show()
}


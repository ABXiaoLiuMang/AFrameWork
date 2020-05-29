package commonsdk.ktx.ext.view

import android.widget.TextView

/**
 *
 */
fun TextView.notEmpty(f: TextView.() -> Unit, t: TextView.() -> Unit) {
    if (text.toString().isNotEmpty()) f() else t()
}
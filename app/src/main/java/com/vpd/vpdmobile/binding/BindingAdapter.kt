package com.vpd.vpdmobile.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.vpd.vpdmobile.R
import com.vpd.vpdmobile.data.transaction.TransactionHistory
import java.text.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@BindingAdapter("transactionDate")
fun bindTransactionDate(view: TextView, value: TransactionHistory?) {
    value?.let {
        val currentCalDate: Calendar = Calendar.getInstance()
        currentCalDate.timeInMillis = value.transactionTime
        val dateFormat: DateFormat = SimpleDateFormat("MMM d, yyyy hh:mm a")
        view.text = dateFormat.format(currentCalDate.time)
    }
}

@BindingAdapter("description")
fun bindDescriptionData(view: TextView, value: TransactionHistory?) {
    value?.let {
        view.text = String.format("%.3f", value.amount)
    }
}

@BindingAdapter("amountValue")
fun amountData(view: TextView, value: String?) {
    value?.let {
        view.text = "₦" + NumberFormat.getNumberInstance(Locale.US).format(value.toDouble())
    }
}

@BindingAdapter("imageTransac")
fun imageTransaction(img: ImageView, value: String) {
    value?.let {
        if (it.contains("Successful")) {
            img.setImageResource(R.drawable.transaction_img)
        } else {
            img.setImageResource(R.drawable.transaction_image_failed)
        }
    }
}

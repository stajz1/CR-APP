package com.example.zoran

import android.content.Context
import android.util.Log
import android.widget.Toast

fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.toast(stringResId: Int) = Toast.makeText(this, stringResId, Toast.LENGTH_SHORT).show()

fun Context.toastLong(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.toastLong(stringResId: Int) =
    Toast.makeText(this, stringResId, Toast.LENGTH_LONG).show()

fun Context.logError(message: String) = Log.e(this::class.java.name, message)

fun isInteger(str: String?) = str?.toIntOrNull()?.let { true } ?: false
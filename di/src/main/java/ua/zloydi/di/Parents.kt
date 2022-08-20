package ua.zloydi.di

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment

val Fragment.parents get() = Iterable { iterator }
val Activity.parents get() = Iterable { iterator }
val Application.parents get() = Iterable { iterator }

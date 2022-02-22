package com.example.rickmortymvvm.util.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.rickmortymvvm.util.adapter.DiffUtilGeneric.Compare

class DiffUtilGeneric<T : Compare<in T>>(var oldList: List<T>?, var newList: List<T>?) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return if (oldList != null) oldList!!.size else 0
    }

    override fun getNewListSize(): Int {
        return newList!!.size ?: 0
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList!![newItemPosition].id == oldList!![oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList!![newItemPosition].isEqual(oldList!![oldItemPosition])
    }

    interface Compare<T> {
        val id: Int
        fun isEqual(o: T): Boolean
    }
}

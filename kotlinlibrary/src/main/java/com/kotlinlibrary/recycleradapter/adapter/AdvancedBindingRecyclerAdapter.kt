package com.kotlinlibrary.recycleradapter.adapter

import com.kotlinlibrary.recycleradapter.adapter.base.BindingRecyclerAdapter
import com.kotlinlibrary.recycleradapter.util.insert
import com.kotlinlibrary.recycleradapter.util.move
import com.kotlinlibrary.recycleradapter.util.remove
import com.kotlinlibrary.recycleradapter.util.replace

class AdvancedBindingRecyclerAdapter : BindingRecyclerAdapter() {

    val items: List<Any>
        get() = displayedItems

    fun <ItemT : Any> set(items: List<ItemT>) {
        displayedItems = items
        notifyDataSetChanged()
    }

    fun <ItemT : Any> insert(item: ItemT, position: Int? = null) {
        displayedItems = displayedItems.insert(item, position)
        notifyItemInserted(position ?: displayedItems.size)
    }

    fun <ItemT : Any> insert(items: List<ItemT>, positionStart: Int? = null) {
        displayedItems = displayedItems.insert(items, positionStart)
        notifyItemRangeInserted(positionStart ?: displayedItems.size, items.size)
    }

    fun <ItemT : Any> remove(item: ItemT) {
        displayedItems.indexOf(item).let { remove(it) }
    }

    fun remove(position: Int) {
        displayedItems = displayedItems.remove(position)
        notifyItemRemoved(position)
    }

    fun <ItemT : Any> move(item: ItemT, toPosition: Int) {
        displayedItems.indexOf(item).let { move(it, toPosition) }
    }

    fun move(fromPosition: Int, toPosition: Int) {
        displayedItems = displayedItems.move(fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    fun <ItemT : Any> replace(item: ItemT, position: Int) {
        displayedItems = displayedItems.replace(item, position)
        notifyItemChanged(position)
    }

    fun <ItemT : Any> replaceAll(item: ItemT, predicate: (Any) -> Boolean) {
        displayedItems.forEachIndexed { index, displayedItem ->
            if (predicate(displayedItem)) replace(item, index)
        }
    }

    fun <ItemT : Any> replaceFirst(item: ItemT, predicate: (Any) -> Boolean) {
        val index = displayedItems.indexOfFirst { predicate(it) }
        if (index != -1) replace(item, index)
    }

    fun <ItemT : Any> replaceLast(item: ItemT, predicate: (Any) -> Boolean) {
        val index = displayedItems.indexOfLast { predicate(it) }
        if (index != -1) replace(item, index)
    }
}
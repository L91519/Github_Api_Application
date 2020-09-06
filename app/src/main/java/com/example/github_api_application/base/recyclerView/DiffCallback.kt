package com.example.github_api_application.base.recyclerView

import androidx.recyclerview.widget.DiffUtil

class DiffCallback<E>(
	private val oldItems: List<E>,
	private val newItems: List<E>,
	private val itemIdGetter: (E) -> Any?
) : DiffUtil.Callback() {

	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
		itemIdGetter(oldItems[oldItemPosition]) == itemIdGetter(newItems[newItemPosition])

	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldItems[oldItemPosition] == newItems[newItemPosition]

	override fun getOldListSize() = oldItems.size

	override fun getNewListSize() = newItems.size
}

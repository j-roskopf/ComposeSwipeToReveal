package com.joetr.swipetoreveal

data class ContentState(
    val data: List<DataItem> = emptyList(),
    val revealedItems: List<DataItem> = emptyList(),
)
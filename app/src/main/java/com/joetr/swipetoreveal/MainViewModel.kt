package com.joetr.swipetoreveal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _state: MutableStateFlow<ContentState> =
        MutableStateFlow(ContentState())

    val state: MutableStateFlow<ContentState> get() = _state

    init {
        val data = mutableListOf<DataItem>()
        for (i in 0..100) {
            data.add(
                DataItem(
                    id = i,
                    display = i.toString()
                )
            )
        }

        viewModelScope.launch {
            _state.emit(
                ContentState(
                    data = data,
                    revealedItems = emptyList()
                )
            )
        }
    }

    fun itemExpanded(item: DataItem) {
        val list = _state.value.revealedItems.toMutableList().also { mutableList ->
            mutableList.add(item)
        }

        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    revealedItems = list
                )
            )
        }
    }

    fun itemCollapsed(item: DataItem) {
        val list = _state.value.revealedItems.toMutableList().also { mutableList ->
            mutableList.remove(item)
        }

        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    revealedItems = list
                )
            )
        }
    }

    fun isItemRevealed(item: DataItem): Boolean {
        return _state.value.revealedItems.contains(item)
    }

}
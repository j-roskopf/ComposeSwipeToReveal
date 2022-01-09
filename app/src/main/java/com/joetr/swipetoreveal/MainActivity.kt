package com.joetr.swipetoreveal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joetr.swipetoreveal.ui.theme.DefaultBackground
import com.joetr.swipetoreveal.ui.theme.SwipeToRevealTheme

private const val ACTION_ROW_WIDTH = 500

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SwipeToRevealTheme {
                Surface(color = MaterialTheme.colors.background) {

                    val state = viewModel.state.collectAsState()

                    LazyColumn {
                        itemsIndexed(
                            state.value.data,
                            key = { _, item -> item.id }
                        ) { _, item ->
                            SwipeToRevealContainer(
                                swipeToRevealParameters = SwipeToRevealParameters(
                                    swipeToRevealAnimationDurationMs = 500,
                                    cardOffset = ACTION_ROW_WIDTH.dp,
                                ),
                                isRevealed = viewModel.isItemRevealed(item),
                                onExpand = {
                                    viewModel.itemExpanded(item)
                                },
                                onCollapse = {
                                    viewModel.itemCollapsed(item)
                                },
                                rowContent = {
                                    RowContent(
                                        item = item,
                                    )
                                },
                                actionContent = {
                                    ActionRow(
                                        item = item,
                                        rowScope = this,
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun RowContent(
        item: DataItem,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(DefaultBackground)
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = item.display
            )
        }
    }

    @Composable
    fun ActionRow(
        item: DataItem,
        rowScope: RowScope,
    ) {
        rowScope.apply {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .width(ACTION_ROW_WIDTH.dp),
                text = "\uD83D\uDC4B Hello from ${item.display}"
            )
        }
    }
}


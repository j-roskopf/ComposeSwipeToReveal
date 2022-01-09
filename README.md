# ComposeSwipeToReveal
A compose solution for adding swipe to reveal to a list.

A full sample is included in the :app module.

# Usage

```kotlin

private const val ACTION_ROW_WIDTH = 500
private const val SWIPE_ANIMATION_DURATION_MS = 500

val state = viewModel.state.collectAsState()

LazyColumn {
    itemsIndexed(
        state.value.data,
        key = { _, item -> item.id }
    ) { _, item ->
        SwipeToRevealContainer(
            swipeToRevealParameters = SwipeToRevealParameters(
                swipeToRevealAnimationDurationMs = SWIPE_ANIMATION_DURATION_MS,
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

```
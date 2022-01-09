# ComposeSwipeToReveal

[![](https://jitpack.io/v/j-roskopf/ComposeSwipeToReveal.svg)](https://jitpack.io/#j-roskopf/ComposeSwipeToReveal)

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

https://user-images.githubusercontent.com/7951665/148693975-51737bdf-9d63-45cd-99ae-1c986dd48c43.mov

# Add it to your project

Step 1. Add it in your root build.gradle at the end of repositories:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency

```
dependencies {
        implementation 'com.github.j-roskopf:ComposeSwipeToReveal:<latest_version>'
}
```
package com.loc.newsapp.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.loc.newsapp.presentation.Dimens.MediumPadding2
import com.loc.newsapp.presentation.Dimens.PageIndicatorWidth
import com.loc.newsapp.presentation.common.NewsButton
import com.loc.newsapp.presentation.common.NewsTextButton
import com.loc.newsapp.presentation.common.PageIndicator
import com.loc.newsapp.presentation.onboarding.components.OnBoardingPage
import kotlinx.coroutines.launch
import kotlin.reflect.KSuspendFunction1

/**
 * Composable function for displaying the onboarding screen.
 *
 * @param event A suspend function for handling onboarding events. It takes an [OnBoardingEvent] as a parameter.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    event: KSuspendFunction1<OnBoardingEvent, Unit>
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Remember the pager state, initial page is 0
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        // Remember the button state based on the current page
        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "繼續")
                    1 -> listOf("返回", "繼續")
                    2 -> listOf("返回", "開始使用")
                    else -> listOf("", "")
                }
            }
        }

        // Display different onboarding pages based on the current page index
        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pages[index])
        }
        Spacer(modifier = Modifier.weight(1f))

        // Display the page indicator and buttons at the bottom of the screen
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier.width(PageIndicatorWidth),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Remember a CoroutineScope for pager state switching animations
                val scope = rememberCoroutineScope()

                // Display the previous button if there is a previous page
                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(
                        text = buttonState.value[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        }
                    )
                }

                // Display the next or finish button based on the current page
                NewsButton(
                    text = buttonState.value[1],
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == 2) {
                                event(OnBoardingEvent.SaveAppEntry)
                            } else {
                                pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                            }
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}

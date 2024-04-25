package com.example.mocord.ui.view.container

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.mocord.ui.view.page.ApplicationSetting
import com.example.mocord.ui.view.page.Estimation
import com.example.mocord.ui.view.page.PanelDisplay
import com.example.mocord.ui.view.page.PanelSetting
import com.example.mocord.ui.view.page.Table
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
suspend fun changePage(pagerState: PagerState, page: Int){
    pagerState.animateScrollToPage(page)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageHolder(popUp: PopUpView){
    val pagerState = rememberPagerState(initialPage = 1) {5}
    val acs = rememberCoroutineScope()

    return Column {
        TabRow(selectedTabIndex = pagerState.currentPage) {
            repeat(pagerState.pageCount) {index ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        acs.launch { changePage(pagerState, index) }
                    }
                )
            }
        }
        HorizontalPager(state = pagerState) {
            when(it){
                0-> Page{PanelSetting(popUp)}
                1-> Page{PanelDisplay(popUp)}
                2-> Page{Estimation(popUp)}
                3-> Page{Table(popUp)}
                4-> Page{ApplicationSetting(popUp)}
            }
        }
    }
}

@Composable
fun Page(component: @Composable ()->Unit){
    return Box(modifier = Modifier.fillMaxSize()) {
        component()
    }
}
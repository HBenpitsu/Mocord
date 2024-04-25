package com.example.mocord.ui.view.container

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
class PopUpView {
    private var _show: MutableState<Boolean> = mutableStateOf(false)
    private var section: @Composable () -> Unit = {}
    private var subSection: @Composable () -> Unit = {}

    private var pagerState: PagerState? = null
    private var acs: CoroutineScope? = null

    fun show(section: @Composable () -> Unit){
        this.section = section
        _show.value = true
    }
    fun dismiss(){
        _show.value = false
        showMainSection()
    }

    fun showSubSection(section: @Composable () -> Unit){
        this.subSection = section
        acs?.launch { changePage(pagerState!!, 1) }
    }

    fun showMainSection(){
        acs?.launch { changePage(pagerState!!, 0) }
    }

    @Composable
    fun Construct(){
        _show = remember {mutableStateOf(false)}
        pagerState = rememberPagerState(initialPage = 0) {2}
        acs = rememberCoroutineScope()

        if (_show.value) {
            Dialog(onDismissRequest = { dismiss() }) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 24.dp, top = 64.dp)
                        .clip(RoundedCornerShape(16.dp))
                    ,
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Row {
                            Button(onClick = { dismiss() }) {
                                Text(text = "Close")
                            }
                        }
                        pagerState?.let{ pagerState ->
                            HorizontalPager(
                                state = pagerState,
                                modifier = Modifier.fillMaxSize(),
                                userScrollEnabled = false
                            ) {index ->
                                when(index){
                                    0-> section()
                                    1-> subSection()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
package com.example.ecommerceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecommerceapp.ui.theme.EcommerceAppTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcommerceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val images = listOf(
        R.drawable.p1,
        R.drawable.p2,
        R.drawable.p3,
        R.drawable.p4
    )
    val pagerState = rememberPagerState(pageCount = { images.size })
    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.scrollToPage(nextPage)
        }
    }
    var text = remember {
        mutableStateOf("")
    }
    Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = text.value,
            onValueChange = {
                text.value = it
            },
            modifier.padding(26.dp),
            trailingIcon= {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "emailIcon"
                )
            },placeholder = { Text(text = "Food") })
        Box(modifier = modifier.wrapContentSize()) {
            HorizontalPager(
                state = pagerState,
                modifier
                    .wrapContentSize()
                    .padding(26.dp)
            ) { currentPage ->
                Card(
                    modifier = modifier.wrapContentSize(),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = images[currentPage]),
                        contentDescription = null
                    )

                }

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EcommerceAppTheme {
        Greeting()
    }
}
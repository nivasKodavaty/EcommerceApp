package com.example.ecommerceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.ui.theme.Fruits
import com.example.ecommerceapp.ui.theme.EcommerceAppTheme
import kotlinx.coroutines.delay
import kotlin.random.Random

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
@Composable
fun MainPage(){
LazyColumn (modifier = Modifier.fillMaxSize()){
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
    val text = remember {
        mutableStateOf("")
    }
    Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = text.value,
            onValueChange = {
                text.value = it
            }, Modifier.padding(8.dp),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "emailIcon"
                )
            }, placeholder = { Text(text = "Food") })
        Text(text = "Exciting offers", fontSize = 24.sp, modifier = Modifier.padding(end = 148.dp))
        Box(
            modifier = modifier.wrapContentWidth()
        ) {
            HorizontalPager(
                state = pagerState,
                modifier.wrapContentSize()
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
        Text(text = "Top picks",Modifier.padding(end=200.dp), fontSize = 28.sp)
        FruitsRow()
    }
}

@Composable
fun FruitsRow() {
    val fruits = listOf(
        Fruits(
            R.drawable.f1, "Cherry", 56
        ),
        Fruits(
            R.drawable.f2, "Apple", 56
        ), Fruits(
            R.drawable.f3, "Berry", 56
        ), Fruits(
            R.drawable.f4, "PineApple", 56
        ), Fruits(
            R.drawable.f5, "Lemon", 56
        ), Fruits(
            R.drawable.v1, "Banana", 56
        )
    )
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(fruits) { item ->
            ItemsCard(image = item.image, name = item.title, price = item.price)

        }
    }


}


@Composable
fun ItemsCard(@DrawableRes image: Int, name: String, price: Int) {
    Card(
        Modifier
            .fillMaxWidth()
            .height(Random.nextInt(300, 450).dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .padding(16.dp)) {
        Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(text = name, fontSize = 24.sp)
            Spacer(Modifier.size(16.dp))
            Text(text = "$ " + price.toString(), fontSize = 24.sp)
            Spacer(Modifier.size(16.dp))
            IconButton(onClick = {}) {
                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = null,
                    modifier = Modifier.size(38.dp)
                )
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
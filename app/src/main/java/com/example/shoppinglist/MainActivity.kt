package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.ui.theme.ShoppingListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingListTheme {
                Scaffold {
                    shoppingList(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(it),
                    )
                }
            }
        }
    }
}

@Composable
fun shoppingList(modifier: Modifier = Modifier) {
    var shoppingListItems by remember { mutableStateOf(listOf<shoppingListItem>()) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { }, modifier = Modifier.height(40.dp).width(150.dp)) {
            Text(text = "Add Item")
        }
        LazyColumn(modifier = Modifier.padding(16.dp).fillMaxSize()) {
            items(shoppingListItems) {

            }
//            items(shoppingListItems.size) {
//                shoppingListItems[it]
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun shoppingListPreview() {
    ShoppingListTheme {
        shoppingList(modifier = Modifier.fillMaxSize())
    }
}

data class shoppingListItem(
    var item: String,
    var quantity: Int,
    var isEditing: Boolean = false,
)

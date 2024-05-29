package com.example.shoppinglist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ShoppingList(modifier: Modifier = Modifier) {
    var shoppingListItems by remember { mutableStateOf(listOf<ShoppingListItem>()) }
    var showAlert: Boolean by remember { mutableStateOf(false) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { showAlert = true }, modifier = Modifier
                .height(40.dp)
                .width(150.dp)
                .offset(y = 8.dp)
        ) {
            Text(text = "Add Item")
        }
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            items(shoppingListItems) { item ->
                if (item.isEditing) {
                    EditingTile(currentItem = item, onEditComplete = { editedItem, quantity ->
                        shoppingListItems = shoppingListItems.map { it.copy(isEditing = false) }
                        var beingEdited = shoppingListItems.find { it.id == item.id }
                        beingEdited?.quantity = quantity
                        beingEdited?.item = editedItem
                    })
                } else {
                    MakeTile(
                        item = item.item,
                        quantity = item.quantity.toString(),
                        id = item.id,
                        edit = {
                            shoppingListItems =
                                shoppingListItems.map { it.copy(isEditing = it.id == item.id) }
                        },
                        delete = { shoppingListItems = shoppingListItems - item }
                    )
                }
//                MakeTile(item = it.item, quantity = it.quantity.toString(), {}, {}, it.id)
            }

        }
    }

    if (showAlert) {
        var item by remember { mutableStateOf("") }
        var quantity by remember { mutableStateOf("") }
        Dialog(onDismissRequest = { showAlert = false }) {
            Column(
                Modifier
                    .background(color = Color.Transparent)
                    .wrapContentSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Add Item:",
                    style = TextStyle(
                        fontSize = TextUnit(24f, TextUnitType.Sp),
                        fontFamily = FontFamily.Monospace,
                        color = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = item,
                    onValueChange = { item = it },
                    label = { Text(text = "Item Name") },
                    shape = RoundedCornerShape(16.dp),
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text(text = "Quantity") },
                    shape = RoundedCornerShape(16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = {
                    if (item.isNotBlank() && quantity.isNotBlank() && quantity.toIntOrNull() != null) {
                        showAlert = false;

                        shoppingListItems = shoppingListItems + ShoppingListItem(
                            item,
                            quantity.toInt(),
                            false,
                            shoppingListItems.size + 1
                        )
                    }

                }, content = {
                    Text(text = "Add Item")
                })
            }

        }


    }
}

data class ShoppingListItem(
    var item: String,
    var quantity: Int,
    var isEditing: Boolean = false,
    val id: Int

)

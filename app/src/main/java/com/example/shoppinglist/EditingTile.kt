package com.example.shoppinglist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun EditingTile(currentItem: ShoppingListItem, onEditComplete: (String, Int) -> Unit) {
    var editedName by remember { mutableStateOf(currentItem.item) }
    var editedQuantity by remember { mutableStateOf(currentItem.quantity) }
    var isEditing by remember { mutableStateOf(currentItem.isEditing) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.Gray, shape = RoundedCornerShape(16.dp))
            .border(width = 1.dp, shape = RoundedCornerShape(16.dp), color = Color.Black),
        Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.5f)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            BasicTextField(value = editedName, onValueChange = { editedName = it })
            BasicTextField(value = editedQuantity.toString(), onValueChange = {
                if (it.toIntOrNull() != null) {
                    editedQuantity = it.toInt()
                } else {
                    editedQuantity = 0
                }
            })
        }
        Button(
            onClick = {
                onEditComplete(editedName, editedQuantity)
                //shoppingListItems[currentItem.id] = editedName; currentItem.quantity = editedQuantity
            }, modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(0.25f)
        ) {
            Text("Save")
        }

    }
}
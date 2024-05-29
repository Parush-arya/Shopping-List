package com.example.shoppinglist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@Composable
fun MakeTile(item: String, quantity: String, id: Int, edit: () -> Unit, delete: () -> Unit) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                BorderStroke(1.dp, color = Color.Black),
                shape = RoundedCornerShape(8.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item,
            fontWeight = FontWeight(700),
            fontSize = TextUnit(32f, TextUnitType.Sp),
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
        )
        Row(
            //verticalAlignment = Alignment.CenterVertically,
            //horizontalArrangement = Arrangement.End,
            Modifier.weight(0.70f)
        ) {
            Text(
                text = "qty:" + quantity,
                Modifier
                    .padding(8.dp),
                fontSize = TextUnit(16f, TextUnitType.Sp)

            )
            IconButton(onClick = edit) {
                Icon(Icons.Default.Edit, contentDescription = "edit")
            }
            IconButton(onClick = delete) {
                Icon(Icons.Default.Delete, contentDescription = "delete")
            }

        }


    }
}
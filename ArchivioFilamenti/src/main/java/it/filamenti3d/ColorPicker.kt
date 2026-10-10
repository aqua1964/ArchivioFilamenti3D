package it.filamenti3d.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val COLORI_PREDEFINITI = listOf(
    "#000000", "#FFFFFF", "#808080", "#FF0000", 
    "#008000", "#0000FF", "#FFFF00", "#FFA500", 
    "#800080", "#FFC0CB", "#D4AF37", "#C0C0C0"
)

@Composable
fun ColorPickerGrid(
    coloreSelezionatoHex: String,
    onColoreSelezionato: (String) -> Unit
) {
    Column {
        Text("Colore Filamento", style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(8.dp))
        
        LazyVerticalGrid(
            columns = GridCells.Fixed(6),
            modifier = Modifier.height(90.dp)
        ) {
            items(COLORI_PREDEFINITI) { hex ->
                val isSelected = hex.equals(coloreSelezionatoHex, ignoreCase = true)
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(android.graphics.Color.parseColor(hex)))
                        .border(
                            width = if (isSelected) 3.dp else 1.dp,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
                            shape = CircleShape
                        )
                        .clickable { onColoreSelezionato(hex) }
                )
            }
        }
    }
}

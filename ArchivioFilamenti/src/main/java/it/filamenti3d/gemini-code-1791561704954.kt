package it.filamenti3d.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BadgeStatoBobina(pesoAttualeG: Double?) {
    when {
        pesoAttualeG == null -> {
            AssistChip(
                onClick = {},
                label = { Text("⚪ Peso non tracciato") }
            )
        }
        pesoAttualeG <= 50.0 -> {
            AssistChip(
                onClick = {},
                label = { Text(if (pesoAttualeG == 0.0) "🔴 Esaurita" else "🔴 In Esaurimento (${pesoAttualeG.toInt()}g)") },
                colors = AssistChipDefaults.assistChipColors(containerColor = Color(0xFFFFCDD2))
            )
        }
        pesoAttualeG <= 250.0 -> {
            AssistChip(
                onClick = {},
                label = { Text("🟡 Quasi a metà (${pesoAttualeG.toInt()}g)") },
                colors = AssistChipDefaults.assistChipColors(containerColor = Color(0xFFFFF9C4))
            )
        }
        else -> {
            AssistChip(
                onClick = {},
                label = { Text("🟢 Rimanenti: ${pesoAttualeG.toInt()}g") },
                colors = AssistChipDefaults.assistChipColors(containerColor = Color(0xFFC8E6C9))
            )
        }
    }
}
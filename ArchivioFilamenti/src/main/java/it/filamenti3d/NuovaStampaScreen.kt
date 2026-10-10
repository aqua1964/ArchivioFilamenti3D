package it.filamenti3d.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import it.filamenti3d.data.BobinaEntity
import it.filamenti3d.data.ImpostazioniEntity
import it.filamenti3d.data.StampaEntity
import java.time.LocalDate

@Composable
fun NuovaStampaForm(
    bobina: BobinaEntity,
    impostazioni: ImpostazioniEntity,
    onSalvaStampa: (StampaEntity, Double?) -> Unit
) {
    var nomeStampa by remember { mutableStateOf("") }
    var grammiText by remember { mutableStateOf("85") }
    var oreText by remember { mutableStateOf("4.5") }

    val grammi = grammiText.toDoubleOrNull() ?: 0.0
    val ore = oreText.toDoubleOrNull() ?: 0.0

    // Calcolo automatico in tempo reale dei costi
    val costoFilamento = bobina.costoAlGrammo * grammi
    val costoEnergia = ore * (impostazioni.potenzaW / 1000.0) * impostazioni.costoKwhEuro
    val costoMacchina = ore * impostazioni.costoMacchinaOrarioEuro
    val costoTotale = costoFilamento + costoEnergia + costoMacchina

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Calcola e Salva Stampa", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(12.dp))

        Text("Bobina: ${bobina.marca} - ${bobina.nomeProdotto} (${bobina.coloreNome})")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = nomeStampa,
            onValueChange = { nomeStampa = it },
            label = { Text("Nome della Stampa") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = grammiText,
            onValueChange = { grammiText = it },
            label = { Text("Filamento Utilizzato (grammi)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = oreText,
            onValueChange = { oreText = it },
            label = { Text("Tempo di Stampa (ore)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Card con anteprima live dei costi
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("Costo Filamento: €${String.format("%.2f", costoFilamento)}")
                Text("Costo Energia: €${String.format("%.2f", costoEnergia)}")
                Text("Costo Macchina: €${String.format("%.2f", costoMacchina)}")
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                Text(
                    text = "COSTO TOTALE: €${String.format("%.2f", costoTotale)}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (nomeStampa.isNotBlank() && grammi > 0) {
                    val nuovaStampa = StampaEntity(
                        bobinaId = bobina.id,
                        nomeStampa = nomeStampa,
                        grammiUtilizzati = grammi,
                        oreStampa = ore,
                        costoFilamentoEuro = costoFilamento,
                        costoEnergiaEuro = costoEnergia,
                        costoMacchinaEuro = costoMacchina,
                        costoTotaleEuro = costoTotale,
                        dataStampa = LocalDate.now().toString()
                    )
                    
                    // Calcola il nuovo peso residuo (se il peso attuale era tracciato)
                    val nuovoPeso: Double? = bobina.pesoAttualeG?.let { att ->
                        kotlin.math.max(0.0, att - grammi)
                    }

                    onSalvaStampa(nuovaStampa, nuovoPeso)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salva Stampa e Aggiorna Bobina")
        }
    }
}

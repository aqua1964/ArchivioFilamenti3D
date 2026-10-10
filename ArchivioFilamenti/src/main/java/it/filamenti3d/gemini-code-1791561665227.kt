package it.filamenti3d.data

import androidx.room.*

@Entity(tableName = "bobina")
data class BobinaEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val marca: String,
    val nomeProdotto: String,
    val materiale: String,
    val coloreNome: String,
    val coloreHex: String,
    val tipoFormato: String, // "BOBINA" o "REFILL"
    val pesoInizialeG: Double,
    val pesoAttualeG: Double?, // null se il peso non è tracciato
    val prezzoAcquistoEuro: Double,
    val photoUri: String? = null,
    val venditore: String? = null,
    val skuProduttore: String? = null,
    val dataAcquisto: String? = null,
    val note: String? = null
) {
    val costoAlGrammo: Double
        get() = if (pesoInizialeG > 0) prezzoAcquistoEuro / pesoInizialeG else 0.0
}

@Entity(tableName = "stampa")
data class StampaEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val bobinaId: Long,
    val nomeStampa: String,
    val grammiUtilizzati: Double,
    val oreStampa: Double,
    val costoFilamentoEuro: Double,
    val costoEnergiaEuro: Double,
    val costoMacchinaEuro: Double,
    val costoTotaleEuro: Double,
    val dataStampa: String
)

@Entity(tableName = "acquisto")
data class AcquistoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val venditore: String,
    val marca: String,
    val materiale: String,
    val colore: String,
    val quantita: Int,
    val prezzoUnitarioEuro: Double,
    val dataAcquisto: String
)

@Entity(tableName = "impostazioni")
data class ImpostazioniEntity(
    @PrimaryKey val id: Int = 1,
    val costoKwhEuro: Double = 0.25,
    val potenzaW: Double = 200.0,
    val costoMacchinaOrarioEuro: Double = 0.10
)
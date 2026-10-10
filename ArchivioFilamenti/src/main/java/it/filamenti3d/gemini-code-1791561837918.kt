package it.filamenti3d

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainAppStructure()
            }
        }
    }
}

enum class AppScreen(val title: String, val icon: ImageVector) {
    Home("Home", Icons.Default.Home),
    Filamenti("Filamenti", Icons.Default.List),
    NuovaStampa("Nuova Stampa", Icons.Default.Add),
    StoricoAcquisti("Acquisti", Icons.Default.ShoppingCart),
    StoricoStampe("Stampe", Icons.Default.DateRange)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppStructure() {
    var currentScreen by remember { mutableStateOf(AppScreen.Home) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Archivio Filamenti 3D") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        bottomBar = {
            NavigationBar {
                AppScreen.values().forEach { screen ->
                    NavigationBarItem(
                        selected = currentScreen == screen,
                        onClick = { currentScreen = screen },
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentScreen) {
                AppScreen.Home -> HomeScreen()
                AppScreen.Filamenti -> FilamentiScreen()
                AppScreen.NuovaStampa -> NuovaStampaPlaceholderScreen()
                AppScreen.StoricoAcquisti -> AcquistiScreen()
                AppScreen.StoricoStampe -> StoricoStampeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("📊 Riepilogo Inventario", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Benvenuto in Archivio Filamenti 3D!")
    }
}

@Composable
fun FilamentiScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("🧵 Inventario Filamenti", style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun NuovaStampaPlaceholderScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("🖨️ Calcola Costo Stampa", style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun AcquistiScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("🛒 Storico Acquisti", style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun StoricoStampeScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("📜 Storico Stampe", style = MaterialTheme.typography.titleLarge)
    }
}
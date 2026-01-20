package com.example.parkingaccess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ParkingAccessScreen()
            }
        }
    }
}

@Composable
fun ParkingAccessScreen() {
    var semaforoOn by remember { mutableStateOf(false) }
    var serrandaOn by remember { mutableStateOf(false) }
    var sbarraOn by remember { mutableStateOf(false) }

    fun enforceRule(toggled: String) {
        val active = mutableListOf<String>()
        if (semaforoOn) active.add("semaforo")
        if (serrandaOn) active.add("serranda")
        if (sbarraOn) active.add("sbarra")

        if (active.size > 2) {
            val candidates = active.filter { it != toggled }
            val toDisable = candidates.random()
            when (toDisable) {
                "semaforo" -> semaforoOn = false
                "serranda" -> serrandaOn = false
                "sbarra" -> sbarraOn = false
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Modalità di accesso al parcheggio Fincons",
            style = MaterialTheme.typography.titleLarge
        )

        ToggleRow(
            label = "Semaforo",
            offText = "Rosso",
            onText = "Verde",
            checked = semaforoOn,
            onCheckedChange = {
                semaforoOn = it
                enforceRule("semaforo")
            }
        )

        ToggleRow(
            label = "Serranda",
            offText = "Chiusa",
            onText = "Alzata",
            checked = serrandaOn,
            onCheckedChange = {
                serrandaOn = it
                enforceRule("serranda")
            }
        )

        ToggleRow(
            label = "Sbarra",
            offText = "Abbassata",
            onText = "Alzata",
            checked = sbarraOn,
            onCheckedChange = {
                sbarraOn = it
                enforceRule("sbarra")
            }
        )
    }
}

@Compos

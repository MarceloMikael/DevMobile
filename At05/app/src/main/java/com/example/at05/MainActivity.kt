package com.example.at05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.at05.ui.theme.At05Theme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            At05Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    CounterApp()
                }
            }
        }
    }
}

@Composable
fun CounterApp() {
    var result by remember { mutableStateOf(0.0) }
    var input by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Resultado: $result",
            fontSize = 24.sp,
            modifier = Modifier.padding(8.dp)
        )

        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Digite um número") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    result += input.toDoubleOrNull() ?: 0.0
                    input = ""
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Incrementar")
            }
            // Botão Decrementar
            Button(
                onClick = {
                    result -= input.toDoubleOrNull() ?: 0.0
                    input = ""
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Decrementar")
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Button(
                onClick = {
                    result *= input.toDoubleOrNull() ?: 1.0
                    input = ""
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Multiplicar")
            }

            Button(
                onClick = {
                    val value = input.toDoubleOrNull() ?: 1.0
                    if (value != 0.0) {
                        result /= value
                    }
                    input = ""
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Dividir")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                result = 0.0
                input = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Limpar")
        }
    }
}
package com.example.roomdatabase

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton

class MainActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                UserApp(viewModel = viewModel)
            }
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserApp(viewModel: UserViewModel) {

    val usuarios by viewModel.users.observeAsState(emptyList())

    var nome by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }

    var usuarioEditando by remember { mutableStateOf<User?>(null) }

    var mensagemErro by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Gerenciamento de Usuários") },
                backgroundColor = Color(0xFF1F2A44),
                contentColor = Color.White
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = if (usuarioEditando == null) "Adicionar um novo usuário" else "Editar usuário",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF1F2A44),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text(text = "Nome") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = idade,
                onValueChange = { idade = it },
                label = { Text(text = "Idade") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    try {
                        if (nome.isNotEmpty() && idade.isNotEmpty()) {
                            val idadeInt = idade.toInt()
                            if (usuarioEditando == null) {
                                viewModel.addUser(User(name = nome, age = idadeInt))
                            } else {
                                viewModel.updateUser(usuarioEditando!!.copy(name = nome, age = idadeInt))
                                usuarioEditando = null
                            }
                            nome = ""
                            idade = ""
                            mensagemErro = null
                        } else {
                            mensagemErro = "Preencha todos os campos!"
                        }
                    } catch (e: NumberFormatException) {
                        mensagemErro = "Idade inválida. Digite apenas números."
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1F2A44))
            ) {
                Text(
                    text = if (usuarioEditando == null) "Adicionar usuário" else "Salvar alterações",
                    color = Color.White
                )
            }

            mensagemErro?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    it,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Lista de Usuários",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF1F2A44)
            )

            LazyColumn {
                items(usuarios) { user ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "• ${user.name}, ${user.age} anos",
                            fontSize = 16.sp
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        OutlinedButton(
                            onClick = {
                                usuarioEditando = user
                                nome = user.name
                                idade = user.age.toString()
                            },
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color(0xFF1F2A44)
                            )
                        ) {
                            Text("Editar")
                        }
                    }
                }
            }
        }
    }
}

package com.example.zooapp.models

import com.example.zooapp.R

data class Animal (
    val id: Int,
    val name: String,
    val genero: String,
    val imageRes: Int,
    val description: String,
    val desenvolvedora: String,
    var isFavorite: Boolean = false
)

val animalList = listOf(
    Animal(
        id = 1,
        name = "Terraria",
        genero = "Sobrevivencia em mundo aberto, Faça o que Quiser, 2D, Multijogador",
        imageRes = R.drawable.terraria,
        description = "Dig, fight, explore, build! Nothing is impossible in this action-packed adventure game. Four Pack also available!",
        desenvolvedora = "Re-Logic."
    ),
    Animal(
        id = 2,
        name = "Factorio",
        genero = "Automação, Estrategia, 2D",
        imageRes = R.drawable.factorio,
        description = "Factorio is a game about building and creating automated factories to produce items of increasing complexity, within an infinite 2D world. Use your imagination to design your factory, combine simple elements into ingenious structures, and finally protect it from the creatures who don't really like you.",
        desenvolvedora = "Wube Software LTD."
    ),
    Animal(
        id = 3,
        name = "Skyrim",
        genero = "Ação e Aventura, Mediavel, RPG",
        imageRes = R.drawable.skyrim,
        description = "Vencedor de mais de 200 prémios de Jogo do Ano, The Elder Scrolls V: Skyrim Special Edition dá vida à fantasia épica com um nível de detalhe espantoso. A Special Edition inclui o jogo aclamado pela crítica e suplementos com novas funcionalidades.",
        desenvolvedora = "Bethesda Games Studios"
    ),
)

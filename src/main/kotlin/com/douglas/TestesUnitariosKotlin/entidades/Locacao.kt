package com.douglas.TestesUnitariosKotlin.entidades

import java.util.*

class Locacao {
    var usuario: Usuario? = null
    var filmes: List<Filme>? = null
    var dataLocacao: Date? = null
    var dataRetorno: Date? = null
    var valor: Double? = null
}
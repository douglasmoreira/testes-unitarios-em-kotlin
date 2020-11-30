package com.douglas.TestesUnitariosKotlin.entidades

class Filme {
    var nome: String? = null
    var estoque: Int? = null
    var precoLocacao: Double? = null

    constructor() {}
    constructor(nome: String?, estoque: Int?, precoLocacao: Double?) {
        this.nome = nome
        this.estoque = estoque
        this.precoLocacao = precoLocacao
    }
}
package com.douglas.TestesUnitariosKotlin.entidades

class Usuario {
    var nome: String? = null

    constructor() {}
    constructor(nome: String?) {
        this.nome = nome
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Usuario

        if (nome != other.nome) return false

        return true
    }

    override fun hashCode(): Int {
        return nome?.hashCode() ?: 0
    }


}
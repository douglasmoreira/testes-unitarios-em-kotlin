package com.douglas.TestesUnitariosKotlin.servicos

import com.douglas.TestesUnitariosKotlin.exception.NaoPodeDividirPorZeroException

class Calculadora {

    fun somar(a: Int, b: Int): Int {
        return a + b
    }

    fun subtrair(a: Int, b: Int): Int {
        return a - b
    }

    @Throws(NaoPodeDividirPorZeroException::class)
    fun divide(a: Int, b: Int): Int {
        if (b == 0) {
            throw NaoPodeDividirPorZeroException()
        }
        return a / b
    }
}

package com.douglas.TestesUnitariosKotlin.servicos

import com.douglas.TestesUnitariosKotlin.exception.NaoPodeDividirPorZeroException
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CalculadoraTest {

    var calculadora: Calculadora? = null

    @Before
    fun setUp() {
        calculadora = Calculadora()
    }

    @Test
    fun deveSomarDoisValores() {

        //cenário
        val a = 5
        val b = 3

        //açao
        val resultado: Int = calculadora!!.somar(a, b)

        //verificação
        Assert.assertEquals(8, resultado.toLong())
    }

    @Test
    fun deveSubtrairDoisValores() {

        //cenário
        val a = 8
        val b = 5

        //açao
        val resultado: Int = calculadora!!.subtrair(a, b)

        //verificação
        Assert.assertEquals(3, resultado.toLong())
    }

    @Test
    fun deveDividirDoisValores() {

        //cenário
        val a = 6
        val b = 3

        //açao
        val resultado: Int = calculadora!!.divide(a, b)

        //verificação
        Assert.assertEquals(2, resultado.toLong())
    }

    @Test(expected = NaoPodeDividirPorZeroException::class)
    @Throws(NaoPodeDividirPorZeroException::class)
    fun deveLancarExcecaoQuandoDividirPorZero() {

        //cenário
        val a = 10
        val b = 0

        //açao
        val resultado = calculadora!!.divide(a, b)

        //verificação
        Assert.assertEquals(2, resultado.toLong())
    }
}
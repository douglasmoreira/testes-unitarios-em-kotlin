package com.douglas.TestesUnitariosKotlin.servicos

import com.douglas.TestesUnitariosKotlin.entidades.Filme
import com.douglas.TestesUnitariosKotlin.entidades.Usuario
import com.douglas.TestesUnitariosKotlin.utils.DataUtils.isMesmaData
import com.douglas.TestesUnitariosKotlin.utils.DataUtils.obterDataComDiferencaDias
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ErrorCollector
import java.util.*

class LocacaoServiceTest {

    @get:Rule
//    @Rule @JvmField // outra forma de anotar e tornar a Rule publica
    var error = ErrorCollector()

    @Test
    fun main() {
        //cenario
        val service = LocacaoService()
        val usuario = Usuario("Usuario1")
        val filme = Filme("filme 1", 2, 5.0)

        //acão
        val locacao = service.alugarFilme(usuario, filme)

        //verificaçao
        Assert.assertTrue(locacao.valor == 5.0)
        Assert.assertTrue(isMesmaData(locacao.dataLocacao, Date()))
        Assert.assertTrue(isMesmaData(locacao.dataRetorno, obterDataComDiferencaDias(1)))

        //teste usando o ErrorCollector

        //teste usando o ErrorCollector
        error.checkThat(locacao.valor, `is`(equalTo(5.0)))
        error.checkThat(isMesmaData(locacao.dataLocacao, Date()), `is`(true))
        error.checkThat(isMesmaData(locacao.dataRetorno, obterDataComDiferencaDias(1)), `is`(true))


        //Estudo assertThat()
        Assert.assertThat(locacao.valor, `is`(equalTo(5.0)))
        Assert.assertThat(locacao.valor, `is`(not(6.0)))
        Assert.assertThat(isMesmaData(locacao.dataLocacao, Date()), CoreMatchers.`is`(true))
        Assert.assertThat(isMesmaData(locacao.dataRetorno, obterDataComDiferencaDias(1)), `is`(true))
    }
}
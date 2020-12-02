package com.douglas.TestesUnitariosKotlin.servicos

import com.douglas.TestesUnitariosKotlin.entidades.Filme
import com.douglas.TestesUnitariosKotlin.entidades.Usuario
import com.douglas.TestesUnitariosKotlin.exception.FilmeSemEstoqueException
import com.douglas.TestesUnitariosKotlin.exception.LocadoraException
import com.douglas.TestesUnitariosKotlin.utils.DataUtils.isMesmaData
import com.douglas.TestesUnitariosKotlin.utils.DataUtils.obterDataComDiferencaDias
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ErrorCollector
import org.junit.rules.ExpectedException
import java.util.*

class LocacaoServiceTest {

    @get:Rule
//    @Rule @JvmField // outra forma de anotar e tornar a Rule publica
    var error = ErrorCollector()

    @get:Rule
    var expection = ExpectedException.none()

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

//   forma elegante
    @Test(expected = FilmeSemEstoqueException::class)
    fun testLocacao_filmeSemEstoque()    {

        //cenario
        val service = LocacaoService()
        val usuario = Usuario("Usuario1")
        val filme = Filme("filme 1", 0, 5.0)

        //acão
        service.alugarFilme(usuario, filme)
    }

//    forma robusta
    @Test
    fun testLocacao_filmeSemEstoque2() {

        //cenario
        val service = LocacaoService()
        val usuario = Usuario("Usuario1")
        val filme = Filme("filme 1", 0, 5.0)

        //acão
        try {
            service.alugarFilme(usuario, filme)
            Assert.fail("Deveria ter lançado uma excessão")
        } catch (e: Exception) {
            Assert.assertThat(e.message, `is`("Filme sem estoque"))
        }
    }

//    forma nova
    @Test
    @Throws(FilmeSemEstoqueException::class)
    fun testLocacao_filmeSemEstoque3() {

        //cenario
        val service = LocacaoService()
        val usuario = Usuario("Usuario1")
        val filme = Filme("filme 1", 0, 5.0)

        expection.expect(java.lang.Exception::class.java)
        expection.expectMessage("Filme sem estoque")

        //acão
        service.alugarFilme(usuario, filme)
    }

//    forma robusta
    @Test
    @Throws(FilmeSemEstoqueException::class)
    fun testLocacao_usuarioVazio() {

        //cenario
        val service = LocacaoService()
        val filme = Filme("filme 1", 1, 5.0)

        //acão
        try {
            service.alugarFilme(null, filme)
            Assert.fail()
        } catch (e: LocadoraException) {
            Assert.assertThat(e.message, `is`("Usuário vazio"))
        }
    }

//    forma nova
    @Test
    @Throws(FilmeSemEstoqueException::class, LocadoraException::class)
    fun testLocacao_filmeVazio() {

        //cenario
        val service = LocacaoService()
        val usuario = Usuario("Usuario1")
        expection.expect(LocadoraException::class.java)
        expection.expectMessage("Filme vazio")

        //acão
        service.alugarFilme(usuario, null)
    }
}
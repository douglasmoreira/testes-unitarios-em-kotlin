package com.douglas.TestesUnitariosKotlin.servicos

import com.douglas.TestesUnitariosKotlin.entidades.Filme
import com.douglas.TestesUnitariosKotlin.entidades.Locacao
import com.douglas.TestesUnitariosKotlin.entidades.Usuario
import com.douglas.TestesUnitariosKotlin.exception.FilmeSemEstoqueException
import com.douglas.TestesUnitariosKotlin.exception.LocadoraException
import com.douglas.TestesUnitariosKotlin.utils.DataUtils
import com.douglas.TestesUnitariosKotlin.utils.DataUtils.isMesmaData
import com.douglas.TestesUnitariosKotlin.utils.DataUtils.obterDataComDiferencaDias
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.*
import org.junit.rules.ErrorCollector
import org.junit.rules.ExpectedException
import java.util.*
import java.util.Arrays.asList

class LocacaoServiceTest {

    private lateinit var service: LocacaoService

    @Before
    fun setUp() {
        service = LocacaoService()
    }

    @get:Rule
//    @Rule @JvmField // outra forma de anotar e tornar a Rule publica
    var error = ErrorCollector()

    @get:Rule
    var expection = ExpectedException.none()

    @Test
//    @Ignore // caso precise ignorar o teste
    fun deveAlugarFilme() {

        Assume.assumeFalse(DataUtils.verificarDiaSemana(Date(), Calendar.SATURDAY))


        //cenario
        val usuario = Usuario("Usuario1")
        val filme = asList(Filme("filme 1", 2, 5.0))

        //acão
        val locacao = service?.alugarFilme(usuario, filme)

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
    fun deveLancarExcecaoAoAlugarFilmeSemEstoque()    {

        //cenario
        val usuario = Usuario("Usuario1")
        val filme = asList(Filme("filme 1", 0, 5.0))

        //acão
        service.alugarFilme(usuario, filme)
    }

//    forma robusta
    @Test
    fun deveLancarExcecaoAoAlugarFilmeSemEstoque2() {

        //cenario
        val usuario = Usuario("Usuario1")
        val filme = asList(Filme("filme 1", 0, 5.0))

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
    fun deveLancarExcecaoAoAlugarFilmeSemEstoque3() {

        //cenario
        val usuario = Usuario("Usuario1")
        val filme = asList(Filme("filme 1", 0, 5.0))

        expection.expect(java.lang.Exception::class.java)
        expection.expectMessage("Filme sem estoque")

        //acão
        service.alugarFilme(usuario, filme)
    }

//    forma robusta
    @Test
    @Throws(FilmeSemEstoqueException::class)
    fun naoDeveAlugarFilmeSemUsuario() {

        //cenario
        val filme = asList(Filme("filme 1", 1, 5.0))

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
    fun naoDeveAlugarFilmeSemFilme() {

        //cenario
        val usuario = Usuario("Usuario1")
        expection.expect(LocadoraException::class.java)
        expection.expectMessage("Filme vazio")

        //acão
        service.alugarFilme(usuario, null)
    }

    @Test
    @Throws(FilmeSemEstoqueException::class, LocadoraException::class)
    fun devePagar75PctNoFilme3() {

        //cenario
        val usuario = Usuario("Usuario1")
        val filmes = asList(
            Filme("filme 1", 1, 4.0),
            Filme("filme 2", 1, 4.0),
            Filme("filme 3", 1, 4.0)
        )

        //acão
        val resultado: Locacao = service.alugarFilme(usuario, filmes)

        //verificaçao
        Assert.assertThat(resultado.valor, `is`(11.0))
    }

    @Test
    @Throws(FilmeSemEstoqueException::class, LocadoraException::class)
    fun devePagar50PctNoFilme4() {

        //cenario
        val usuario = Usuario("Usuario1")
        val filmes = asList(
            Filme("filme 1", 1, 4.0),
            Filme("filme 2", 1, 4.0),
            Filme("filme 3", 1, 4.0),
            Filme("filme 4", 1, 4.0)
        )

        //acão
        val resultado: Locacao = service.alugarFilme(usuario, filmes)

        //verificaçao
        Assert.assertThat(resultado.valor, `is`(13.0))
    }

    @Test
    @Throws(FilmeSemEstoqueException::class, LocadoraException::class)
    fun devePagar25PctNoFilme5() {

        //cenario
        val usuario = Usuario("Usuario1")
        val filmes = asList(
            Filme("filme 1", 1, 4.0),
            Filme("filme 2", 1, 4.0),
            Filme("filme 3", 1, 4.0),
            Filme("filme 4", 1, 4.0),
            Filme("filme 5", 1, 4.0)
        )

        //acão
        val resultado: Locacao = service.alugarFilme(usuario, filmes)

        //verificaçao
        Assert.assertThat(resultado.valor, `is`(14.0))
    }

    @Test
    @Throws(FilmeSemEstoqueException::class, LocadoraException::class)
    fun devePagar0PctNoFilme6() {

        //cenario
        val usuario = Usuario("Usuario1")
        val filmes = asList(
            Filme("filme 1", 1, 4.0),
            Filme("filme 2", 1, 4.0),
            Filme("filme 3", 1, 4.0),
            Filme("filme 4", 1, 4.0),
            Filme("filme 5", 1, 4.0),
            Filme("filme 6", 1, 4.0)
        )

        //acão
        val resultado: Locacao = service.alugarFilme(usuario, filmes)

        //verificaçao
        Assert.assertThat(resultado.valor, `is`(14.0))
    }

    @Test
    @Throws(FilmeSemEstoqueException::class, LocadoraException::class)
    fun naoDeveDevolverFilmeNoDomingo() {
        Assume.assumeTrue(DataUtils.verificarDiaSemana(Date(), Calendar.SATURDAY))

        //cenario
        val usuario = Usuario("Usuario1")
        val filmes = asList(Filme("filme 1", 1, 4.0))

        //acão
        val resultado = service.alugarFilme(usuario, filmes)

        //verificaçao
        val ehSegunda: Boolean = DataUtils.verificarDiaSemana(resultado.dataRetorno, Calendar.MONDAY)
        Assert.assertTrue(ehSegunda)
    }
}
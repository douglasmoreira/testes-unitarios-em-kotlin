package com.douglas.TestesUnitariosKotlin.servicos

import com.douglas.TestesUnitariosKotlin.entidades.Filme
import com.douglas.TestesUnitariosKotlin.entidades.Usuario
import com.douglas.TestesUnitariosKotlin.utils.DataUtils
import org.junit.Assert
import org.junit.Test
import java.util.*

class LocacaoServiceTest {

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
        Assert.assertTrue(DataUtils.isMesmaData(locacao.dataLocacao, Date()))
        Assert.assertTrue(DataUtils.isMesmaData(locacao.dataRetorno, DataUtils.obterDataComDiferencaDias(1)))
    }
}
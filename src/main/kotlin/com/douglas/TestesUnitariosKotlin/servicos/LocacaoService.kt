package com.douglas.TestesUnitariosKotlin.servicos

import com.douglas.TestesUnitariosKotlin.entidades.Filme
import com.douglas.TestesUnitariosKotlin.entidades.Locacao
import com.douglas.TestesUnitariosKotlin.entidades.Usuario
import com.douglas.TestesUnitariosKotlin.utils.DataUtils
import com.douglas.TestesUnitariosKotlin.utils.DataUtils.adicionarDias
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*

class LocacaoService {
    fun alugarFilme(usuario: Usuario?, filme: Filme): Locacao {
        val locacao = Locacao()
        locacao.filme = filme
        locacao.usuario = usuario
        locacao.dataLocacao = Date()
        locacao.valor = filme.precoLocacao

        //Entrega no dia seguinte
        var dataEntrega: Date? = Date()
        dataEntrega = adicionarDias(dataEntrega, 1)
        locacao.dataRetorno = dataEntrega

        //Salvando a locacao...
        //TODO adicionar método para salvar
        return locacao
    }

    @Test
    fun main() {
        //cenario
        val service = LocacaoService()
        val usuario = Usuario("Usuario1")
        val filme = Filme("filme 1", 2, 5.0)

        //acão
        val locacao = service.alugarFilme(usuario, filme)

        //verificaçao
        assertTrue(locacao.valor == 5.0)
        assertTrue(DataUtils.isMesmaData(locacao.dataLocacao, Date()))
        assertTrue(DataUtils.isMesmaData(locacao.dataRetorno, DataUtils.obterDataComDiferencaDias(1)))
    }
}
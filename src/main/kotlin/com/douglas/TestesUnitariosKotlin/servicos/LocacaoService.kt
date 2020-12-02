package com.douglas.TestesUnitariosKotlin.servicos

import com.douglas.TestesUnitariosKotlin.entidades.Filme
import com.douglas.TestesUnitariosKotlin.entidades.Locacao
import com.douglas.TestesUnitariosKotlin.entidades.Usuario
import com.douglas.TestesUnitariosKotlin.exception.FilmeSemEstoqueException
import com.douglas.TestesUnitariosKotlin.exception.LocadoraException
import com.douglas.TestesUnitariosKotlin.utils.DataUtils.adicionarDias
import java.util.*

class LocacaoService {
    fun alugarFilme(usuario: Usuario?, filme: Filme?): Locacao {

        if (usuario == null) {
            throw LocadoraException("Usuário vazio")
        }

        if (filme == null) {
            throw LocadoraException("Filme vazio")
        }

        if (filme.estoque === 0) {
            throw FilmeSemEstoqueException("Filme sem estoque")
        }

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
}
package com.douglas.TestesUnitariosKotlin.servicos

import com.douglas.TestesUnitariosKotlin.entidades.Filme
import com.douglas.TestesUnitariosKotlin.entidades.Locacao
import com.douglas.TestesUnitariosKotlin.entidades.Usuario
import com.douglas.TestesUnitariosKotlin.exception.FilmeSemEstoqueException
import com.douglas.TestesUnitariosKotlin.exception.LocadoraException
import com.douglas.TestesUnitariosKotlin.utils.DataUtils.adicionarDias
import java.util.*

class LocacaoService {
    fun alugarFilme(usuario: Usuario?, filmes: List<Filme>?): Locacao {

        if (usuario == null) {
            throw LocadoraException("Usuário vazio")
        }

        if (filmes == null) {
            throw LocadoraException("Filme vazio")
        }

        for (filme in filmes) {
            if (filme.estoque === 0) {
                throw FilmeSemEstoqueException("Filme sem estoque")
            }
        }

        val locacao = Locacao()
        locacao.filmes = filmes
        locacao.usuario = usuario
        locacao.dataLocacao = Date()
        var valorTotal = 0.0
        for (filme in filmes) {
            valorTotal += filme.precoLocacao!!
        }
        locacao.valor =valorTotal

        //Entrega no dia seguinte
        var dataEntrega: Date? = Date()
        dataEntrega = adicionarDias(dataEntrega, 1)
        locacao.dataRetorno = dataEntrega

        //Salvando a locacao...
        //TODO adicionar método para salvar
        return locacao
    }
}
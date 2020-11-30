package com.douglas.TestesUnitariosKotlin.utils

import java.util.*

object DataUtils {
    /**
     * Retorna a data enviada por parametro com a adição dos dias desejado
     * a Data pode estar no futuro (dias > 0) ou no passado (dias < 0)
     *
     * @param data
     * @param dias
     * @return
     */
    fun adicionarDias(data: Date?, dias: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = data
        calendar.add(Calendar.DAY_OF_MONTH, dias)
        return calendar.time
    }

    /**
     * Retorna a data atual com a diferenca de dias enviados por parametro
     * a Data pode estar no futuro (parametro positivo) ou no passado (parametro negativo)
     *
     * @param dias Quantidade de dias a ser incrementado/decrementado
     * @return Data atualizada
     */
    fun obterDataComDiferencaDias(dias: Int): Date {
        return adicionarDias(Date(), dias)
    }

    /**
     * Retorna uma instância de `Date` refletindo os valores passados por parametro
     *
     * @param dia
     * @param mes
     * @param ano
     * @return
     */
    fun obterData(dia: Int, mes: Int, ano: Int): Date {
        val calendar = Calendar.getInstance()
        calendar[Calendar.DAY_OF_MONTH] = dia
        calendar[Calendar.MONTH] = mes - 1
        calendar[Calendar.YEAR] = ano
        return calendar.time
    }

    /**
     * Verifica se uma data é igual a outra
     * Esta comparação considera apenas dia, mes e ano
     *
     * @param data1
     * @param data2
     * @return
     */
    fun isMesmaData(data1: Date?, data2: Date?): Boolean {
        val calendar1 = Calendar.getInstance()
        calendar1.time = data1
        val calendar2 = Calendar.getInstance()
        calendar2.time = data2
        return (calendar1[Calendar.DAY_OF_MONTH] == calendar2[Calendar.DAY_OF_MONTH]
                && calendar1[Calendar.MONTH] == calendar2[Calendar.MONTH]
                && calendar1[Calendar.YEAR] == calendar2[Calendar.YEAR])
    }

    /**
     * Verifica se uma determinada data é o dia da semana desejado
     *
     * @param data Data a ser avaliada
     * @param diaSemana `true` caso seja o dia da semana desejado, `false` em caso contrário
     * @return
     */
    fun verificarDiaSemana(data: Date?, diaSemana: Int): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = data
        return calendar[Calendar.DAY_OF_WEEK] == diaSemana
    }
}
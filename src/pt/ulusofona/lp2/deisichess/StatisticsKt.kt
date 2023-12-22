package pt.ulusofona.lp2.deisichess

import java.util.*

class StatisticsKt {

    fun  getStatsCalculator(tipo: StatType): Function1<GameManager, ArrayList<String>> {
        when (tipo) {
            StatType.TOP_5_PONTOS -> return ::calculaMaximo
            StatType.TOP_5_CAPTURAS -> return ::calculaMaximo
            StatType.PECAS_MAIS_5_CAPTURAS -> return ::calculaMaximo
            StatType.PECAS_MAIS_BARALHADAS -> return ::calculaMaximo
            StatType.TIPOS_CAPTURADOS -> return ::calculaMaximo

        }
    }


    fun calculaMaximo(numeros: GameManager) : ArrayList<String> {
        val arrayList = ArrayList<String>()
        return  arrayList
    }




}
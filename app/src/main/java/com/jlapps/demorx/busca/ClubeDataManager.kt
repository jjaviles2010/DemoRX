package com.jlapps.demorx.busca

class ClubeDataManager {

    fun getClubes(): List<Clube> {
        val clubes = arrayListOf<Clube>()
        clubes.add(Clube("Palmeiras"))
        clubes.add(Clube("Bragantino"))
        clubes.add(Clube("Santos"))
        clubes.add(Clube("Real Madrid"))
        clubes.add(Clube("PSG"))
        clubes.add(Clube("Brasil de Pelotas Tche"))
        return clubes
    }

}
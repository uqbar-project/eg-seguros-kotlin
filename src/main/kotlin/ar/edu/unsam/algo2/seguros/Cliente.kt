package ar.edu.unsam.algo2.seguros

import java.time.LocalDate

abstract class Cliente {
    protected var deuda = 0

    abstract fun puedeCobrarSiniestro(): Boolean

    fun esMoroso() = deuda > 0

    fun facturar(monto: Int) {
        deuda += monto
    }
}

val MAXIMO_FLOTA_MUCHOS_AUTOS = 10000
val MAXIMO_FLOTA_POCOS_AUTOS = 5000
val LIMITE_MUCHOS_AUTOS = 5

class Flota(var autos: Int) : Cliente() {

    override fun puedeCobrarSiniestro() =
        deuda <= maximoPermitido()

    fun maximoPermitido() =
        if (autos <= LIMITE_MUCHOS_AUTOS) MAXIMO_FLOTA_POCOS_AUTOS else MAXIMO_FLOTA_MUCHOS_AUTOS

}

class ClienteNormal : Cliente() {
    private val diasDeConsulta = mutableListOf<LocalDate>()

    fun registrarConsulta() {
        if (esMoroso() && !tieneConsultas(LocalDate.now())) {
            diasDeConsulta.add(LocalDate.now())
        }
    }

    fun tieneConsultas(dia: LocalDate) = diasDeConsulta.any { it == dia }

    override fun puedeCobrarSiniestro(): Boolean {
        registrarConsulta()
        return !esMoroso()
    }
}
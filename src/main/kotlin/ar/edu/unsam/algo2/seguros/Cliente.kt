package ar.edu.unsam.algo2.seguros

import java.time.LocalDate

abstract class Cliente {
    protected var deuda: Int = 0

    abstract fun puedeCobrarSiniestro(): Boolean

    fun esMoroso() = deuda > 0

    fun generarDeuda(monto: Int) {
        this.deuda = this.deuda + monto
    }
}

val MAXIMO_FLOTA_MUCHOS_AUTOS = 10000
val MAXIMO_FLOTA_POCOS_AUTOS = 5000
val LIMITE_MUCHOS_AUTOS = 5

class Flota : Cliente() {
    var autos: Int = 0

    override fun puedeCobrarSiniestro() =
        this.deuda <= maximoPermitido()

    fun maximoPermitido() =
        if (autos <= LIMITE_MUCHOS_AUTOS) MAXIMO_FLOTA_POCOS_AUTOS else MAXIMO_FLOTA_MUCHOS_AUTOS

}

class ClienteNormal : Cliente() {
    var diasDeConsulta: MutableList<LocalDate> = mutableListOf()

    fun registrarConsulta() {
        if (this.esMoroso() && !tieneConsultas(LocalDate.now())) {
            diasDeConsulta.add(LocalDate.now())
        }
    }

    fun tieneConsultas(dia: LocalDate) = diasDeConsulta.any { it == dia }

    override fun puedeCobrarSiniestro(): Boolean {
        registrarConsulta()
        return !esMoroso()
    }
}
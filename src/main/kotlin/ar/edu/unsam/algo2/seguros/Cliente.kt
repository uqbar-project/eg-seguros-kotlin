package ar.edu.unsam.algo2.seguros

open class Cliente {
    protected var deuda: Int = 0

    open fun puedeCobrarSiniestro() = !esMoroso()

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

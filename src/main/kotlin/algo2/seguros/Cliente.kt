package algo2.seguros

open class Cliente {
    protected var deuda: Int = 0

    open fun puedeCobrarSiniestro(): Boolean {
        return !esMoroso()
    }

    fun esMoroso(): Boolean {
        return this.deuda > 0
    }

    fun generarDeuda(monto: Int) {
        this.deuda = this.deuda + monto
    }

}

public val MAXIMO_FLOTA_MUCHOS_AUTOS = 10000
public val MAXIMO_FLOTA_POCOS_AUTOS = 5000
public val LIMITE_MUCHOS_AUTOS = 5

class Flota : Cliente() {
    var autos: Int = 0

    override fun puedeCobrarSiniestro(): Boolean {
        return this.deuda <= maximoPermitido()
    }

    fun maximoPermitido(): Int {
        return if (autos <= LIMITE_MUCHOS_AUTOS) MAXIMO_FLOTA_POCOS_AUTOS else MAXIMO_FLOTA_MUCHOS_AUTOS
    }
}
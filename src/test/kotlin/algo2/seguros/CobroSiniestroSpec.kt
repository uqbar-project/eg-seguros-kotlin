package algo2.seguros

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

// https://kotest.io/
class CobroSiniestroSpec : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("Tests Cobro Siniestro") {
        describe("Dado un cliente normal") {
            val cliente = Cliente()
            it("si no es moroso puede cobrar siniestro") {
                cliente.puedeCobrarSiniestro() shouldBe true
            }
            it("si tiene deuda no puede cobrar siniestro") {
                cliente.generarDeuda(10)
                cliente.puedeCobrarSiniestro() shouldBe false
            }
        }
        describe("Dada una flota con muchos autos") {
            val flotaConMuchosAutos = Flota()
            flotaConMuchosAutos.autos = LIMITE_MUCHOS_AUTOS + 1
            it("si tiene mucha deuda no puede cobrar siniestro") {
                flotaConMuchosAutos.generarDeuda(MAXIMO_FLOTA_MUCHOS_AUTOS + 1)
                flotaConMuchosAutos.puedeCobrarSiniestro() shouldBe false
            }
            it("si no tiene poca deuda puede cobrar siniestro") {
                flotaConMuchosAutos.generarDeuda(MAXIMO_FLOTA_MUCHOS_AUTOS)
                flotaConMuchosAutos.puedeCobrarSiniestro() shouldBe true
            }
        }
        describe("Dada una flota con pocos autos") {
            val flotaConMuchosAutos = Flota()
            flotaConMuchosAutos.autos = LIMITE_MUCHOS_AUTOS
            it("si tiene mucha deuda no puede cobrar siniestro") {
                flotaConMuchosAutos.generarDeuda(MAXIMO_FLOTA_POCOS_AUTOS + 1)
                flotaConMuchosAutos.puedeCobrarSiniestro() shouldBe false
            }
            it("si no tiene poca deuda puede cobrar siniestro") {
                flotaConMuchosAutos.generarDeuda(MAXIMO_FLOTA_POCOS_AUTOS)
                flotaConMuchosAutos.puedeCobrarSiniestro() shouldBe true
            }
        }
    }
})
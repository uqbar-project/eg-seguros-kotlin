package ar.edu.unsam.algo2.seguros

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

// En los tests prescindimos de utilizar las mismas constantes que en cliente
// para que si esos valores cambian los test se rompan (esta hecho por diseño).
//
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
            // Arrange
            val flotaConMuchosAutos = crearFlota(6)
            it("si tiene mucha deuda no puede cobrar siniestro") {
                // Act
                flotaConMuchosAutos.generarDeuda(10001)
                // Assert
                flotaConMuchosAutos.puedeCobrarSiniestro() shouldBe false
            }
            it("si no tiene poca deuda puede cobrar siniestro") {
                // Act
                flotaConMuchosAutos.generarDeuda(10000)
                // Assert
                flotaConMuchosAutos.puedeCobrarSiniestro() shouldBe true
            }
        }
        describe("Dada una flota con pocos autos") {
            val flotaConMuchosAutos = Flota()
            flotaConMuchosAutos.autos = 5
            it("si tiene mucha deuda no puede cobrar siniestro") {
                flotaConMuchosAutos.generarDeuda(5001)
                flotaConMuchosAutos.puedeCobrarSiniestro() shouldBe false
            }
            it("si no tiene poca deuda puede cobrar siniestro") {
                flotaConMuchosAutos.generarDeuda(5000)
                flotaConMuchosAutos.puedeCobrarSiniestro() shouldBe true
            }
        }
    }
})

fun crearFlota(cantidadAutos: Int) =
    Flota().apply {
        autos = cantidadAutos
    }
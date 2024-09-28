/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.quince;

import game.computer.Computer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import ui.menu.Menu;


public class Quince {
    private List<Integer> numerosJugador;
    private List<Integer> numerosComputadora;
    private Computer computadora;
    private Menu menu;

    public Quince(Menu menu) {
        this.numerosJugador = new ArrayList<>();
        this.numerosComputadora = new ArrayList<>();
        this.computadora = new Computer();
        this.menu = menu;  // Referencia al menú
    }

    // Método para procesar el número seleccionado por el jugador
    public void seleccionarNumeroJugador(int numero) {
        // Verificar si el número ya ha sido seleccionado
        if (numerosJugador.contains(numero) || numerosComputadora.contains(numero)) {
            System.out.println("El número " + numero + " ya ha sido seleccionado.");
            return;
        }

        // Registrar jugada del jugador
        registrarJugadaJugador(numero);

        // Verificar si el jugador ha ganado
        if (verificarGanador(numerosJugador)) {
            JOptionPane.showMessageDialog(null, "¡El Jugador Gana!");
            menu.actualizarEstadisticas(1);  // Actualizar estadísticas del jugador
            reiniciarJuego();  // Reiniciar juego
        } else if (numerosJugador.size() + numerosComputadora.size() < 9) {
            // Turno de la computadora
            seleccionarNumeroComputadora();
        } else {
            // Empate
            JOptionPane.showMessageDialog(null, "¡Empate!");
            menu.actualizarEstadisticas(0);  // Actualizar estadísticas de empate
            reiniciarJuego();
        }
    }

    // Método para que la computadora seleccione un número
    private void seleccionarNumeroComputadora() {
        // Computadora elige un número estratégico
        int numeroSeleccionado = computadora.jugar(numerosJugador, numerosComputadora);
        numerosComputadora.add(numeroSeleccionado);
        System.out.println("Computadora eligió: " + numeroSeleccionado);

        // Verificar si la computadora ha ganado
        if (verificarGanador(numerosComputadora)) {
            JOptionPane.showMessageDialog(null, "¡La Computadora ha ganado!");
            menu.actualizarEstadisticas(2);  // Actualizar estadísticas de la computadora
            reiniciarJuego();  // Reiniciar juego
        } else if (numerosJugador.size() + numerosComputadora.size() == 9) {
            // Empate
            JOptionPane.showMessageDialog(null, "¡Empate!");
            menu.actualizarEstadisticas(0);  // Actualizar estadísticas de empate
            reiniciarJuego();
        }
    }

    // Verificar si un jugador tiene una combinación ganadora
    private boolean verificarGanador(List<Integer> numeros) {
        int[][] combinacionesGanadoras = {
            {4, 9, 2}, {3, 5, 7}, {8, 1, 6},
            {4, 3, 8}, {9, 5, 1}, {2, 7, 6},
            {4, 5, 6}, {2, 5, 8}
        };

        for (int[] combinacion : combinacionesGanadoras) {
            if (numeros.contains(combinacion[0]) &&
                numeros.contains(combinacion[1]) &&
                numeros.contains(combinacion[2])) {
                return true;
            }
        }
        return false;
    }

    // Registrar jugada del jugador y reflejar en Computer
    private void registrarJugadaJugador(int numeroJugador) {
        // Agregar el número a la lista de jugadas del jugador
        numerosJugador.add(numeroJugador);

        // Registrar la jugada en la clase Computer para eliminarla de los números disponibles
        computadora.registrarJugadaJugador(numeroJugador);

        // Mostrar en consola la elección del jugador
        System.out.println("Jugador eligió: " + numeroJugador);
    }

    // Reiniciar los valores del juego
    public void reiniciarJuego() {
        numerosJugador.clear();
        numerosComputadora.clear();
    }

    // Obtener los números del jugador y computadora para actualizar UI
    public List<Integer> getNumerosJugador() {
        return numerosJugador;
    }

    public List<Integer> getNumerosComputadora() {
        return numerosComputadora;
    }

}
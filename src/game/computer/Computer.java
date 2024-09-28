/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.computer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Computer {
    
    private final int[][] cuadroMagico = {
        {4, 9, 2},
        {3, 5, 7},
        {8, 1, 6}
    };

    private List<Integer> numerosDisponibles = new ArrayList<>();

    public Computer() {
        // Inicializamos los números disponibles del 1 al 9
        for (int i = 1; i <= 9; i++) {
            numerosDisponibles.add(i);
        }
    }

    // Método que toma en cuenta las jugadas del jugador y la computadora
    public int jugar(List<Integer> jugadasJugador, List<Integer> jugadasComputadora) {
        // 1. Verificar si la computadora puede ganar
        int jugadaGanadora = buscarJugadaGanadora(jugadasComputadora);
        if (jugadaGanadora != -1) {
            return realizarJugada(jugadaGanadora);
        }

        // 2. Verificar si el jugador puede ganar en el próximo turno y bloquear
        int jugadaBloqueo = buscarJugadaGanadora(jugadasJugador);
        if (jugadaBloqueo != -1) {
            return realizarJugada(jugadaBloqueo);
        }
        
        // 3. Elegir una jugada estratégica basada en el cuadro mágico
        return realizarJugada(elegirJugadaEstrategica());
    }

    // Método para buscar si hay una combinación ganadora
    private int buscarJugadaGanadora(List<Integer> jugadas) {
        for (int i = 0; i < cuadroMagico.length; i++) {
            for (int j = 0; j < cuadroMagico[i].length; j++) {
                int num1 = cuadroMagico[i][0];
                int num2 = cuadroMagico[i][1];
                int num3 = cuadroMagico[i][2];

                if (jugadas.contains(num1) && jugadas.contains(num2) && numerosDisponibles.contains(num3)) {
                    return num3;
                }
                if (jugadas.contains(num1) && jugadas.contains(num3) && numerosDisponibles.contains(num2)) {
                    return num2;
                }
                if (jugadas.contains(num2) && jugadas.contains(num3) && numerosDisponibles.contains(num1)) {
                    return num1;
                }
            }
        }
        return -1;  // No hay jugada ganadora
    }

    // Método para elegir una jugada estratégica
    private int elegirJugadaEstrategica() {
        for (int[] fila : cuadroMagico) {
            for (int num : fila) {
                if (numerosDisponibles.contains(num)) {
                    return num;
                }
            }
        }
        return -1;  // No quedan jugadas disponibles
    }

    // Método para realizar la jugada y actualizar los números disponibles
    private int realizarJugada(int jugada) {
        numerosDisponibles.remove(Integer.valueOf(jugada));
        return jugada;
    }
    
    public void registrarJugadaJugador(int numeroJugador) {
        // Eliminar el número del jugador de los números disponibles
        numerosDisponibles.remove(Integer.valueOf(numeroJugador));
    }
    
    private void actualizarNumerosDisponibles(List<Integer> jugadasJugador) {
        for (int jugada : jugadasJugador) {
            numerosDisponibles.remove(Integer.valueOf(jugada));
        }
    }
}

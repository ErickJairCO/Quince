package game.quince;

import game.computer.Computadora;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;

public class Quince {
    
    public ArrayList<Integer> cartasJugador = new ArrayList<>();
    public ArrayList<Integer> cartasComputer = new ArrayList<>();
    public boolean turno;
    public Computadora computadora;
    
    public Quince() {
        computadora = new Computadora(cartasJugador, cartasComputer);
        turno = determinarQuienEmpieza();
    }
    
    public void secuenceGame(int carta, JButton[] botonesUI) {
        // Jugador selecciona carta
        cartasJugador.add(carta);
        actualizarBotonUI(carta, botonesUI, "player");
        turno = true;
        
        // Verificar si el jugador ganó o alcanzó 15
        if (sumaCartas(cartasJugador) >= 15 || cartasJugador.size() == 3) {
            verificarGanador();
            return;
        }

        // Turno de la computadora
        int cartaComputadora = computadora.jugadasComputadora(turno);
        cartasComputer.add(cartaComputadora);
        actualizarBotonUI(cartaComputadora, botonesUI, "computer");

        // Verificar si la computadora ganó o alcanzó 15
        if (sumaCartas(cartasComputer) >= 15 || cartasComputer.size() == 3) {
            verificarGanador();
        }
    }

    // Determinar aleatoriamente quién empieza
    public boolean determinarQuienEmpieza() {
        // Aquí puedes usar un JOptionPane para informar al usuario
        int quienEmpieza = (Math.random() < 0.5) ? 0 : 1;
        return quienEmpieza == 1;
    }

    public void verificarGanador() {
        int sumaJugador = sumaCartas(cartasJugador);
        int sumaComputadora = sumaCartas(cartasComputer);
        
        if (sumaJugador == 15) {
            // Aquí podrías mostrar un JOptionPane diciendo que el jugador ganó
            System.out.println("¡Jugador gana!");
        } else if (sumaComputadora == 15) {
            // Aquí podrías mostrar un JOptionPane diciendo que la computadora ganó
            System.out.println("¡Computadora gana!");
        } else if (cartasJugador.size() == 3 && cartasComputer.size() == 3) {
            // Si ambos tienen 3 cartas, decidir quién ganó
            if (sumaJugador > sumaComputadora) {
                System.out.println("¡Jugador gana!");
            } else if (sumaComputadora > sumaJugador) {
                System.out.println("¡Computadora gana!");
            } else {
                System.out.println("Empate!");
            }
        }
    }

    public int sumaCartas(ArrayList<Integer> cartas) {
        int suma = 0;
        for (int carta : cartas) {
            suma += carta;
        }
        return suma;
    }

    // Método para actualizar la interfaz de usuario
    public void actualizarBotonUI(int valorCarta, JButton[] botonesUI, String jugador) {
        for (JButton boton : botonesUI) {
            if (Integer.parseInt(boton.getText()) == valorCarta) {
                boton.setEnabled(false);
                boton.setBackground(jugador.equals("player") ? Color.RED : Color.GREEN);
                boton.setOpaque(true);
                break;
            }
        }
    }
}

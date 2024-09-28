package ui.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.game.QuinceUI;

public class Menu extends JFrame {
    private int victoriasJugador;
    private int victoriasComputadora;
    private int empates;

    public Menu() {
        setTitle("Menu de Quince");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        // Botón para iniciar un nuevo juego
        JButton btnNuevoJuego = new JButton("Nuevo Juego");
        btnNuevoJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QuinceUI nuevoJuego = new QuinceUI(Menu.this);
                nuevoJuego.setVisible(true);
                setVisible(false); // Ocultar el menú
            }
        });

        // Botón para ver estadísticas
        JButton btnVerEstadisticas = new JButton("Ver Estadísticas");
        btnVerEstadisticas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEstadisticas();
            }
        });

        // Botón para salir del juego
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Agregar botones al menú
        add(btnNuevoJuego);
        add(btnVerEstadisticas);
        add(btnSalir);
    }

    // Método para actualizar las estadísticas
    public void actualizarEstadisticas(int ganador) {
        if (ganador == 1) {
            victoriasJugador++;
        } else if (ganador == 2) {
            victoriasComputadora++;
        } else {
            empates++;
        }
    }

    // Mostrar las estadísticas
    private void mostrarEstadisticas() {
        JOptionPane.showMessageDialog(this, 
                "Victorias del Jugador: " + victoriasJugador + "\n" +
                "Victorias de la Computadora: " + victoriasComputadora + "\n" +
                "Empates: " + empates, 
                "Estadísticas", JOptionPane.INFORMATION_MESSAGE);
    }

    // Reiniciar las estadísticas
    public void reiniciarEstadisticas() {
        victoriasJugador = 0;
        victoriasComputadora = 0;
        empates = 0;
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.setVisible(true);
    }
}

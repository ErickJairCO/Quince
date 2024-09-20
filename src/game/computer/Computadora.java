/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.computer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Computadora {
    
    public int sumaJug;
    public int sumaCompu;
    public int sumaTotal;
    public int botonSeleccion;
    
    public ArrayList<Integer> disponibles;
    public ArrayList<Integer> cartasJug;
    public ArrayList<Integer> cartasComputadora;
    
    
    public Computadora (ArrayList<Integer> cartasJug, ArrayList<Integer> cartasComputadora){
        sumaCompu = 0;
        sumaJug = 0;
        disponibles = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        this.cartasJug = cartasJug;
        this.cartasComputadora = cartasComputadora;
    }
    
    public int jugadasComputadora (boolean turno) {
        eliminarCartas(); //Se eliminan las cartas que haga el jugador y la computadora
        if(turno){
            sumaCompu = sumaCartas(cartasComputadora);
            sumaJug = sumaCartas(cartasJug);
            System.out.println("Suma de la computadora: "+sumaCompu);
            if(sumaJug > 0){
                if(sumaCompu + sumaJug > 15){
                    System.out.println("Entro a bloquear");
                    bloquear();
                }
                else{
                    System.out.println("Elijo carta primera opcion");
                    elegir();
                }
            }
            else {
                System.out.println("Elijo carta segunda opcion");
                elegir();
            }
        }
        return botonSeleccion;
    }
    
    public void bloquear() {
        if(disponibles.contains(sumaJug - 15)){
            //Busca un numero que no deje ganar al jugador
            botonSeleccion = sumaCompu - 15;
        }
    }
    
    public void elegir() {
        Random random = new Random();
        if(sumaCompu == 0){
            //Elija cualquiera
            botonSeleccion = disponibles.get(random.nextInt(disponibles.size()));
        }
        else{
            int mejorOpcion = -1;
            for (Integer carta : disponibles) {
                if (sumaCompu + carta <= 15 && carta > mejorOpcion) {
                    mejorOpcion = carta; // Busca la mejor opción que no supere 15
                }
            }
            botonSeleccion = mejorOpcion != -1 ? mejorOpcion : disponibles.get(random.nextInt(disponibles.size()));
        }
    }
    
    public int sumaCartas (ArrayList<Integer> valores) {
        
        for(int i=0; i<valores.size(); i++){
            sumaTotal += valores.get(i);
        }
        return sumaTotal;
    }
    
    public void eliminarCartas (){
        ArrayList<Integer> cartasCombinadas = new ArrayList<>();
        cartasCombinadas.addAll(cartasJug);
        cartasCombinadas.addAll(cartasComputadora);
        System.out.println("cartas computadora: " + cartasComputadora);

        for (Integer carta : cartasCombinadas) {
            disponibles.remove(carta);
        }
        System.out.println("La lista de disponibles es: " + disponibles);
    }
}

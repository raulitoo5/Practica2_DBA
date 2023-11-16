/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica2;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import static java.lang.Math.sqrt;
import java.util.Scanner;


public class Agente extends Agent{    
    int iteraciones = 0;
    Entorno entorno;
    
    @Override
    protected void setup() {  
        // Creamos un entorno vacío para obtener las dimensiones del mapa
        entorno = new Entorno();
                
        // Solicitar al usuario las posiciones iniciales y objetivo por teclado
        int[] posicionInicial = pedirPosicion("Ingrese la posición inicial");
        int[] posicionObjetivo = pedirPosicion("\n\nIngrese la posición objetivo");
        
        // Creamos el entorno con las posiciones proporcionadas
        entorno = new Entorno(posicionInicial[0], posicionInicial[1], posicionObjetivo[0], posicionObjetivo[1]);
        
        // Mostramos el mapa con la posicion inicial y objetivo creadas
        entorno.mostrarMapa();

        // Agregamos un comportamiento cíclico para moverse hacia el objetivo
        addBehaviour(new llegarObjetivo());
    }
    
    private int[] pedirPosicion(String mensaje) {
        int x = -1;             // Inicializamos la posicion x a una invalida
        int y = -1;             // Inicializamos la posicion y a una invalida
        
        Scanner scanner = new Scanner(System.in);
        System.out.print(mensaje);
        
        // Coordenada x
        System.out.print("\nCoordenada X (filas): ");
        
        // Mientras no se reciba correctamente un número seguimos solicitando
        while(!scanner.hasNextInt()){
            scanner = new Scanner(System.in);
            System.out.println("Error...");
            System.out.println("\n\nPor favor, ingrese la coordenada X correctamente...");
        }
        
        // Si se recibe correctamente un número
        if (scanner.hasNextInt()){
            x = scanner.nextInt();
        }
        
        // Coordenada y
        System.out.print("\nCoordenada Y (columnas): ");
        
        // Mientras no se reciba correctamente un número seguimos solicitando
        while(!scanner.hasNextInt()){
            scanner = new Scanner(System.in);
            System.out.println("Error...");
            System.out.println("\n\nPor favor, ingrese la coordenada Y correctamente...");
        }
        
        // Si se recibe correctamente un número
        if (scanner.hasNextInt()){
            y = scanner.nextInt();
        }
        
        // Si la posicion ingresada es inválida
        if(x < 0 || x > entorno.getFilas() || y < 0 && y > entorno.getColumnas() ||
           entorno.getValorMapa(x, y) != 0){
            System.out.print("\n\nLa posición introducida es inválida. Se creará una posición válida aleatoria.\n\n");
            return new int[]{-1, -1};   // Devolvemos una posicion invalida
        }
        
        System.out.println("\nPosicion: {"+ x + "," + y + "}");
        return new int[]{x, y};
    }

    private class llegarObjetivo extends CyclicBehaviour {
        
        @Override
        public void action() {
            if (!entorno.getObjetivo()) {
                // Movemos al agente
                
                // Estas variables indican la distancia euclidea existente si el siguiente movimiento que hacen es ese
                // Inicializamos las variables a valores grandes para que así no coja alguno que no haya podido calcular la distancia euclídea
                double distanciaArriba = 50;    // Distancia euclidea si el siguiente movimiento que hace es ir abajo
                double distanciaAbajo = 50;
                double distanciaDerecha = 50;
                double distanciaIzquierda = 50;
                double distanciaMenor = 0;

                // Si se puede mover hacia abajo y todavía no hemos calculado la distancia en esa posición
                // Si la posición de abajo es 0 o 5 calculamos la posición
                if ((entorno.getPosX() + 1 < entorno.getFilas() && entorno.getPosY() < entorno.getColumnas()) && entorno.getValorMapa(entorno.getPosX() + 1, entorno.getPosY()) == 0 || entorno.getValorMapa(entorno.getPosX() + 1, entorno.getPosY()) == 5) {
                    distanciaAbajo = distanciaEuclidea(entorno.getPosX() + 1, entorno.getPosY(), entorno.getPosObjetivoX(), entorno.getPosObjetivoY());
                    // Si no se puede mover a esa casilla porque es un obstáculo sumamos 20
                } else if ((entorno.getPosX() + 1 < entorno.getFilas() && entorno.getPosY() < entorno.getColumnas()) && entorno.getValorMapa(entorno.getPosX() + 1, entorno.getPosY()) == -1) {
                    distanciaAbajo += 20;
                } // Si no se puede mover porque se sale del mapa si hace ese movimiento
                else if ((entorno.getPosX() + 1 == entorno.getFilas() && entorno.getPosY() < entorno.getColumnas())) {
                    distanciaAbajo += 20;
                } // Si ya ha pasado dos veces por esa casilla que no pase más
                else if ((entorno.getPosX() + 1 < entorno.getFilas() && entorno.getPosY() < entorno.getColumnas()) && (entorno.getValorMapa(entorno.getPosX() + 1, entorno.getPosY()) == 7)) {
                    distanciaAbajo += 20;
                }

                // Si se puede mover hacia la derecha calcula la distancia euclidea de ese movimiento
                if ((entorno.getPosX() < entorno.getFilas() && entorno.getPosY() + 1 < entorno.getColumnas()) && (entorno.getValorMapa(entorno.getPosX(), entorno.getPosY() + 1) == 0 || entorno.getValorMapa(entorno.getPosX(), entorno.getPosY() + 1) == 5)) {
                    distanciaDerecha = distanciaEuclidea(entorno.getPosX(), entorno.getPosY() + 1, entorno.getPosObjetivoX(), entorno.getPosObjetivoY());
                    // Si no se puede mover a esa casilla porque es un obstáculo
                } else if ((entorno.getPosX() < entorno.getFilas() && entorno.getPosY() + 1 < entorno.getColumnas()) && (entorno.getValorMapa(entorno.getPosX(), entorno.getPosY() + 1) == -1)) {
                    distanciaDerecha += 20;
                } // Si no se puede mover porque si hace ese movimiento se sale del mapa
                else if ((entorno.getPosX() < entorno.getFilas() && entorno.getPosY() + 1 == entorno.getColumnas())) {
                    distanciaDerecha += 20;
                } // Si ya se ha movido dos veces en esa casilla
                else if ((entorno.getPosX() < entorno.getFilas() && entorno.getPosY() + 1 < entorno.getColumnas()) && (entorno.getValorMapa(entorno.getPosX(), entorno.getPosY() + 1) == 7)) {
                    distanciaDerecha += 20;
                }

                //Movimiento izquierda
                if ((entorno.getPosX() < entorno.getFilas() && entorno.getPosY() - 1 < entorno.getColumnas()) && (entorno.getValorMapa(entorno.getPosX(), entorno.getPosY() - 1) == 0 || entorno.getValorMapa(entorno.getPosX(), entorno.getPosY() - 1) == 5)) {
                    distanciaIzquierda = distanciaEuclidea(entorno.getPosX(), entorno.getPosY() - 1, entorno.getPosObjetivoX(), entorno.getPosObjetivoY());
                    // Si la casilla es un obstáculo
                } else if ((entorno.getPosX() < entorno.getFilas() && entorno.getPosY() - 1 < entorno.getColumnas()) && (entorno.getValorMapa(entorno.getPosX(), entorno.getPosY() - 1) == -1)) {
                    distanciaIzquierda += 20;
                } // Si hace este movimiento se sale de la casilla
                else if ((entorno.getPosX() < entorno.getFilas() && entorno.getPosY() - 1 == entorno.getColumnas())) {
                    distanciaIzquierda += 20;
                } //si ya ha pasado dos vece por esa casilla
                else if ((entorno.getPosX() < entorno.getFilas() && entorno.getPosY() - 1 < entorno.getColumnas()) && (entorno.getValorMapa(entorno.getPosX(), entorno.getPosY() - 1) == 7)) {
                    distanciaIzquierda += 20;
                }

                //Movimiento arriba
                if ((entorno.getPosX() - 1 < entorno.getFilas() && entorno.getPosY() < entorno.getColumnas()) && (entorno.getValorMapa(entorno.getPosX() - 1, entorno.getPosY()) == 0 || entorno.getValorMapa(entorno.getPosX() - 1, entorno.getPosY()) == 5)) {
                    distanciaArriba = distanciaEuclidea(entorno.getPosX() - 1, entorno.getPosY(), entorno.getPosObjetivoX(), entorno.getPosObjetivoY());
                    // Si la casilla es un obstáculo
                } else if ((entorno.getPosX() - 1 < entorno.getFilas() && entorno.getPosY() < entorno.getColumnas()) && (entorno.getValorMapa(entorno.getPosX() - 1, entorno.getPosY()) == -1)) {
                    distanciaArriba += 20;
                } // Si hace este movimiento se sale de la casilla
                else if ((entorno.getPosX() - 1 == entorno.getFilas() && entorno.getPosY() < entorno.getColumnas())) {
                    distanciaArriba += 20;
                } //si ya ha pasado dos vece por esa casilla
                else if ((entorno.getPosX() - 1 < entorno.getFilas() && entorno.getPosY() < entorno.getColumnas()) && (entorno.getValorMapa(entorno.getPosX() - 1, entorno.getPosY()) == 7)) {
                    distanciaArriba += 20;
                }

                // Ahora vemos cual de todos tiene la distancia euclidea menor
                distanciaMenor = Math.min(Math.min(Math.min(distanciaAbajo, distanciaDerecha), distanciaIzquierda), distanciaArriba);
                System.out.println("Distancia menor: " + distanciaMenor);
                System.out.println("Distancia arriba: " + distanciaArriba);
                System.out.println("Distancia abajo: " + distanciaAbajo);
                System.out.println("Distancia derecha: " + distanciaDerecha);
                System.out.println("Distancia Izquierda: " + distanciaIzquierda);

                int movimiento;
                
                if (distanciaMenor == distanciaAbajo) {
                    System.out.println("\nDevuelve abajo");
                    movimiento = 0;
                } else if (distanciaMenor == distanciaDerecha) {
                    System.out.println("\nDevuelve derecha");
                    movimiento = 1;
                } else if (distanciaMenor == distanciaIzquierda) {
                    System.out.println("\nDevuleve izquierda");
                    movimiento = 2;
                } else {
                    System.out.println("\nDevuleve arriba");
                    movimiento = 3;
                }

                
                iteraciones++;

                // Mostramos el mapa
                entorno.mostrarMapa();

                System.out.println("Movimientos realizados: " + iteraciones);
                System.out.println("Posicion actual del agente: " + entorno.getPosX() + " " + entorno.getPosY());

                // Realiza el movimiento dependiendo de lo que haya retornado la función pensar que esto será en base
                // a lo que es el mejor movimiento
                
                switch (movimiento) {
                    case 0:
                        moverAbajo();
                        break;
                    case 1:
                        giroDerecha();
                        break;
                    case 2:
                        giroIzquierda();
                        break;
                    case 3:
                        moverArriba();
                        break;
                }

                // Si nos posicionamos encima del objetivo
                if (entorno.getValorMapa(entorno.getPosX(), entorno.getPosY()) == 5) {
                    objetivoEncontrado();
                }

            } else {
                System.out.println("\n\n¡El objetivo se ha encontrado!");

                // Finaliza el agente.
                myAgent.doDelete();
            }
        }
    }
    
    @Override
    protected void takeDown (){
        System.out.println("\n\nFInalizando agente...");
    }
    
    // Colocamos el objetivo en una posicón aleatoria que sea válida
    public void colocarObjetivo(){
        entorno.colocarObjetivo();
    }
    
    public Entorno getEntorno(){
        return entorno;
    }
    
    // Función que señala que se ha encontrado el objetivo
    public boolean objetivoEncontrado(){
        return entorno.objetivoEncontrado();
    }
    
    public void marcarCasilla(int i, int j){
        entorno.marcarCasilla(i, j);
    }

    
    public void giroDerecha(){
        entorno.giroDerecha();
    }
    
    public void giroIzquierda(){
        entorno.giroIzquierda();
    }
   
    
    public void moverAbajo(){
        entorno.moverAbajo();
    }
    
    public void moverArriba(){   
        entorno.moverArriba();
    }

    double distanciaEuclidea(int x1, int y1, int x2, int y2){
        double distancia = 0.0;
            distancia = sqrt((Math.pow(x2-x1, 2)) + (Math.pow(y2-y1,2)));
        return distancia;
    }
}

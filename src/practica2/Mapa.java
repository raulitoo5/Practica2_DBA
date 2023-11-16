///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package practica2;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import practica2.Agente;
//
//
//public class Mapa {
//    private int filas;
//    private int columnas;
//    private int[][] matrizMapa;
//    
//    public Mapa(String archivo) {
//        try {
//            
//            BufferedReader reader = new BufferedReader(new FileReader(archivo));
//          
//            // Leer el número de filas, columnas y creamos la matriz
//            filas = Integer.parseInt(reader.readLine());
//            columnas = Integer.parseInt(reader.readLine());
//            matrizMapa = new int[filas][columnas];
//
//            // Leemos la matriz
//            for (int i = 0; i < filas; i++) {
//                String[] line = reader.readLine().split("\t");
//                for (int j = 0; j < columnas; j++) {
//                    matrizMapa[i][j] = Integer.parseInt(line[j]);
//                }
//            }
//
//            // Cerrar el lector
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    public void mostrarMapa(){
//        // Imprimir la matriz
//        for (int i = 0; i < filas; i++) {
//            for (int j = 0; j < columnas; j++) {
//                System.out.print(matrizMapa[i][j] + "\t");
//            }
//            System.out.println();
//        }
//    }
//    
//    public int[][] getMapa(){
//        return matrizMapa;
//    }
//    
//    public int getFilas(){
//        return filas;
//    }
//    
//    public int getColumnas(){
//        return columnas;
//    }
//    
//    public void marcarCasilla(int fil, int col){
//        // Esta comprobación la hacemos para que si la casilla es 5 no se cambie el valor
//        if(matrizMapa[fil][col] != 5){
//        // Si hemos pasado dos veces por esa casilla se pone en 7
//            if(matrizMapa[fil][col] == 2){
//                System.out.println("matriz mapa es 2");
//                matrizMapa[fil][col] = 7;
//            // Si hemos pasado una vez por esa casilla se pone en 2
//            }else{
//                System.out.println("marca casilla 2");
//                matrizMapa[fil][col] = 2;
//            }
//        }
//    }
//    
//    // Este metodo muestra la casilla i,j del mapa
//    public int getValorMapa(int i, int j){
//        // Comprobamos que los índices están dentro del rango posible
//        if(i<filas && i>=0 && j<columnas && j>=0){
//            return matrizMapa[i][j];
//            
//        }
//        else{
//            return -3;
//        }
//    }
//    
//    public void colocarObjetivo(int i, int j){
//        if(i<filas && j<columnas && matrizMapa[i][j] != -1){
//            matrizMapa[i][j] = 5;
//        }
//    }
//        
////    // ESTE MÉTODO IRÁ AL ENTORNO
////    boolean posicionValida(int fil, int col){
////        if(fil < filas && col < columnas && matrizMapa[fil][col] ==  0){
////            return true;
////        } else
////            return false;
////    }
//}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import practica2.Agente;


public class Mapa {
    private int filas;
    private int columnas;
    private int[][] matrizMapa;
    
    public Mapa(String archivo) {
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
          
            // Leer el número de filas, columnas y creamos la matriz
            filas = Integer.parseInt(reader.readLine());
            columnas = Integer.parseInt(reader.readLine());
            matrizMapa = new int[filas][columnas];

            // Leemos la matriz
            for (int i = 0; i < filas; i++) {
                String[] line = reader.readLine().split("\t");
                for (int j = 0; j < columnas; j++) {
                    matrizMapa[i][j] = Integer.parseInt(line[j]);
                }
            }

            // Cerrar el lector
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void mostrarMapa(){
        String separador = "-----------------------------------------------------------" + 
                           "-----------------------------------------------------------" + 
                           "-----------------------------------------------------------";
        
        // Imprimir la matriz
        System.out.println(separador);
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matrizMapa[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
    public int[][] getMapa(){
        return matrizMapa;
    }
    
    public int getFilas(){
        return filas;
    }
    
    public int getColumnas(){
        return columnas;
    }
    
    public int getValor(int fil, int col){
        return matrizMapa[fil][col];
    }
    
    public void marcarCasilla(int fil, int col){
        // Esta comprobación la hacemos para que si la casilla es 5 no se cambie el valor
        if(matrizMapa[fil][col] != 5){
        // Si hemos pasado dos veces por esa casilla se pone en 7
            if(matrizMapa[fil][col] == 2){
                matrizMapa[fil][col] = 7;
            // Si hemos pasado una vez por esa casilla se pone en 2
            }else{
                matrizMapa[fil][col] = 2;
            }
        }
    }
    
    // Este metodo muestra la casilla i,j del mapa
    public int getValorMapa(int i, int j){
        // Comprobamos que los índices están dentro del rango posible
        if(i<filas && i>=0 && j<columnas && j>=0){
            return matrizMapa[i][j];
            
        }
        else{
            return -3;
        }
    }
    
    public void colocarObjetivo(int i, int j){
        if(i<filas && j<columnas && matrizMapa[i][j] != -1){
            matrizMapa[i][j] = 5;
        }
    }
}

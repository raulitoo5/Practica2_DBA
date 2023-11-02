///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package practica2;
//import practica2.Mapa;
///**
// *
// * @author yo
// */
//public class Practica2 {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        String archivoMapa = "mapa.txt";
//        System.out.println("mapa.txt");
//        var mapa = new Mapa("mapa.txt");
//        mapa.mostrarMapa();
//    }
//}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica2;


/**
 *
 * @author yo
 */

public class Practica2 {
    
    public static void main(String[] args) {        
        
        // Esto lo he quitado ya que el mapa se hace automáticamente cuando se crea el agente
//        Agente agente = new Agente();
        Agente agente = new Agente(9,9,0,0);
        agente.marcarCasilla(agente.getX(), agente.getY());
        //agente.colocarObjetivo();
        agente.getMapa().mostrarMapa();

        for (int i = 0; !agente.getObjetivo(); i++) {

            System.out.println("---------------------------------------------------------------------------");
            agente.MoverAgente();
            agente.getMapa().mostrarMapa();
            System.out.println("Posición del objetivo X: " + agente.getXobjetivo() + "positicón del objetivo Y: " + agente.getYobjetivo());
            System.out.println("llevo: " + i + " movimientos");
            System.out.println("posicion actual del agente: " + agente.getX() + " " + agente.getY());
        }
    }
}




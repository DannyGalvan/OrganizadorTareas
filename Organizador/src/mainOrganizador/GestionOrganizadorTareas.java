/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainOrganizador;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import static java.time.Clock.system;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author cgalv
 */
public class GestionOrganizadorTareas {
     public static ArrayList<TableroTareas> listaTableros = new ArrayList<>();
     
     
      public static int getUltimoId(){
        ArrayList<TableroTareas> T = traerTableros();
        int ultimo = 1;
        
        if (!T.isEmpty()) {
            ultimo = T.size() - 1;
            TableroTareas TT = T.get(ultimo);        
            ultimo = TT.getIdentificacion() + 1;
        }
        
        return ultimo;        
    }
    
     public static void agregarTableros(TableroTareas oTablero){
        ArrayList<TableroTareas> newTablero = new ArrayList<>();
        newTablero.add(oTablero);
        OperacionArchivo.aniadirArchivo(newTablero);                
     }
     
     public static ArrayList traerTableros(){
         listaTableros = OperacionArchivo.leerArchivo();
         return listaTableros;
     }
     
     public static void eliminarTableros(int id){
         listaTableros = OperacionArchivo.leerArchivo();         
         TableroTareas tablero = GestionOrganizadorTareas.BuscarTablero(id);
         ArrayList<ListadoTareas> listadosTareas = tablero.leerTareasTablero();
         for(ListadoTareas item : listadosTareas){
             item.eliminarTareasLista();
         }
         tablero.eliminarTareasTablero();
         listaTableros.removeIf(x -> x.getIdentificacion() == id);
         ArrayList<TableroTareas> newList = new ArrayList<>();
         
         for(int i=0; i<listaTableros.size(); i++){
             TableroTareas item = listaTableros.get(i);
             newList.add(item);
         }
         OperacionArchivo.crearArchivo(newList);
     }
     
     public static void modificarTableros(int id, String nombre){
         listaTableros = OperacionArchivo.leerArchivo();
         TableroTareas tablero = GestionOrganizadorTareas.BuscarTablero(id);        
         tablero.setNombreTareasTablero(nombre);
         tablero.setNombre(nombre);
         EstadoGlobal.TransferenciaTablero = tablero;
         
         ArrayList<TableroTareas> newList = new ArrayList<>();
         
         for(int i=0; i<listaTableros.size(); i++){
             TableroTareas item = listaTableros.get(i);
             item.setIdentificacion(i+1);
             newList.add(item);
         }
         OperacionArchivo.crearArchivo(newList);         
     }
     
      public static int cantidadTableros(){
         listaTableros = OperacionArchivo.leerArchivo();
         return listaTableros.size();
     }
      
    public static TableroTareas BuscarTablero(int id) {  
        Optional<TableroTareas> tablero = listaTableros.stream()
            .filter(p -> p.getIdentificacion() == id)
            .findFirst();
        System.out.println("el tablero es: " + tablero.get().getNombre());
        return tablero.isPresent() ? tablero.get() : null;
    }
}

    
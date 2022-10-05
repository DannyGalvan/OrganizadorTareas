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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author cgalv
 */
public class TableroTareas {
    
    private int Identificacion;
    private String Nombre;
    private ArrayList<ListadoTareas> Tareas = new ArrayList();
    private int TotalTareas;
    
    public int getIdentificacion() {
        return this.Identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.Identificacion = identificacion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public ArrayList<ListadoTareas> getTareas() {
        return Tareas;
    }

    public void addTareas(ListadoTareas tarea) {
        this.Tareas.add(tarea);
    }    
    
    public int countTareas(){
        return this.leerTareasTablero().size();
    }
    
    public int getTotalTareas() {
        this.TotalTareas = this.leerTareasTablero().size();
        return TotalTareas;
    }

    public void setTotalTareas(int totalTareas) {
        this.TotalTareas = totalTareas;
    }
    
    public int getUltimoId(){
        ArrayList<ListadoTareas> T = this.leerTareasTablero();
        int ultimo = 1;
        
        if (!T.isEmpty()) {
            ultimo = T.size() - 1;
            ListadoTareas L = T.get(ultimo);        
            L.getIdListadoTareas();
            ultimo = L.getIdListadoTareas() + 1;
        }
        
        return ultimo;        
    }
    
    public void setNombreTareasTablero(String nombre){     
         Path source = Paths.get("C:/Organizador/ListaTareas/" + this.Nombre + ".txt");
         System.out.println(source.toAbsolutePath());
        try{                 
           Files.move(source, source.resolveSibling("C:/Organizador/ListaTareas/" + nombre + ".txt"));
            System.out.println("Archivo Renombrado con exito");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList leerTareasTablero() {
		// crea el flujo para leer desde el archivo
                           
		File file = new File("C:/Organizador/ListaTareas/" + this.Nombre + ".txt");
		ArrayList listaTareas= new ArrayList<ListadoTareas>();	
		Scanner scanner;
		try {
			//se pasa el flujo al objeto scanner
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				// el objeto scanner lee linea a linea desde el archivo
				String linea = scanner.nextLine();
				Scanner delimitar = new Scanner(linea);
				//se usa una expresión regular
				//que valida que antes o despues de una coma (,) exista cualquier cosa
				//parte la cadena recibida cada vez que encuentre una coma				
				delimitar.useDelimiter("\\s*,\\s*");
				ListadoTareas e = new ListadoTareas();
                                e.setIdListadoTareas(Integer.parseInt(delimitar.next()));
                                e.setIdTableroTareas(Integer.parseInt(delimitar.next()));
                                e.setNombreListado(delimitar.next());
				listaTareas.add(e);
                                this.Tareas = listaTareas;
			}
			//se cierra el ojeto scanner
			scanner.close();
                        System.out.println("Listas leidas satisfactoriamente..");
		} catch (FileNotFoundException e) {
                    System.out.println(e);
                    this.Tareas = new ArrayList<ListadoTareas>();
		}
		return Tareas ;
	}
	
    //añadir más tareas al archivo
    public void aniadirTareasTablero(ArrayList<ListadoTareas> lista) {
            File directorio = new File("C:/Organizador/ListaTareas");
                if (!directorio.exists()) {
                   if (directorio.mkdirs()) {
                       System.out.println("Directorio creado");                        
                   } else {
                       System.out.println("Error al crear directorio");
                   }
               }      
            FileWriter flwriter = null;
            try {//además de la ruta del archivo recibe un parámetro de tipo boolean, que le indican que se va añadir más registros 
                    flwriter = new FileWriter("C:/Organizador/ListaTareas/" + this.Nombre + ".txt", true);
                try (BufferedWriter bfwriter = new BufferedWriter(flwriter)) {
                    for (ListadoTareas tareas : lista) {
                        //escribe los datos en el archivo
                        bfwriter.write(tareas.getIdListadoTareas() + "," + tareas.getIdTableroTareas()+ "," + tareas.getNombreListado() + "\n");
                    }
                }
                    System.out.println("Listas modificadas satisfactoriamente..");

            } catch (IOException e) {
            } finally {
                    if (flwriter != null) {
                            try {
                                    flwriter.close();
                            } catch (IOException e) {
                            }
                    }
            }
    }
    
    public void eliminarTareasTablero(){
        File archivo = new File("C:/Organizador/ListaTareas/" + this.Nombre + ".txt");
        if (archivo.delete()) {
            System.out.println("El fichero ha sido borrado satisfactoriamente");
        }else{
           System.out.println("El fichero no puede ser borrado");
        }
    }
    
    public void modificarTareasTablero(ArrayList<ListadoTareas> lista) {
		FileWriter flwriter = null;
		try {
			//crea el flujo para escribir en el archivo
			flwriter = new FileWriter("C:/Organizador/ListaTareas/" + this.Nombre + ".txt");
                    try ( //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
                            BufferedWriter bfwriter = new BufferedWriter(flwriter)) {
                        for (ListadoTareas tareas : lista) {
                            //escribe los datos en el archivo
                            bfwriter.write(tareas.getIdListadoTareas() + "," + tareas.getIdTableroTareas()+ "," + tareas.getNombreListado() + "\n");
                        }
                        //cierra el buffer intermedio
                    }
			System.out.println("Lista modificado satisfactoriamente..");

		} catch (IOException e) {
		} finally {
			if (flwriter != null) {
				try {//cierra el flujo principal
					flwriter.close();
				} catch (IOException e) {
				}
			}
		}
	}
        
}

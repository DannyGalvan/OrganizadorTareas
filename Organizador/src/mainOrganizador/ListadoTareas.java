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
public class ListadoTareas {

    private int IdListadoTareas;
    private int IdTableroTareas;
    private String NombreListado;
    private ArrayList<Tarea> Tareas;
    private int TotalTareas;

    /**
     * @return the IdListadoTareas
     */
    public int getIdListadoTareas() {
        return IdListadoTareas;
    }

    /**
     * @param IdListadoTareas the IdListadoTareas to set
     */
    public void setIdListadoTareas(int IdListadoTareas) {
        this.IdListadoTareas = IdListadoTareas;
    }

    /**
     * @return the IdTableroTareas
     */
    public int getIdTableroTareas() {
        return IdTableroTareas;
    }

    /**
     * @param IdTableroTareas the IdTableroTareas to set
     */
    public void setIdTableroTareas(int IdTableroTareas) {
        this.IdTableroTareas = IdTableroTareas;
    }

    /**
     * @return the NombreListado
     */
    public String getNombreListado() {
        return NombreListado;
    }

    /**
     * @param NombreListado the NombreListado to set
     */
    public void setNombreListado(String NombreListado) {
        this.NombreListado = NombreListado;
    }

    /**
     * @return the Tareas
     */
    public ArrayList<Tarea> getTareas() {
        return Tareas;
    }

    /**
     * @param Tareas the Tareas to set
     */
    public void setTareas(ArrayList<Tarea> Tareas) {
        this.Tareas = Tareas;
    }
    
     /**
     * @return the TotalTareas
     */
    public int getTotalTareas() {
        this.TotalTareas = this.leerTareasLista().size();
        return TotalTareas;
    }

    /**
     * @param TotalTareas the TotalTareas to set
     */
    public void setTotalTareas(int TotalTareas) {
        this.TotalTareas = TotalTareas;
    }

    public int getUltimoId() {
        ArrayList<Tarea> T = this.leerTareasLista();
        int ultimo = 1;

        if (!T.isEmpty()) {
            ultimo = T.size() - 1;
            Tarea L = T.get(ultimo);
            L.getId();
            ultimo = L.getId() + 1;
        }

        return ultimo;
    }

    public void setNombreListadoTareas(String nombre) {
        Path source = Paths.get("C:/Organizador/Tareas/" + this.NombreListado + ".txt");
        System.out.println(source.toAbsolutePath());
        try {
            Files.move(source, source.resolveSibling("C:/Organizador/Tareas/" + nombre + ".txt"));
            System.out.println("Archivo Renombrado con exito");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList leerTareasLista() {
        // crea el flujo para leer desde el archivo

        File file = new File("C:/Organizador/Tareas/" + this.NombreListado + ".txt");
        ArrayList listaTareas = new ArrayList<Tarea>();
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
                Tarea e = new Tarea();
                e.setId(Integer.parseInt(delimitar.next()));
                e.setIdLista(Integer.parseInt(delimitar.next()));
                e.setNombre(delimitar.next());
                e.setDescripcion(delimitar.next());
                listaTareas.add(e);
                this.Tareas = listaTareas;
            }
            //se cierra el ojeto scanner
            scanner.close();
            System.out.println("Tareas leidas satisfactoriamente..");
        } catch (FileNotFoundException e) {
            System.out.println(e);
            this.Tareas = new ArrayList<Tarea>();
        }
        return Tareas;
    }

    //añadir más tareas al archivo
    public void aniadirTareasLista(ArrayList<Tarea> lista) {
        File directorio = new File("C:/Organizador/Tareas");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        FileWriter flwriter = null;
        try {//además de la ruta del archivo recibe un parámetro de tipo boolean, que le indican que se va añadir más registros 
            flwriter = new FileWriter("C:/Organizador/Tareas/" + this.NombreListado + ".txt", true);
            try ( BufferedWriter bfwriter = new BufferedWriter(flwriter)) {
                for (Tarea tareas : lista) {
                    //escribe los datos en el archivo
                    bfwriter.write(tareas.getId() + "," + tareas.getIdLista() + "," + tareas.getNombre() + "," + tareas.getDescripcion() + "\n");
                }
            }
            System.out.println("Tareas modificadas satisfactoriamente..");

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

    public void eliminarTareasLista() {
        File archivo = new File("C:/Organizador/Tareas/" + this.NombreListado + ".txt");
        if (archivo.delete()) {
            System.out.println("El fichero ha sido borrado satisfactoriamente");
        } else {
            System.out.println("El fichero no puede ser borrado");
        }
    }

    public void modificarTareasLista(ArrayList<Tarea> lista) {
        FileWriter flwriter = null;
        try {
            //crea el flujo para escribir en el archivo
            flwriter = new FileWriter("C:/Organizador/Tareas/" + this.NombreListado + ".txt");
            try ( //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
                     BufferedWriter bfwriter = new BufferedWriter(flwriter)) {
                for (Tarea tareas : lista) {
                    //escribe los datos en el archivo
                    bfwriter.write(tareas.getId() + "," + tareas.getIdLista() + "," + tareas.getNombre() + "\n");
                }
                //cierra el buffer intermedio
            }
            System.out.println("Tablero modificado satisfactoriamente..");

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

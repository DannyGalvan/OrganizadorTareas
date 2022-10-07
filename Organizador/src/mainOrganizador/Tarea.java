/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainOrganizador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author cgalv
 */
public class Tarea {
    private int Id;
    private int IdLista;
    private String Nombre;
    private String Descripcion;
    private int Posicion;
    private String FechaInicio;
    private String FechaFinal;
    private String Vigencia;

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * @return the IdLista
     */
    public int getIdLista() {
        return IdLista;
    }

    /**
     * @param IdLista the IdLista to set
     */
    public void setIdLista(int IdLista) {
        this.IdLista = IdLista;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    /**
     * @return the Posicion
     */
    public int getPosicion() {
        return Posicion;
    }

    /**
     * @param Posicion the Posicion to set
     */
    public void setPosicion(int Posicion) {
        this.Posicion = Posicion;
    }

    /**
     * @return the FechaInicio
     */
    public String getFechaInicio() {
        return FechaInicio;
    }

    /**
     * @param FechaInicio the FechaInicio to set
     */
    public void setFechaInicio(String FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    /**
     * @return the FechaFinal
     */
    public String getFechaFinal() {
        return FechaFinal;
    }

    /**
     * @param FechaFinal the FechaFinal to set
     */
    public void setFechaFinal(String FechaFinal) {
        this.FechaFinal = FechaFinal;
    }

    /**
     * @return the Vigencia
     */
    public String getVigencia() {
        return Vigencia;
    }

    /**
     * @param Vigencia the Vigencia to set
     */
    public void setVigencia(String FechaFinal) throws ParseException {
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        Date dt_2 = fecha.parse(FechaFinal);
        Date dt_1 = new Date();
        String Vigencia = "";
        if (dt_1.compareTo(dt_2) > 0) {  
            Vigencia = "Vencida"; 
        } // el método compareTo devuelve el valor mayor que 0 si esta Fecha está después del argumento Fecha.  
        else if (dt_1.compareTo(dt_2) < 0) {  
            Vigencia = "En tiempo"; 
        } // el método compareTo devuelve el valor menor que 0 si esta Fecha es anterior al argumento Fecha; 
        else if (dt_1.compareTo(dt_2) == 0) {  
            Vigencia = "Por vencer";  
        } // el método compareTo devuelve el valor 0 si el argumento Fecha es igual a la segunda Fecha;
        else {  
            System.out.println("¡Pareces ser un viajero del tiempo!"); 
        }         
        this.Vigencia = Vigencia;
    }
    
    public void setVigenciaToString(String Vigencia){
          this.Vigencia = Vigencia;
    }
    
    
    
}

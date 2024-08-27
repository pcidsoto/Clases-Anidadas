
import java.text.DecimalFormat;
import java.util.ArrayList;

import Exceptions.MatriculaException;

// Extendemos la clase Alumno con la interfaz de Comparable y poder agregar la funcion compareTo.
public class Alumno implements Comparable<Alumno> {

    static DecimalFormat df = new DecimalFormat(" #,###");
    private int numero;
    private int run;
    private String dv;
    private String nombre;
    private String apellido;
    private int semestre;
    private Carrera carrera;

// constructor vacío.
    public Alumno() {
        this.carrera = null;
    }
// constructor

    public Alumno(int numero, int run, String dv, String nombre, String apellido, int semestre) {
        this.numero = numero;
        this.run = run;
        this.dv = dv;
        this.nombre = nombre;
        this.apellido = apellido;
        this.semestre = semestre;
    }

// Geters y Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getRun() {
        return run;
    }

    public String getRunStr() {
        return  String.valueOf(run);
    }

    public void setRun(int run) {
        this.run = run;
    }

    public String getDv() {

        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public void matricularCarrera(Carrera carrera) throws MatriculaException{
        if (this.carrera != null){
            throw new MatriculaException("El alumno ya tiene una carrera matriculada, elevar solicitud a dirección de carrera");
        }
        this.carrera = carrera;
    }

    public boolean tieneCarrera(){
        return this.carrera != null;
    }

    public Carrera getCarrera(){
        return this.carrera;
    }

    public Ramo getRamoPorNombre(String nombreRamo) {
        if (this.carrera == null) {
            return null;
        }
        return this.carrera.buscarRamoPorNombre(nombreRamo);
    }

// Método toString
    @Override
    public String toString() {
        String nombreCarrera = "Alumno no está matriculado";
        ArrayList<Ramo> ramos = new ArrayList<>();
        if (this.carrera != null){
            nombreCarrera = carrera.getNombreCarrera();
            ramos = carrera.getRamos();
        }

        return "\nNUMERO: [" + numero + "]"
                + "\nNOMBRE: " + nombre + " " + apellido
                + "\nRUT: " + df.format(run) + "-" + dv
                + "\nSEMESTRE: " + semestre
                + "\nCARRERA:" + nombreCarrera
                + "\nRamos:" + ramos.toString();
    }

    // Metodo para comparar por run.
    @Override
    public int compareTo(Alumno otroAlumno) {
        return Integer.compare(this.run, otroAlumno.run);
    }
}

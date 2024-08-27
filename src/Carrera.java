
import java.util.ArrayList;

import Exceptions.LimiteExcedidoException;

public class Carrera {
    private String nombreCarrera;
    private int codigo;
    private ArrayList<Ramo> ramos;
    private ArrayList<Alumno> alumnos;

    public Carrera(){
        this.ramos = new ArrayList<>();
        this.alumnos = new ArrayList<>();
    }

    public Carrera(String nombreCarrera, int codigo) {
        this.nombreCarrera = nombreCarrera;
        this.codigo = codigo;
        // Me aseguro de incializar los arrayList para poder agregar objetos.
        this.ramos = new ArrayList<>();
        this.alumnos = new ArrayList<>();
    }

    // cada carrera podrá tener un máximo de cuatro ramos.
    void addRamo(Ramo ramo, Alumno alumno) throws LimiteExcedidoException {
        
        if (ramos.size() >= 4 && alumno != null) {
            throw new LimiteExcedidoException("No se pueden inscribir mas de 4 ramos");
        }
        ramos.add(ramo);
    }

    void addAlumno(Alumno alumno) {
        alumnos.add(alumno);
    }

    public String getNombreCarrera(){
        return this.nombreCarrera;
    }

    public boolean tieneRamo(String nombre){
        return this.ramos
                .stream()
                .anyMatch(ramo -> ramo.getNombre().equals(nombre));
    }

    public Ramo buscarRamoPorNombre(String nombreRamo) {
        for (Ramo ramo : ramos) {
            if (ramo.getNombre().equalsIgnoreCase(nombreRamo)) {
                return ramo;
            }
        }
        return null;
    }

    public String toString() {
        return "\n Nombre: [" + nombreCarrera + "]"
                + "\nCodigo: " + codigo
                + "\nRamos: " + ramos.toString();
    }

    public int getCodigo() {
        return codigo;
    }

    public ArrayList<Ramo> getRamos() {
        return ramos;
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setRamos(ArrayList<Ramo> ramos){
        this.ramos = ramos;
    }

    // Método de clonación profunda
    public Carrera deepClone() {
        return new Carrera(this.nombreCarrera, this.codigo);
    }
}

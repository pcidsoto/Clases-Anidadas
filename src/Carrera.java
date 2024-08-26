
import java.util.ArrayList;

public class Carrera {
    String nombreCarrera;
    int codigo;
    ArrayList<Ramo> ramos;
    ArrayList<Alumno> alumnos;

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
    void addRamo(Ramo ramo) {
        ramos.add(ramo);
    }

    void addAlumno(Alumno alumno) {
        alumnos.add(alumno);
    }

    public String getNombreCarrera(){
        return this.nombreCarrera;
    }

    public String toString() {
        return "\n Nombre: [" + nombreCarrera + "]"
                + "\nCadigo: " + codigo
                + "\nRamos: " + ramos.toString();
    }
}


import java.util.ArrayList;

public class Carrera {

    String nombreCarrera;
    int codigo;
    ArrayList<Ramo> ramos;
    ArrayList<Alumno> alumnos;
//constuctor vacío

    public Carrera() {
    }

    public Carrera(String nombreCarrera, int codigo) {
        this.nombreCarrera = nombreCarrera;
        this.codigo = codigo;
        // Me aseguro de incializar los arrayList para poder agregar objetos.
        this.ramos = new ArrayList<>();
        this.alumnos = new ArrayList<>();
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Ramo> getRamos() {
        return ramos;
    }

    // cada carrera podrá tener un máximo de cuatro ramos.
    public void addRamo(Ramo ramo) {
        if (this.ramos.size() >= 4) {
            throw new IllegalStateException("No se pueden agregar más de 4 ramos a la carrera.");
        }
        this.ramos.add(ramo);
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void addAlumno(Alumno alumno) {
        this.alumnos.add(alumno);
    }

    public String toString() {
        return "\n Nombre: [" + nombreCarrera + "]"
                + "\nCadigo: " + codigo 
                + "\nRamos: " + ramos.toString()
                + "\nAlumnos: " + alumnos.toString();
    }
}// clase carrera;

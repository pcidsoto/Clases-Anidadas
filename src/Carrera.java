
import java.util.ArrayList;

public class Carrera {

    private String nombreCarrera;
    private ArrayList<Ramo> ramos;
    private ArrayList<Alumno> alumnos;

//constuctor vacío inicializamos los arrayslist
    public Carrera() {

        this.ramos = new ArrayList<>();
        this.alumnos = new ArrayList<>();

    }

    public Carrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nCarrera: ").append(nombreCarrera)
                .append("\nRamos: ");

        if (ramos.isEmpty()) {
            sb.append("No hay ramos asignados.");
        } else {
            for (Ramo ramo : ramos) {
                sb.append("\n - ").append(ramo.toString());
            }
        }

        sb.append("\nAlumnos: ");
        if (alumnos.isEmpty()) {
            sb.append("No hay alumnos inscritos.");
        } else {
            for (Alumno alumno : alumnos) {
                sb.append("\n - ").append(alumno.toString());
            }
        }

        return sb.toString();
    }

}// clase carrera;

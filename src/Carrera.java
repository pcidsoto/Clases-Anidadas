
import java.util.ArrayList;

public class Carrera {

    String nombreCarrera;
    int codigo;
    ArrayList<Ramo> ramos;
    ArrayList<Alumno> alumnos;
//constuctor vacío

    public Carrera() {
    }

    public Carrera(String nombreCarrera, int codigo, Ramo ramos, Alumno alumno) {
        this.nombreCarrera = nombreCarrera;
        this.codigo = codigo;
        this.ramos.add(ramos);
        this.alumnos.add(alumno);
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

    public void setRamos(ArrayList<Ramo> ramos) {
        this.ramos = ramos;
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(ArrayList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

}// clase carrera;

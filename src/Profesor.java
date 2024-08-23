
import java.util.ArrayList;

public class Profesor {

    private String nombre;
    private String apellido;
    private String especialidad;
    private ArrayList<Carrera> carrera;

// contructor vacío
    public Profesor() {
    }

    public Profesor(String nombre, String apellido, String especialidad, Carrera carrera) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.carrera.add(carrera);
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public ArrayList<Carrera> getCursos() {
        return carrera;
    }

    public void setCursos(ArrayList<Carrera> carrera) {
        this.carrera = carrera;
    }

}

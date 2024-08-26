import java.util.ArrayList;
public class Profesor {

    private String nombre;
    private String apellido;
    private String especialidad;
    private ArrayList<Ramo> ramos;

// contructor vacío
    public Profesor() {
        this.ramos = new ArrayList<>();
    }

    public Profesor(String nombre, String apellido, String especialidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.ramos = new ArrayList<>();
    }

    public ArrayList<Ramo> getRamos(){
        return this.ramos;
    }

    public String getFullName(){
        return getNombre() +" "+getApellido();
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

    public void setRamos(ArrayList<Ramo> ramos){
        this.ramos = ramos;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void addRamo(Ramo ramo){
        this.ramos.add(ramo);
    }

    @Override
    public String toString() {
        return "\nNombre: " + nombre
                + ", \nApellido: " + apellido
                + ", \nEspecialidad: " + especialidad
                + ", \nRamos dictados: " + ramos.toString();
    }
}

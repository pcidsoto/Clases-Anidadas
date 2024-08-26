public class Profesor {

    private String nombre;
    private String apellido;
    private String especialidad;

// contructor vacío
    public Profesor() {
    }

    public Profesor(String nombre, String apellido, String especialidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
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


    @Override
    public String toString() {
        return "\nNombre: " + nombre
                + ", \nApellido: " + apellido
                + ", \nEspecialidad: " + especialidad;
    }

}

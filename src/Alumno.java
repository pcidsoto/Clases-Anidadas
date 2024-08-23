
import java.text.DecimalFormat;

public class Alumno {

    static DecimalFormat df = new DecimalFormat(" #,###");
    private int numero;
    private int run;
    private String dv;
    private String nombre;
    private String apellido;
    private int semestre;

// constructor vacío.
    public Alumno() {
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

    public void setNumero() {
        this.numero = numero;
    }

    public int getRun() {
        return run;
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

// Método toString
    @Override
    public String toString() {
        return "\nNUMERO: [" + numero + "]"
                + "\nNOMBRE: " + nombre + " " + apellido
                + "\nRUT: " + df.format(run) + "-" + dv
                + "\nSEMESTRE: " + semestre;
    }

}

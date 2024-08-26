import java.util.ArrayList;

import Exceptions.LimiteExcedidoException;
import Exceptions.ValorInvalidoException;

public class Ramo {

    int codigo;
    String nombre;
    Profesor profesor;
    ArrayList<Nota> notas;

    public Ramo() {
        this.notas = new ArrayList<>(3);
    }

    

    public Ramo(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.notas = new ArrayList<>(3);
    }

    void agregarNota(String fecha, byte valor) throws LimiteExcedidoException, ValorInvalidoException {
        if (notas.size() >= 3) {
            throw new LimiteExcedidoException("No se pueden agregar más de 3 notas por ramo");
        }
        // Si nota no está en el rango emite un error.
        notas.add(new Nota(fecha, valor));
    }

    @Override
    public String toString() {
        return "Ramo [codigo=" + codigo + ", nombre=" + nombre + "]";
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public ArrayList<Nota> getNotas() {
        return notas;
    }
}

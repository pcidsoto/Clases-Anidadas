import Exceptions.ValorInvalidoException;

class Nota {
    private String fecha;
    private byte valor;

    Nota(String fecha, byte valor) throws ValorInvalidoException{
        if (valor < 0 || valor > 70) {
            throw new ValorInvalidoException("La nota debe estar entre 0 y 70");
        }
        this.fecha = fecha;
        this.valor = valor;
    }
}
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public byte getValor() {
        return valor;
    }

    public void setValor(byte valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Nota [fecha=" + fecha + ", valor=" + valor + "]";
    }
}
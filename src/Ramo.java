
public class Ramo {

    private String nombreRamo;
    private double[] notas = new double[3]; // maximo 3 notas

    public Ramo() {

    }

    public Ramo(String nombreRamo, double[] notas) {

        this.nombreRamo = nombreRamo;
        if (notas.length <= 3) {
            this.notas = notas.clone();
        } else {
            System.out.println("Error: Solo se permiten hasta 3 notas.");
        }
    }

    public String getNombreRamo() {
        return nombreRamo;
    }

    public void setNombreRamo(String nombreRamo) {
        this.nombreRamo = nombreRamo;
    }

    public double[] getNotas() {
        return notas.clone();
    }

    public void setNotas(double[] notas) {
        if (notas.length <= 3) {
            this.notas = notas.clone();
        } else {
            System.out.println("Error: Solo se permiten hasta 3 notas.");
        }
    }

    @Override
    public String toString() {
        StringBuilder notasStr = new StringBuilder();
        for (double nota : notas) {
            if (nota != 0) { // Suponiendo que 0 es el valor por defecto
                notasStr.append(nota).append(" ");
            }
        }
        return "Ramo: " + nombreRamo;// + "\nNotas: " + notasStr.toString().trim();
    }

};

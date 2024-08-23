
import java.awt.Color;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Principal {

    static String busqueda[] = {" Buscar ", " Nómina ", " Salir "};

    public static void main(String[] args) throws Exception {

        Alumno listado[] = new Alumno[15];

        listado[0] = new Alumno(1, 16344426, "7", "Miguel Angel", "Ancapan Bobadilla", 3);
        listado[1] = new Alumno(2, 20118888, "1", "Ignacio Alejandro", "Araya Miranda", 3);
        listado[2] = new Alumno(3, 18663081, "5", "Eduardo Andres", "Berrios Rojas", 3);
        listado[3] = new Alumno(4, 17966925, "0", "Pedro Antonio", "Cid Soto", 3);
        listado[4] = new Alumno(5, 17727715, "0", "Claudio Ignacio", "Garrido Arias", 3);
        listado[5] = new Alumno(6, 19439483, "7", "Daniel Alejandro", "Labrana Trujillo", 3);
        listado[6] = new Alumno(7, 17630592, "4", "Angelo Felipe", "Maldonado Maldonado", 3);
        listado[7] = new Alumno(8, 27144351, "k", "Veronica Lizeth", "Manchola Zúñiga", 3);
        listado[8] = new Alumno(9, 18478372, "k", "Lucas Alejandro", "Molina Cespedes", 3);
        listado[9] = new Alumno(10, 16869778, "3", "Carla Pamela", "Molina Jerez", 3);
        listado[10] = new Alumno(11, 19471111, "5", "Sebastián Orlando", "Osorio Palma", 3);
        listado[11] = new Alumno(12, 17579449, "2", "Marvyn Glen", "Palacios Gamboa", 3);
        listado[12] = new Alumno(13, 17325030, "4", "Javiera Alejandra", "Vergara Zuñiga", 3);
        listado[13] = new Alumno(14, 17824056, "0", "Carlos Ignacio", "Villareal Díaz", 3);
        listado[14] = new Alumno(15, 18580136, "5", "Camila Andrea", "Villarroel Riquelme", 3);

        boolean ingresar = true;

        while (ingresar) {

            int opcion = JOptionPane.showOptionDialog(null, "Seleccione operación:", "Menú", 0,
                    JOptionPane.QUESTION_MESSAGE, null, busqueda, busqueda[0]);

            if (opcion == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Operación finalizada");
                ingresar = false;

            } else {
                switch (opcion) {

                    case 0:
                        // El código (a1, a2) -> Integer.compare(a1.getRun(), a2.getRun()) es una expresión lambda que compara dos alumnos según su RUT.
                        Arrays.sort(listado, (a1, a2) -> Integer.compare(a1.getRun(), a2.getRun()));
                        int key = 0;
                        boolean valido = false;
                        while (!valido) {
                            try {
                                String datos = JOptionPane.showInputDialog("Ingrese rut sin dígito verificador: ");
                                if (datos == null) {
                                    JOptionPane.showMessageDialog(null, "Operación cancelada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                                    return;
                                }
                                key = Integer.parseInt(datos);
                                valido = true;
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "El valor ingresado no es un número válido.", "ERROR!", JOptionPane.WARNING_MESSAGE);
                            }
                        }// while valido
// ALgoritmo de busqueda binaria.

                        int lo = 0;
                        int max = listado.length - 1;
                        int indice = -1;

                        while (lo <= max) {

                            int medio = (lo + max) / 2;
                            if (key == listado[medio].getRun()) {
                                indice = medio;
                                JOptionPane.showMessageDialog(null, "Alumno: " + listado[indice]);
                                break;
                            }
                            if (key < listado[medio].getRun()) {
                                max = medio - 1;
                            }
                            if (key > listado[medio].getRun()) {
                                lo = medio + 1;
                            }
                        }// end while busqueda binaria
                        if (indice == -1) {
                            JOptionPane.showMessageDialog(null, "Alumno no encontrado.");
                        }
                        break;
                    case 1:
                        //contatenar cadenas de texto.
                        StringBuilder nomina = new StringBuilder();

                        for (int i = 0; i < listado.length; i++) {

                            nomina.append(" ------------------------------------------------------------------------ ").append(listado[i]).append("\n");

                        }

                        JTextArea planilla = new JTextArea(nomina.toString());
                        planilla.setEditable(false);
                        planilla.setBackground(Color.white);

                        JScrollPane scroll = new JScrollPane(planilla);
                        scroll.getVerticalScrollBar().setBackground(Color.black);
                        scroll.setPreferredSize(new java.awt.Dimension(600, 600));

                        var mostrarPlanilla = JOptionPane.showConfirmDialog(null,
                                scroll, "Nómina de alumnos", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Operación finalizada");
                        ingresar = false;
                        break;
                }

            }//while supremo
        }

    }
}

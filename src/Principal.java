
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Principal {

    static String busqueda[] = {" Buscar ", " Nómina ", " Actualizar nónimas", " Salir "};
    static String nominal[] = {"Carreras", "Alumnos", "Profesores"};
    static String actualizar[] = {"Carreras", "Alumnos", "Profesores"};

    public static void main(String[] args) throws Exception {
        ArrayList<Alumno> alumnos = loadAlumnos();
        ArrayList<Carrera> carreras = loadCarreras();
        ArrayList<Profesor> profesores = loadProfesores();

        boolean ingresar = true;
        while (ingresar) {
            int opcion = JOptionPane.showOptionDialog(null, "Seleccione operación:", "Menú", 0,
                    JOptionPane.QUESTION_MESSAGE, null, busqueda, busqueda[0]);

            if (opcion == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Operación finalizada");
                ingresar = false;
            }
            switch (opcion) {
                case 0:
                    int indice = BuscarAlumno(alumnos);
                    // end while busqueda binaria
                    if (indice == -1) {
                        JOptionPane.showMessageDialog(null, "Alumno no encontrado.");
                    } else if (indice == -2) {
                        JOptionPane.showMessageDialog(null, "Operación cancelada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    } else {
                        // Si encuentro un alumno le doy la posibilidad de inscribir carreras.
                        // Convertir ArrayList a array de String para usar con JOptionPane
                        String[] opciones = carreras.stream()
                                .map(Carrera::getNombreCarrera)
                                .toArray(String[]::new);

                        // Mostrar el diálogo de opciones
                        String seleccion = (String) JOptionPane.showInputDialog(
                                null,
                                "Seleccione una carrera:",
                                "Selección de Carrera",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                opciones,
                                opciones[0] // opción por defecto
                        );

                        // Manejar la selección
                        if (seleccion != null) {
                            JOptionPane.showMessageDialog(null, "Has agregado: " + seleccion + " a: " + alumnos.get(indice).getNombre());
                            // Busco la carrera seleccionada con filter de Stream
                            Optional<Carrera> carreraSeleccionada = carreras.stream()
                                    .filter(a -> a.getNombreCarrera() == seleccion)
                                    .findFirst();
                            // Si es encontrada agrega al alumno a la carrera
                            if (carreraSeleccionada.isPresent()) {
                                carreraSeleccionada.get().addAlumno(alumnos.get(indice));
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No has seleccionado ninguna carrera.");
                        };
                    }
                    break;
                case 1: // En este switch se muestra un menú de las diferentes nomias disponibles [carreras, Alumnos,Profesores] sin opción editar, solo visualizar
                    boolean _nominal = true;
                    while (_nominal) {
                        int nominas = JOptionPane.showOptionDialog(null, "Seleccione nomina:", "Menú", 0,
                                JOptionPane.QUESTION_MESSAGE, null, nominal, nominal[0]);

                        switch (nominas) {
                            case 0: // Mostrar nómina de carreras
                                MostrarCarreras(carreras);
                                break;
                            case 1: // Mostrar nómina de alumnos
                                MostrarNomina(alumnos);
                                break;
                            case 2:
                                MostrarProfesores(profesores);
                                break;
                            case JOptionPane.CLOSED_OPTION: // Si el usuario cierra el diálogo
                                _nominal = false;
                                break;
                            default:
                                _nominal = false;
                                break;
                        }
                    }
                    break;
//*******************************************Pieza agregada sabado 2300 hrs************************************************************************************************************** */
                case 2:

                    boolean _actualizar = true;
                    while (_actualizar) {

                        int actualizar1 = JOptionPane.showOptionDialog(null, "Seleccione nómina:", "Menú", 0,
                                JOptionPane.QUESTION_MESSAGE, null, actualizar, actualizar[0]);

                        switch (actualizar1) {
                            case 0:
//*******************************************************************aqui lor cid las ventanas!!*********************************************************************************************** */
                                JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

                                JTextField nameField = new JTextField(10);
                                JTextField ramoField = new JTextField(10);

                                // Agregar etiquetas y campos de texto al panel
                                panel.add(new JLabel("Carrera:"));
                                panel.add(nameField);
                                panel.add(new JLabel("Ramos(separar por comas):"));
                                panel.add(ramoField);

                                int result = JOptionPane.showConfirmDialog(null, panel, "Ingrese datos", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                                if (result == JOptionPane.OK_OPTION) {
                                    String nombreCarrera = nameField.getText();
                                    String ramosCarrera = ramoField.getText();

                                    ArrayList<Ramo> ramos = new ArrayList<>();
                                    if (!ramosCarrera.trim().isEmpty()) {
                                        String[] ramosArray = ramosCarrera.split(",");
                                        for (String ramoNombre : ramosArray) {
                                            ramos.add(new Ramo(ramoNombre.trim(), new double[0])); // Crear el objeto Ramo y agregarlo a la lista
                                        }
                                    }
                                    Carrera nuevaCarrera = new Carrera(nombreCarrera);
                                    for (Ramo ramo : ramos) {
                                        nuevaCarrera.addRamo(ramo);
                                    }

                                    carreras.add(nuevaCarrera);
                                    JOptionPane.showMessageDialog(null, nuevaCarrera.toString());

                                }

                                break;
//**************************************************************************************************************************************************************************** */                              
                            case 1: // Mostrar nómina de alumnos

                                break;
                            case 2:

                                break;
                            case JOptionPane.CLOSED_OPTION: // Si el usuario cierra el diálogo
                                _actualizar = false;
                                break;
                            default:
                                _actualizar = false;
                                break;
                        }
                    }

                    break;
//***************************************************************************************************************************************************************************************** */                    
                case 3:
                    JOptionPane.showMessageDialog(null, "Operación finalizada");
                    ingresar = false;
                    break;
            }

        }//while supremo
    }

    private static int BuscarAlumno(ArrayList<Alumno> listado) {
        listado.sort(Comparator.comparingInt(Alumno::getRun));
        int key = 0;
        boolean valido = false;
        while (!valido) {
            try {
                String datos = JOptionPane.showInputDialog("Ingrese rut sin dígito verificador: ");
                if (datos == null) {
                    return -2;
                }
                key = Integer.parseInt(datos);
                valido = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El valor ingresado no es un número válido.", "ERROR!", JOptionPane.WARNING_MESSAGE);
            }
        }// while valido

        Alumno a = new Alumno();
        a.setRun(key);
        // ALgoritmo de busqueda binaria.
        return busquedaBinaria(listado, a);
    }

    // Nomina de alumnos
    private static void MostrarNomina(ArrayList<Alumno> listado) {
        //contatenar cadenas de texto.
        StringBuilder nomina = new StringBuilder();

        for (int i = 0; i < listado.size(); i++) {

            nomina.append(" ------------------------------------------------------------------------ ").append(listado.get(i)).append("\n");

        }

        JTextArea planilla = new JTextArea(nomina.toString());
        planilla.setEditable(false);
        planilla.setBackground(Color.white);

        JScrollPane scroll = new JScrollPane(planilla);
        scroll.getVerticalScrollBar().setBackground(Color.black);
        scroll.setPreferredSize(new java.awt.Dimension(600, 600));

        JOptionPane.showConfirmDialog(null,
                scroll, "Nómina de alumnos", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

    }

    // Nomina de carreras.
    private static void MostrarCarreras(ArrayList<Carrera> carreras) {
        //contatenar cadenas de texto.
        StringBuilder nomina = new StringBuilder();

        for (int i = 0; i < carreras.size(); i++) {

            nomina.append(" ------------------------------------------------------------------------ ").append(carreras.get(i).toString()).append("\n");

        }

        JTextArea planilla = new JTextArea(nomina.toString());
        planilla.setEditable(false);
        planilla.setBackground(Color.white);

        JScrollPane scroll = new JScrollPane(planilla);
        scroll.getVerticalScrollBar().setBackground(Color.black);
        scroll.setPreferredSize(new java.awt.Dimension(600, 600));

        JOptionPane.showConfirmDialog(null,
                scroll, "Nómina Carreras", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

    // Cargamos las carreras
    private static ArrayList<Carrera> loadCarreras() {
        ArrayList<Carrera> carreras = new ArrayList<>();
        /*  carreras.add(new Carrera("Ingenieria Comercial"));
        carreras.add(new Carrera("Ingenierìa Informatica"));
        carreras.add(new Carrera("Enfermerìa"); */

        return carreras;
    }

    // Cargamos los alumnos
    private static ArrayList<Alumno> loadAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();

        alumnos.add(new Alumno(1, 16344426, "7", "Miguel Angel", "Ancapan Bobadilla", 3));
        alumnos.add(new Alumno(2, 20118888, "1", "Ignacio Alejandro", "Araya Miranda", 3));
        // listado[2] = new Alumno(3, 18663081, "5", "Eduardo Andres", "Berrios Rojas", 3);
        // listado[3] = new Alumno(4, 17966925, "0", "Pedro Antonio", "Cid Soto", 3);
        // listado[4] = new Alumno(5, 17727715, "0", "Claudio Ignacio", "Garrido Arias", 3);
        // listado[5] = new Alumno(6, 19439483, "7", "Daniel Alejandro", "Labrana Trujillo", 3);
        // listado[6] = new Alumno(7, 17630592, "4", "Angelo Felipe", "Maldonado Maldonado", 3);
        // listado[7] = new Alumno(8, 27144351, "k", "Veronica Lizeth", "Manchola Zúñiga", 3);
        // listado[8] = new Alumno(9, 18478372, "k", "Lucas Alejandro", "Molina Cespedes", 3);
        // listado[9] = new Alumno(10, 16869778, "3", "Carla Pamela", "Molina Jerez", 3);
        // listado[10] = new Alumno(11, 19471111, "5", "Sebastián Orlando", "Osorio Palma", 3);
        // listado[11] = new Alumno(12, 17579449, "2", "Marvyn Glen", "Palacios Gamboa", 3);
        // listado[12] = new Alumno(13, 17325030, "4", "Javiera Alejandra", "Vergara Zuñiga", 3);
        // listado[13] = new Alumno(14, 17824056, "0", "Carlos Ignacio", "Villareal Díaz", 3);
        // listado[14] = new Alumno(15, 18580136, "5", "Camila Andrea", "Villarroel Riquelme", 3);
        return alumnos;
    }

//********************************************PENDIENTE****************PENMDIENTE*************************************************************************** */
    // Nomina de Profesores.
    private static void MostrarProfesores(ArrayList<Profesor> profesores) {
        //contatenar cadenas de texto.
        StringBuilder nomina = new StringBuilder();

        for (int i = 0; i < profesores.size(); i++) {

            nomina.append(" ------------------------------------------------------------------------ ").append(profesores.get(i).toString()).append("\n");

        }

        JTextArea planilla = new JTextArea(nomina.toString());
        planilla.setEditable(false);
        planilla.setBackground(Color.white);

        JScrollPane scroll = new JScrollPane(planilla);
        scroll.getVerticalScrollBar().setBackground(Color.black);
        scroll.setPreferredSize(new java.awt.Dimension(400, 400));

        JOptionPane.showConfirmDialog(null,
                scroll, "Nómina de profesores", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

// Cargamos lo profesores
    private static ArrayList<Profesor> loadProfesores() {
        ArrayList<Profesor> profesores = new ArrayList<>();

        profesores.add(new Profesor("Carlos", "Naranjo", "Ing. Informática", new Carrera("Ing.Comercial")));
        profesores.add(new Profesor("Claudio", "Monares", "Tecnólogo médico", new Carrera("Enfermería")));
        profesores.add(new Profesor("Pedro", "Arriagada", "Ing. Nuclear", new Carrera("Ing. informática")));
        return profesores;
    }

//***************************************************************************************************************** */
    // La busqueda binaria se hace generica.
    public static <T extends Comparable<T>> int busquedaBinaria(ArrayList<T> lista, T objetivo) {
        int izquierda = 0;
        int derecha = lista.size() - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            int comparacion = lista.get(medio).compareTo(objetivo);

            if (comparacion == 0) {
                return medio; // Elemento encontrado
            }
            if (comparacion < 0) {
                izquierda = medio + 1; // El objetivo está en la mitad derecha
            } else {
                derecha = medio - 1; // El objetivo está en la mitad izquierda
            }
        }

        return -1; // Elemento no encontrado
    }

}

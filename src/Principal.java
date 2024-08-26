
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Exceptions.MatriculaException;
import Utils.Utils;

public class Principal {

    static String busqueda[] = {" Buscar ", " Menú alumnos ", "Menú Carreras", " Salir "};
    static String nominal[] = {"Carreras", "Alumnos", "Profesores"};
    static String menuCarrera[] = {"listar Carreras", "Crear Carrera", "Crear Ramo"};
    static String menuAlumnos[] = {"listar Alumnos", "Crear Alumno", "Matricular Alumno"};

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

                    break;
                case 1: // menu alumnos.
                    menuAlumnos(alumnos, carreras);
                    break;
//*******************************************Pieza agregada sabado 2300 hrs************************************************************************************************************** */
                case 2:
                    // Menú carreras
                    menuCarreras(carreras);
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

    // Cargamos las carreras
    private static ArrayList<Carrera> loadCarreras() {
        ArrayList<Carrera> carreras = new ArrayList<>();
        carreras.add(new Carrera("Ingenieria Comercial", 1021));
        carreras.add(new Carrera("Ingenierìa Informatica", 1031));
        carreras.add(new Carrera("Enfermerìa", 1041));
        return carreras;
    }

    // Cargamos los alumnos
    private static ArrayList<Alumno> loadAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();

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

        profesores.add(new Profesor("Carlos", "Naranjo", "Ing. Informática"));
        profesores.add(new Profesor("Claudio", "Monares", "Tecnólogo médico"));
        profesores.add(new Profesor("Pedro", "Arriagada", "Ing. Nuclear"));
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

    private static void menuCarreras(ArrayList<Carrera> carreras) {
        // menuCarrera[] = {"listar Carreras", "Crear Carrera", "Crear Ramo"};
        boolean _actualizar = true;
        while (_actualizar) {

            int actualizar1 = JOptionPane.showOptionDialog(null, "Carreras", "Menú", 0,
                    JOptionPane.QUESTION_MESSAGE, null, menuCarrera, menuCarrera[0]);

            switch (actualizar1) {
                case 0: // Listar carreras.
                    Utils.mostrarPlanilla(carreras, "Nomina de carreras");
                    break;
                case 1: // Crear carrera.
                    Carrera newCarrera = Utils.crearFormulario(
                            Carrera.class,
                            new ArrayList<>(Arrays.asList("nombreCarrera", "codigo")));
                    if (newCarrera != null) {
                        carreras.add(newCarrera);
                    }
                    break;
                case 2:
                    // Listar carreras.
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
                        // Busco la carrera seleccionada con filter de Stream para agregar un ramo.
                        Optional<Carrera> carreraSeleccionada = carreras.stream()
                                .filter(a -> a.getNombreCarrera().equals(seleccion))
                                .findFirst();
                        // Si es encontrada agrega al alumno a la carrera
                        if (carreraSeleccionada.isPresent()) {
                            Ramo newRamo = Utils.crearFormulario(
                                    Ramo.class,
                                    new ArrayList<>(Arrays.asList("nombre", "codigo")));

                            // Si es encontrada agrega el ramo a la carrera
                            if (carreraSeleccionada.isPresent()) {
                                carreraSeleccionada.get().addRamo(newRamo);
                                JOptionPane.showMessageDialog(null, "Ramo agregado exitosamente.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No has seleccionado ninguna carrera.");
                    }

                    break;
                case JOptionPane.CLOSED_OPTION: // Si el usuario cierra el diálogo
                    _actualizar = false;
                    break;
                default:
                    _actualizar = false;
                    break;
            }
        }
    }

    private static void menuAlumnos(ArrayList<Alumno> alumnos, ArrayList<Carrera> carreras) throws MatriculaException {
        boolean _nominal = true;
        while (_nominal) {
            int nominas = JOptionPane.showOptionDialog(null, "Seleccione opcion:", "Menú", 0,
                    JOptionPane.QUESTION_MESSAGE, null, menuAlumnos, menuAlumnos[0]);

            switch (nominas) {
                case 0: // Mostrar nómina de alumnos.
                    Utils.mostrarPlanilla(alumnos, "Nomina de alumnos");
                    break;
                case 1: // Crear alumno
                    Alumno newAlumno = Utils.crearFormulario(
                            Alumno.class,
                            new ArrayList<>(Arrays.asList("nombre", "apellido","run","dv","semestre")));
                    if (newAlumno != null) {
                        alumnos.add(newAlumno);
                    }
                    break;
                case 2: // Matricular Alumno.
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
                            try {

                                Optional<Carrera> carreraSeleccionada = carreras.stream()
                                        .filter(a -> a.getNombreCarrera() == seleccion)
                                        .findFirst();
                                // Si es encontrada agrega al alumno a la carrera

                                if (carreraSeleccionada.isPresent()) {
                                    // si el alumno tiene una carrera matriculada se origina un error de matricula.
                                    alumnos.get(indice).matricularCarrera(carreraSeleccionada.get());

                                    // si no hay error de matricula, se matricula el alumno en la carrera y se suma el alumno a la carrera seleccionada.
                                    carreraSeleccionada.get().addAlumno(alumnos.get(indice));
                                }
                                JOptionPane.showMessageDialog(null, "Has agregado: " + seleccion + " a: " + alumnos.get(indice).getNombre());
                                // Busco la carrera seleccionada con filter de Stream
                            } catch (MatriculaException e) {
                                JOptionPane.showMessageDialog(null, "No se pudo agregar el ramo: " + e.getMessage(),
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "No has seleccionado ninguna carrera.");
                        };
                    }
                    break;
                case JOptionPane.CLOSED_OPTION: // Si el usuario cierra el diálogo
                    _nominal = false;
                    break;
                default:
                    _nominal = false;
                    break;
            }
        }
    }
}

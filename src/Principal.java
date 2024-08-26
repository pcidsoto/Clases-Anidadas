
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

import javax.swing.JOptionPane;

import Exceptions.MatriculaException;
import Utils.Utils;

public class Principal {

    static String menuPrincipal[] = {"Menú Profesores", " Menú alumnos ", "Menú Carreras", " Salir "};
    static String menuCarrera[] = {"listar Carreras", "Crear Carrera", "Crear Ramo"};
    static String menuAlumnos[] = {"listar Alumnos", "Crear Alumno", "Matricular Alumno"};
    static String menuProfesor[] = {"mis ramos", "calificar"};

    public static void main(String[] args) throws Exception {
        ArrayList<Alumno> alumnos = loadAlumnos();
        ArrayList<Carrera> carreras = loadCarreras();
        ArrayList<Profesor> profesores = loadProfesores();

        // Set ramosy profesores por default.
        Ramo liderazgo = new Ramo(1, "Liderazgo");
        // Registro el profesor en el ramo.
        liderazgo.setProfesor(profesores.get(0));
        // Registro los ramos que dicta el profesor.
        profesores.get(0).addRamo(liderazgo);
        // Registro el ramo en la carrera.
        carreras.get(0).addRamo(liderazgo);

        boolean ingresar = true;
        while (ingresar) {
            int opcion = JOptionPane.showOptionDialog(null, "Seleccione operación:", "Menú", 0,
                    JOptionPane.QUESTION_MESSAGE, null, menuPrincipal, menuPrincipal[0]);

            if (opcion == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Operación finalizada");
                ingresar = false;
            }
            switch (opcion) {
                case 0 -> {
                    menuProfesor(carreras, alumnos, profesores);
                }
                case 1 -> // menu alumnos.
                    menuAlumnos(alumnos, carreras);
                case 2 -> // Menú carreras
                    menuCarreras(carreras);
                case 3 -> {
                    JOptionPane.showMessageDialog(null, "Operación finalizada");
                    ingresar = false;
                }
            }
        }
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
        return Utils.busquedaBinaria(listado, a);
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

    // Cargamos lo profesores
    private static ArrayList<Profesor> loadProfesores() {
        ArrayList<Profesor> profesores = new ArrayList<>();

        profesores.add(new Profesor("Carlos", "Naranjo", "Ing. Informática"));
        profesores.add(new Profesor("Claudio", "Monares", "Tecnólogo médico"));
        profesores.add(new Profesor("Pedro", "Arriagada", "Ing. Nuclear"));
        return profesores;
    }

    private static void menuCarreras(ArrayList<Carrera> carreras) {
        // menuCarrera[] = {"listar Carreras", "Crear Carrera", "Crear Ramo"};
        boolean _actualizar = true;
        while (_actualizar) {

            int actualizar1 = JOptionPane.showOptionDialog(null, "Carreras", "Menú", 0,
                    JOptionPane.QUESTION_MESSAGE, null, menuCarrera, menuCarrera[0]);

            switch (actualizar1) {
                case 0 -> // Listar carreras.
                    Utils.mostrarPlanilla(carreras, "Nomina de carreras");
                case 1 -> {
                    // Crear carrera.
                    Carrera newCarrera = Utils.crearFormulario(
                            Carrera.class,
                            new ArrayList<>(Arrays.asList("nombreCarrera", "codigo")));
                    if (newCarrera != null) {
                        carreras.add(newCarrera);
                    }
                }
                case 2 -> {
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
                }
                case JOptionPane.CLOSED_OPTION -> // Si el usuario cierra el diálogo
                    _actualizar = false;
                default ->
                    _actualizar = false;
            }
        }
    }

    private static void menuAlumnos(ArrayList<Alumno> alumnos, ArrayList<Carrera> carreras) throws MatriculaException {
        boolean _nominal = true;
        while (_nominal) {
            int nominas = JOptionPane.showOptionDialog(null, "Seleccione opcion:", "Menú", 0,
                    JOptionPane.QUESTION_MESSAGE, null, menuAlumnos, menuAlumnos[0]);
            switch (nominas) {
                case 0 ->
                    // Mostrar nómina de alumnos.
                    Utils.mostrarPlanilla(alumnos, "Nomina de alumnos");
                case 1 -> {
                    // Crear alumno
                    Alumno newAlumno = Utils.crearFormulario(
                            Alumno.class,
                            new ArrayList<>(Arrays.asList("nombre", "apellido", "run", "dv", "semestre")));
                    if (newAlumno != null) {
                        alumnos.add(newAlumno);
                    }
                }
                case 2 -> {
                    // Matricular Alumno.
                    int indice = BuscarAlumno(alumnos);

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
                                        .filter(a -> a.getNombreCarrera().equals(seleccion))
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
                        }
                    }
                }
                case JOptionPane.CLOSED_OPTION -> // Si el usuario cierra el diálogo
                    _nominal = false;
                default ->
                    _nominal = false;
            }
        }
    }

    private static void menuProfesor(ArrayList<Carrera> carreras, ArrayList<Alumno> alumnos, ArrayList<Profesor> profesores) {
        // menuProfesor[] = {"mis ramos", "calificar"};
        boolean _actualizar = true;
        while (_actualizar) {

            int actualizar1 = JOptionPane.showOptionDialog(null, "Carreras", "Menú", 0,
                    JOptionPane.QUESTION_MESSAGE, null, menuProfesor, menuProfesor[0]);

            switch (actualizar1) {
                case 0 -> {// Listar carreras.
                    // Convertir ArrayList a array de String para usar con JOptionPane
                    String[] opcionesProfesor = profesores.stream()
                            .map(Profesor::getFullName)
                            .toArray(String[]::new);

                    // Mostrar el diálogo de opciones
                    String seleccionProfesor = (String) JOptionPane.showInputDialog(
                            null,
                            "Seleccione un profesor:",
                            "Selección de profesor",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            opcionesProfesor,
                            opcionesProfesor[0] // opción por defecto
                    );

                    // Busco la carrera seleccionada con filter de Stream para agregar un ramo.
                    Optional<Profesor> profesorSeleccionado = profesores.stream()
                            .filter(a -> a.getFullName().equals(seleccionProfesor))
                            .findFirst();

                    Utils.mostrarPlanilla(profesorSeleccionado.get().getRamos(), "Nomina de profesores");

                }
                case 1 -> {
                    // Calificar
                    // Convertir ArrayList a array de String para usar con JOptionPane
                    String[] opcionesProfesor = profesores.stream()
                            .map(Profesor::getFullName)
                            .toArray(String[]::new);
                    if (opcionesProfesor.length == 0) {
                        JOptionPane.showMessageDialog(null, "No hay Profesores inscritos");
                        break;
                    }
                    // Mostrar el diálogo de opciones
                    String seleccionProfesor = (String) JOptionPane.showInputDialog(
                            null,
                            "Seleccione un profesor:",
                            "Selección de profesor",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            opcionesProfesor,
                            opcionesProfesor[0] // opción por defecto
                    );

                    // Busco la carrera seleccionada con filter de Stream para agregar un ramo.
                    Optional<Profesor> profesorSeleccionado = profesores.stream()
                            .filter(a -> a.getFullName().equals(seleccionProfesor))
                            .findFirst();

                    // Seleccionar ramo donde calificar.
                    String[] opcionesRamos = profesorSeleccionado.get().getRamos()
                            .stream()
                            .map(Ramo::getNombre)
                            .toArray(String[]::new);

                    if (opcionesRamos.length == 0) {
                        JOptionPane.showMessageDialog(null, "No hay Ramos inscritos");
                        break;
                    }

                    String seleccionRamo = (String) JOptionPane.showInputDialog(
                            null,
                            "Seleccione un profesor:",
                            "Selección de profesor",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            opcionesRamos,
                            opcionesRamos[0] // opción por defecto
                    );
                    // Seleccion de alumnos para calificar.
                    String[] opcionesAlumnos = alumnos.stream()
                            .filter(a -> a.tieneCarrera() == true)
                            .filter(a -> a.getCarrera().tieneRamo(seleccionRamo))
                            .map(Alumno::getRun)
                            .toArray(String[]::new);
                    if (opcionesAlumnos.length == 0) {
                        JOptionPane.showMessageDialog(null, "No hay alumnos inscritos");
                        break;
                    }

                    String seleccionAlumno = (String) JOptionPane.showInputDialog(
                            null,
                            "Seleccione un alumno para calificar:",
                            "Selección de alumno",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            opcionesAlumnos,
                            opcionesAlumnos[0] // opción por defecto
                    );

                    Optional<Alumno> alumnoSeleccionado = alumnos.stream()
                            .filter(a -> a.getRun() == Integer.parseInt(seleccionAlumno))
                            .findFirst();

                    String notaStr = JOptionPane.showInputDialog(
                            null,
                            "Ingrese el valor de la nota (0-100) para " + alumnoSeleccionado.get().getNombre() + ":",
                            "Agregar Nota",
                            JOptionPane.QUESTION_MESSAGE
                    );
                    LocalDateTime ahora = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    try {
                        alumnoSeleccionado.get().getRamoPorNombre(seleccionRamo).agregarNota(ahora.format(formatter), Byte.parseByte(notaStr));;
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, e.toString());
                    }

                }
                case 2 -> {
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
                }
                case JOptionPane.CLOSED_OPTION -> // Si el usuario cierra el diálogo
                    _actualizar = false;
                default ->
                    _actualizar = false;
            }
        }
    }

}

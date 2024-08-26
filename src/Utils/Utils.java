package Utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Utils {

    
    //Muestra una planilla con los elementos de un ArrayList en una ventana de diálogo.
    public static <T> void mostrarPlanilla(ArrayList<T> lista, String titulo, int ancho, int alto) {
        StringBuilder contenido = new StringBuilder();

        for (T elemento : lista) {
            contenido.append(" ------------------------------------------------------------------------ ")
                     .append(elemento.toString())
                     .append("\n");
        }

        JTextArea areaTexto = new JTextArea(contenido.toString());
        areaTexto.setEditable(false);
        areaTexto.setBackground(Color.white);

        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.getVerticalScrollBar().setBackground(Color.black);
        scrollPane.setPreferredSize(new Dimension(ancho, alto));

        JOptionPane.showConfirmDialog(null,
                scrollPane, titulo, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Sobrecarga del método mostrarPlanilla con dimensiones predeterminadas.
     */
    public static <T> void mostrarPlanilla(ArrayList<T> lista, String titulo) {
        mostrarPlanilla(lista, titulo, 600, 600);
    }

    /**
     * Crea y muestra un formulario dinámico basado en los campos de una clase.
     *
     * <T> El tipo de la clase para la cual se crea el formulario
     * clazz La clase cuyos campos se utilizarán para crear el formulario
     * ArrayList<String> los campos que quiero ingresar de la clase.
     * @return Un objeto del tipo T con los valores ingresados, o null si se cancela
     */
    public static <T> T crearFormulario(Class<T> clazz, ArrayList<String> inputCampos ) {
        // Retorna los campos de la clase que se quiere crear el formulario.
        Field[] campos = clazz.getDeclaredFields();
        // Se crea el panel donde estarán los campos de la clase.
        JPanel panel = new JPanel(new GridLayout(campos.length, 2, 5, 5));

        // Se crea un map
        Map<String, JTextField> camposTexto = new HashMap<>();

        for (Field campo : campos) {
            if (inputCampos.contains(campo.getName())){
                panel.add(new JLabel(campo.getName() + ":"));
                JTextField textField = new JTextField(15);
                panel.add(textField);
                camposTexto.put(campo.getName(), textField);
            }
        }

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Ingrese datos para " + clazz.getSimpleName(),
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                // Invoca al constructor sin argumentos (Constructor vacío);
                T objeto = clazz.getDeclaredConstructor().newInstance();
                for (Field campo : campos) {
                    // Parseo solo los campos inputCampos.
                    if (inputCampos.contains(campo.getName())){
                        campo.setAccessible(true);
                        String valor = camposTexto.get(campo.getName()).getText();
                        if (campo.getType() == int.class) {
                            campo.set(objeto, Integer.parseInt(valor));
                        } else if (campo.getType() == double.class) {
                            campo.set(objeto, Double.parseDouble(valor));
                        } else {
                            campo.set(objeto, valor);
                        }
                    }
                }
                return objeto;
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al crear el objeto: " + e.getMessage());
            }
        }
        return null;
    }
}
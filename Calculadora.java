import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora {

    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Calculadora Básica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        // Crear el campo de texto para mostrar resultados
        JTextField display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);

        // Crear el panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));

        // Lista de botones de la calculadora
        String[] botones = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        // Variables para manejar la lógica
        final String[] operador = {""};
        final double[] numero1 = {0};

        // Añadir botones al panel
        for (String texto : botones) {
            JButton boton = new JButton(texto);
            boton.setFont(new Font("Arial", Font.BOLD, 20));
            buttonPanel.add(boton);

            // Añadir eventos a cada botón
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String comando = e.getActionCommand();

                    if ("0123456789".contains(comando)) {
                        // Si es un número, añadirlo al display
                        display.setText(display.getText() + comando);
                    } else if ("/*-+".contains(comando)) {
                        // Si es un operador, guardar el número y el operador
                        operador[0] = comando;
                        numero1[0] = Double.parseDouble(display.getText());
                        display.setText("");
                    } else if ("=".equals(comando)) {
                        // Si es igual, realizar la operación
                        double numero2 = Double.parseDouble(display.getText());
                        double resultado = 0;

                        switch (operador[0]) {
                            case "+" -> resultado = numero1[0] + numero2;
                            case "-" -> resultado = numero1[0] - numero2;
                            case "*" -> resultado = numero1[0] * numero2;
                            case "/" -> resultado = numero1[0] / numero2;
                        }

                        display.setText(String.valueOf(resultado));
                    } else if ("C".equals(comando)) {
                        // Si es "C", limpiar todo
                        display.setText("");
                        numero1[0] = 0;
                        operador[0] = "";
                    }
                }
            });
        }

        // Añadir el panel de botones a la ventana
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Hacer visible la ventana
        frame.setVisible(true);
    }
}

package com.iudigital.view;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
	
	public static void main(String[] args) {
	
        SwingUtilities.invokeLater(() -> {
            // Crear el JFrame principal de la aplicación
            JFrame frame = new JFrame("Gestión de Funcionarios");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(750, 750); // Ajusta el tamaño de la ventana según tus necesidades
            frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
            
            // Crear una instancia del panel Form y añadirlo al JFrame
            Form formPanel = new Form();
            frame.add(formPanel);
            
            // Hacer visible el JFrame
            frame.setVisible(true);
        });
    }
}

package com.iudigital.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.iudigital.controller.FuncionarioController;
import com.iudigital.domain.Funcionario;

import java.awt.Font;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;





public class Form extends JPanel {
	private final FuncionarioController funcionarioController;
	private final static String[] COLUMNS = { "ID", "TIPO DOCUMENTO", "NRO DOCUMENTO", "NOMBRE", "APELLIDO", "TELEFONO",
			"DIRECCIÓN", "GÉNERO", "ESTADO CIVIL","FECHA NACIMIENTO", "FECHA CREACIÓN", "FECHA ACTUALIZACIÓN" };

	private final static String SELECCIONE = "--SELECCIONE-";

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNroDocumento;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldDireccion;
	private JTextField textFieldTelefono;
	private JFormattedTextField formattedTextFieldFechaNacimiento;
	private JFormattedTextField formattedTextFieldFechaNacimientoEdit;
	private JTextField textFieldNumeroDocumentoEdit;
	private JTextField textFieldNombreEdit;
	private JTextField textFieldApellidoEdit;
	private JTextField textFieldTelefonoEdit;
	private JTextField textFieldDireccionEdit;
	private JComboBox<Funcionario> comboBoxFuncionarios;
	private JComboBox<String> comboBoxTipoDocumentoEdit;
	private JComboBox<String> comboBoxSexoEdit;
	private JComboBox<String> comboBoxEstadoCivilEdit;
	private JTable tableFuncionarios;
	private DefaultTableModel tableModel;

	/**
	 * Create the panel.
	 */
	
	private void listarFuncuinarios() {
		tableFuncionarios.removeAll();
		Funcionario funcionario1 = new Funcionario();
		funcionario1.setNombres(SELECCIONE);
		funcionario1.setApellidos("-");
		funcionario1.setNumeroIdentificacion("");
		comboBoxFuncionarios.addItem(funcionario1);
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		
		for (String COLUMN: COLUMNS) {
			defaultTableModel.addColumn(COLUMN);
		}
			tableFuncionarios.setModel(defaultTableModel);
		try {
			List<Funcionario> funcionarios = funcionarioController.obtenerFuncionarios();
			if(funcionarios.isEmpty()) {
				return;
			}
			defaultTableModel.setRowCount(funcionarios.size());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			int row = 0;
			for (Funcionario funcionario : funcionarios) {
				defaultTableModel.setValueAt(funcionario.getFuncionarioId(), row, 0);
				defaultTableModel.setValueAt(funcionario.getTipoDocumento(), row, 1);
				defaultTableModel.setValueAt(funcionario.getNumeroIdentificacion(), row, 2);
				defaultTableModel.setValueAt(funcionario.getNombres(), row, 3);
				defaultTableModel.setValueAt(funcionario.getApellidos(), row, 4);
				defaultTableModel.setValueAt(funcionario.getTelefono(), row, 5);
				defaultTableModel.setValueAt(funcionario.getDireccion(), row, 6);
				defaultTableModel.setValueAt(funcionario.getSexo(), row, 7);
				defaultTableModel.setValueAt(funcionario.getEstadoCivil(), row, 8);
				defaultTableModel.setValueAt(funcionario.getFechaNacimiento(), row, 9);
				defaultTableModel.setValueAt(funcionario.getFechaCreacion().format(formatter), row, 10);
				defaultTableModel.setValueAt(funcionario.getFechaActualizacion().format(formatter), row, 11);
				row++;
				
				comboBoxFuncionarios.addItem(funcionario);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addListener() {
		comboBoxFuncionarios.addItemListener(event -> {
			Funcionario funcionarioSeleccionado = (Funcionario) event.getItem();
			if(funcionarioSeleccionado.getNombres().equals(SELECCIONE)) {
				formattedTextFieldFechaNacimientoEdit.setValue("");
				textFieldNumeroDocumentoEdit.setText("");
				textFieldNombreEdit.setText("");
				textFieldApellidoEdit.setText("");
				textFieldTelefonoEdit.setText("");
				textFieldDireccionEdit.setText("");
				comboBoxTipoDocumentoEdit.setSelectedIndex(-1);
				comboBoxSexoEdit.setSelectedIndex(-1);
				comboBoxEstadoCivilEdit.setSelectedIndex(-1);
			}else {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				formattedTextFieldFechaNacimientoEdit.setText(formatter.format(funcionarioSeleccionado.getFechaNacimiento()));    
				formattedTextFieldFechaNacimientoEdit.setValue(funcionarioSeleccionado.getFechaNacimiento());
				comboBoxTipoDocumentoEdit.setSelectedItem(funcionarioSeleccionado.getTipoDocumento());
				comboBoxEstadoCivilEdit.setSelectedItem(funcionarioSeleccionado.getEstadoCivil());
				comboBoxSexoEdit.setSelectedItem(funcionarioSeleccionado.getSexo());
				textFieldNumeroDocumentoEdit.setText(String.valueOf(funcionarioSeleccionado.getNumeroIdentificacion()));
				textFieldNombreEdit.setText(String.valueOf(funcionarioSeleccionado.getNombres()));
				textFieldApellidoEdit.setText(String.valueOf(funcionarioSeleccionado.getApellidos()));
				textFieldTelefonoEdit.setText(String.valueOf(funcionarioSeleccionado.getTelefono()));
				textFieldDireccionEdit.setText(String.valueOf(funcionarioSeleccionado.getDireccion()));
				System.out.println("Fecha de nacimiento: " + funcionarioSeleccionado.getFechaNacimiento());
			}
		});
	}
	
	public Form() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SOFTWARE PARA LA GESTIÓN DE FUNCIONARIOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(200, 31, 753, 29);
		add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(200, 71, 753, 334);
		add(tabbedPane);
		
		JPanel panelCrearFuncionario = new JPanel();
		tabbedPane.addTab("Crear funcionario", null, panelCrearFuncionario, null);
		panelCrearFuncionario.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Numero de documento");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(138, 86, 174, 14);
		panelCrearFuncionario.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setBounds(138, 142, 63, 14);
		panelCrearFuncionario.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Apellido");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setBounds(346, 25, 63, 20);
		panelCrearFuncionario.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Sexo");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1.setBounds(346, 142, 63, 14);
		panelCrearFuncionario.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Dirección");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1.setBounds(519, 25, 63, 20);
		panelCrearFuncionario.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Teléfono");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1_1.setBounds(519, 86, 63, 14);
		panelCrearFuncionario.add(lblNewLabel_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Fecha de nacimiento");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_2.setBounds(346, 86, 145, 14);
		panelCrearFuncionario.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Estado civil");
		lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_2_1.setBounds(519, 142, 109, 14);
		panelCrearFuncionario.add(lblNewLabel_1_1_2_1);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Tipo documento");
		lblNewLabel_1_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_2_1_1.setBounds(138, 25, 155, 20);
		panelCrearFuncionario.add(lblNewLabel_1_1_2_1_1);
		
		textFieldNroDocumento = new JTextField();
		textFieldNroDocumento.setColumns(10);
		textFieldNroDocumento.setBounds(138, 111, 116, 20);
		panelCrearFuncionario.add(textFieldNroDocumento);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(138, 167, 110, 20);
		panelCrearFuncionario.add(textFieldNombre);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setColumns(10);
		textFieldApellido.setBounds(346, 56, 110, 20);
		panelCrearFuncionario.add(textFieldApellido);
		
		textFieldDireccion = new JTextField();
		textFieldDireccion.setColumns(10);
		textFieldDireccion.setBounds(518, 55, 110, 20);
		panelCrearFuncionario.add(textFieldDireccion);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(518, 111, 110, 20);
		panelCrearFuncionario.add(textFieldTelefono);
		
		JComboBox comboBoxTipoDocumento = new JComboBox();
		comboBoxTipoDocumento.setModel(new DefaultComboBoxModel(new String[] {"Cédula de Ciudadania", "Tarjeta de Identidad", "Pasaporte", "Licencia de Conducir", "Carné de Estudiante"}));
		comboBoxTipoDocumento.setBounds(138, 55, 116, 22);
		panelCrearFuncionario.add(comboBoxTipoDocumento);
		
		JComboBox comboBoxSexo = new JComboBox();
		comboBoxSexo.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		comboBoxSexo.setBounds(346, 167, 100, 22);
		panelCrearFuncionario.add(comboBoxSexo);
		
		JComboBox comboBoxEstadoCivil = new JComboBox();
		comboBoxEstadoCivil.setModel(new DefaultComboBoxModel(new String[] {"Soltero/a", "Casado/a", "Divorciado/a", "Viudo/a", "Unión libre"}));
		comboBoxEstadoCivil.setBounds(519, 167, 109, 22);
		panelCrearFuncionario.add(comboBoxEstadoCivil);
		
		JButton btnCrearFuncionario = new JButton("GUARDAR");
		btnCrearFuncionario.setForeground(new Color(0, 0, 0));
		btnCrearFuncionario.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCrearFuncionario.setBackground(new Color(0, 128, 0));
		btnCrearFuncionario.setBounds(297, 224, 145, 35);
		panelCrearFuncionario.add(btnCrearFuncionario);
		
		btnCrearFuncionario.addActionListener(new ActionListener() {
			 @Override
				 public  void actionPerformed(ActionEvent e) {
				 		Funcionario funcionario = new Funcionario();
				 		funcionario.setNumeroIdentificacion(textFieldNroDocumento.getText().trim());
				 		funcionario.setNombres(textFieldNombre.getText().trim());
				 		funcionario.setApellidos(textFieldApellido.getText().trim());
				 		funcionario.setDireccion(textFieldDireccion.getText().trim());
				 		funcionario.setTelefono(textFieldTelefono.getText().trim());
				 		funcionario.setFechaNacimiento(java.sql.Date.valueOf("1999-03-03"));
				 		funcionario.setSexo(comboBoxSexo.getSelectedItem().toString().trim());
				 		funcionario.setEstadoCivil(comboBoxEstadoCivil.getSelectedItem().toString().trim());
				 		funcionario.setTipoDocumento(comboBoxTipoDocumento.getSelectedItem().toString().trim());

				 		try {
							funcionarioController.crearFuncionario(funcionario);
							textFieldNroDocumento.setText("");
							textFieldNombre.setText("");
							textFieldApellido.setText("");
							textFieldDireccion.setText("");
							textFieldTelefono.setText("");
							formattedTextFieldFechaNacimiento.setText("");
							comboBoxTipoDocumento.setSelectedIndex(-1);
							comboBoxSexo.setSelectedIndex(-1);
							comboBoxEstadoCivil.setSelectedIndex(-1);
							listarFuncuinarios();
							JOptionPane.showMessageDialog(null, "Funcionario creado con éxito");
							
						} catch (SQLException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "No fué posible crear el funcionario");
						}
	
				 
				        String numeroDocumento = textFieldNroDocumento.getText();
				        String nombre = textFieldNombre.getText();
				        String apellido = textFieldApellido.getText();
				        String direccion = textFieldDireccion.getText();
				        String telefono = textFieldTelefono.getText();
				        String sexo = (String) comboBoxSexo.getSelectedItem();
				        String estadoCivil = (String) comboBoxEstadoCivil.getSelectedItem();
				        String tipoDocumento = (String) comboBoxTipoDocumento.getSelectedItem();
				        Date fechaNacimiento = null;
				        
				        // Convertir la fecha de texto a tipo Date si es necesario
				        try {
				            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				            fechaNacimiento = new Date(formatter.parse(formattedTextFieldFechaNacimiento.getText()).getTime());
				        } catch (ParseException ex) {
				            ex.printStackTrace();
				            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use dd/MM/yyyy");
				            return;
				        }

				        // Aquí puedes agregar la lógica para guardar el funcionario
				        Funcionario nuevoFuncionario = new Funcionario();
				        nuevoFuncionario.setNumeroIdentificacion(numeroDocumento);
				        nuevoFuncionario.setNombres(nombre);
				        nuevoFuncionario.setApellidos(apellido);
				        nuevoFuncionario.setDireccion(direccion);
				        nuevoFuncionario.setTelefono(telefono);
				        nuevoFuncionario.setSexo(sexo);
				        nuevoFuncionario.setEstadoCivil(estadoCivil);
				        nuevoFuncionario.setTipoDocumento(tipoDocumento);
				        nuevoFuncionario.setFechaNacimiento(fechaNacimiento);

				        // Aquí llamas a un método del controlador para guardar el nuevo funcionario
				        try {
				            funcionarioController.crearFuncionario(nuevoFuncionario);
				            JOptionPane.showMessageDialog(null, "Funcionario guardado exitosamente");
				            // Llamar a un método para refrescar la tabla o limpiar campos
				            listarFuncuinarios();
				        } catch (SQLException ex) {
				            ex.printStackTrace();
				            JOptionPane.showMessageDialog(null, "Error al guardar el funcionario");
				        }
				    }
				});
			 
			 
		JPanel panelEditarFuncionario = new JPanel();
		tabbedPane.addTab("Editar funcionario", null, panelEditarFuncionario, null);
		panelEditarFuncionario.setLayout(null);
		
		JLabel lblNewLabel_1_1_2_1_1_1 = new JLabel("Tipo documento");
		lblNewLabel_1_1_2_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2_1_1_1.setBounds(96, 68, 155, 20);
		panelEditarFuncionario.add(lblNewLabel_1_1_2_1_1_1);
		
		comboBoxTipoDocumentoEdit = new JComboBox();
		comboBoxTipoDocumentoEdit.setModel(new DefaultComboBoxModel(new String[] {"Cédula de Ciudadania", "Tarjeta de Identidad", "Pasaporte", "Licencia de Conducir", "Carné de Estudiante"}));
		comboBoxTipoDocumentoEdit.setBounds(96, 98, 116, 22);
		panelEditarFuncionario.add(comboBoxTipoDocumentoEdit);
		
		textFieldNumeroDocumentoEdit = new JTextField();
		textFieldNumeroDocumentoEdit.setColumns(10);
		textFieldNumeroDocumentoEdit.setBounds(96, 154, 116, 20);
		panelEditarFuncionario.add(textFieldNumeroDocumentoEdit);
		
		JLabel lblNewLabel_1_2 = new JLabel("Numero de documento");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(96, 129, 174, 14);
		panelEditarFuncionario.add(lblNewLabel_1_2);
		
		textFieldNombreEdit = new JTextField();
		textFieldNombreEdit.setColumns(10);
		textFieldNombreEdit.setBounds(96, 210, 110, 20);
		panelEditarFuncionario.add(textFieldNombreEdit);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Nombre");
		lblNewLabel_1_1_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_3.setBounds(96, 185, 63, 14);
		panelEditarFuncionario.add(lblNewLabel_1_1_3);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Apellido");
		lblNewLabel_1_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2.setBounds(304, 68, 63, 20);
		panelEditarFuncionario.add(lblNewLabel_1_1_1_2);
		
		textFieldApellidoEdit = new JTextField();
		textFieldApellidoEdit.setColumns(10);
		textFieldApellidoEdit.setBounds(304, 99, 110, 20);
		panelEditarFuncionario.add(textFieldApellidoEdit);
		
		JLabel lblNewLabel_1_1_2_2 = new JLabel("Fecha de nacimiento");
		lblNewLabel_1_1_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2_2.setBounds(304, 129, 145, 14);
		panelEditarFuncionario.add(lblNewLabel_1_1_2_2);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Sexo");
		lblNewLabel_1_1_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1_2.setBounds(304, 185, 63, 14);
		panelEditarFuncionario.add(lblNewLabel_1_1_1_1_2);
		
		comboBoxSexoEdit = new JComboBox();
		comboBoxSexoEdit.setModel(new DefaultComboBoxModel(new String[] {"M", "F"}));
		comboBoxSexoEdit.setBounds(304, 210, 100, 22);
		panelEditarFuncionario.add(comboBoxSexoEdit);
		
		comboBoxEstadoCivilEdit = new JComboBox();
		comboBoxEstadoCivilEdit.setModel(new DefaultComboBoxModel(new String[] {"Soltero/a", "Casado/a", "Divorciado/a", "Viudo/a", "Unión libre"}));
		comboBoxEstadoCivilEdit.setBounds(477, 210, 109, 22);
		panelEditarFuncionario.add(comboBoxEstadoCivilEdit);
		
		JLabel lblNewLabel_1_1_2_1_2 = new JLabel("Estado civil");
		lblNewLabel_1_1_2_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2_1_2.setBounds(477, 185, 109, 14);
		panelEditarFuncionario.add(lblNewLabel_1_1_2_1_2);
		
		textFieldTelefonoEdit = new JTextField();
		textFieldTelefonoEdit.setColumns(10);
		textFieldTelefonoEdit.setBounds(476, 154, 110, 20);
		panelEditarFuncionario.add(textFieldTelefonoEdit);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Teléfono");
		lblNewLabel_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(477, 129, 63, 14);
		panelEditarFuncionario.add(lblNewLabel_1_1_1_1_1_1_1);
		
		textFieldDireccionEdit = new JTextField();
		textFieldDireccionEdit.setColumns(10);
		textFieldDireccionEdit.setBounds(476, 98, 110, 20);
		panelEditarFuncionario.add(textFieldDireccionEdit);
		
		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("Dirección");
		lblNewLabel_1_1_1_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1_1_2.setBounds(477, 68, 63, 20);
		panelEditarFuncionario.add(lblNewLabel_1_1_1_1_1_2);
		
		JLabel lblNewLabel_1_1_2_1_1_1_1 = new JLabel("Seleccione un funcionario");
		lblNewLabel_1_1_2_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_2_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2_1_1_1_1.setBounds(126, 24, 191, 20);
		panelEditarFuncionario.add(lblNewLabel_1_1_2_1_1_1_1);
		
		JButton btnActualizarFuncionario = new JButton("ACTUALIZAR");
		btnActualizarFuncionario.setForeground(new Color(0, 0, 0));
		btnActualizarFuncionario.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnActualizarFuncionario.setBackground(new Color(0, 128, 0));
		btnActualizarFuncionario.setBounds(188, 260, 145, 35);
		panelEditarFuncionario.add(btnActualizarFuncionario);
		
		JButton btnEliminarFuncionario = new JButton("ELIMINAR");
		btnEliminarFuncionario.setForeground(new Color(0, 0, 0));
		btnEliminarFuncionario.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEliminarFuncionario.setBackground(new Color(202, 0, 0));
		btnEliminarFuncionario.setBounds(360, 260, 145, 35);
		panelEditarFuncionario.add(btnEliminarFuncionario);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 416, 1104, 214);
		add(scrollPane);
		
        tableModel = new DefaultTableModel(null, COLUMNS);
        tableFuncionarios = new JTable(tableModel);
        tableFuncionarios.setRowSelectionAllowed(false);
        tableFuncionarios.setEnabled(false);
        tableFuncionarios.setFillsViewportHeight(true);
        tableFuncionarios.setRowHeight(40);
        scrollPane.setViewportView(tableFuncionarios);
        
		
		comboBoxFuncionarios = new JComboBox<>();
		comboBoxFuncionarios.setBounds(329, 25, 211, 22);
		panelEditarFuncionario.add(comboBoxFuncionarios);
		
		
		formattedTextFieldFechaNacimientoEdit = new JFormattedTextField();
		formattedTextFieldFechaNacimientoEdit.setBounds(304, 154, 110, 20);
		panelEditarFuncionario.add(formattedTextFieldFechaNacimientoEdit);
		    
		
        JFormattedTextField formattedTextField = null;
        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('_');
            formattedTextFieldFechaNacimiento = new JFormattedTextField(dateMask);
    		formattedTextFieldFechaNacimiento.setText("01/01/2000");
    		formattedTextFieldFechaNacimiento.setBounds(346, 111, 110, 20);
    		panelCrearFuncionario.add(formattedTextFieldFechaNacimiento);
    		
        } catch (ParseException e) {
            e.printStackTrace();
            formattedTextField = new JFormattedTextField();
            formattedTextField.setBounds(248, 97, 110, 20);
            panelCrearFuncionario.add(formattedTextField);
        }
		funcionarioController = new FuncionarioController();
		listarFuncuinarios();
		TableUtils.ajustarAnchoColumnasPersonalizado(tableFuncionarios);
		addListener();
	}
}

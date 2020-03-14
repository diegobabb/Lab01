/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.Controller;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import models.Curso;
import models.Model;
import models.Profesor;
import models.TableCursos;
import models.TableProfesor;

/**
 *
 * @author Diego Babb
 */
public final class View extends javax.swing.JFrame implements Observer {

    private final Model model;
    private TableRowSorter<TableCursos> sorterCursos;
    private TableRowSorter<TableProfesor> sorterProfesores;

    /**
     * Creates new form View
     *
     * @param model
     */
    public View(Model model) {
        this.model = model;
        this.initComponents();
        this.initHeader();
        this.FormProfesor.setVisible(false);
        this.FormCurso.setVisible(false);
        this.setTitle("Lab01");
        this.TableProfesorFilter();
        this.TableCursoFilter();
        this.tableCursor.getTableHeader().setReorderingAllowed(false); // not allow re-ordering of columns
        this.tableProfesor.getTableHeader().setResizingAllowed(false);
        this.setLocationRelativeTo(null);
    }

    private void newFilterCurso() {
        RowFilter<TableCursos, Object> rf = null;
        try {
            rf = RowFilter.regexFilter(filterTextCurso.getText(), filterComboBoxCurso.getSelectedIndex());
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorterCursos.setRowFilter(rf);
    }

    public void TableCursoFilter() {
        filterTextCurso.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                newFilterCurso();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                newFilterCurso();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                newFilterCurso();
            }
        });

        sorterCursos = new TableRowSorter<TableCursos>(this.model.getTableCursos());
        this.tableCursor.setRowSorter(sorterCursos);
        tableCursor.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int viewRow = tableCursor.getSelectedRow();
                if (viewRow < 0) {
                    System.out.println();
                } else {
                    int modelRow
                            = tableCursor.convertRowIndexToModel(viewRow);
                    System.out.println(
                            String.format("Selected Row in view: %d. "
                                    + "Selected Row in model: %d.",
                                    viewRow, modelRow));
                }
            }
        }
        );

    }

    private void newFilterProfesor() {
        RowFilter<TableProfesor, Object> rf = null;
        try {
            rf = RowFilter.regexFilter(filterTextProfesor.getText(), filterComboBoxProfesor.getSelectedIndex());
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorterProfesores.setRowFilter(rf);
    }

    public void TableProfesorFilter() {
        filterTextProfesor.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                newFilterProfesor();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                newFilterProfesor();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                newFilterProfesor();
            }
        });

        sorterProfesores = new TableRowSorter<TableProfesor>(this.model.getTableProfesores());
        this.tableProfesor.setRowSorter(sorterProfesores);
        tableProfesor.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int viewRow = tableProfesor.getSelectedRow();
                if (viewRow < 0) {
                    System.out.println();
                } else {
                    int modelRow
                            = tableProfesor.convertRowIndexToModel(viewRow);
                    System.out.println(
                            String.format("Selected Row in view: %d. "
                                    + "Selected Row in model: %d.",
                                    viewRow, modelRow));
                }
            }
        }
        );

    }

    void initHeader() {
        JTableHeader tableProfesoresHeader = this.tableProfesor.getTableHeader();
        JTableHeader tableCursorHeader = this.tableCursor.getTableHeader();
        tableProfesoresHeader.setBackground(new java.awt.Color(3, 181, 170));
        tableProfesoresHeader.setFont(new java.awt.Font("Segoe UI", 0, 15));
        tableProfesoresHeader.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tableProfesoresHeader.setForeground(new java.awt.Color(255, 255, 255));
        tableCursorHeader.setBackground(new java.awt.Color(3, 181, 170));
        tableCursorHeader.setFont(new java.awt.Font("Segoe UI", 0, 15));
        tableCursorHeader.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tableCursorHeader.setForeground(new java.awt.Color(255, 255, 255));
    }

    public void addListeners(Controller aThis) {
        this.guardarProfesor.addActionListener(aThis);
        this.eliminarProfesor.addActionListener(aThis);
        this.guardarCambiosProfesor.addActionListener(aThis);
        this.guardarCurso.addActionListener(aThis);
        this.guardarCambiosCurso.addActionListener(aThis);
        this.eliminarCurso.addActionListener(aThis);
        this.logoutButton.addActionListener(aThis);
        this.refreshButton.addActionListener(aThis);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.tableProfesor.setModel(model.getTableProfesores());
        this.tableCursor.setModel(model.getTableCursos());
        this.sorterProfesores = new TableRowSorter<TableProfesor>(this.model.getTableProfesores());
        this.tableProfesor.setRowSorter(sorterProfesores);
        this.sorterCursos = new TableRowSorter<TableCursos>(this.model.getTableCursos());
        this.tableCursor.setRowSorter(sorterCursos);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        profesorTablePopMenu = new javax.swing.JPopupMenu();
        eliminarProfesor = new javax.swing.JMenuItem();
        editarProfesor = new javax.swing.JMenuItem();
        cursosTablePopMenu = new javax.swing.JPopupMenu();
        eliminarCurso = new javax.swing.JMenuItem();
        editarCurso = new javax.swing.JMenuItem();
        Principal = new javax.swing.JPanel();
        encabezado = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        cursosTab = new javax.swing.JLabel();
        profesoresTab = new javax.swing.JLabel();
        SubTitulo = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        contenedorTable = new javax.swing.JPanel();
        contenedorCursos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCursor = new javax.swing.JTable();
        agregarCurso = new javax.swing.JButton();
        filterComboBoxCurso = new javax.swing.JComboBox<>();
        filterTextCurso = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        contenedorProfesores = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProfesor = new javax.swing.JTable();
        agregarProfesor = new javax.swing.JButton();
        filterTextProfesor = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        filterComboBoxProfesor = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        FormProfesor = new javax.swing.JPanel();
        cancerlarProfesor = new javax.swing.JButton();
        guardarProfesor = new javax.swing.JButton();
        textNombre = new javax.swing.JTextField();
        textCedula = new javax.swing.JTextField();
        textEmail = new javax.swing.JTextField();
        textTelefono = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        guardarCambiosProfesor = new javax.swing.JButton();
        comboBoxCurso = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        FormCurso = new javax.swing.JPanel();
        cancerlarCurso = new javax.swing.JButton();
        guardarCurso = new javax.swing.JButton();
        textNombreCurso = new javax.swing.JTextField();
        textCodigo = new javax.swing.JTextField();
        textCreditos = new javax.swing.JTextField();
        textHorasSemanales = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        guardarCambiosCurso = new javax.swing.JButton();
        comboBoxCarrera = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();

        profesorTablePopMenu.setBackground(new java.awt.Color(255, 255, 255));
        profesorTablePopMenu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        profesorTablePopMenu.setForeground(new java.awt.Color(2, 116, 109));
        profesorTablePopMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(2, 116, 109)));

        eliminarProfesor.setBackground(new java.awt.Color(255, 255, 255));
        eliminarProfesor.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        eliminarProfesor.setForeground(new java.awt.Color(2, 116, 109));
        eliminarProfesor.setText("Eliminar profesor");
        eliminarProfesor.setBorder(null);
        profesorTablePopMenu.add(eliminarProfesor);

        editarProfesor.setBackground(new java.awt.Color(255, 255, 255));
        editarProfesor.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        editarProfesor.setForeground(new java.awt.Color(2, 116, 109));
        editarProfesor.setText("Editar profesor");
        editarProfesor.setBorder(null);
        editarProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarProfesorActionPerformed(evt);
            }
        });
        profesorTablePopMenu.add(editarProfesor);

        cursosTablePopMenu.setBackground(new java.awt.Color(255, 255, 255));
        cursosTablePopMenu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cursosTablePopMenu.setForeground(new java.awt.Color(2, 116, 109));
        cursosTablePopMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(2, 116, 109)));

        eliminarCurso.setBackground(new java.awt.Color(255, 255, 255));
        eliminarCurso.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        eliminarCurso.setForeground(new java.awt.Color(2, 116, 109));
        eliminarCurso.setText("Eliminar curso");
        eliminarCurso.setBorder(null);
        cursosTablePopMenu.add(eliminarCurso);

        editarCurso.setBackground(new java.awt.Color(255, 255, 255));
        editarCurso.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        editarCurso.setForeground(new java.awt.Color(2, 116, 109));
        editarCurso.setText("Editar curso");
        editarCurso.setBorder(null);
        editarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarCursoActionPerformed(evt);
            }
        });
        cursosTablePopMenu.add(editarCurso);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        Principal.setBackground(new java.awt.Color(255, 255, 255));
        Principal.setForeground(new java.awt.Color(255, 255, 255));

        encabezado.setBackground(new java.awt.Color(255, 255, 255));
        encabezado.setForeground(new java.awt.Color(255, 255, 255));

        titulo.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        titulo.setForeground(new java.awt.Color(3, 181, 170));
        titulo.setText("Lab 01");

        cursosTab.setBackground(new java.awt.Color(2, 116, 109));
        cursosTab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cursosTab.setForeground(new java.awt.Color(255, 255, 255));
        cursosTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cursosTab.setText("Cursos");
        cursosTab.setOpaque(true);
        cursosTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cursosTabMouseClicked(evt);
            }
        });

        profesoresTab.setBackground(new java.awt.Color(3, 181, 170));
        profesoresTab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        profesoresTab.setForeground(new java.awt.Color(255, 255, 255));
        profesoresTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profesoresTab.setText("Profesores");
        profesoresTab.setOpaque(true);
        profesoresTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profesoresTabMouseClicked(evt);
            }
        });

        SubTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        SubTitulo.setForeground(new java.awt.Color(3, 181, 170));
        SubTitulo.setText("Desktop");

        logoutButton.setBackground(new java.awt.Color(255, 255, 255));
        logoutButton.setForeground(new java.awt.Color(255, 255, 255));
        logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/exit.png"))); // NOI18N
        logoutButton.setActionCommand("logout");
        logoutButton.setBorder(null);

        refreshButton.setBackground(new java.awt.Color(255, 255, 255));
        refreshButton.setForeground(new java.awt.Color(255, 255, 255));
        refreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/refresh.png"))); // NOI18N
        refreshButton.setActionCommand("refresh");
        refreshButton.setBorder(null);

        javax.swing.GroupLayout encabezadoLayout = new javax.swing.GroupLayout(encabezado);
        encabezado.setLayout(encabezadoLayout);
        encabezadoLayout.setHorizontalGroup(
            encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(encabezadoLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(encabezadoLayout.createSequentialGroup()
                        .addComponent(titulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SubTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logoutButton)
                        .addGap(19, 19, 19))
                    .addGroup(encabezadoLayout.createSequentialGroup()
                        .addComponent(cursosTab, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(profesoresTab, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(refreshButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        encabezadoLayout.setVerticalGroup(
            encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(encabezadoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(titulo)
                        .addComponent(SubTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(logoutButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(refreshButton)
                    .addGroup(encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(profesoresTab, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cursosTab, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        contenedorTable.setBackground(new java.awt.Color(255, 255, 255));
        contenedorTable.setForeground(new java.awt.Color(255, 255, 255));
        contenedorTable.setLayout(new java.awt.CardLayout());

        contenedorCursos.setBackground(new java.awt.Color(255, 255, 255));
        contenedorCursos.setForeground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));

        tableCursor.setBackground(new java.awt.Color(255, 255, 255));
        tableCursor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tableCursor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableCursor.setForeground(new java.awt.Color(2, 116, 109));
        tableCursor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableCursor.setComponentPopupMenu(cursosTablePopMenu);
        tableCursor.setGridColor(new java.awt.Color(200, 224, 240));
        tableCursor.setRowHeight(30);
        tableCursor.setSelectionBackground(new java.awt.Color(2, 116, 109));
        tableCursor.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tableCursor.setShowGrid(false);
        tableCursor.setShowHorizontalLines(true);
        jScrollPane2.setViewportView(tableCursor);

        agregarCurso.setBackground(new java.awt.Color(255, 255, 255));
        agregarCurso.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        agregarCurso.setForeground(new java.awt.Color(3, 181, 170));
        agregarCurso.setText("+");
        agregarCurso.setToolTipText("");
        agregarCurso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        agregarCurso.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agregarCurso.setName(""); // NOI18N
        agregarCurso.setOpaque(false);
        agregarCurso.setRequestFocusEnabled(false);
        agregarCurso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agregarCursoMouseClicked(evt);
            }
        });

        filterComboBoxCurso.setBackground(new java.awt.Color(255, 255, 255));
        filterComboBoxCurso.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        filterComboBoxCurso.setForeground(new java.awt.Color(2, 116, 109));
        filterComboBoxCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Codigo", "Nombre", "Creditos", "Horas", "Carrera" }));
        filterComboBoxCurso.setBorder(null);

        filterTextCurso.setBackground(new java.awt.Color(255, 255, 255));
        filterTextCurso.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        filterTextCurso.setForeground(new java.awt.Color(2, 116, 109));
        filterTextCurso.setBorder(null);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(2, 116, 109));
        jLabel14.setText("Filtro");

        javax.swing.GroupLayout contenedorCursosLayout = new javax.swing.GroupLayout(contenedorCursos);
        contenedorCursos.setLayout(contenedorCursosLayout);
        contenedorCursosLayout.setHorizontalGroup(
            contenedorCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorCursosLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(contenedorCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contenedorCursosLayout.createSequentialGroup()
                        .addComponent(filterComboBoxCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(contenedorCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(filterTextCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(agregarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(contenedorCursosLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
                .addGap(81, 81, 81))
        );
        contenedorCursosLayout.setVerticalGroup(
            contenedorCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorCursosLayout.createSequentialGroup()
                .addGroup(contenedorCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(contenedorCursosLayout.createSequentialGroup()
                        .addComponent(filterTextCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(agregarCurso)
                    .addGroup(contenedorCursosLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterComboBoxCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
        );

        contenedorTable.add(contenedorCursos, "card3");

        contenedorProfesores.setBackground(new java.awt.Color(255, 255, 255));
        contenedorProfesores.setForeground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        tableProfesor.setBackground(new java.awt.Color(255, 255, 255));
        tableProfesor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tableProfesor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableProfesor.setForeground(new java.awt.Color(2, 116, 109));
        tableProfesor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableProfesor.setComponentPopupMenu(profesorTablePopMenu);
        tableProfesor.setGridColor(new java.awt.Color(200, 224, 240));
        tableProfesor.setRowHeight(30);
        tableProfesor.setSelectionBackground(new java.awt.Color(2, 116, 109));
        tableProfesor.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tableProfesor.setShowGrid(false);
        tableProfesor.setShowHorizontalLines(true);
        jScrollPane1.setViewportView(tableProfesor);

        agregarProfesor.setBackground(new java.awt.Color(255, 255, 255));
        agregarProfesor.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        agregarProfesor.setForeground(new java.awt.Color(3, 181, 170));
        agregarProfesor.setText("+");
        agregarProfesor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        agregarProfesor.setOpaque(false);
        agregarProfesor.setRequestFocusEnabled(false);
        agregarProfesor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agregarProfesorMouseClicked(evt);
            }
        });

        filterTextProfesor.setBackground(new java.awt.Color(255, 255, 255));
        filterTextProfesor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        filterTextProfesor.setForeground(new java.awt.Color(2, 116, 109));
        filterTextProfesor.setBorder(null);

        filterComboBoxProfesor.setBackground(new java.awt.Color(255, 255, 255));
        filterComboBoxProfesor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        filterComboBoxProfesor.setForeground(new java.awt.Color(2, 116, 109));
        filterComboBoxProfesor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cedula", "Nombre", "Telefono", "Email" }));
        filterComboBoxProfesor.setBorder(null);

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(2, 116, 109));
        jLabel13.setText("Filtro");

        javax.swing.GroupLayout contenedorProfesoresLayout = new javax.swing.GroupLayout(contenedorProfesores);
        contenedorProfesores.setLayout(contenedorProfesoresLayout);
        contenedorProfesoresLayout.setHorizontalGroup(
            contenedorProfesoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorProfesoresLayout.createSequentialGroup()
                .addGroup(contenedorProfesoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contenedorProfesoresLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(contenedorProfesoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contenedorProfesoresLayout.createSequentialGroup()
                                .addComponent(filterComboBoxProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contenedorProfesoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(filterTextProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
                        .addComponent(agregarProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        contenedorProfesoresLayout.setVerticalGroup(
            contenedorProfesoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorProfesoresLayout.createSequentialGroup()
                .addGroup(contenedorProfesoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(agregarProfesor)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorProfesoresLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(contenedorProfesoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(filterComboBoxProfesor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorProfesoresLayout.createSequentialGroup()
                                .addComponent(filterTextProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        contenedorTable.add(contenedorProfesores, "card2");

        javax.swing.GroupLayout PrincipalLayout = new javax.swing.GroupLayout(Principal);
        Principal.setLayout(PrincipalLayout);
        PrincipalLayout.setHorizontalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(encabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(contenedorTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PrincipalLayout.setVerticalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addComponent(encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contenedorTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        FormProfesor.setBackground(new java.awt.Color(255, 255, 255));
        FormProfesor.setForeground(new java.awt.Color(255, 255, 255));

        cancerlarProfesor.setBackground(new java.awt.Color(255, 255, 255));
        cancerlarProfesor.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        cancerlarProfesor.setForeground(new java.awt.Color(234, 82, 111));
        cancerlarProfesor.setText("Cancelar");
        cancerlarProfesor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        cancerlarProfesor.setOpaque(false);
        cancerlarProfesor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancerlarProfesorMouseClicked(evt);
            }
        });

        guardarProfesor.setBackground(new java.awt.Color(255, 255, 255));
        guardarProfesor.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        guardarProfesor.setForeground(new java.awt.Color(57, 115, 103));
        guardarProfesor.setText("Guardar profesor");
        guardarProfesor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        guardarProfesor.setRequestFocusEnabled(false);

        textNombre.setBackground(new java.awt.Color(255, 255, 255));
        textNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textNombre.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textNombre.setBorder(null);
        textNombre.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textNombre.setOpaque(false);
        textNombre.setSelectionColor(new java.awt.Color(3, 181, 170));

        textCedula.setBackground(new java.awt.Color(255, 255, 255));
        textCedula.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textCedula.setBorder(null);

        textEmail.setBackground(new java.awt.Color(255, 255, 255));
        textEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textEmail.setBorder(null);

        textTelefono.setBackground(new java.awt.Color(255, 255, 255));
        textTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textTelefono.setBorder(null);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(3, 181, 170));
        jLabel1.setText("Datos de nuevo Profesor");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(2, 116, 109));
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(2, 116, 109));
        jLabel3.setText("Email");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(2, 116, 109));
        jLabel4.setText("Cedula");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(2, 116, 109));
        jLabel5.setText("Telefono");

        guardarCambiosProfesor.setBackground(new java.awt.Color(255, 255, 255));
        guardarCambiosProfesor.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        guardarCambiosProfesor.setForeground(new java.awt.Color(57, 115, 103));
        guardarCambiosProfesor.setText("Guardar cambios");
        guardarCambiosProfesor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        guardarCambiosProfesor.setRequestFocusEnabled(false);

        comboBoxCurso.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxCurso.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        comboBoxCurso.setForeground(new java.awt.Color(3, 181, 170));
        comboBoxCurso.setBorder(null);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(2, 116, 109));
        jLabel6.setText("Grupo");

        javax.swing.GroupLayout FormProfesorLayout = new javax.swing.GroupLayout(FormProfesor);
        FormProfesor.setLayout(FormProfesorLayout);
        FormProfesorLayout.setHorizontalGroup(
            FormProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FormProfesorLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(FormProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FormProfesorLayout.createSequentialGroup()
                        .addGroup(FormProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(textTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                            .addComponent(jSeparator8)
                            .addComponent(textEmail)
                            .addComponent(jSeparator7)
                            .addComponent(textCedula)
                            .addComponent(jSeparator9))
                        .addGap(50, 50, 50)
                        .addComponent(comboBoxCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(FormProfesorLayout.createSequentialGroup()
                        .addGroup(FormProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FormProfesorLayout.createSequentialGroup()
                                .addGroup(FormProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4)
                                    .addComponent(textNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                                    .addComponent(jSeparator6))
                                .addGap(50, 50, 50)
                                .addComponent(jLabel6))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FormProfesorLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(65, 65, 65)
                                .addComponent(guardarProfesor)
                                .addGap(32, 32, 32)
                                .addComponent(cancerlarProfesor))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FormProfesorLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(guardarCambiosProfesor)))
                        .addGap(0, 42, Short.MAX_VALUE))))
        );
        FormProfesorLayout.setVerticalGroup(
            FormProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FormProfesorLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(FormProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(guardarProfesor)
                    .addComponent(cancerlarProfesor))
                .addGap(16, 16, 16)
                .addGroup(FormProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(guardarCambiosProfesor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FormProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FormProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        FormCurso.setBackground(new java.awt.Color(255, 255, 255));
        FormCurso.setForeground(new java.awt.Color(255, 255, 255));

        cancerlarCurso.setBackground(new java.awt.Color(255, 255, 255));
        cancerlarCurso.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        cancerlarCurso.setForeground(new java.awt.Color(234, 82, 111));
        cancerlarCurso.setText("Cancelar");
        cancerlarCurso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        cancerlarCurso.setOpaque(false);
        cancerlarCurso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancerlarCursoMouseClicked(evt);
            }
        });

        guardarCurso.setBackground(new java.awt.Color(255, 255, 255));
        guardarCurso.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        guardarCurso.setForeground(new java.awt.Color(57, 115, 103));
        guardarCurso.setText("Guardar curso");
        guardarCurso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        guardarCurso.setRequestFocusEnabled(false);

        textNombreCurso.setBackground(new java.awt.Color(255, 255, 255));
        textNombreCurso.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textNombreCurso.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textNombreCurso.setBorder(null);
        textNombreCurso.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textNombreCurso.setOpaque(false);
        textNombreCurso.setSelectionColor(new java.awt.Color(3, 181, 170));

        textCodigo.setBackground(new java.awt.Color(255, 255, 255));
        textCodigo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textCodigo.setBorder(null);

        textCreditos.setBackground(new java.awt.Color(255, 255, 255));
        textCreditos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textCreditos.setBorder(null);

        textHorasSemanales.setBackground(new java.awt.Color(255, 255, 255));
        textHorasSemanales.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textHorasSemanales.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(3, 181, 170));
        jLabel7.setText("Datos de nuevo Curso");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(2, 116, 109));
        jLabel8.setText("Nombre");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(2, 116, 109));
        jLabel9.setText("Creditos");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(2, 116, 109));
        jLabel10.setText("Codigo");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(2, 116, 109));
        jLabel11.setText("Horas Semanales");

        guardarCambiosCurso.setBackground(new java.awt.Color(255, 255, 255));
        guardarCambiosCurso.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        guardarCambiosCurso.setForeground(new java.awt.Color(57, 115, 103));
        guardarCambiosCurso.setText("Guardar cambio");
        guardarCambiosCurso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        guardarCambiosCurso.setRequestFocusEnabled(false);

        comboBoxCarrera.setBackground(new java.awt.Color(255, 255, 255));
        comboBoxCarrera.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        comboBoxCarrera.setForeground(new java.awt.Color(2, 116, 109));
        comboBoxCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ingenieria en Sistemas", "Administracion de Empresas", "Matematicas", "Educacion" }));
        comboBoxCarrera.setBorder(null);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(2, 116, 109));
        jLabel12.setText("Carrera");

        javax.swing.GroupLayout FormCursoLayout = new javax.swing.GroupLayout(FormCurso);
        FormCurso.setLayout(FormCursoLayout);
        FormCursoLayout.setHorizontalGroup(
            FormCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FormCursoLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(FormCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(FormCursoLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(65, 65, 65)
                        .addComponent(guardarCurso)
                        .addGap(32, 32, 32)
                        .addComponent(cancerlarCurso)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(FormCursoLayout.createSequentialGroup()
                        .addGroup(FormCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FormCursoLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(407, 407, 407))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FormCursoLayout.createSequentialGroup()
                                .addGroup(FormCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel9)
                                    .addComponent(textHorasSemanales, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                    .addComponent(jSeparator12)
                                    .addComponent(textCreditos)
                                    .addComponent(jSeparator11)
                                    .addComponent(textCodigo)
                                    .addComponent(jSeparator10)
                                    .addComponent(textNombreCurso)
                                    .addComponent(jSeparator13))
                                .addGap(63, 63, 63)))
                        .addGroup(FormCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(guardarCambiosCurso)
                            .addGroup(FormCursoLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(FormCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(comboBoxCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(64, Short.MAX_VALUE))))
        );
        FormCursoLayout.setVerticalGroup(
            FormCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FormCursoLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(FormCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(guardarCurso)
                    .addComponent(cancerlarCurso))
                .addGap(16, 16, 16)
                .addGroup(FormCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(guardarCambiosCurso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textNombreCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FormCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FormCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textCreditos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textHorasSemanales, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FormProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FormCurso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FormProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FormCurso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cursosTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cursosTabMouseClicked
        this.setLblColor(this.cursosTab);
        this.resetLblColor(this.profesoresTab);
        this.contenedorProfesores.setVisible(false);
        this.contenedorCursos.setVisible(true);
    }//GEN-LAST:event_cursosTabMouseClicked

    private void profesoresTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profesoresTabMouseClicked
        this.setLblColor(this.profesoresTab);
        this.resetLblColor(this.cursosTab);
        this.contenedorCursos.setVisible(false);
        this.contenedorProfesores.setVisible(true);
    }//GEN-LAST:event_profesoresTabMouseClicked

    private void cancerlarProfesorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancerlarProfesorMouseClicked
        this.volverAPrincipal();
        this.limpiarEspaciosPanelAgregarProfesor();
    }//GEN-LAST:event_cancerlarProfesorMouseClicked

    private void editarProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarProfesorActionPerformed
        this.comboBoxCurso.setModel(this.model.getComboBoxCursos());
        this.FormProfesor.setVisible(true);
        this.guardarProfesor.setVisible(false);
        this.guardarCambiosProfesor.setVisible(true);
        this.Principal.setVisible(false);
        Profesor model = this.model.getProfesor(this.getSelectedRowTableProfesores());
        this.textCedula.setText(model.getCedula());
        this.textNombre.setText(model.getNombre());
        this.textEmail.setText(model.getEmail());
        this.textTelefono.setText(model.getTelefono());
        this.comboBoxCurso.setSelectedIndex(this.model.getCursoPosition(model.getCurso()));
        this.textCedula.setEditable(false);
    }//GEN-LAST:event_editarProfesorActionPerformed

    private void cancerlarCursoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancerlarCursoMouseClicked
        this.volverAPrincipal();
        this.limpiarEspaciosPanelAgregarCurso();
    }//GEN-LAST:event_cancerlarCursoMouseClicked

    private void agregarCursoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agregarCursoMouseClicked
        this.FormCurso.setVisible(true);
        this.guardarCurso.setVisible(true);
        this.textCodigo.setEditable(true);
        this.guardarCambiosCurso.setVisible(false);
        this.Principal.setVisible(false);
    }//GEN-LAST:event_agregarCursoMouseClicked

    private void editarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarCursoActionPerformed
        this.FormCurso.setVisible(true);
        this.guardarCurso.setVisible(false);
        this.guardarCambiosCurso.setVisible(true);
        this.Principal.setVisible(false);
        Curso model = this.model.getCurso(this.getSelectedRowTableCurso());
        this.textCodigo.setText(model.getCodigo());
        this.textNombreCurso.setText(model.getNombre());
        this.textCreditos.setText(Integer.toString(model.getCredito()));
        this.textHorasSemanales.setText(Integer.toString(model.getHoras()));
        this.comboBoxCarrera.setSelectedIndex(model.getCarrera() - 1);
        this.textCodigo.setEditable(false);
    }//GEN-LAST:event_editarCursoActionPerformed

    private void agregarProfesorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agregarProfesorMouseClicked
        this.comboBoxCurso.setModel(this.model.getComboBoxCursos());
        this.FormProfesor.setVisible(true);
        this.guardarProfesor.setVisible(true);
        this.textCedula.setEditable(true);
        this.guardarCambiosProfesor.setVisible(false);
        this.Principal.setVisible(false);
    }//GEN-LAST:event_agregarProfesorMouseClicked

    private void setLblColor(javax.swing.JLabel l) {
        l.setBackground(new Color(2, 116, 109));
    }

    private void resetLblColor(javax.swing.JLabel l) {
        l.setBackground(new Color(3, 181, 170));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FormCurso;
    private javax.swing.JPanel FormProfesor;
    private javax.swing.JPanel Principal;
    private javax.swing.JLabel SubTitulo;
    private javax.swing.JButton agregarCurso;
    private javax.swing.JButton agregarProfesor;
    private javax.swing.JButton cancerlarCurso;
    private javax.swing.JButton cancerlarProfesor;
    private javax.swing.JComboBox<String> comboBoxCarrera;
    private javax.swing.JComboBox<String> comboBoxCurso;
    private javax.swing.JPanel contenedorCursos;
    private javax.swing.JPanel contenedorProfesores;
    private javax.swing.JPanel contenedorTable;
    private javax.swing.JLabel cursosTab;
    private javax.swing.JPopupMenu cursosTablePopMenu;
    private javax.swing.JMenuItem editarCurso;
    private javax.swing.JMenuItem editarProfesor;
    private javax.swing.JMenuItem eliminarCurso;
    private javax.swing.JMenuItem eliminarProfesor;
    private javax.swing.JPanel encabezado;
    private javax.swing.JComboBox<String> filterComboBoxCurso;
    private javax.swing.JComboBox<String> filterComboBoxProfesor;
    private javax.swing.JTextField filterTextCurso;
    private javax.swing.JTextField filterTextProfesor;
    private javax.swing.JButton guardarCambiosCurso;
    private javax.swing.JButton guardarCambiosProfesor;
    private javax.swing.JButton guardarCurso;
    private javax.swing.JButton guardarProfesor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPopupMenu profesorTablePopMenu;
    private javax.swing.JLabel profesoresTab;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTable tableCursor;
    private javax.swing.JTable tableProfesor;
    private javax.swing.JTextField textCedula;
    private javax.swing.JTextField textCodigo;
    private javax.swing.JTextField textCreditos;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textHorasSemanales;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textNombreCurso;
    private javax.swing.JTextField textTelefono;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables

    public String getTextCedula() {
        return this.textCedula.getText();
    }

    public String getTextEmail() {
        return this.textEmail.getText();
    }

    public String getTextNombre() {
        return this.textNombre.getText();
    }

    public String getTextTelefono() {
        return this.textTelefono.getText();
    }

    public String getTextNombreCurso() {
        return this.textNombreCurso.getText();
    }

    public String getTextCodigo() {
        return this.textCodigo.getText();
    }

    public String getTextHorasSemanales() {
        return this.textHorasSemanales.getText();
    }

    public String getTextCreditos() {
        return this.textCreditos.getText();
    }

    public int getSelectedIndexComboBoxCurso() {
        return this.comboBoxCurso.getSelectedIndex();
    }

    public int getSelectedIndexComboBoxCarrera() {
        return this.comboBoxCarrera.getSelectedIndex() + 1;
    }

    public void limpiarEspaciosPanelAgregarProfesor() {
        this.textCedula.setText("");
        this.textEmail.setText("");
        this.textNombre.setText("");
        this.textTelefono.setText("");
        this.comboBoxCurso.setSelectedIndex(0);
    }

    public void volverAPrincipal() {
        this.Principal.setVisible(true);
        this.FormProfesor.setVisible(false);
        this.FormCurso.setVisible(false);

    }

    public int getSelectedRowTableProfesores() {
        return this.tableProfesor.getSelectedRow();
    }

    public int getSelectedRowTableCurso() {
        return this.tableCursor.getSelectedRow();
    }

    public void limpiarEspaciosPanelAgregarCurso() {
        this.textCodigo.setText("");
        this.textCreditos.setText("");
        this.textNombreCurso.setText("");
        this.textHorasSemanales.setText("");
        this.comboBoxCarrera.setSelectedIndex(0);
    }
}

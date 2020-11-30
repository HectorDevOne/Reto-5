package controlador;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.Profesor;
import modelo.Curso;
import modelo.Estudiante;
import vista.Dashboard;
import vista.IngresoDatos;
import vista.Resultado;




public class Controlador implements ActionListener, ListSelectionListener{
    
    private Dashboard tablero;
    private IngresoDatos admonDatos;
    private Resultado resConsulta;
    private Estudiante est1;
    private int funcion;
    private WindowListener l;

    public Controlador(Dashboard tablero, IngresoDatos admonDatos, Resultado resConsulta) {
        this.tablero = tablero;
        this.admonDatos = admonDatos;
        this.resConsulta = resConsulta;
        this.tablero.jButton1.addActionListener(this);
        this.tablero.jButton2.addActionListener(this);
        this.tablero.jButton3.addActionListener(this);
        this.admonDatos.jButton1.addActionListener(this);
        this.admonDatos.jButton2.addActionListener(this);
        this.admonDatos.jButton3.addActionListener(this);
        this.admonDatos.jButton4.addActionListener(this);
        this.admonDatos.jComboBox1.addActionListener(this);
        this.tablero.jList1.addListSelectionListener(this);
        this.tablero.jList2.addListSelectionListener(this);
        this.tablero.jList3.addListSelectionListener(this);
        
        
        
        
    }
    
    public void iniciar(){
        
        tablero.setLocationRelativeTo(null);
        setNombresComponentes();
        llenarListas();
        tablero.setVisible(true);
        admonDatos.setVisible(false);
        resConsulta.setVisible(false);
        admonDatos.addWindowListener(l);
        
    }
    
    public void llenarListas(){
        
        ArrayList<Estudiante> estudiantes = Estudiante.leer();
        ArrayList<Profesor> profesores = Profesor.leer();
        ArrayList<Curso> cursos = Curso.leer();
        
        DefaultListModel model1 = new DefaultListModel();
        DefaultListModel model2 = new DefaultListModel();
        DefaultListModel model3 = new DefaultListModel();
        
        for (Estudiante e: estudiantes){
            
            model2.addElement(e.getId()+"-"+e.getNombre()); 
            
        }
        tablero.jList2.setModel(model2);
        
        for (Profesor p: profesores){
            model1.addElement(p.getId()+"-"+p.getNombre());
        }
        tablero.jList1.setModel(model1);
        
        for (Curso c: cursos){
            model3.addElement(c.getId()+"-"+c.getNombre());
        }
        tablero.jList3.setModel(model3);
        
    }
    public void limpiarCampos(){
        
        admonDatos.jTextField1.setText("");
        admonDatos.jTextField2.setText("");
        admonDatos.jTextField3.setText("");
        admonDatos.jTextField4.setText("");
        admonDatos.jComboBox1.removeAllItems();
        
        
        
    }
    
    private void setNombresComponentes(){
        tablero.jList1.setName("listaProfesor");
        tablero.jList2.setName("listaEstudiante");
        tablero.jList3.setName("listaCurso");
        
        tablero.jButton1.setName("agregarProfesor");
        tablero.jButton2.setName("agregarEstudiante");
        tablero.jButton3.setName("agregarCurso");
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        //Prepara Agregar Profesor
        if(e.getSource()== tablero.jButton1){
            funcion = 1;
            limpiarCampos();
            admonDatos.setVisible(true);
            admonDatos.jLabel6.setText("Agregar Profesor");
            admonDatos.jLabel1.setText("ID:");
            admonDatos.jLabel2.setText("Nombre: ");
            admonDatos.jLabel3.setText("Correo: ");
            admonDatos.jLabel4.setText("Edad: ");
            admonDatos.jLabel4.setVisible(true);
            admonDatos.jTextField4.setVisible(true);
            admonDatos.jLabel5.setVisible(false);
            admonDatos.jComboBox1.setVisible(false);
            admonDatos.jButton2.setVisible(false);
            admonDatos.jButton3.setVisible(false);
            admonDatos.jButton1.setText("Agregar");
            
        }
        // Crear Profesor
        if (e.getSource() == admonDatos.jButton1 && funcion == 1){
            
            //System.out.println("se ejecuta?");
            String nombre = admonDatos.jTextField2.getText();
            String correo = admonDatos.jTextField3.getText();
            int edad = Integer.parseInt(admonDatos.jTextField4.getText());
            Profesor p = new Profesor(nombre, correo, edad);
            int id = p.Crear();
            admonDatos.jTextField1.setText(""+id);
            
            JOptionPane.showMessageDialog(admonDatos, "Profesor Creado - Id: "+id);
            admonDatos.dispose();
            llenarListas();
            limpiarCampos();
            
        }
        
        if (e.getSource() == tablero.jButton2){
            //Prepara crear Estudiante
            funcion =2;
            limpiarCampos();
            admonDatos.setVisible(true);
            admonDatos.jLabel6.setText("Agregar Estudiante");
            admonDatos.jLabel1.setText("ID:");
            admonDatos.jLabel2.setText("Nombre: ");
            admonDatos.jLabel3.setText("Correo: ");
            admonDatos.jLabel4.setText("Edad: ");
            admonDatos.jLabel4.setVisible(true);
            admonDatos.jTextField4.setVisible(true);
            admonDatos.jLabel5.setVisible(true);
            admonDatos.jLabel5.setText("Curso: ");
            admonDatos.jComboBox1.setVisible(true);
            admonDatos.jButton2.setVisible(false);
            admonDatos.jButton3.setVisible(false);
            admonDatos.jButton1.setText("Agregar");
            
            ArrayList<Curso> c = new ArrayList();
            c = Curso.leer();
            for (Curso cur: c){
                admonDatos.jComboBox1.addItem(cur.getId()+" - "+cur.getNombre());
                
            }
           
        }
        //Crea Estudiante
        if (e.getSource() == admonDatos.jButton1 && funcion == 2){
            
            String nombre = admonDatos.jTextField2.getText();
            String correo = admonDatos.jTextField3.getText();
            int edad = Integer.parseInt(admonDatos.jTextField4.getText());
            
            
            String[] tCurso = admonDatos.jComboBox1.getSelectedItem().toString().split(" ");
            String id_cur = tCurso[0];
            int curso = Integer.parseInt(id_cur);
            
            Estudiante es1 = new Estudiante(nombre, correo, edad);
            int idt = es1.Crear();
            admonDatos.jTextField1.setText(""+idt);
            
            Estudiante.agregarCurso(idt, curso);
            
            JOptionPane.showMessageDialog(null, "Estudiante Creado - Id: "+idt);
            admonDatos.dispose();
            llenarListas();
            limpiarCampos();
        }
        if (e.getSource()== tablero.jButton3){
            //Prepara crear curso
            funcion = 3;
            limpiarCampos();
            admonDatos.setVisible(true);
            admonDatos.jLabel6.setText("Agregar Curso");
            admonDatos.jLabel1.setText("ID:");
            admonDatos.jLabel2.setText("Nombre: ");
            admonDatos.jLabel3.setText("Capacidad: ");
            admonDatos.jLabel4.setVisible(false);
            admonDatos.jTextField4.setVisible(false);
            admonDatos.jLabel5.setVisible(true);
            admonDatos.jLabel5.setText("Profesor: ");
            admonDatos.jButton2.setVisible(false);
            admonDatos.jButton3.setVisible(false);
            admonDatos.jButton1.setText("Agregar");
            admonDatos.jComboBox1.setVisible(true);
            
            ArrayList<Profesor> pro1 = new ArrayList();
            pro1 = Profesor.leer();
            for (Profesor p: pro1){
                admonDatos.jComboBox1.addItem(p.getId()+" - "+p.getNombre());
            }
            
        }
        //Crear Curso
        if (e.getSource() == admonDatos.jButton1 && funcion == 3){
            
            String nombre = admonDatos.jTextField2.getText();
            int capacidad = Integer.parseInt(admonDatos.jTextField3.getText());
            
            String[] tProfesor = admonDatos.jComboBox1.getSelectedItem().toString().split(" ");
            String id_pro = tProfesor[0];
            int profesor = Integer.parseInt(id_pro); 
            
            Curso cur1 = new Curso(nombre, capacidad, profesor);
            int idt = cur1.Crear();
            admonDatos.jTextField1.setText(""+idt);
            
            JOptionPane.showMessageDialog(null, "Curso Creado - Id: "+idt);
            admonDatos.dispose();
            llenarListas();
            limpiarCampos();
            
        }
        //Actualiza profesor
        if (e.getSource() == admonDatos.jButton2 && funcion == 4){ 
            
            int id = Integer.parseInt(admonDatos.jTextField1.getText());
            String nombre = admonDatos.jTextField2.getText();
            String correo = admonDatos.jTextField3.getText();
            int edad = Integer.parseInt(admonDatos.jTextField4.getText());
            Profesor p = new Profesor(id, nombre, correo, edad);
            p.actualizar();
            admonDatos.dispose();
            llenarListas();
            limpiarCampos();
            
        }
        //Actualiza Estudiante
        if (e.getSource() == admonDatos.jButton2 && funcion == 5){
            
            int id = Integer.parseInt(admonDatos.jTextField1.getText());
            String nombre = admonDatos.jTextField2.getText();
            String correo = admonDatos.jTextField3.getText();
            int edad = Integer.parseInt(admonDatos.jTextField4.getText());
            
            
            String[] tCurso = admonDatos.jComboBox1.getSelectedItem().toString().split(" ");
            String id_cur = tCurso[0];
            int curso = Integer.parseInt(id_cur);
            
            Estudiante es1 = new Estudiante(id, nombre, correo, edad);
            es1.actualizar();
            Estudiante.agregarCurso(id, curso);
            admonDatos.dispose();
            llenarListas();
            limpiarCampos();
        }
        //Actualiza Curso
        if (e.getSource() == admonDatos.jButton2 && funcion == 6){
            
            int id = Integer.parseInt(admonDatos.jTextField1.getText());
            String nombre = admonDatos.jTextField2.getText();
            int capacidad = Integer.parseInt(admonDatos.jTextField3.getText());
            
            String[] tProfesor = admonDatos.jComboBox1.getSelectedItem().toString().split(" ");
            String id_pro = tProfesor[0];
            int profesor = Integer.parseInt(id_pro); 
            
            Curso cur1 = new Curso(id, nombre, capacidad, profesor);
            cur1.actualizar();
            admonDatos.dispose();
            llenarListas();
            limpiarCampos();
            
            
        }
        //Borrar Profesor
        if (e.getSource() == admonDatos.jButton4 && funcion == 4){
            
            int id = Integer.parseInt(admonDatos.jTextField1.getText());
            Profesor.borrar(id);
            admonDatos.dispose();
            llenarListas();
            limpiarCampos();
            
        }
        //Borrar Estudiante
        if (e.getSource() == admonDatos.jButton4 && funcion == 5){
            
            int id = Integer.parseInt(admonDatos.jTextField1.getText());
            Estudiante.borrar(id);
            admonDatos.dispose();
            llenarListas();
            limpiarCampos();
            
        }
        //Borrar Curso
        if (e.getSource() == admonDatos.jButton4 && funcion == 6){
            
            int id = Integer.parseInt(admonDatos.jTextField1.getText());
            Curso.borrar(id);
            admonDatos.dispose();
            llenarListas();
            limpiarCampos();
            
        }
        //Consulta de estudiantes x profesor
        if (e.getSource() == admonDatos.jButton3 && funcion == 4){
            
            int id = Integer.parseInt(admonDatos.jTextField1.getText());
            ArrayList<Estudiante> r = Estudiante.consultaEstudiantes(id);
            
            DefaultTableModel model = new DefaultTableModel();
            String [] encabezados = {"Id", "Nombre", "Correo", "Edad"};
            model.setColumnIdentifiers(encabezados);
            Object [] obj1 = new Object[4];
            
            for (Estudiante e10: r){
                obj1[0]= e10.getId();
                obj1[1]= e10.getNombre();
                obj1[2]= e10.getCelular();
                obj1[3]= e10.getEdad();
                model.addRow(obj1);
                
            }
            resConsulta.jTable1.setModel(model);
            resConsulta.setVisible(true);
            
        }
        //Consulta de cursos x profesor
        if (e.getSource() == admonDatos.jButton1 && funcion == 4){
            
            int id = Integer.parseInt(admonDatos.jTextField1.getText());
            ArrayList<Curso> c = Curso.consultaCursos(id);
            
            
            DefaultTableModel model = new DefaultTableModel();
            String [] encabezados = {"Id", "Nombre", "Capacidad", "Id Profesor"};
            model.setColumnIdentifiers(encabezados);
            Object [] obj2 = new Object[4];
            
            for (Curso c10: c){
                obj2[0]= c10.getId();
                obj2[1]= c10.getNombre();
                obj2[2]= c10.getCapacidad();
                obj2[3]= c10.getIdProfesor();
                model.addRow(obj2);
                
            }
            resConsulta.jTable1.setModel(model);
            resConsulta.setVisible(true);
            
        }
        //Consulta de estudiantes x curso
        if (e.getSource() == admonDatos.jButton3 && funcion == 6){
            
            int id = Integer.parseInt(admonDatos.jTextField1.getText());
            ArrayList<Estudiante> r3 = Estudiante.consultaEstudiantesPorCurso(id);
            
            DefaultTableModel model = new DefaultTableModel();
            String [] encabezados = {"Id", "Nombre", "Correo", "Edad"};
            model.setColumnIdentifiers(encabezados);
            Object [] obj3 = new Object[4];
            
            for (Estudiante e11: r3){
                obj3[0]= e11.getId();
                obj3[1]= e11.getNombre();
                obj3[2]= e11.getCelular();
                obj3[3]= e11.getEdad();
                model.addRow(obj3);
                
            }
            resConsulta.jTable1.setModel(model);
            resConsulta.setVisible(true);
        }
    }
    
    
    
    
    
    
    @Override
    public void valueChanged(ListSelectionEvent evt){
        
        if (evt.getSource()== tablero.jList1){
            
            funcion = 4;
            
            
            String[] pro = tablero.jList1.getSelectedValue().split("-");
            int id_pro = Integer.parseInt(pro[0]);
            Profesor p = Profesor.consultar(id_pro);
            admonDatos.setVisible(true);
            limpiarCampos();
            admonDatos.jLabel6.setText("Consultar Profesor");
            admonDatos.jLabel1.setText("ID:");
            admonDatos.jLabel2.setText("Nombre: ");
            admonDatos.jLabel3.setText("Correo: ");
            admonDatos.jLabel4.setText("Edad: ");
            admonDatos.jTextField1.setText(""+p.getId());
            admonDatos.jTextField2.setText(""+p.getNombre());
            admonDatos.jTextField3.setText(""+p.getCorreo());
            admonDatos.jTextField4.setText(""+p.getEdad());
            admonDatos.jLabel5.setVisible(false);
            admonDatos.jComboBox1.setVisible(false);
            admonDatos.jButton2.setVisible(true);
            admonDatos.jButton3.setVisible(true);
            admonDatos.jButton3.setText("Consultar Estudiantes");
            admonDatos.jButton1.setVisible(true);
            admonDatos.jButton1.setText("Consultar Cursos");
            tablero.jList1.clearSelection();
            
            
        }
        if (evt.getSource()== tablero.jList2){
            
            funcion = 5;
            
            String [] est3 = tablero.jList2.getSelectedValue().split("-");
            int id_est = Integer.parseInt(est3[0]);
            Estudiante e = Estudiante.consultar(id_est);
            
            admonDatos.setVisible(true);
            limpiarCampos();
            admonDatos.jLabel6.setText("Consultar Estudiante");
            admonDatos.jLabel1.setText("ID:");
            admonDatos.jLabel2.setText("Nombre: ");
            admonDatos.jLabel3.setText("Correo: ");
            admonDatos.jLabel4.setText("Edad: ");
            admonDatos.jTextField1.setText(""+e.getId());
            admonDatos.jTextField2.setText(""+e.getNombre());
            admonDatos.jTextField3.setText(""+e.getCelular());
            admonDatos.jTextField4.setText(""+e.getEdad());
            admonDatos.jLabel5.setVisible(true);
            admonDatos.jLabel5.setText("Curso: ");
            admonDatos.jComboBox1.setVisible(true);
            ArrayList<Curso> c = new ArrayList();
            c = Curso.leer();
            for (Curso cur: c){
                admonDatos.jComboBox1.addItem(cur.getId()+" - "+cur.getNombre());
                
            }
            admonDatos.jButton2.setVisible(true);
            admonDatos.jButton3.setVisible(false);
            //admonDatos.jButton3.setText("Consultar Cursos");
            admonDatos.jButton1.setVisible(false);
            tablero.jList2.clearSelection();
            
        }
        if (evt.getSource()== tablero.jList3){
            
            funcion =6;
            
            String [] cur3 = tablero.jList3.getSelectedValue().split("-");
            int id_cur = Integer.parseInt(cur3[0]);
            System.out.println(""+id_cur);
            Curso c = Curso.consultar(id_cur);
            
            admonDatos.setVisible(true);
            limpiarCampos();
            admonDatos.jLabel6.setText("Consultar Curso");
            admonDatos.jLabel1.setText("ID:");
            admonDatos.jLabel2.setText("Nombre: ");
            admonDatos.jLabel3.setText("Capacidad: ");
            admonDatos.jTextField1.setText(""+c.getId());
            admonDatos.jTextField2.setText(""+c.getNombre());
            admonDatos.jTextField3.setText(""+c.getCapacidad());
            admonDatos.jLabel4.setVisible(true);
            admonDatos.jLabel4.setText("Profesor Asignado");
            admonDatos.jTextField4.setText(""+c.getIdProfesor());
            admonDatos.jTextField4.setVisible(true);
            admonDatos.jLabel5.setText("Profesor: ");
            admonDatos.jComboBox1.setVisible(true);
            
            ArrayList<Profesor> pro1 = new ArrayList();
            pro1 = Profesor.leer();
            for (Profesor p: pro1){
                admonDatos.jComboBox1.addItem(p.getId()+" - "+p.getNombre());
            }
            admonDatos.jButton2.setVisible(true);
            admonDatos.jButton3.setVisible(true);
            admonDatos.jButton3.setText("Consultar Estudiantes");
            admonDatos.jButton1.setVisible(false);
            tablero.jList3.clearSelection();
            
        }
        
        
        
        
        
    }
    public void WindowClosed(WindowEvent e){
        
        
        limpiarCampos();
        System.out.println("Este comando hace algo");
        admonDatos.dispose();
            
          
        
    }
    
    
    
    
    
}

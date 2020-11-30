package modelo;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;



public class Curso {
    
    private long id; 
    private int capacidad, idProfesor;
    private String nombre;
    private Profesor profesor;
    private ArrayList<Estudiante> estudiantes;

    public Curso(long id, String nombre, int capacidad, int profesor) {
        this.id = id;
        this.capacidad = capacidad;
        this.idProfesor = profesor;
        this.nombre = nombre;
        
    }

    public Curso(String nombre, int capacidad, int profesor) {
        this.capacidad = capacidad;
        this.idProfesor = profesor;
        this.nombre = nombre;
        
    }

    public Curso(String nombre, int capacidad) {
        this.capacidad = capacidad;
        this.nombre = nombre;
    }

    public Curso(long id, String nombre,int capacidad ) {
        this.id = id;
        this.capacidad = capacidad;
        this.nombre = nombre;
    }

    public Curso() {
    }
    
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
    
    
    public void crear(){
        
        Conexion con = new Conexion();
        Statement st;
        ResultSet rs;
        
        String sql = "insert into cursos(id, nombre, capacidad)values('"+id+"', '" +nombre+ "', '" +capacidad+"')";
        //System.out.println(sql);
            try{
                con.conectar();
                Connection cn = con.getConexion();
                st = cn.createStatement();
                st.executeUpdate(sql);
                //System.out.println("Curso creado: "+capacidad+", nombre: "+nombre+", profesor: "+idProfesor);
                
                con.cerrar();
                
            }catch (Exception e){
                
            }
        
    }
    
    public static void crear(int idCurso, int idProfesor){
        
        Conexion con = new Conexion();
        Statement st;
        ResultSet rs;
        
        String sql = "insert into cursos(id, profesor)values('" +idCurso+ "', '" +idProfesor+"')";
        //System.out.println(sql);
            try{
                con.conectar();
                Connection cn = con.getConexion();
                st = cn.createStatement();
                st.executeUpdate(sql);
                //System.out.println("Curso creado: "+idCurso+",  profesor: "+idProfesor);
                
                con.cerrar();
                
            }catch (Exception e){
                
            }
        
    }
    
    
    
    public static ArrayList<Curso> leer(){
        
        Conexion con = new Conexion();
        Statement st;
        ResultSet resultado;
        ArrayList<Curso> eList = new ArrayList();
        
        con.conectar();
        
            try{
                
                st = con.getConexion().createStatement();
                resultado = st.executeQuery("SELECT * FROM cursos");
                
                while (resultado.next()){
               
                    eList.add(new Curso(resultado.getInt("id"),  resultado.getString("nombre"), resultado.getInt("capacidad"), resultado.getInt("profesor")));
                    
                }
                
                con.cerrar();
                
            }catch (Exception e){
                //System.out.println("Error: "+e);
                
            }
        //System.out.println("Esta corriendo?"+eList);
        return eList;
        
    }
    
    public void actualizar(){
        
        Conexion con = new Conexion();
        Statement st;
        ResultSet resultado;
        long x = this.getId();
        
        try{
            
            String sql2 = "UPDATE cursos SET nombre = '"+this.getNombre()+"', capacidad ='"+this.getCapacidad()+"' , profesor ='"+this.getIdProfesor()+"'  WHERE Id = '"+x+"'";
            
            con.conectar();
            Connection cn = con.getConexion();
            st = cn.createStatement();
            st.executeUpdate(sql2);
            //System.out.println("Curso actualizado: "+this.getCapacidad()+", nombre: "+this.getNombre()+", profesor: "+this.getIdProfesor());
            JOptionPane.showMessageDialog(null,"Curso Actualizado");
            st.close();
            
            }catch (Exception e){
                //System.err.println("Error en la actualizacion: "+e);
                
            }
        con.cerrar();
        
    }
    
    public void borrar(){
        
        Conexion con = new Conexion();
        Statement st;
        ResultSet rs;
        
        String sql = "DELETE FROM cursos WHERE Id ='"+this.getId()+"'";
        //System.out.println(sql);
            try{
                con.conectar();
                Connection cn = con.getConexion();
                st = cn.createStatement();
                st.executeUpdate(sql);
                //System.out.println("Registro eliminado");
                //JOptionPane.showMessageDialog(null,"Curso Eliminado");
                
            }catch (Exception e){
                //System.out.println("Error eliminando el curso: "+e);
                
            }
        
        con.cerrar();
    }
    
    public static void borrar(int x){
        
        Conexion con = new Conexion();
        Statement st;
        ResultSet rs;
        String sql = "DELETE FROM cursos WHERE Id ='"+x+"'";
        String sql2 = "DELETE FROM curso_estudiante WHERE id_curso = '"+x+"'";
        //System.out.println(sql);
            try{
                con.conectar();
                Connection cn = con.getConexion();
                st = cn.createStatement();
                st.executeUpdate(sql);
                st.executeUpdate(sql2);
                JOptionPane.showMessageDialog(null, "Curso Eliminado - ID: "+x);
                 
            }catch (Exception e){
                
            }
        con.cerrar();
        
    }
 
    public static void agregarProfesor(int idcurso, int idprofesor){
        
        Conexion con = new Conexion();
        Statement st;
        ResultSet rs;
        
        String sql = "update cursos set profesor = '"+idprofesor+"' where id = '"+idcurso+"'";
        //System.out.println(sql);
            try{
                con.conectar();
                Connection cn = con.getConexion();
                st = cn.createStatement();
                st.executeUpdate(sql);
                //System.out.println("Curso actualizado: "+idcurso+",  profesor: "+idprofesor);
                
                con.cerrar();
                
            }catch (Exception e){
                
            }
        
        
    }
    
    public static ArrayList<Curso> consultaCursos(int idprofesor){
        
        Conexion con = new Conexion();
        Statement st;
        ResultSet resultado;
        ArrayList<Curso> eList = new ArrayList();
        
        con.conectar();
        
            try{
                
                st = con.getConexion().createStatement();
                resultado = st.executeQuery("SELECT * FROM cursos WHERE profesor = '"+idprofesor+"'");
                
                while (resultado.next()){
               
                    eList.add(new Curso(resultado.getInt("id"),  resultado.getString("nombre"), resultado.getInt("capacidad"), resultado.getInt("profesor")));
                    
                }
                
                con.cerrar();
                
            }catch (Exception e){
                //System.out.println("Error: "+e);
                
            }
        //System.out.println("Esta corriendo?"+eList);
        
        return eList;
        
        
    }
    public int Crear(){
        
        Conexion con = new Conexion();
        Statement st;
        ResultSet rs;
        int rsId =0;
         
        String sql = "insert into cursos(nombre, capacidad, profesor)values('"+nombre+ "', '" +capacidad+"', '" +idProfesor+"')";
        
            try{
                con.conectar();
                Connection cn = con.getConexion();
                st = cn.createStatement();
                st.executeUpdate(sql);
                rs = st.executeQuery("SELECT id FROM cursos WHERE nombre = '"+nombre+"' AND capacidad = '"+capacidad+"' ");
                rsId= rs.getInt("id");
                
            }catch (Exception e){
                System.out.println("Error creando el curso: "+e);
                
            }
        
        con.cerrar();
        return rsId;
    }
    public static Curso consultar(long id){
         
        Conexion con = new Conexion();
        Statement st;
        ResultSet resultado;
        long x = id;
        Curso c1 = new Curso();
        String sql = "SELECT * FROM cursos WHERE Id = '"+x+"'" ;     
        
        try{
                con.conectar();
                Connection cn = con.getConexion();
                st = cn.createStatement();
                resultado = st.executeQuery(sql);
                c1 = new Curso(resultado.getInt("id"), resultado.getString("nombre"), resultado.getInt("capacidad"), resultado.getInt("profesor"));
                
        }catch (Exception e){
            System.out.println("No se ha podido conectar a la BD: "+e);
                
        }
         con.cerrar();
         return c1;
         
     }

    
    
    
    
    
}
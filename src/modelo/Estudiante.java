package modelo;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Estudiante {
    private long id; 
    private int edad;
    private String nombre, celular;
    private ArrayList<Curso> cursos; 

    public Estudiante(long id, String nombre, String celular, int edad) {
        this.id = id;
        this.edad = edad;
        this.nombre = nombre;
        this.celular = celular;
    }

    public Estudiante(String nombre, String celular, int edad) {
        this.edad = edad;
        this.nombre = nombre;
        this.celular = celular;
    }

    public Estudiante() {
    }
    

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }
    
    public void crear(){
        
        Conexion con = new Conexion();
        Statement st;
        ResultSet rs;
        
        String sql = "insert into estudiantes(id, nombre, celular, edad)values('"+id+"', '"+nombre+ "', '" +celular+"', '" +edad+"')";
        //System.out.println(sql);
            try{
                con.conectar();
                Connection cn = con.getConexion();
                st = cn.createStatement();
                st.executeUpdate(sql);
                //System.out.println("Estudiante creado: "+celular+", nombre: "+nombre+", edad: "+edad);
                
                con.cerrar();
                
            }catch (Exception e){
                
            }
        
    }
    
    
    public static ArrayList<Estudiante> leer(){
        
        Conexion con = new Conexion();
        Statement st;
        ResultSet resultado;
        ArrayList<Estudiante> eList = new ArrayList();
        
        con.conectar();
        
            try{
                
                st = con.getConexion().createStatement();
                resultado = st.executeQuery("SELECT * FROM estudiantes");
                
                while (resultado.next()){
               
                    eList.add(new Estudiante(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("celular"), resultado.getInt("edad")));
                    
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
            
            String sql2 = "UPDATE estudiantes SET nombre = '"+this.getNombre()+"', celular ='"+this.getCelular()+"' , edad ='"+this.getEdad()+"'  WHERE Id = '"+x+"'";
            
            con.conectar();
            Connection cn = con.getConexion();
            st = cn.createStatement();
            st.executeUpdate(sql2);
            //System.out.println("Estudiante actualizado: "+this.getCelular()+", nombre: "+this.getNombre()+", edad: "+this.getEdad());
            JOptionPane.showMessageDialog(null,"Estudiante Actualizado");
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
        
        String sql = "DELETE FROM estudiantes WHERE Id ='"+this.getId()+"'";
        //System.out.println(sql);
            try{
                con.conectar();
                Connection cn = con.getConexion();
                st = cn.createStatement();
                st.executeUpdate(sql);
                //System.out.println("Registro eliminado");
                //JOptionPane.showMessageDialog(null,"Estudiante Eliminado");
                
            }catch (Exception e){
                //System.out.println("Error eliminando al estudiante: "+e);
                
            }
        
        con.cerrar();
    }
    
    public static void borrar(int x){
        
        Conexion con = new Conexion();
        Statement st;
        ResultSet rs;
        String sql = "DELETE FROM estudiantes WHERE Id ='"+x+"'";
        String sql2 = "DELETE FROM curso_estudiante WHERE id_estudiante = '"+x+"'";
        //System.out.println(sql);
            try{
                con.conectar();
                Connection cn = con.getConexion();
                st = cn.createStatement();
                st.executeUpdate(sql);
                st.executeUpdate(sql2);
                JOptionPane.showMessageDialog(null,"Estudiante Eliminado - ID: "+x);
                 
            }catch (Exception e){
                
            }
        con.cerrar();
        
    }
    
    
    public static void agregarCurso(int idestudiante, int idcurso){
        
        Conexion con = new Conexion();
        Statement st;
        ResultSet rs;
        
        String sql = "insert or ignore into curso_estudiante (id_estudiante, id_curso)values('" +idestudiante+ "', '" +idcurso+"')";
        //System.out.println(sql);
            try{
                con.conectar();
                Connection cn = con.getConexion();
                st = cn.createStatement();
                st.executeUpdate(sql);
                //System.out.println("Estudiante: "+idestudiante+",  se unio al curso: "+idcurso);
                
                con.cerrar();
                
            }catch (Exception e){
                
            }
        
    }
    
    public static ArrayList<Estudiante> consultaEstudiantes(int idprofesor){
        
        Conexion con = new Conexion();
        Statement st;
        ResultSet resultado;
        ArrayList<Estudiante> eList = new ArrayList();
        
        con.conectar();
        
            try{
                
                st = con.getConexion().createStatement();
                resultado = st.executeQuery("SELECT DISTINCT estudiantes.id, estudiantes.nombre, estudiantes.celular, estudiantes.edad FROM estudiantes JOIN curso_estudiante JOIN cursos ON estudiantes.id = curso_estudiante.id_estudiante AND curso_estudiante.id_curso = cursos.id WHERE cursos.profesor = '"+idprofesor+"' ");
                
                while (resultado.next()){
               
                    eList.add(new Estudiante(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("celular"), resultado.getInt("edad")));
                    
                }
                
                con.cerrar();
                
            }catch (Exception e){
                //System.out.println("Error: "+e);
                
            }
        //System.out.println("Esta corriendo?"+eList);
        return eList;
        
    }
    
    
    public static ArrayList<Estudiante> consultaEstudiantesPorCurso(int idcurso){
        
        Conexion con = new Conexion();
        Statement st;
        ResultSet resultado;
        ArrayList<Estudiante> eList = new ArrayList();
        
        con.conectar();
        
            try{
                
                st = con.getConexion().createStatement();
                resultado = st.executeQuery("SELECT DISTINCT estudiantes.id, estudiantes.nombre, estudiantes.celular, estudiantes.edad FROM estudiantes JOIN curso_estudiante JOIN cursos ON estudiantes.id = curso_estudiante.id_estudiante AND curso_estudiante.id_curso = cursos.id WHERE cursos.id = '"+idcurso+"' ");
                
                while (resultado.next()){
               
                    eList.add(new Estudiante(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("celular"), resultado.getInt("edad")));
                    
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
         
        String sql = "insert into estudiantes(nombre, celular, edad)values('"+nombre+ "', '" +celular+"', '" +edad+"')";
        
            try{
                con.conectar();
                Connection cn = con.getConexion();
                st = cn.createStatement();
                st.executeUpdate(sql);
                rs = st.executeQuery("SELECT id FROM estudiantes WHERE celular = '"+celular+"' AND edad = '"+edad+"' ");
                rsId= rs.getInt("id");
                
            }catch (Exception e){
                System.out.println("Error creando el estudiante: "+e);
                
            }
        
        con.cerrar();
        return rsId;
    }
    public static Estudiante consultar(long id){
         
        Conexion con = new Conexion();
        Statement st;
        ResultSet resultado;
        long x = id;
        Estudiante e1 = new Estudiante();
        String sql = "SELECT * FROM estudiantes WHERE Id = '"+x+"'" ;     
        
        try{
                con.conectar();
                Connection cn = con.getConexion();
                st = cn.createStatement();
                resultado = st.executeQuery(sql);
                e1 = new Estudiante(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("celular"), resultado.getInt("edad"));
                
        }catch (Exception e){
            System.out.println("No se ha podido conectar a la BD: "+e);
                
        }
         con.cerrar();
         return e1;
         
     }
    
    
    
    
    
    
    
    
    

}
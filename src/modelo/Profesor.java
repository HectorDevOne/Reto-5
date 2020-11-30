package modelo;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;



public class Profesor {
    
    private long id;
    private int edad;
    private String nombre, correo;
    private ArrayList<Curso> cursos;

    public Profesor(long id, String nombre, String correo, int edad) {
        this.id = id;
        this.edad = edad;
        this.nombre = nombre;
        this.correo = correo;
    }

    public Profesor(String nombre, String correo, int edad) {
        this.edad = edad;
        this.nombre = nombre;
        this.correo = correo;
    }

    public Profesor() {
    }
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
        
        String sql = "insert into profesores(nombre, correo, edad)values('"+nombre+ "', '" +correo+"', '" +edad+"')";
        //System.out.println(sql);
            try{
                con.conectar();
                Connection cn = con.getConexion();
                st = cn.createStatement();
                st.executeUpdate(sql);
                //System.out.println("Profesor creado: "+correo+", nombre: "+nombre+", edad: "+edad);
                
                con.cerrar();
                
            }catch (Exception e){
                
            }
        
    }
    
    
    public static ArrayList<Profesor> leer(){
        
        Conexion con = new Conexion();
        Statement st;
        ResultSet resultado;
        ArrayList<Profesor> eList = new ArrayList();
        
        con.conectar();
        
            try{
                
                st = con.getConexion().createStatement();
                resultado = st.executeQuery("SELECT * FROM profesores");
                
                while (resultado.next()){
               
                    eList.add(new Profesor(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("correo"), resultado.getInt("edad")));
                    
                }
                
                con.cerrar();
                
            }catch (Exception e){
                System.out.println("Error: "+e);
                
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
            
            String sql2 = "UPDATE profesores SET nombre = '"+this.getNombre()+"', correo ='"+this.getCorreo()+"' , edad ='"+this.getEdad()+"'  WHERE Id = '"+x+"'";
            
            con.conectar();
            Connection cn = con.getConexion();
            st = cn.createStatement();
            st.executeUpdate(sql2);
            //System.out.println("Profesor actualizado: "+this.getCorreo()+", nombre: "+this.getNombre()+", edad: "+this.getEdad());
            JOptionPane.showMessageDialog(null,"Profesor Actualizado");
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
        
        String sql = "DELETE FROM profesores WHERE Id ='"+this.getId()+"'";
        //System.out.println(sql);
            try{
                con.conectar();
                Connection cn = con.getConexion();
                st = cn.createStatement();
                st.executeUpdate(sql);
                //System.out.println("Registro eliminado");
                //JOptionPane.showMessageDialog(null,"Profesor Eliminado");
                
            }catch (Exception e){
                //System.out.println("Error eliminando al profersor: "+e);
                
            }
        
        con.cerrar();
    }
    
    public static void borrar(int x){
        
        Conexion con = new Conexion();
        Statement st;
        ResultSet rs;
        String sql = "DELETE FROM profesores WHERE Id ='"+x+"'";
        
            try{
                con.conectar();
                Connection cn = con.getConexion();
                st = cn.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Profesor Eliminado - ID: "+x);
                 
            }catch (Exception e){
                
            }
        con.cerrar();
        
    }
    
    public int Crear(){
        
        Conexion con = new Conexion();
        Statement st;
        ResultSet rs;
        int rsId =0;
         
        String sql = "insert into profesores(nombre, correo, edad)values('"+nombre+ "', '" +correo+"', '" +edad+"')";
        
            try{
                con.conectar();
                Connection cn = con.getConexion();
                st = cn.createStatement();
                st.executeUpdate(sql);
                rs = st.executeQuery("SELECT id FROM profesores WHERE Correo = '"+correo+"' AND edad = '"+edad+"' ");
                rsId= rs.getInt("id");
                
            }catch (Exception e){
                System.out.println("Error creando el empleado: "+e);
                
            }
        
        con.cerrar();
        return rsId;
    }
    public static Profesor consultar(long id){
         
        Conexion con = new Conexion();
        Statement st;
        ResultSet resultado;
        long x = id;
        Profesor p1 = new Profesor();
        String sql = "SELECT * FROM profesores WHERE Id = '"+x+"'" ;     //"﻿SELECT * FROM Empleados WHERE Id= '"+x+"'"
        
        try{
                con.conectar();
                Connection cn = con.getConexion();
                st = cn.createStatement();
                resultado = st.executeQuery(sql);
                p1 = new Profesor(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("correo"), resultado.getInt("edad"));
                
        }catch (Exception e){
            System.out.println("No se ha podido conectar a la BD: "+e);
                
        }
         con.cerrar();
         return p1;
         
     }
    
    
    
    
    
}
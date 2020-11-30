package modelo;


import java.sql.Connection;
import java.sql.DriverManager;


public class Conexion {
    
    String url ="colegio.db" ;
    Connection conexion;

    public Conexion() {
    }

    public Conexion(Connection conexion) {
        this.conexion = conexion;
    }
    
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
    public void conectar(){
        
        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:"+url);
            if (conexion!=null) {
                //System.out.println("Conectado");
            }
        }catch (Exception e) {
            //System.err.println("No se ha podido conectar a la base de datos\n"+e.getMessage());
        }
        
        
    }
    
    public void cerrar(){
        try {
            conexion.close();
        } catch (Exception e) {
            //System.err.println("No se ha podido cerrar la conexion\n"+e.getMessage());
            
        }
    }
    
    
    
}

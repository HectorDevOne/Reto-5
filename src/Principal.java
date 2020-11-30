import controlador.Controlador;
import vista.Dashboard;
import vista.IngresoDatos;
import vista.Resultado;



public class Principal {
    
    
    public static void main(String[] args) {
        
        Dashboard tablero = new Dashboard();
        IngresoDatos admonDatos = new IngresoDatos();
        Resultado resConsulta = new Resultado();
        Controlador ctrl = new Controlador(tablero, admonDatos, resConsulta);
        
        ctrl.iniciar();
        
        
        
        
        
        
        
        
        
        
//        Scanner lector = new Scanner(System.in);
//        
//        //Codigo de los profesores
//        
//        for (int i = 0; i<3; i++){
//            
//            long id = Long.parseLong(lector.nextLine());
//            String nombre = lector.nextLine();
//            String correo = lector.nextLine();
//            int edad = Integer.parseInt(lector.nextLine());
//            
//            Profesor p = new Profesor(id, nombre, correo, edad);
//            p.crear();
//            
//        }
//        
//        // Codigo de los estudiantes
//        
//        for (int i = 0;i<5;i++){
//            
//            int id = Integer.parseInt(lector.nextLine());
//            String nombre = lector.nextLine();
//            String celular = lector.nextLine();
//            int edad = Integer.parseInt(lector.nextLine());
//            
//            Estudiante e = new Estudiante(id, nombre, celular, edad);
//            e.crear();
//            
//        }
//        
//        // Codigo de los cursos
//        
//        for (int i = 0;i<4; i++){
//            
//            int id = Integer.parseInt(lector.nextLine());
//            String nombre = lector.nextLine();
//            int cap = Integer.parseInt(lector.nextLine());
//            
//            Curso c = new Curso(id, nombre, cap);
//            c.crear();
//            
//        }
//        
//        
//        // Codigo de las operaciones
//        
//        for (int i = 0;i<16;i++){
//            
//            int operacion = Integer.parseInt(lector.nextLine());
//            String op = lector.nextLine();
//            String[] ids = op.split(" - ");
//            
//            if (operacion == 1){
//                
//                int idProfesor = Integer.parseInt(ids[0]);
//                int idCurso = Integer.parseInt(ids[1]);
//                
//                Curso.agregarProfesor(idCurso, idProfesor);
//                
//            }else{
//                
//                int idEstudiante = Integer.parseInt(ids[0]);
//                int idCurso = Integer.parseInt(ids[1]);
//                
//                Estudiante.agregarCurso(idEstudiante, idCurso);
//                
//            }
//            
//        }
//        
//        // Consultas 
//        
//        int idprofesor = Integer.parseInt(lector.nextLine());
//        ArrayList<Curso> cursos = Curso.consultaCursos(idprofesor);
//        
//        for (Curso c: cursos){
//            
//            System.out.println("Curso: "+c.getId()+" - "+c.getNombre()+" - "+c.getCapacidad());
//            
//        }
//        
//        idprofesor = Integer.parseInt(lector.nextLine());
//        ArrayList<Estudiante> students = Estudiante.consultaEstudiantes(idprofesor);
//        
//        for (Estudiante e: students){
//            
//            System.out.println("Estudiante: "+e.getId()+" - "+e.getNombre()+" - "+e.getEdad());
//            
//        }
//        
//        int idcurso = Integer.parseInt(lector.nextLine());
//        ArrayList<Estudiante> stud2 = Estudiante.consultaEstudiantesPorCurso(idcurso);
//        
//        for (Estudiante e: stud2){
//            
//            System.out.println("Estudiante: "+e.getId()+" - "+e.getNombre()+" - "+e.getEdad());
//            
//        }
    }
    
}

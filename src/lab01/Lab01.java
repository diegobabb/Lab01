
package lab01;

import exceptions.GlobalException;
import exceptions.NoDataException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Curso;
import models.Profesor;
import services.ServiceCurso;
import services.ServiceGrupo;
import services.ServiceProfesor;

/**
 *
 * @author Diego Babb
 */
public class Lab01 {

    public static void main(String[] args) {

        Profesor profe = new Profesor("1", "1", "1", "1");
        Curso curso = new Curso("a","a",2,2);
        
        ServiceProfesor sp = new ServiceProfesor();
        ServiceCurso sc = new ServiceCurso();
        ServiceGrupo sg = new ServiceGrupo();
        
        try {
           // System.out.print("Insertando profesor");
           // sp.insert(profe);
            System.out.println("Modificando profesor");
            profe.setNombre("Juan");
            sp.update(profe);
            System.out.println("Buscando profesor :" + sp.find(profe.getCedula()));
            System.out.println("Listando todos profe :" + sp.selectAll());
            
          //  System.out.print("Insertando curso");
          //  sc.insert(curso);
          System.out.print("Modificando curso");
          curso.setHoras(13);
          sc.update(curso);
          
          System.out.println("Buscando curso :" + sc.find(curso.getCodigo()));
           System.out.println("Listando todos los cursos :" +  sc.selectAll());
           
         /// System.out.println("Creando grupo");
        //   sg.insert(curso, profe);
           
          System.out.println("Buscando grupo :" + sg.find(curso, profe));
            
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(Lab01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

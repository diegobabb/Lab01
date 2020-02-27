
package lab01;

import exceptions.GlobalException;
import exceptions.NoDataException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ServiceProfesor;

/**
 *
 * @author Diego Babb
 */
public class Lab01 {

    public static void main(String[] args) {

        ServiceProfesor sp = new ServiceProfesor();
        try {
            System.out.print(sp.selectAll());
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(Lab01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

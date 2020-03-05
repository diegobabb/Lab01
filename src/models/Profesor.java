
package models;

/**
 *
 * @author diego
 */
public class Profesor {

    private String cedula;
    private String nombre;
    private String telefono;
    private String email;
    private String curso;


    public Profesor(String cedula, String nombre, String telefono, String email, String curso) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.curso = curso;
    }

    public Profesor() {;
        this.cedula = "";
        this.nombre = "";
        this.telefono = "";
        this.email = "";
        this.curso = "";
    }


    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
   
 
}

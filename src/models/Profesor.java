
package models;

/**
 *
 * @author diego
 */
public class Profesor {

    
    private int id;
    private String cedula;
    private String nombre;
    private String telefono;
    private String email;

    public Profesor(int id, String cedula, String nombre, String telefono, String email) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public Profesor(String cedula, String nombre, String telefono, String email) {
        this.id = -1;
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public Profesor() {
        this.id = -1;
        this.cedula = "";
        this.nombre = "";
        this.telefono = "";
        this.email = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Profesor{" + "id=" + id + ", cedula=" + cedula + ", nombre=" + nombre + ", telefono=" + telefono + ", email=" + email + '}';
    }
}

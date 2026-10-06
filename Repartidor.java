public class Repartidor {
    private int id;
    private String nombre;

    public Repartidor() {
    }

    public Repartidor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Este método permite mostrar el formato id - nombre en los JComboBox de la interfaz gráfica
    @Override
    public String toString() {
        return id + " - " + nombre;
    }
}

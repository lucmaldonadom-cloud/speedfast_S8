public class Pedido {
    private int id;
    private String cliente;
    private String direccion;
    private String estado;
    private int repartidorId;

    public Pedido() {
    }

    public Pedido(int id, String cliente, String direccion, String estado, int repartidorId) {
        this.id = id;
        this.cliente = cliente;
        this.direccion = direccion;
        this.estado = estado;
        this.repartidorId = repartidorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getRepartidorId() {
        return repartidorId;
    }

    public void setRepartidorId(int repartidorId) {
        this.repartidorId = repartidorId;
    }
}

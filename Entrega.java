public class Entrega {
    private int id;
    private int pedidoId;
    private String fechaEntrega;
    private String observaciones;

    public Entrega() {
    }

    public Entrega(int id, int pedidoId, String fechaEntrega, String observaciones) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.fechaEntrega = fechaEntrega;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}

package sharingclothes;

public class Shipped extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getClothesId() {
        return orderId;
    }

    public void setClothesId(Long orderId) {
        this.orderId = orderId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
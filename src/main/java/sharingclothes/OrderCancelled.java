package sharingclothes;

public class OrderCancelled extends AbstractEvent {

    private Long id;
    private Long clothesId;
    private Integer qty;

    public OrderCancelled(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getClothesId() {
        return clothesId;
    }

    public void setClothesId(Long clothesId) {
        this.clothesId = clothesId;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}

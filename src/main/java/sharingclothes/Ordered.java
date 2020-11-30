package sharingclothes;

public class Ordered extends AbstractEvent {

    private Long id;
    private Long clothesId;
    private Integer qty;

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

    //추가시작
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    //추가끝
}
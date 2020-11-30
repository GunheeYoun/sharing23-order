package sharingclothes;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long clothesId;
    private Integer qty;
    //추가시작
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    //추가끝

    //추가시작
    @PrePersist
    public void onPrePersist(){
        try {
            Thread.currentThread().sleep((long) (800 + Math.random() * 220));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //추가끝

    @PostPersist
    public void onPostPersist(){
        Ordered ordered = new Ordered();
        BeanUtils.copyProperties(this, ordered);
        System.out.println("오더 테스트");
        System.out.println("오더 테스트 getId : " +ordered.getId());
        System.out.println("오더 테스트 getClothesId : " +ordered.getClothesId());
        System.out.println("오더 테스트 getQty : " +ordered.getQty());
        ordered.setStatus("Shipped");
        ordered.publishAfterCommit();


    }

    @PreRemove
    public void onPreRemove(){
        OrderCancelled orderCancelled = new OrderCancelled();
        BeanUtils.copyProperties(this, orderCancelled);
        orderCancelled.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        sharingclothes.external.Cancellation cancellation = new sharingclothes.external.Cancellation();

        //추가 시작
        System.out.println("캔슬 테스트");
        System.out.println("캔슬 테스트 getId : " +this.getId());
        System.out.println("캔슬 테스트 getClothesId : " +this.getClothesId());
        // 주문 취소 시 관련 정보 저장
        cancellation.setId(this.getId());
        cancellation.setOrderId(this.getClothesId());
        cancellation.setStatus("DeleveryCancelled");
        //추가 종료
        System.out.println("캔슬 테스트 getStatus : " +cancellation.getStatus());

        // mappings goes here
        OrderApplication.applicationContext.getBean(sharingclothes.external.CancellationService.class)
            .cancel(cancellation);

        System.out.println("캔슬 테스트 마무리 되는가: ");


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

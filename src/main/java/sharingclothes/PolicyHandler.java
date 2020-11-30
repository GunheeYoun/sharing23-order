package sharingclothes;

import sharingclothes.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    //추가
    @Autowired
    OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverShipped_UpdateStatus(@Payload Shipped shipped){

        //추가
        // Optional<Order> --> null 일 수 있음
        Optional<Order> orderOptional = orderRepository.findById(shipped.getClothesId());
        Order order = orderOptional.get();
        System.out.println("오더값 테스트 : " + shipped.getStatus());

//        if(shipped.getStatus()==null)
//            order.setStatus("Shipped1");
//        else
            order.setStatus(shipped.getStatus());
        System.out.println("테스트 : wheneverShipped_UpdateStatus");

        orderRepository.save(order);

        if(shipped.isMe()){
            System.out.println("##### listener UpdateStatus : " + shipped.toJson());
        }
    }

}

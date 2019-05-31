package dubbo.consumer;

import dubbo.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//实例化该对象
@Service
public class ConsumerService {
    @Autowired
    ProviderService providerService;
    public void getService(String name){
        String result = providerService.getInfoService(name);
        System.out.println(result);
    }
}

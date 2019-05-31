package dubbo.serviceimpl;

import dubbo.ProviderService;

public class ProviderServiceImpl implements ProviderService {

    @Override
    public String getInfoService(String name) {
        System.out.println("providerservice..............");
        return name+"获取的服务";
    }
}

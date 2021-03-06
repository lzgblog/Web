
package com.cxf.weather;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "WeatherServiceInterfaceService", targetNamespace = "http://weathde.cxf.com/", wsdlLocation = "http://127.0.0.1:34567/weather?wsdl")
public class WeatherServiceInterfaceService
    extends Service
{

    private final static URL WEATHERSERVICEINTERFACESERVICE_WSDL_LOCATION;
    private final static WebServiceException WEATHERSERVICEINTERFACESERVICE_EXCEPTION;
    private final static QName WEATHERSERVICEINTERFACESERVICE_QNAME = new QName("http://weathde.cxf.com/", "WeatherServiceInterfaceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://127.0.0.1:34567/weather?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WEATHERSERVICEINTERFACESERVICE_WSDL_LOCATION = url;
        WEATHERSERVICEINTERFACESERVICE_EXCEPTION = e;
    }

    public WeatherServiceInterfaceService() {
        super(__getWsdlLocation(), WEATHERSERVICEINTERFACESERVICE_QNAME);
    }

    public WeatherServiceInterfaceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), WEATHERSERVICEINTERFACESERVICE_QNAME, features);
    }

    public WeatherServiceInterfaceService(URL wsdlLocation) {
        super(wsdlLocation, WEATHERSERVICEINTERFACESERVICE_QNAME);
    }

    public WeatherServiceInterfaceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WEATHERSERVICEINTERFACESERVICE_QNAME, features);
    }

    public WeatherServiceInterfaceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WeatherServiceInterfaceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WeatherServiceInterface
     */
    @WebEndpoint(name = "WeatherServiceInterfacePort")
    public WeatherServiceInterface getWeatherServiceInterfacePort() {
        return super.getPort(new QName("http://weathde.cxf.com/", "WeatherServiceInterfacePort"), WeatherServiceInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WeatherServiceInterface
     */
    @WebEndpoint(name = "WeatherServiceInterfacePort")
    public WeatherServiceInterface getWeatherServiceInterfacePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://weathde.cxf.com/", "WeatherServiceInterfacePort"), WeatherServiceInterface.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WEATHERSERVICEINTERFACESERVICE_EXCEPTION!= null) {
            throw WEATHERSERVICEINTERFACESERVICE_EXCEPTION;
        }
        return WEATHERSERVICEINTERFACESERVICE_WSDL_LOCATION;
    }

}

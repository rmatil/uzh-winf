
package ch.uzh.winf.stockservice;

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
@WebServiceClient(name = "StockServiceImplService", targetNamespace = "http://stockservice.sparctron.wi.imrg.ifi.uzh.ch/", wsdlLocation = "http://wi.imrg.ifi.uzh.ch:8080/stockService?wsdl")
public class StockServiceImplService
    extends Service
{

    private final static URL STOCKSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException STOCKSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName STOCKSERVICEIMPLSERVICE_QNAME = new QName("http://stockservice.sparctron.wi.imrg.ifi.uzh.ch/", "StockServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://wi.imrg.ifi.uzh.ch:8080/stockService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        STOCKSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        STOCKSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public StockServiceImplService() {
        super(__getWsdlLocation(), STOCKSERVICEIMPLSERVICE_QNAME);
    }

    public StockServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), STOCKSERVICEIMPLSERVICE_QNAME, features);
    }

    public StockServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, STOCKSERVICEIMPLSERVICE_QNAME);
    }

    public StockServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, STOCKSERVICEIMPLSERVICE_QNAME, features);
    }

    public StockServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public StockServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns StockService
     */
    @WebEndpoint(name = "StockServiceImplPort")
    public StockService getStockServiceImplPort() {
        return super.getPort(new QName("http://stockservice.sparctron.wi.imrg.ifi.uzh.ch/", "StockServiceImplPort"), StockService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns StockService
     */
    @WebEndpoint(name = "StockServiceImplPort")
    public StockService getStockServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://stockservice.sparctron.wi.imrg.ifi.uzh.ch/", "StockServiceImplPort"), StockService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (STOCKSERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw STOCKSERVICEIMPLSERVICE_EXCEPTION;
        }
        return STOCKSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}

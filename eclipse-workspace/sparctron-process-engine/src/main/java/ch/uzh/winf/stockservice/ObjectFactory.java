
package ch.uzh.winf.stockservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ch.uzh.ifi.imrg.wi.sparctron.stockservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetComponentIdByName_QNAME = new QName("http://stockservice.sparctron.wi.imrg.ifi.uzh.ch/", "getComponentIdByName");
    private final static QName _GetQuantityInStock_QNAME = new QName("http://stockservice.sparctron.wi.imrg.ifi.uzh.ch/", "getQuantityInStock");
    private final static QName _GetQuantityInStockResponse_QNAME = new QName("http://stockservice.sparctron.wi.imrg.ifi.uzh.ch/", "getQuantityInStockResponse");
    private final static QName _GetComponentIdByNameResponse_QNAME = new QName("http://stockservice.sparctron.wi.imrg.ifi.uzh.ch/", "getComponentIdByNameResponse");
    private final static QName _GetComponentNameByIdResponse_QNAME = new QName("http://stockservice.sparctron.wi.imrg.ifi.uzh.ch/", "getComponentNameByIdResponse");
    private final static QName _GetComponentNameById_QNAME = new QName("http://stockservice.sparctron.wi.imrg.ifi.uzh.ch/", "getComponentNameById");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ch.uzh.ifi.imrg.wi.sparctron.stockservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetComponentIdByNameResponse }
     * 
     */
    public GetComponentIdByNameResponse createGetComponentIdByNameResponse() {
        return new GetComponentIdByNameResponse();
    }

    /**
     * Create an instance of {@link GetComponentNameByIdResponse }
     * 
     */
    public GetComponentNameByIdResponse createGetComponentNameByIdResponse() {
        return new GetComponentNameByIdResponse();
    }

    /**
     * Create an instance of {@link GetComponentNameById }
     * 
     */
    public GetComponentNameById createGetComponentNameById() {
        return new GetComponentNameById();
    }

    /**
     * Create an instance of {@link GetComponentIdByName }
     * 
     */
    public GetComponentIdByName createGetComponentIdByName() {
        return new GetComponentIdByName();
    }

    /**
     * Create an instance of {@link GetQuantityInStock }
     * 
     */
    public GetQuantityInStock createGetQuantityInStock() {
        return new GetQuantityInStock();
    }

    /**
     * Create an instance of {@link GetQuantityInStockResponse }
     * 
     */
    public GetQuantityInStockResponse createGetQuantityInStockResponse() {
        return new GetQuantityInStockResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetComponentIdByName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stockservice.sparctron.wi.imrg.ifi.uzh.ch/", name = "getComponentIdByName")
    public JAXBElement<GetComponentIdByName> createGetComponentIdByName(GetComponentIdByName value) {
        return new JAXBElement<GetComponentIdByName>(_GetComponentIdByName_QNAME, GetComponentIdByName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuantityInStock }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stockservice.sparctron.wi.imrg.ifi.uzh.ch/", name = "getQuantityInStock")
    public JAXBElement<GetQuantityInStock> createGetQuantityInStock(GetQuantityInStock value) {
        return new JAXBElement<GetQuantityInStock>(_GetQuantityInStock_QNAME, GetQuantityInStock.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuantityInStockResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stockservice.sparctron.wi.imrg.ifi.uzh.ch/", name = "getQuantityInStockResponse")
    public JAXBElement<GetQuantityInStockResponse> createGetQuantityInStockResponse(GetQuantityInStockResponse value) {
        return new JAXBElement<GetQuantityInStockResponse>(_GetQuantityInStockResponse_QNAME, GetQuantityInStockResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetComponentIdByNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stockservice.sparctron.wi.imrg.ifi.uzh.ch/", name = "getComponentIdByNameResponse")
    public JAXBElement<GetComponentIdByNameResponse> createGetComponentIdByNameResponse(GetComponentIdByNameResponse value) {
        return new JAXBElement<GetComponentIdByNameResponse>(_GetComponentIdByNameResponse_QNAME, GetComponentIdByNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetComponentNameByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stockservice.sparctron.wi.imrg.ifi.uzh.ch/", name = "getComponentNameByIdResponse")
    public JAXBElement<GetComponentNameByIdResponse> createGetComponentNameByIdResponse(GetComponentNameByIdResponse value) {
        return new JAXBElement<GetComponentNameByIdResponse>(_GetComponentNameByIdResponse_QNAME, GetComponentNameByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetComponentNameById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stockservice.sparctron.wi.imrg.ifi.uzh.ch/", name = "getComponentNameById")
    public JAXBElement<GetComponentNameById> createGetComponentNameById(GetComponentNameById value) {
        return new JAXBElement<GetComponentNameById>(_GetComponentNameById_QNAME, GetComponentNameById.class, null, value);
    }

}

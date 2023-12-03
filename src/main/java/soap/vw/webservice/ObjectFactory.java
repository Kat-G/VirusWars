
package soap.vw.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap.vw.webservice package. 
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

    private final static QName _AddClient_QNAME = new QName("http://connect.soap/", "addClient");
    private final static QName _AddClientResponse_QNAME = new QName("http://connect.soap/", "addClientResponse");
    private final static QName _FindPlayer_QNAME = new QName("http://connect.soap/", "findPlayer");
    private final static QName _FindPlayerResponse_QNAME = new QName("http://connect.soap/", "findPlayerResponse");
    private final static QName _FirstMove_QNAME = new QName("http://connect.soap/", "firstMove");
    private final static QName _FirstMoveResponse_QNAME = new QName("http://connect.soap/", "firstMoveResponse");
    private final static QName _SendToClient_QNAME = new QName("http://connect.soap/", "sendToClient");
    private final static QName _SendToClientResponse_QNAME = new QName("http://connect.soap/", "sendToClientResponse");
    private final static QName _SetMove_QNAME = new QName("http://connect.soap/", "setMove");
    private final static QName _SetMoveResponse_QNAME = new QName("http://connect.soap/", "setMoveResponse");
    private final static QName _SkipMove_QNAME = new QName("http://connect.soap/", "skipMove");
    private final static QName _SkipMoveResponse_QNAME = new QName("http://connect.soap/", "skipMoveResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap.vw.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddClient }
     * 
     */
    public AddClient createAddClient() {
        return new AddClient();
    }

    /**
     * Create an instance of {@link AddClientResponse }
     * 
     */
    public AddClientResponse createAddClientResponse() {
        return new AddClientResponse();
    }

    /**
     * Create an instance of {@link FindPlayer }
     * 
     */
    public FindPlayer createFindPlayer() {
        return new FindPlayer();
    }

    /**
     * Create an instance of {@link FindPlayerResponse }
     * 
     */
    public FindPlayerResponse createFindPlayerResponse() {
        return new FindPlayerResponse();
    }

    /**
     * Create an instance of {@link FirstMove }
     * 
     */
    public FirstMove createFirstMove() {
        return new FirstMove();
    }

    /**
     * Create an instance of {@link FirstMoveResponse }
     * 
     */
    public FirstMoveResponse createFirstMoveResponse() {
        return new FirstMoveResponse();
    }

    /**
     * Create an instance of {@link SendToClient }
     * 
     */
    public SendToClient createSendToClient() {
        return new SendToClient();
    }

    /**
     * Create an instance of {@link SendToClientResponse }
     * 
     */
    public SendToClientResponse createSendToClientResponse() {
        return new SendToClientResponse();
    }

    /**
     * Create an instance of {@link SetMove }
     * 
     */
    public SetMove createSetMove() {
        return new SetMove();
    }

    /**
     * Create an instance of {@link SetMoveResponse }
     * 
     */
    public SetMoveResponse createSetMoveResponse() {
        return new SetMoveResponse();
    }

    /**
     * Create an instance of {@link SkipMove }
     * 
     */
    public SkipMove createSkipMove() {
        return new SkipMove();
    }

    /**
     * Create an instance of {@link SkipMoveResponse }
     * 
     */
    public SkipMoveResponse createSkipMoveResponse() {
        return new SkipMoveResponse();
    }

    /**
     * Create an instance of {@link Player }
     * 
     */
    public Player createPlayer() {
        return new Player();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddClient }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddClient }{@code >}
     */
    @XmlElementDecl(namespace = "http://connect.soap/", name = "addClient")
    public JAXBElement<AddClient> createAddClient(AddClient value) {
        return new JAXBElement<AddClient>(_AddClient_QNAME, AddClient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddClientResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddClientResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://connect.soap/", name = "addClientResponse")
    public JAXBElement<AddClientResponse> createAddClientResponse(AddClientResponse value) {
        return new JAXBElement<AddClientResponse>(_AddClientResponse_QNAME, AddClientResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindPlayer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindPlayer }{@code >}
     */
    @XmlElementDecl(namespace = "http://connect.soap/", name = "findPlayer")
    public JAXBElement<FindPlayer> createFindPlayer(FindPlayer value) {
        return new JAXBElement<FindPlayer>(_FindPlayer_QNAME, FindPlayer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindPlayerResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindPlayerResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://connect.soap/", name = "findPlayerResponse")
    public JAXBElement<FindPlayerResponse> createFindPlayerResponse(FindPlayerResponse value) {
        return new JAXBElement<FindPlayerResponse>(_FindPlayerResponse_QNAME, FindPlayerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FirstMove }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FirstMove }{@code >}
     */
    @XmlElementDecl(namespace = "http://connect.soap/", name = "firstMove")
    public JAXBElement<FirstMove> createFirstMove(FirstMove value) {
        return new JAXBElement<FirstMove>(_FirstMove_QNAME, FirstMove.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FirstMoveResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FirstMoveResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://connect.soap/", name = "firstMoveResponse")
    public JAXBElement<FirstMoveResponse> createFirstMoveResponse(FirstMoveResponse value) {
        return new JAXBElement<FirstMoveResponse>(_FirstMoveResponse_QNAME, FirstMoveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendToClient }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SendToClient }{@code >}
     */
    @XmlElementDecl(namespace = "http://connect.soap/", name = "sendToClient")
    public JAXBElement<SendToClient> createSendToClient(SendToClient value) {
        return new JAXBElement<SendToClient>(_SendToClient_QNAME, SendToClient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendToClientResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SendToClientResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://connect.soap/", name = "sendToClientResponse")
    public JAXBElement<SendToClientResponse> createSendToClientResponse(SendToClientResponse value) {
        return new JAXBElement<SendToClientResponse>(_SendToClientResponse_QNAME, SendToClientResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetMove }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SetMove }{@code >}
     */
    @XmlElementDecl(namespace = "http://connect.soap/", name = "setMove")
    public JAXBElement<SetMove> createSetMove(SetMove value) {
        return new JAXBElement<SetMove>(_SetMove_QNAME, SetMove.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetMoveResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SetMoveResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://connect.soap/", name = "setMoveResponse")
    public JAXBElement<SetMoveResponse> createSetMoveResponse(SetMoveResponse value) {
        return new JAXBElement<SetMoveResponse>(_SetMoveResponse_QNAME, SetMoveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SkipMove }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SkipMove }{@code >}
     */
    @XmlElementDecl(namespace = "http://connect.soap/", name = "skipMove")
    public JAXBElement<SkipMove> createSkipMove(SkipMove value) {
        return new JAXBElement<SkipMove>(_SkipMove_QNAME, SkipMove.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SkipMoveResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SkipMoveResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://connect.soap/", name = "skipMoveResponse")
    public JAXBElement<SkipMoveResponse> createSkipMoveResponse(SkipMoveResponse value) {
        return new JAXBElement<SkipMoveResponse>(_SkipMoveResponse_QNAME, SkipMoveResponse.class, null, value);
    }

}


package soap.vw.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for player complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="player"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="firstMove" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="index" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="moves" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="playerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ready" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="response" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "player", propOrder = {
    "firstMove",
    "index",
    "moves",
    "playerName",
    "ready",
    "response"
})
public class Player {

    protected boolean firstMove;
    protected int index;
    protected int moves;
    protected String playerName;
    protected boolean ready;
    protected String response;

    /**
     * Gets the value of the firstMove property.
     * 
     */
    public boolean isFirstMove() {
        return firstMove;
    }

    /**
     * Sets the value of the firstMove property.
     * 
     */
    public void setFirstMove(boolean value) {
        this.firstMove = value;
    }

    /**
     * Gets the value of the index property.
     * 
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets the value of the index property.
     * 
     */
    public void setIndex(int value) {
        this.index = value;
    }

    /**
     * Gets the value of the moves property.
     * 
     */
    public int getMoves() {
        return moves;
    }

    /**
     * Sets the value of the moves property.
     * 
     */
    public void setMoves(int value) {
        this.moves = value;
    }

    /**
     * Gets the value of the playerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets the value of the playerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlayerName(String value) {
        this.playerName = value;
    }

    /**
     * Gets the value of the ready property.
     * 
     */
    public boolean isReady() {
        return ready;
    }

    /**
     * Sets the value of the ready property.
     * 
     */
    public void setReady(boolean value) {
        this.ready = value;
    }

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponse(String value) {
        this.response = value;
    }

}

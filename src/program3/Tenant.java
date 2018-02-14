/*
 * Hai Le, Scott Mitchell
 * 02/27/18
 * Program 3
 * The purpose of this program is to simulate a keypad at apartment entrance.
 */
package program3;

public class Tenant {
    // stores apartnemnt number, pincode and first name of a tenant
    private int aptNo;
    private int pin;
    private String name;
    
    // default constructor
    public Tenant() {
        aptNo = -1;
        pin = -1;
        name = "";
    } // end Tenant
    
    // overloaded constructor
    public Tenant(int apt, int pin, String name) {
        aptNo = apt;
        this.pin = pin;
        this.name = name;
    } // end Tenant

    // Getters and Setters for apartment number
    public int getAptNo() {
        return aptNo;
    } // end getAptNo

    public void setAptNo(int aptNo) {
        this.aptNo = aptNo;
    } // end setAptNo

    // Getters and Setters for apartment pincode
    public int getPin() {
        return pin;
    } // end getPin

    public void setPin(int pin) {
        this.pin = pin;
    } // end setPin

    // Getters and Setters for tenant first name
    public String getName() {
        return name;
    } // end getName

    public void setName(String name) {
        this.name = name;
    } // end setName
} // end Tenant

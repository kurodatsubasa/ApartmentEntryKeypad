/*
 * Hai Le, Scott Mitchell
 * 02/27/18
 * Program 3
 * This class holds methods and fields for tenant objects. data from tenants.txt
 * is used to instantiate objects of this class.
 */
package program3;

public class Tenant {
    
    //private fields for apartment number, pin number, and tenant name
    private int aptNo;
    private int pin;
    private String name;
    
    //constructor to instantiate private fields to defaults
    public Tenant() {
        aptNo = -1;
        pin = -1;
        name = "";
    }
    
    //contructor that fills private fields
    public Tenant(int apt, int pin, String name) {
        aptNo = apt;
        this.pin = pin;
        this.name = name;
    }

    //getters and setters for class's private fields.
    public int getAptNo() {
        return aptNo;
    }

    public void setAptNo(int aptNo) {
        this.aptNo = aptNo;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

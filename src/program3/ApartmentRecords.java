/*
 * Hai Le, Scott Mitchell
 * 02/27/18
 * Program 3
 * The purpose of this program is to simulate a keypad at apartment entrance.
 */
package program3;

import java.util.ArrayList;

// This class serves the purpose of storing the list of tenants and processing it
public class ApartmentRecords {
    // stores a list of tenants
    private ArrayList<Tenant> tenants;

    // default constructor
    public ApartmentRecords() {
        tenants = new ArrayList<>();
    } // end ApartmentRecords

    // adds tenant to the list
    public void add(String line) {
        try {
            // splits the line into apartment number, pincode and name
            String[] tokens = line.split(" ");
            tenants.add(new Tenant(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), tokens[2]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Data format error! The following tenant entry "
                    + "could not be added to the records:\n" + line + "\n");
        } // end try catch
    } // end add

    // checks tenant access by looking for apartment number and pincode in the list 
    public String checkTenant(String input) {
        try {
            // splits the string into apartment number and pincode
            String[] tokens = input.split("#");
            // checks if the input apartmenet number exists
            Tenant tenant = findByApt(Integer.parseInt(tokens[0]));
            // if apartment number and pincode match, returns the welcome message
            if (tenant != null && tenant.getPin() == Integer.parseInt(tokens[1])) {
                return "Welcome home, " + tenant.getName();
            }
            // catches the case where the user presses '#' without actually inputting
            // apartment number and/or pincode
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        } // end try catch
        
        // if apartment number and/or pincode do not match, returns error message
        return "Invalid apartment or PIN number!";
    } // end checkTenant

    // searches for tenant by apartment number
    private Tenant findByApt(int apt) {
        // loops through the arraylist and compares the apartment number
        for (Tenant tenant : tenants) {
            if (tenant.getAptNo() == apt) {
                // if apt no matches, returns tenant associated with that apt no
                return tenant;
            } // end if
        } // end for
        // if no match, returns null reference
        return null;
    } // end findByApt

    // Getters and Setters for arraylist of tenants
    public ArrayList<Tenant> getTenants() {
        return tenants;
    } // end getTenants

    public void setTenants(ArrayList<Tenant> tenants) {
        this.tenants = tenants;
    } // end setTenants
} // end setTenants

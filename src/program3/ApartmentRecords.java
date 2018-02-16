/*
 * Hai Le, Scott Mitchell
 * 02/27/18
 * Program 3
 * This class holds fields and methods for using Tenant.java
 */
package program3;

import java.util.ArrayList;

public class ApartmentRecords {
    
    //private field, arraylist to hold tenant objects
    private ArrayList<Tenant> tenants;
    
    //constructor instantiates arraylist of Tenant objects
    ApartmentRecords() {
        tenants = new ArrayList<>();
    }

    //adds a tenant to tenants. this method is only used in FileIO.java while reading source db
    public void add(String line) {
        try {
            String[] tokens = line.split(" ");
            tenants.add(new Tenant(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), tokens[2]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Data format error! The following tenant entry "
                    + "could not be added to the records:\n" + line + "\n");
        }
    }

    //searches for a tenant
    public String findTenant(String input) {
        //try to find the tenant.
        //If there's a problem with the formatting or arrayoutofbounds print it to the console
        //If its found return a welcome message with the tenants name.
        //If no exception and not found return access denied message
        try {
            //split the input using # as delimiter
            String[] tokens = input.split("#");
            //intantiate tenant, searching for them by apartment number.
            Tenant tenant = findByApt(Integer.parseInt(tokens[0]));
            //if findByApt worked and the pin is correct return the welcome message
            if (tenant != null && tenant.getPin() == Integer.parseInt(tokens[1])) {
                return "Welcome home, " + tenant.getName();
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
           System.out.println(e);
        }
            return "Invalid apartment or PIN number!";
        }

    
    //searches for tenant using apartment number. returns null if not found.
    private Tenant findByApt(int apt) {
        for (Tenant tenant : tenants) {
            if (tenant.getAptNo() == apt) {
                return tenant;
            }
        }
        return null;
    }

    //getters and setters for tenants
    public ArrayList<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(ArrayList<Tenant> tenants) {
        this.tenants = tenants;
    }
    

}

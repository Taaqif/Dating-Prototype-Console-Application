//
//  @ Project : ICT373 Assignment 1
//  @ File Name : CustomerHandler.java
//  @ Details : A cusotmerhnadler class to manage a list of cusotmers
//  @ Assumptions : The programmer knows about each super and subclass and knows their functions 
//                  There is an available Cusotmer superclass, with derrived responder and advertiser classes class
//  @ Date : 9/04/2017
//  @ Author : Taaqif
//
package ict373assignment1;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Customer Handler object to handle a list of customers. Uses a hash map as the main storage type with the key being the login name and the corresponding object as the value. 
 * @author Taaqif
 */
public class CustomerHandler {
    private final HashMap<String, Customer> customers = new HashMap();
    private Customer loggedInCustomer;
    
    /**
     * Adds a new customer to the hashmap
     * @param newcustomer the customer to add
     * @throws IllegalArgumentException if customer with similar login exists
     */
    public void addCustomer(Customer newcustomer) throws IllegalArgumentException{
        if(userExists(newcustomer.Getlogin())){
            throw new IllegalArgumentException("Customer with same login exsists.\nChoose different login name");     
        }
            
        customers.put(newcustomer.Getlogin(), newcustomer);
    }

    /**
     * Deletes a customer from the hashmap, does error handling
     * @param login the login name to remove
     * @param pass the corresponding password
     */
    public void deleteCustomer(String login, String pass){
        try{
            if (userExists(login)){
                if (customers.get(login).checkPassword(pass)){
                    customers.remove(login);
                    System.out.println("Customer deleted");
                }else{
                    throw new Exception("ERROR: Trying delete customer. Password does not match");
                }
            } else {
                throw new Exception("ERROR: Trying to delete customer. Customer not found");
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    /**
     * checks if  user exists in the hashmap
     * @param login the login to check
     * @return true if login exists in the hashmap
     */
    public boolean userExists(String login){
        return customers.containsKey(login);
    }

    /**
     * Sets the logged in customer (for easier access later on) using user name or password?
     * @param customerLogin the customer to attempt to login to
     * @param customerPass the password to check
     * @throws Exception if there is no customer with that login/pass
     */
    public void SetLoggedInCustomer(String customerLogin, String customerPass) throws Exception{
        if (userExists(customerLogin) && GetCustomer(customerLogin).checkPassword(customerPass)){
            this.loggedInCustomer = GetCustomer(customerLogin);
        }else{
            throw new Exception("ERROR: Trying to log in. User not found");
        }
        
    }

    /**
     * Gets the customer by login name
     * @param login the login name to find customer from 
     * @return the customer object with the specified login name
     */
    public Customer GetCustomer(String login){
        return customers.get(login);
    }

    /**
     * gets the logged in customer
     * @return the logged in customer 
     */
    public Customer GetLoggedInCustomer(){
        return loggedInCustomer;
    }

    /**
     * Removes the logged in customer 
     */
    public void RemoveLoggedInCustomer(){
        this.loggedInCustomer = null;
    }

    /**
     * displays the customer details based on the specified type
     * @param type the type to check, all, responder,advertiser
     */
    public void displayAllCustomerDetails(String type){
        boolean found = false;
        for(HashMap.Entry<String, Customer> entry : customers.entrySet()) {
            Customer value = entry.getValue();
            switch(type.toLowerCase()){
                case "all":
                    if (value instanceof Customer){
                        found = true;
                        value.displayDetails();
                    }
                break;
                case "responder": 
                    if (value instanceof Responder){
                        found = true;
                        value.displayDetails();
                    }
                break;
                case "advertiser":
                    if (value instanceof Advertiser){
                        found = true;
                        value.displayDetails();
                    }
                break;
                default: 
                    throw new IllegalArgumentException("Not a valid type");
            }
            
        }
        if (!found){
            System.out.println("No records found");
        }
    }
    /**
     * gets the responder matches as an arrayList, very hacky, needs work. 
     * @param r the responder to return matches from 
     * @return an arraylist containing all the matches for a responder
     */
    public ArrayList<Advertiser> getResponderMatches(Responder r){
        ArrayList<Advertiser> tmpmatches = new ArrayList<>();
        //for each entry of the customers
            for(HashMap.Entry<String, Customer> entry : customers.entrySet()) {
                //check if value is advertiser
                Customer value = entry.getValue();
                if (value instanceof Advertiser){
                    //check if responder matches with advertised partner
                    if (((Advertiser) value).GetsoughtPartner().CheckPartnerMatch(loggedInCustomer)){
                        //add advertisert to the arraylist
                        tmpmatches.add(((Advertiser) value));
                    }
                }
            }
        return tmpmatches;
    }
    /**
     * Checks if there is a logged in customer
     * @return true if there is a logged in customer
     */
    public boolean hasLoggedinCustomer(){
        return loggedInCustomer != null;
    }
}

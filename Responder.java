//
//  @ Project : ICT373 Assignment 1
//  @ File Name : Responder.java
//  @ Details : Responder class which extends customer. used to manage a responder object
//  @ Assumptions : An customer abstract class needs to be available
//  @ Date : 9/04/2017
//  @ Author : Taaqif
//


package ict373assignment1;

/**
 * Specific responder class derived from customer super class. 
 * @author Taaqif
 */
public class Responder extends Customer{

    /**
     * Default constructor for responder
     */
    public Responder(){
        
    }

    /**
     * Specific constructor for responder to quickly initialism a new responder
     * @param newlogin login to set
     * @param newpassword password to set
     * @param newgender gender to set, only m/f
     * @param newage age to set, only 18-125 allowed
     * @param newincome income to set, must be positive
     */
    public Responder(String newlogin, String newpassword, char newgender, int newage, double newincome){
            super(newlogin, newpassword,  newgender, newage, newincome);   
    }

    /**
     * Prompt all details, nothing fancy other than specific heading
     */
    @Override
    public void promptAllDetails(){
        System.out.println("ENTERING RESPONDER DETAILS");
        super.promptAllDetails();
                        
    }

    /**
     * Display responder specific details
     */
    @Override
    public void displayDetails(){
            System.out.println("-------\nDetails for: "+ this.Getlogin()+ "\n-------");
            System.out.println("  Type: Responder");
            System.out.println("  Gender: " + this.Getgender());
            System.out.println("  Age: " + this.Getage());
            System.out.println("  Income: $" + this.Getincome());
            System.out.println("-------");
    }
}

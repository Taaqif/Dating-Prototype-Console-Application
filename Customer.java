//
//  @ Project : ICT373 Assignment 1
//  @ File Name : Customer.java
//  @ Details : Customer abstract class which is meant to be extended. 
//              Provides a base from which specific customers can be derived from. 
//              Contains generic information each cusotmer should have
//  @ Assumptions : Will be extended
//  @ Date : 9/04/2017
//  @ Author : Taaqif
//
package ict373assignment1;

import java.util.Scanner;

/**
 * Customer superclass which is meant to be extended
 * Provides common details for all customers
 * @author Taaqif
 */
public abstract class Customer {
	private String login;
	private String password;
	private char gender;
	private int age;
	private double income;
 
    /**
     * Scanner variable to be parsed on to all derived classes. Useful for input
     */
    protected Scanner s; 

    /**
     * Error boolean as all the derived classes seem to use this for error checking. Useful to have
     */
    protected boolean error = false;
        
    /**
     * Display details for each derived class is required as the base details may be the same but some could include more specific details
     */
    public abstract void displayDetails();
        
    /**
     * Easy method to prompt the user for all details, if needed
     */
    public void promptAllDetails(){
            Promptlogin();
            Promptpassword();
            Promptgender();
            Promptage();
            Promptincome();
        }

    /**
     * Prompts the user for a login name and uses the @Setlogin method to error check
     */
    public void Promptlogin(){
            

            do {
                System.out.print("Login Name: ");
                try {
                    String tmplogin = s.nextLine();
                    this.Setlogin(tmplogin);
                    error = false;
                }catch (Exception e){
                    error = true;
                    System.out.println(e.getMessage());

                }
            }while(error);
            
        }

    /**
     * Prompts the user for a password and uses the @Setpassword method to error check
     */
    public void Promptpassword(){
            

            do {
                System.out.print("Password: ");
                try {
                    String tmppassword = s.nextLine();
                    this.Setpassword(tmppassword);
                    error = false;
                }catch (Exception e){
                    error = true;
                    System.out.println(e.getMessage());

                }
            }while(error);

        }

    /**
     * Prompts the user for a gender and uses the @Setgender method to error check
     */
    public void Promptgender(){
           

            do {
                System.out.print("Gender m/f: ");
                try {
                    String tmpgender = s.nextLine();
                    this.Setgender(tmpgender.charAt(0));
                    error = false;
                }catch (Exception e){
                    error = true;
                    System.out.println(e.getMessage());

                }
            }while(error);

        }

    /**
     *Prompts the user for a age and uses the @Setage method to error check
     */
    public void Promptage(){
            
            
            do {
                System.out.print("Age: ");
                try {
                    String tmpage = s.nextLine();
                    this.Setage(Integer.parseInt(tmpage));
                    error = false;
                }catch (NumberFormatException e){
                    error = true;
                    System.out.println("Not a valid number");
                }catch (Exception e){
                    error = true;
                    System.out.println(e.getMessage());
                }
            }while(error);
        }

    /**
     * Prompts the user for a income and uses the @Setincome method to error check
     */
    public void Promptincome(){
            
            do {
                System.out.print("Income: $");
                try {
                    String tmpincome = s.nextLine();
                    this.Setincome(Double.parseDouble(tmpincome));
                    error = false;
                }catch (NumberFormatException e){
                    error = true;
                    System.out.println("Not a valid number");
                    System.out.print("Try again: $");
                }catch (Exception e){
                    error = true;
                    System.out.println(e.getMessage());
                }
            }while(error);

        }

    /**
     * Allows the password for a user to be checked against a string. This is used inplace of getPassword() for enhanced security (Password never leaves object)
     * @param pass the password to check against
     * @return true if password is correct
     */
    public boolean checkPassword(String pass){
            return this.password.equals(pass);
        }
    /**
     * Constructor to create a customer with all variables in place
     * @param newlogin the login to set
     * @param newpassword the password to set
     * @param newgender the gender to set
     * @param newage the age to set
     * @param newincome the income to set
     */
    public Customer(String newlogin, String newpassword, char newgender, int newage, double newincome){
        this.s = new Scanner(System.in);
        Setlogin(newlogin);
        Setpassword(newpassword); 
        Setgender(newgender);
        Setage(newage);
        Setincome(newincome); 
    }
    /**
     * Default constructor for Customer
     */
    public Customer(){
        this.s = new Scanner(System.in);
        
    }
        
    /**
     * Sets the login name for the customer
     * @param newlogin the login to set
     */
    public final void Setlogin(String newlogin) {
        if (newlogin.contains(" ") || newlogin.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: Login can not contain spaces");
        }else{
            this.login = newlogin;
        }
    }
	
    /**
     * Gets the login name for the customer 
     * @return the login name as a string 
     */
    public String Getlogin() {
            return this.login;
	}
	
    /**
     * Sets the password for the customer
     * @param newpassword the password to set, cannot be empty and must be 50 characters (possibly silly?)
     */
    public final void Setpassword(String newpassword) {
        if (newpassword.contains(" ") || newpassword.trim().isEmpty() || newpassword.length() > 50) {
            throw new IllegalArgumentException("ERROR: Password can not contain spaces and must be under 50 characters");
        }else{
            this.password = newpassword;
        }
    }
	
    /**
     * Gets the password for the customer. Private so that password never leaves the object. Not used
     * @return the gender as a string
     */
    private String Getpassword() {
            return this.password;
	}
	
    /**
     * Sets the gender for the customer
     * Throws an exception if invalid option entered. Only m/f allowed 
     * @param newgender Can only be either m or f. Not case sensitive
     */
    public final void Setgender(char newgender){
        switch (Character.toLowerCase(newgender)){
            case 'm':
                this.gender = 'm';
            break; 
            case 'f':
                this.gender = 'm';
            break; 
            default:
                throw new IllegalArgumentException("ERROR: Invalid gender option, only m/f");
        }
    }
	
    /**
     * Gets the gender for the customer
     * @return the gener as a char
     */
    public char Getgender() {
            return this.gender;
	}
	
    /**
     * Sets the age for the customer
     * Checks the age range between 18 and 125
     * @param newage can only be between 18 and 125
     */
    public final void Setage(int newage){
        if (newage < 18 || newage > 125){
            throw new IllegalArgumentException("ERROR: Age not in range (18-125)");
        } else {
            this.age = newage;
        }

	}
	
    /**
     * Gets the age for the customer 
     * @return the age as a int
     */
    public int Getage() {
            return this.age;
	}
	
    /**
     * Sets the income for the customer 
     * Checks if the income is within 0 and 999999
     * @param newincome the income as a double
     */
    public final void Setincome(double newincome) {

        if (newincome >= 0 && newincome <= 9999999){
            this.income = newincome;
        } else {
            throw new IllegalArgumentException("ERROR: Income not in range");
        }

    }
	
    /**
     * Gets the income of the customer
     * @return the income as a double
     */
    public double Getincome() {
            return this.income;
    }

}

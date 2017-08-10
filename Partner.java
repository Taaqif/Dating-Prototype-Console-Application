//
//  @ Project : ICT373 Assignment 1
//  @ File Name : Partner.java
//  @ Details : Partner class to manage the preffered partner for an advertiser 
//  @ Assumptions : An advertiser class needs to be available
//                  There is Range class whcih helps with the range of values
//  @ Date : 9/04/2017
//  @ Author : Taaqif
//
package ict373assignment1;

import java.util.Scanner;

/**
 * A partner class which allows for the manipulation of a prefered partner such as gender, age and income ranges 
 * @author Taaqif
 */
public class Partner {
    private char gender;
    private Range<Integer> ageRange;
    private Range<Double> incomeRange;
    private boolean error;
    private final Scanner s;
    
    /**
     * Constructor to quickly initialize a partner object
     * @param newgender the gender to set
     * @param newageRange the age range to set
     * @param newincomeRange the income range to set
     */
    Partner(char newgender, Range newageRange, Range newincomeRange){
        this.s = new Scanner(System.in);
        this.Setgender(newgender);
        this.SetageRange(newageRange);
        this.SetincomeRange(newincomeRange);
    }
    /**
     * The default constructor to initialize a partner object  
     */
    Partner(){
        this.s = new Scanner(System.in);
        
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
     * @return the gender as a character, should be m/f
     */
    public char Getgender() {
            return this.gender;
    }

    /**
     * Checks the customer against a partner, uses the Range object to test if customer falls within the specifications of partner
     * @param c the customer to check against
     * @return true if the customer matches the partner description
     */
    public boolean CheckPartnerMatch(Customer c){
        
       return (this.Getgender() == c.Getgender() &&
               this.GetageRange().contains(c.Getage()) &&
               this.GetincomeRange().contains(c.Getincome()));
    }

    /**
     * Prompts the user to enter the gender for th customer, can only be m/f
     */
    public void Promptgender(){
            System.out.print("Gender m/f: ");

            do 
                try {
                    String tmpgender = s.nextLine();
                    this.Setgender(tmpgender.charAt(0));
                    error = false;
                }catch (Exception e){
                    error = true;
                    System.out.println(e.getMessage());
                    System.out.print("Try again: ");

                }
            while(error);

        }

    /**
     * Sets the age range using a range object
     * @param newageRange the range object to set
     */
    public void SetageRange(Range<Integer> newageRange) {
        
        ageRange = newageRange;
    }

    /**
     * Sets the age range using a min, max. Basically constructs a range object using those values
     * @param min the min range
     * @param max the max range
     */
    public void SetageRange(int min, int max) {
        ageRange = new Range(min,max);
    }

    /**
     * Prompts the user to enter an age range, does basic error checking 
     */
    public void PromptageRange(){
        int min = 0, max = 0;
        System.out.println("Age Range\nIf you make a mistake, enter 'r' to re-enter age range");
        
        do {
            System.out.print("Minimum age: "); 
            try {
                String tmpage = s.nextLine();
                if(tmpage.toLowerCase().charAt(0) != 'r'){
                    //cast to fit range type
                    min = Integer.parseInt(tmpage);

                    error = false;
                    
                    do {
                        System.out.print("Maximum age: "); 
                        try {
                            tmpage = s.nextLine();
                            if (tmpage.toLowerCase().charAt(0) != 'r'){
                                //cast to fit range type
                                max = Integer.parseInt(tmpage);
                                this.SetageRange(min,max);
                                
                                error = false;
                            }else{
                                error = true;
                                break;
                            }
                            
                        }catch (NumberFormatException e){
                            error = true;
                            System.out.println("Not a valid number");
                        }catch (Exception e){
                            error = true;
                            System.out.println(e.getMessage());
                        }
                    }while(error);
                }else{
                    error = true;
                }
            }catch (NumberFormatException e){
                error = true;
                System.out.println("Not a valid number");
            }
        }while(error);
    }

    /**
     * Gets the age range as a range object
     * @return the age range object
     */
    public Range<Integer> GetageRange() {
        return ageRange;
    }

    /**
     * Sets the income range using a min, max. Basically constructs a range object using those values
     * @param min the min value to set
     * @param max the max value to set
     */
    public void SetincomeRange(double min, double max) {
        incomeRange = new Range(min,max);
    }

    /**
     * Sets the income range using a range object
     * @param newincomeRange the range object  to set
     */
    public void SetincomeRange(Range<Double> newincomeRange) {
        incomeRange = newincomeRange;
    }

    /**
     * Gets the income range
     * @return the income range object
     */
    public Range<Double> GetincomeRange() {
        return incomeRange;
    }

    /**
     * prompts the user for a range, does error checking
     */
    public void PromptincomeRange(){
        double min = 0, max = 0;
        System.out.println("Income Range\nIf you make a mistake, enter 'r' to re-enter income range");
        
        do {
            System.out.print("Minimum income: "); 
            try {
                String tmpincome = s.nextLine();
                if(tmpincome.toLowerCase().charAt(0) != 'r'){
                    //cast to fit range type
                    min = Double.parseDouble(tmpincome);

                    error = false;
                    
                    do {
                        System.out.print("Maximum income: "); 
                        try {
                            tmpincome = s.nextLine();
                            if (tmpincome.toLowerCase().charAt(0) != 'r'){
                                //cast to fit range type
                                max = Double.parseDouble(tmpincome);
                                this.SetincomeRange(min,max);
                                
                                error = false;
                            }else{
                                error = true;
                                break;
                            }
                            
                        }catch (NumberFormatException e){
                            error = true;
                            System.out.println("Not a valid number");
                        }catch (Exception e){
                            error = true;
                            System.out.println(e.getMessage());
                        }
                    }while(error);
                }else{
                    error = true;
                }
            }catch (NumberFormatException e){
                error = true;
                System.out.println("Not a valid number");
            }
        }while(error);
    }

    
}

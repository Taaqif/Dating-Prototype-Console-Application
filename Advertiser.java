//
//  @ Project : ICT373 Assignment 1
//  @ File Name : Advertiser.java
//  @ Details : Advertiser class which extends customer. used to manage an advertiser object
//  @ Assumptions : An cusotmer abstract class needs to be available
//                  There is an available Partner class
//  @ Date : 9/04/2017
//  @ Author : Taaqif
//
package ict373assignment1;

import java.util.HashMap;

/**
 * Advertiser subclass to customer, adds more details to the customer such as text advert and sought partner and messages
 * @author Taaqif
 */
public class Advertiser extends Customer{
	private String textAdvert;
	private Partner soughtPartner = new Partner();
        //hashmap representing the messages. Responders are not allowed more than one message 
        private final HashMap<Responder, String> replyMessages = new HashMap<>();
        /**
         * Default constructor to create advertiser
         */
        public Advertiser(){
            super();
        }
        /**
         * Constructor to allow creation of a advertiser with all details filled
         * @param newlogin the login to set
         * @param newpassword the password to set
         * @param newgender the gender to set
         * @param newage the age to set
         * @param newincome the income to set
         * @param newtextAdvert the advert to  set
         * @param newsoughtPartner  the partner to set
         */
        public Advertiser(String newlogin, String newpassword, char newgender, int newage, double newincome, String newtextAdvert, Partner newsoughtPartner){
            super(newlogin, newpassword,  newgender, newage, newincome);  
            this.textAdvert = newtextAdvert;
            this.soughtPartner = newsoughtPartner;
            
        }
        /**
         * Prompts the user to fill in more specific advertiser details 
         */
        @Override
        public void promptAllDetails(){
            System.out.println("ENTERING ADVERTISER DETAILS");
            super.promptAllDetails();
            this.PrompttextAdvert();
            this.PromptsoughtPartner();
            
        }
        /**
         * Overrides the display details to allow for a better view of advertiser specific details 
         */
        @Override
        public void displayDetails(){
            System.out.println("Details for: "+ this.Getlogin());
            System.out.println("  Type: Advertiser");
            System.out.println("  Gender: " + this.Getgender());
            System.out.println("  Age: " + this.Getage());
            System.out.println("  Income: $" + this.Getincome());
            System.out.println("  Text Advert: " + this.GettextAdvert());
            System.out.println("  --Sought Partner Details--");
            System.out.println("    Prefered gender: " + this.soughtPartner.Getgender());
            System.out.println("    Prefered age range: " + this.soughtPartner.GetageRange().getRangeString());
            System.out.println("    Prefered income range: $" + this.soughtPartner.GetincomeRange().getRangeString());
            System.out.println("-------");
        }
        /**
         * Prompts the user to enter a text advert
         */
        public void PrompttextAdvert(){
            System.out.print("Enter an advert to be broadcasted to potential responders: ");
            System.out.println("Remember to include details such as contact information :)");
            do 
                try {
                    String tmpadvert = s.nextLine();
                    this.SettextAdvert(tmpadvert);
                    error = false;
                }catch (Exception e){
                    error = true;
                    System.out.println(e.getMessage());
                    System.out.print("Try again: ");

                }
            while(error);
        }
        /**
         * Prompts the user to enter partner details 
         */
        public void PromptsoughtPartner(){
            System.out.println("ENTER SOUGHT PARTNER DETAILS");
            this.soughtPartner.Promptgender();
            this.soughtPartner.PromptageRange();
            this.soughtPartner.PromptincomeRange();
            
        }
        /**
         * Sets the text advert
         * @param newtextAdvert the string to set 
         */
	public void SettextAdvert(String newtextAdvert) {
            if (newtextAdvert.trim().isEmpty()){
                throw new IllegalArgumentException("Text advert cannot be blank");
            }else{
                this.textAdvert = newtextAdvert;
            }
	}
	/**
         * Gets the text advert
         * @return the advert to get
         */
	public String GettextAdvert() {
            return this.textAdvert;
	}
	/**
         * Sets the sought partner
         * @param newsoughtPartner the partner object to set 
         */
	public void SetsoughtPartner(Partner newsoughtPartner) {
            soughtPartner = newsoughtPartner;
	}
	/**
         * Gets the sought partner 
         * @return the sought partner object
         */
	public Partner GetsoughtPartner() {
            return soughtPartner;
	}
        /**
         * Adds a reply message to the hashmap of replies from a repsonder and replaces it
         * @param owner the owner of the message
         * @param newMessage the actual message
         */
	public void AddReplyMessage(Responder owner, String newMessage){
            
            //this will overwrite old messages 
            replyMessages.put(owner, newMessage);
           
           
            
        }
        /**
         * Removes a reply message from the hashmap from a repsonder and removes it
         * @param owner the owner of the message
         */
	public void RemoveReplyMessage(Responder owner){
            
            
            replyMessages.remove(owner);
           
           
            
        }
	/**
         * Returns the complete hashmap of the replies, needs work to make more intuitive 
         * @return the hasmhmap of replies
         */
	public HashMap<Responder, String> GetreplyMessages() {
            return this.replyMessages;
	}
}

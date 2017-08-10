//
//  @ Project : ICT373 Assignment 1
//  @ File Name : ICT373Assignment1.javaMain class resides here. Provides all menu options and handles user input
//  @ Assumptions : The client programmer knows about the specifications of the project
//  @ Date : 9/04/2017
//  @ Author : Taaqif
//
package ict373assignment1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * ICT373 Assignment 1 - A prototype program for a dating application. Allows for the basic manipulation of different customers, more specifically, Responders and Advertisers
 * @author Taaqif
 */
public class ICT373Assignment1 {

    /**
     * Main class
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DisplayStudentDetails();
        //set up application, add temporary users
        //all the same password, but it should not matter if they are
        CustomerHandler c = new CustomerHandler();
        c.addCustomer(new Responder("testResponder1","test",'m',18,111000.0));
        c.addCustomer(new Responder("testResponder2","test",'m',20,211000.0));
        c.addCustomer(new Responder("testResponder3","test",'f',45,411000.0));
        c.addCustomer(new Advertiser("testAdvertiser1","pwd",'m',18,100000,"Hi, looking for young weathy people",new Partner('f',new Range(18,20), new Range(210000.0,2000000.0))));
        c.addCustomer(new Advertiser("testAdvertiser2","pwd",'f',20,200000,"Hey, just looking for a bit of fun",new Partner('m',new Range(25,30), new Range(0.0,300000.0))));
        c.addCustomer(new Advertiser("testAdvertiser3","pwd",'f',25,350000,"Hello, I like computers",new Partner('m',new Range(18,50), new Range(0.0,112000.0))));

        System.out.println("-------\nWELCOME TO THE DATING PROTOTYPE\n-------\nMust be over 18 to use.");
        boolean exit = false;
        boolean error = false;
        do{
            
            System.out.println("Choose option");
            System.out.print("Options: \n 1. Login\n 2. Add customer\n 3. Delete customer\n 4. Display Customer Details\n q. Quit\n\nSelection: ");
            try{
                Scanner s = new Scanner(System.in);
                String tmp = s.nextLine();
                
                switch(tmp.toLowerCase().trim().charAt(0)){
                    case '1': 
                        promptLogin(c);
                    break;
                    case '2':
                        promptAddCusotmer(c);
                    break;
                    case '3':
                        promptDeleteCustomer(c);
                    break;
                    case '4':
                        propmptDisplayCustomerDetails(c);
                    break;
                    case 'q':
                        System.out.println("Exiting application...");
                        exit = true;
                        error = false;
                    break;
                    default:
                        error = true; 
                        throw new Exception("Invalid selection");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }while(!exit || error);
    }

    /**
     * Displays my student details
     */
    public static void DisplayStudentDetails(){
        System.out.println("Name: Taaqif Peck\nStudent Number: 32674337\nEnrolement: Internal\nTutor: Ferdous Sohel\nTutorial Time: Mon 12:30");
    }

    /**
     * Prompts the user to add a customer, from which the function will call the specific customer creation method
     * @param c The customer handler object to manipulate
     */
    public static void promptAddCusotmer(CustomerHandler c){
        System.out.println("-------\nEnter new customer details\n-------");
        System.out.println("Choose option");
        boolean exit= false;
        boolean error = false;
        do{
            System.out.print("Type of customer: \n 1. Advertiser\n 2. Responder\n q. Back\n\nSelection: ");
            try{
                Scanner s = new Scanner(System.in);
                String tmp = s.nextLine();
                
                switch(tmp.toLowerCase().trim().charAt(0)){
                    case '1': 
                        promptAddAdvertiser(c);
                    break;
                    case '2':
                        promtAddResponder(c);
                    break;
                    case 'q':
                        exit = true;
                    break;
                    default:
                        error = true;
                        throw new Exception("Invalid selection");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }while(error || !exit);
        
        
    }

    /**
     * Prompts the user to delete a customer, uses the password as a means of checking the deletion process
     * @param c The customer handler object to manipulate
     */
    public static void promptDeleteCustomer(CustomerHandler c){
           Scanner s = new Scanner(System.in);
           System.out.println("-------\nEnter a login name to delete\n-------\nPress enter to quit");
           System.out.print("Login: ");
           String tmplogin = s.nextLine();
           if (!tmplogin.trim().isEmpty()){
               System.out.print("Enter the password: ");
               String tmppass = s.nextLine();
               c.deleteCustomer(tmplogin, tmppass);
           }       

    }

    /**
     * Prompts the user to add a responder to the database. Requests basic information 
     * @param c The customer handler object to manipulate
     */
    public static void promtAddResponder(CustomerHandler c){
        Responder tmpResponder = new Responder();
        boolean error = false;
        do{
            try {
                tmpResponder.Promptlogin();
                //check if user exsists, error checking is also done within the object, this could be refactored
                if (c.userExists(tmpResponder.Getlogin())){
                    error = true;
                    throw new Exception("Customer with same login exsists.\nChoose different login name");
                } else {
                    tmpResponder.Promptpassword();
                    tmpResponder.Promptgender();
                    tmpResponder.Promptage();
                    tmpResponder.Promptincome();

                    c.addCustomer(tmpResponder);
                    error = false;
                    System.out.println("Responder added");
                }
            }catch (Exception e){
                error = true;
                System.out.println(e.getMessage());
            }
        }while (error);
    }

    /**
     * Prompt the user to add an advertiser to the database, asks more specific questions to populate the advertiser such as preferred partner and custom message.
     * @param c The customer handler object to manipulate
     */
    public static void promptAddAdvertiser(CustomerHandler c){
        Advertiser tmpAdvertiser = new Advertiser();
        boolean error = false;
        do{
            try {
                tmpAdvertiser.Promptlogin();
                //because the customer handler can handle all customers, advertisers and responders should have unique names
                if (c.userExists(tmpAdvertiser.Getlogin())){
                    error = true;
                    System.out.println("Customer with same login exsists.\nChoose different login name");
                } else {
                    tmpAdvertiser.Promptpassword();
                    tmpAdvertiser.Promptgender();
                    tmpAdvertiser.Promptage();
                    tmpAdvertiser.Promptincome();
                    tmpAdvertiser.PrompttextAdvert();
                    tmpAdvertiser.PromptsoughtPartner();

                    c.addCustomer(tmpAdvertiser);
                    error = false;
                    System.out.println("Advertiser added");
                }
            }catch (Exception e){
                error = true;
                System.out.println(e.getMessage());
            }
        }while (error); 
    }

    /**
     * Prompt the user to display the customer details they want, this function calls the customer specific functions 
     * @param c The customer handler object to manipulate
     */
    public static void propmptDisplayCustomerDetails(CustomerHandler c){
        System.out.println("-------\nDisplay customer details\n-------");
        System.out.println("Enter the corresponding numbers");
        boolean error = false;
        boolean exit = false;
        do{
            System.out.print("Type of customer: \n 1. Advertiser\n 2. Responder\n 3. Display all customer \n q. Back\n\nSelection: ");
            try{
                Scanner s = new Scanner(System.in);
                String tmp = s.nextLine();
                
                switch(tmp.toLowerCase().trim().charAt(0)){
                    case '1': 
                        c.displayAllCustomerDetails("advertiser");
                    break;
                    case '2':
                        c.displayAllCustomerDetails("responder");
                    break;
                    case '3':
                        c.displayAllCustomerDetails("all");
                    break;
                    case 'q':
                        exit = true;
                    break;
                    default:
                        error = true;
                        throw new Exception("Invalid selection");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }while(error || !exit);  
    }

    /**
     * Prompts the user to login to the system. Sets the logged in customer var in customer handler for easy access for other functions 
     * @param c The customer handler object to manipulate
     */
    public static void promptLogin(CustomerHandler c){
        System.out.println("-------\nLogin to the system\n------- \nPress enter to go back");
        boolean error = false;
        boolean exit = false;
        do{
            System.out.print("Login: ");
            try{
                Scanner s = new Scanner(System.in);
                String tmplogin = s.nextLine();
                if (!tmplogin.trim().isEmpty()){
                    
                    System.out.print("Enter the password: ");
                    String tmppass = s.nextLine();
                    //try to login
                    c.SetLoggedInCustomer(tmplogin, tmppass);
                    
                    System.out.println("\nLogged in successfully");
                    exit = true;
                    promptLoginMenu(c);
                   
                     
                } else {
                    exit = true;
                    error = false;
                }
                 
                
            }catch (Exception e){
                error = true;
                System.out.println(e.getMessage());
            }
        }while(error || !exit);
    }

    /**
     * Prompts the user specific login menu which allows both advertisers and responders to have their own specific view of the actions available to them 
     * @param c The customer handler object to manipulate
     */
    public static void promptLoginMenu(CustomerHandler c){
        //make sure there is a logged in customer 
        //Probably should be done in the customer handler object
        if (c.hasLoggedinCustomer()){
            
            boolean error = false;
            boolean exit = false;
            do{
                System.out.println("-------\nWelcome " + c.GetLoggedInCustomer().Getlogin()+ "\n-------");
                System.out.println("Choose an option");
                //show different options for different types of customers
                if (c.GetLoggedInCustomer() instanceof Responder ){
                    System.out.println(" 1. View matching advertisers");
                }
                if (c.GetLoggedInCustomer() instanceof Advertiser){
                    System.out.println(" 1. Manage reply messages");
                }
                System.out.println(" 2. Display Details");
                System.out.println(" q. Logout");
                System.out.print("Selection: ");
                try{
                    Scanner s = new Scanner(System.in);
                    String tmp = s.nextLine();
                    //do different options for different types of customers

                    switch(tmp.toLowerCase().trim().charAt(0)){
                        case '1': 
                            if (c.GetLoggedInCustomer() instanceof Responder ){
                                displayResponderMatches(c);
                            }
                            if (c.GetLoggedInCustomer() instanceof Advertiser){
                                displayAdvertiserReplyMessages(c);
                            }
                        break;
                        case '2':
                            c.GetLoggedInCustomer().displayDetails();
                        break;
                        case 'q':
                            //logout
                            c.RemoveLoggedInCustomer();
                            System.out.println("Logged out successfully");
                            error = false;
                            exit = true;
                        break;
                        default:
                            error = true;
                            throw new Exception("Invalid selection");
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }while(!exit || error);
            
            
        }
    }

    /**
     * Display the matches that the responder has. uses the advertisers details to check if they match, if so, display it
     * @param c The customer handler object to manipulate
     */
    public static void displayResponderMatches(CustomerHandler c){
            //ArrayList of all the potential matches 
            ArrayList<Advertiser> matches = c.getResponderMatches(((Responder) c.GetLoggedInCustomer()));
            if (!matches.isEmpty()){
                //should probably split this to its own function
                for (int i = 0; i < matches.size(); i++){
                    System.out.println("-------\nMatch " + (i+1) + "\n-------");
                    System.out.println("Login: " + matches.get(i).Getlogin());
                    System.out.println("Message: " + ((Advertiser) matches.get(i)).GettextAdvert());
                    System.out.println();
                }
                System.out.println("Total matches found: " + matches.size());
                boolean error = false;
                boolean exit = false;
                do{
                    //use the above match list to select and send a message to advertiser
                    System.out.println("Enter match number to send message to or press enter to go back ");
                    System.out.print("Selection: ");
                    try{
                        Scanner s = new Scanner(System.in);
                        String tmpmatch = s.nextLine();
                        
                        if(tmpmatch.trim().isEmpty()){
                            exit = true;
                            error= false;
                        }else{
                            String selectedAdvertiser = matches.get(Integer.parseInt(tmpmatch)-1).Getlogin();
                            c.GetCustomer(selectedAdvertiser).displayDetails();
                            System.out.print("Enter the Message you would like to send: ");
                            Scanner m = new Scanner(System.in);
                            String msg = m.nextLine();
                            //complex jumping between sub and super classes. 
                            //basically adds a message to the specific advertiser using the specific responder as a refference 
                            ((Advertiser) c.GetCustomer(selectedAdvertiser)).AddReplyMessage(((Responder) c.GetLoggedInCustomer()), msg);
                            System.out.println("Message sent");
                            exit = true;
                        }

                    }catch (NumberFormatException e){
                        error = true;
                        System.out.println("ERROR: Not a valid selection");
                        
                    } catch (Exception e){
                        error= true;
                        System.out.println(e.getMessage());
                    }
                }while(error || !exit);  
            
            }else{
                 System.out.println("No matches found");
    //            throw new Exception("ERROR: Trying to display matches for responder. Customer not responder type");
            }
    }

    /**
     * Displays the advertiser reply messages. Very complex and possibly needs some rework to make it more intuitive
     * @param c The customer handler object to manipulate
     */
    public static void displayAdvertiserReplyMessages(CustomerHandler c){
        if(c.GetLoggedInCustomer() instanceof Advertiser){
            
            boolean error = false;
            boolean exit = false;
            do{
                System.out.println("-------\nAll messages\n-------");    
                ArrayList<Responder> tmpresponder = new ArrayList<>();
                //uses a hash map for loop to loop around and display each message from all responders.
                for(HashMap.Entry<Responder, String> entry : ((Advertiser) c.GetLoggedInCustomer()).GetreplyMessages().entrySet()) {
                    tmpresponder.add(entry.getKey());
                    System.out.println("Message: " + tmpresponder.size());
                    System.out.println("Responder: "+entry.getKey().Getlogin());
                    System.out.println(" > " + entry.getValue());
                }
                if(!tmpresponder.isEmpty()){
                    System.out.print("\nChoose a message to delete or press enter to go back\nSelection: ");
                    try{
                        Scanner s = new Scanner(System.in);
                        String tmpmatch = s.nextLine();

                        if(tmpmatch.trim().isEmpty()){
                            exit = true;
                            error= false;
                        }else{
                            //String selectedMessage = tmpresponder.get(Integer.parseInt(tmpmatch)-1).Getlogin();
                            //remoes a message for a responder
                            ((Advertiser) c.GetLoggedInCustomer()).RemoveReplyMessage(tmpresponder.get(Integer.parseInt(tmpmatch)-1));
                            System.out.println("Message deleted");
                        }

                    }catch (NumberFormatException e){
                        error = true;
                        System.out.println("ERROR: Not a valid selection");

                    } catch (Exception e){
                        error= true;
                        System.out.println(e.getMessage());
                    }
                }else{
                    error = false;
                    exit = true;
                    System.out.println("No messages found");
                }
            }while(error || !exit);  
            
//            for (int i = 0; i < ((Advertiser) loggedInCustomer).GetreplyMessages().size(); i++) {
//			System.out.println(((Advertiser) loggedInCustomer).GetreplyMessages().get(i).Getmessage());
//            }
        }
    }
}

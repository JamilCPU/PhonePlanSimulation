import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ArrayList<Carrier> carriers = addCarriers();
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Contract> contracts = new ArrayList<>();
        boolean runInteractive = true;//Runs interactive process between user and program.
        boolean runChoices = false;//Runs the list of choices between user and program.
        int userInput = 0;//Used to send user to different cases.
        int response = 0;//Used to determine user choices.
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the phone plan simulator...");
        runChoices = true;
        while (runInteractive) {
            while (runChoices) {
                switch (userInput) {
                    default:
                        System.out.printf("There are currently...\n" +
                                "%d customers,  %d active contracts\n" +
                                "Choose an option... \n" +
                                "1. Create a new customer. \n" +
                                "2. Create a contract between a customer and a carrier. \n" +
                                "3. Delete a customer. \n" +
                                "4. Delete a contract. \n", customers.size(), contracts.size());
                        try {
                            userInput = input.nextInt();
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid command.");
                            Thread.sleep(1000);
                        }
                        break;
                    case 1:
                        customers.add(new Customer());
                        userInput = 99;
                        break;
                    case 2:
                        if (customers.size() > 0) {
                            for (int i = 0; i < customers.size(); i++) {
                                System.out.println(i + ".\n" + customers.get(i));
                            }
                            System.out.println("Pick the number of the customer to generate a contract with.");
                            try {
                                Customer customer = customers.get(input.nextInt());
                                System.out.println("What carrier would you like to generate a contract with?");
                                for (int i = 0; i < carriers.size(); i++) {
                                    System.out.println(i + ". " + carriers.get(i).getProvider());
                                }
                                Carrier carrier = carriers.get(input.nextInt());
                                System.out.println("Which plan would you like to use.");
                                for (int i = 0; i < carrier.getPlan().size(); i++) {
                                    System.out.println(i + ". " + carrier.getPlanKeys()[i]);
                                }
                                String planKey = carrier.getPlanKeys()[input.nextInt()];//Used to retrieve the plan cost within our hashmap.
                                int planCost = (int) Double.parseDouble(String.valueOf(carrier.getPlan().get(planKey)));
                                System.out.println("Forming contract...");
                                contracts.add(new Contract(planKey, planCost, carrier, customer));//Make a contract with the data from user.
                                Thread.sleep(2000);
                                userInput = 99;//Trips default cause

                            } catch (NumberFormatException e) {
                                System.out.println("Please enter a valid command.");
                                Thread.sleep(1000);
                                //Sends the user back to the start of case 2.
                                break;
                            } catch (IndexOutOfBoundsException f) {
                                System.out.println("Please enter a given number");
                                Thread.sleep(1000);
                                userInput = 2;//Sends the user back to the start of case 2.
                                break;
                            }
                        }else {
                            System.out.println("There are currently no customers.");
                            System.out.printf("Would you like to create a customer?\n" +
                                    "1. Yes\n" +
                                    "2. No\n");
                            try {
                                response = input.nextInt();
                                if (response == 1) {
                                    userInput = 1;//Create a customer.

                                } else {
                                    userInput = 99;//Go to main menu.
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Please enter a number");
                                Thread.sleep(1000);
                                userInput = 2;//Restate the prompt.
                                break;
                            }
                        }
                        break;
                    case 3:
                        if (customers.size() == 0) {
                            System.out.println("There are currently no customers.");
                            System.out.printf("Would you like to create a customer?\n" +
                                    "1. Yes\n" +
                                    "2. No\n");
                            try {
                                response = input.nextInt();
                                if (response == 1) {
                                    userInput = 1;//Create a customer.
                                }else{
                                    userInput=99;//Go to main menu.
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Please enter a number");
                                Thread.sleep(1000);
                                userInput = 3;
                                break;
                            }
                        } else {
                            for (int i = 0; i < customers.size(); i++) {
                                System.out.println(i + ". \n" + customers.get(i));//Print out a list of customers.
                            }
                            System.out.println("Which customer would you like to delete?");
                            try {
                                response = input.nextInt();
                                for(int i=0;i<contracts.size();i++){//Check if the chosen customer has any active contracts.
                                    if(contracts.get(i).getCustomer()==customers.get(response)){
                                        System.out.println("Removing " + customers.get(response).getFirstName() +
                                                " " + customers.get(response).getLastName() +
                                                "'s " + contracts.get(response).getPlan() +" contract with"  + contracts.get(i).getCarrier().getProvider());
                                        contracts.remove(i);
                                        Thread.sleep(250);
                                    }
                                }
                                customers.remove(response);
                                System.out.printf("Customer successfully deleted.\n" +
                                        "Current listing of customers...\n");
                                for (int i = 0; i < customers.size(); i++) {
                                    System.out.println(customers.get(i));
                                }
                                Thread.sleep(2000);
                                System.out.println("Returning to main menu...");
                                userInput = 99;//Trips default case
                                break;
                            }catch(IndexOutOfBoundsException e){
                                System.out.println("Please enter a given number.");
                                Thread.sleep(1000);
                                userInput = 3;//Restates the prompt.
                                break;
                            }
                        }
                    case 4:
                        if(contracts.size()==0) {
                            System.out.printf("There are currently no contracts.\n" +
                                    "Would you like to create a contract?\n" +
                                    "1. Yes\n" +
                                    "2. No\n");
                            try{
                                response = input.nextInt();
                                if(response==1){//
                                    userInput = 2;//Create a contract.
                                }else{
                                    userInput = 99;//Go to main menu.
                                }
                                break;
                            }catch (NumberFormatException e){
                                System.out.println("Please enter a number.");
                                Thread.sleep(1000);
                                userInput = 4;//Restate the prompt.
                                break;
                            }
                        }else {
                            System.out.println("Which contract would you like to delete?");
                            for (int i = 0; i < contracts.size(); i++) {
                                System.out.println(i + ". " + contracts.get(i));
                            }
                            try {
                                response = input.nextInt();
                                contracts.remove(response);
                                System.out.printf("Contract successfully deleted...\n");
                                if(contracts.size()!=0) {
                                    for (int i = 0; i < contracts.size(); i++) {
                                        System.out.println(i + ". " + contracts.get(i));
                                    }
                                }
                                Thread.sleep(2000);
                                System.out.println("Returning to main menu...");
                                userInput = 99;//Trips default case
                                break;
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Please enter a given number.");
                                Thread.sleep(1000);
                                userInput = 4;
                                break;
                            }
                        }
                }
            }
        }
    }

        public static ArrayList<Carrier> addCarriers ()throws IOException {
            JsonParser parser = new JsonParser();
            BufferedReader in = new BufferedReader(new FileReader("carrierplans.json"));
            JsonArray carrierData = (JsonArray) parser.parse(in);
            ArrayList<Carrier> carriers = new ArrayList<>();
            carriers.add(new Carrier("Verizon", carrierData));
            carriers.add(new Carrier("Metro", carrierData));
            carriers.add(new Carrier("T-Mobile", carrierData));
            carriers.add(new Carrier("Mint", carrierData));
            carriers.add(new Carrier("AT&T", carrierData));
            carriers.add(new Carrier("Google-Fi", carrierData));
            return carriers;
        }
    }


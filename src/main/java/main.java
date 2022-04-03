import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class main {
    public static void main(String[] args) throws IOException {
        ArrayList<Carrier> carriers = addCarriers();
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Contract> contracts = new ArrayList<>();
        boolean runInteractive = true;//Runs interactive process between user and program.
        boolean runChoices = false;//Runs the list of choices between user and program.
        int userInput = 0;//Used to determine user choices.
        int savedPoint = 0;//Used to recover user's last location in the program
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the phone plan simulator...");

        while (runInteractive) {

        System.out.printf("Choose an option... \n1. Create a new customer. \n2.Create a contract between a customer and a carrier. \n");
            try{
                userInput = Integer.parseInt(input.nextLine());
                runChoices=true;
            }catch(NumberFormatException e){
                System.out.println("Please enter a valid command.");
                continue;
            }
            while(runChoices) {
                switch (userInput) {
                    case 1:
                        customers.add(new Customer());
                        runChoices=false;
                        break;
                    case 2:
                        if (customers.size() > 0) {
                            for (int i = 0; i < customers.size(); i++) {
                                System.out.println(i + ".\n" + customers.get(i).toString2());
                            }
                            System.out.println("Pick the number of the customer to generate a contract with.");
                            try {
                                Customer customer = customers.get(Integer.parseInt(input.nextLine()));
                                System.out.println("What carrier would you like to generate a contract with?");
                                for(int i=0; i< carriers.size(); i++){
                                    System.out.println(i + ".\n" + carriers.get(i).getProvider());
                                }
                                Carrier carrier = carriers.get(Integer.parseInt(input.nextLine()));
                                System.out.println("Which plan would you like to use.");
                                for(int i=0;i<carrier.getPlan().size();i++){
                                    System.out.println(i + ".\n" + carrier.getPlanKeys()[i]);
                                }
                                String planKey = carrier.getPlanKeys()[Integer.parseInt(input.nextLine())];
                                System.out.println((int)carrier.getPlan().get(planKey));
                                int planCost = carrier.getPlan().get(planKey);
                                System.out.println("Forming contract...");
                                contracts.add(new Contract(planKey, planCost, carrier, customer));
                                runChoices=false;
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Please enter a valid command.");
                                userInput = 2;//Sends the user back to the start of case 2.
                                break;
                            } catch(IndexOutOfBoundsException f){
                                System.out.println("Please enter a given number");
                                userInput = 2;//Sends the user back to the start of case 2.
                                break;
                            }
                        }
                        break;
                }
            }
    }
    }
    public static ArrayList<Carrier> addCarriers()throws IOException{
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

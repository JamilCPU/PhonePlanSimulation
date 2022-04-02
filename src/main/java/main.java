import java.io.*;
public class main {
    public static void main(String[] args)throws IOException {

        Carrier Verizon = new Carrier("Verizon");
        Carrier Metro = new Carrier("Metro");
        Customer cust = new Customer();
        System.out.println(cust.toString());
        Contract contract = new Contract("Unlimited", 75, Verizon, cust);
        Contract contract1 = new Contract("Basic", 30, Metro, cust);
    }

}

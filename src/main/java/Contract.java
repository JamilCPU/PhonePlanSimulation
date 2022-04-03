import java.io.IOException;

public class Contract {
    private String plan;
    private int planCost;
    private int accountNumber;
    private String phoneNumber;
    private Customer customer;
    public Contract(String plan, int planCost, Carrier carrier, Customer customer)throws IOException {
        this.plan=plan;
        this.planCost=planCost;
        this.accountNumber = carrier.generateAccountNumber();//Different carriers may generate account numbers differently.
        this.phoneNumber = carrier.generatePhoneNumber();
        this.customer = customer;
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Contract{" +
                "plan='" + plan + '\'' +
                ", planCost=" + planCost +
                ", accountNumber=" + accountNumber +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", customer=" + customer +
                '}';
    }
}

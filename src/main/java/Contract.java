import java.io.IOException;

public class Contract {
    private Carrier carrier;
    private String plan;
    private int planCost;
    private int accountNumber;
    private String phoneNumber;
    private Customer customer;
    public Contract(String plan, int planCost, Carrier carrier, Customer customer)throws IOException {
        this.carrier = carrier;
        this.plan=plan;
        this.planCost=planCost;
        this.accountNumber = carrier.generateAccountNumber();//Different carriers may generate account numbers differently.
        this.phoneNumber = carrier.generatePhoneNumber();
        this.customer = customer;
        System.out.println(this);
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public String getPlan() {
        return plan;
    }

    public int getPlanCost() {
        return planCost;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "plan='" + plan + '\'' +
                ", planCost=" + planCost +
                ", accountNumber=" + accountNumber +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", \ncustomer=" + customer +
                '}';
    }
}

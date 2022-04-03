
import com.github.javafaker.Faker;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String zipCode;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Customer()throws IOException {
        Faker faker = new Faker();//Randomly generate data for a customer
        this.id = String.valueOf(UUID.randomUUID());
        this.firstName = faker.address().firstName();
        this.lastName = faker.address().lastName();
        this.city = faker.address().cityName();
        this.state = faker.address().state();
        this.zipCode = faker.address().zipCode();
        System.out.println(this);
    }


    @Override
    public String toString() {
        return "CUSTOMER GENERATED: \n" +
                "firstName=     " + firstName + '\n' +
                "lastName=      " + lastName + '\n' +
                "city=      " + city + '\n' +
                "state=     " + state + '\n' +
                "zipCode=       " + zipCode + '\n' +
                "-------------------------- \n";
    }

    public String toString2(){
        return "CUSTOMER: \n" +
                "firstName=     " + firstName + '\n' +
                "lastName=      " + lastName + '\n' +
                "city=      " + city + '\n' +
                "state=     " + state + '\n' +
                "zipCode=       " + zipCode + '\n' +
                "-------------------------- \n";
    }


}

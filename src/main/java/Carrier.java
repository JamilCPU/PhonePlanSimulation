import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.function.IntUnaryOperator;

public class Carrier {
    private String provider;
    private HashMap<String, Integer> plan;

    @Override
    public String toString() {
        return "Carrier{" +
                "provider='" + provider + '\'' +
                ", plan=" + plan +
                '}';
    }

    public Carrier(){

    }

    public Carrier(String provider)throws IOException {
        this.provider = provider;
        this.plan = readHashMap("carrierplans.txt");
        System.out.println(this.toString());
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public HashMap<String, Integer> getPlan() {
        return plan;
    }

    public void setPlan(HashMap<String, Integer> plan) {
        this.plan = plan;
    }

    public int generateAccountNumber(){//Must ensure that every account num is unique.
        return (int)Math.floor(Math.random()*999999999-990000000+1)+990000000;
    }

    public String generatePhoneNumber(Customer customer)throws IOException {
        customer.getZipCode();
        URL url = new URL("http://www.fonefinder.net/findzip.php?zipcode=" + customer.getZipCode() + "&zipquerytype=Search+by+Zip");
        Document doc = Jsoup.connect(String.valueOf(url)).get();
        Elements elements = doc.select("a[href]");
        String zipNumber = elements.get(0).text();
        String digitSet1 = generateRand() + generateRand() + generateRand();
        String digitSet2 = generateRand() + generateRand() + generateRand() + generateRand();
        return "(" + zipNumber + ")-" + digitSet1 + "-" + digitSet2;
    }

    public HashMap<String, Integer> readHashMap(String file)throws IOException {
        HashMap<String, Integer> hash = new HashMap<>();
        BufferedReader in = new BufferedReader(new FileReader(file));
        String data = "";
        String[] splitData;
        while((data = in.readLine()) != null){
            if(data.equals(this.getProvider())){
                while(data.trim().isEmpty()){
                    data = in.readLine();
                    splitData = data.split(":");
                    hash.put(splitData[0], Integer.valueOf(splitData[1]));
                }
            }
        }

        return hash;
    }

    private String generateRand(){
        return String.valueOf((int)Math.floor((Math.random()*9)+1));
    }

    public static void main(String[] args)throws IOException {

    }
}

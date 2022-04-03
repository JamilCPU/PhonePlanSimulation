import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
    private String[] planKeys;

    @Override
    public String toString() {
        return "Carrier{" +
                "provider='" + provider + '\'' +
                ", plan=" + plan +
                '}';
    }

    public String[] getPlanKeys() {
        return planKeys;
    }

    public void setPlanKeys(String[] planKeys) {
        this.planKeys = planKeys;
    }

    public Carrier(){

    }

    public Carrier(String provider, JsonArray carrierData)throws IOException {
        this.provider = provider;
        JsonObject providerData = (JsonObject)carrierData.get(findIndex(provider, carrierData));//Get the JsonObject of the index we find our provider located.
        Gson gson = new Gson();
        this.plan = gson.fromJson(providerData.get("plan"), HashMap.class);
        String parsePlan = "";
        parsePlan = plan.toString();
        parsePlan = parsePlan.substring(1,parsePlan.length()-1);
        String[] plans = parsePlan.split(", ");
        for(int i=0;i<plans.length;i++){
            plans[i] = plans[i].substring(0, plans[i].indexOf('='));
        }
        this.planKeys = plans;
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

    public String generatePhoneNumber()throws IOException {
        Faker faker = new Faker();
        return faker.phoneNumber().phoneNumber();
    }

    public int findIndex(String provider, JsonArray carrierData)throws IOException {
        int foundIndex=0;
        for(int i=0; i<carrierData.size();i++){
            JsonObject carrierIndex = (JsonObject) carrierData.get(i);
            if(carrierIndex.get("provider").getAsString().equals(provider)){
                System.out.println(i);
                foundIndex=i;
                break;
            }
        }
        return foundIndex;
    }
}

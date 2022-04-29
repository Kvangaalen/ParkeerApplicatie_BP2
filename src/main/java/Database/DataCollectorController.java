package Database;

import com.mycompany.parkeerapplictie.model.parkeergarages;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataCollectorController {

    private ArrayList<parkeergarages> parkeergarages = new ArrayList();
    private boolean blOpen;
    private boolean blFull;
    private Long intVacant;

    public DataCollectorController(String identifier) {
        String dataString = getDataString(identifier);
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(dataString);
            //  System.out.println("jsonObject " + jsonObject);
            json = (JSONObject) parser.parse(dataString);
            // System.out.println("json " + json);
        } catch (ParseException pe) {
            System.out.println("Error 1 : " + pe.getMessage());
        } catch (Exception e) {
            System.out.println("Error 2 : " + e.getMessage());
        }
        JSONObject pfdi = (JSONObject) json.get("parkingFacilityDynamicInformation");
        JSONObject actualStatus = (JSONObject) pfdi.get("facilityActualStatus");
        this.blOpen = (boolean) actualStatus.get("open");
        this.blFull = (boolean) actualStatus.get("full");
        this.intVacant = (Long) actualStatus.get("vacantSpaces");
        //System.out.println("actualStatus : " + blOpen + " -- " + blFull + " -- " + intVacant);
    }

    private String getDataString(String identifier) {
        StringBuilder sb = new StringBuilder();
        InputStream in = null;
        try {
            URL url = new URL("https://opendata.technolution.nl/opendata/parkingdata/v1/dynamic/" + identifier + "");
            URLConnection con = url.openConnection();
            in = con.getInputStream();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }

    public parkeergarages getHups() {
        parkeergarages garages = new parkeergarages(blOpen, blFull, intVacant);
        return garages;
    }

}

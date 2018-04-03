import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class JsonParser {

    private static String readString(BufferedReader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        String buffer;
        while ((buffer = reader.readLine()) != null){
            builder.append(buffer);
        }
        return builder.toString();
    }

    private static JSONObject downloadFile(String url){
        try {
            InputStream inputStream = new URL(url).openStream();
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
            String strJson = readString(inputReader);
            return new JSONObject(strJson);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] ivara) throws JSONException {
        System.out.println("Enter the url of JSON file you'd like to be parsed : ");
        Scanner scanner = new Scanner(System.in);

        JSONObject jsonObject = downloadFile(scanner.nextLine());
        JSONArray keys = jsonObject.names();
        for (int i = 0; i < keys.length(); i++){
            System.out.printf(keys.get(i).toString() + "\t");
            System.out.println(jsonObject.get((String) keys.get(i)).toString());
        }

    }

}

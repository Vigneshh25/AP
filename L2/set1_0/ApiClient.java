package L2.set1_0;

/**
 * Created by Vignesh.V on 26/09/23.
 */
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ApiClient {
    public static void main(String[] args) {
        // Create an HttpClient
        HttpClient httpClient = HttpClients.createDefault();

        // Define the URL of the Spring Boot application
        String apiUrl = "http://localhost:8080/getData"; // Replace with the actual URL

        // Create an HTTP GET request
        HttpGet httpGet = new HttpGet(apiUrl);

        try {
            // Execute the GET request
            HttpResponse response = httpClient.execute(httpGet);

            // Get and print the response content
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Response Body:");
            System.out.println(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


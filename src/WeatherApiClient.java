import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApiClient {
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String API_KEY = "a3d077449a694eb6e723043ba3eca5cb";

    public double fetchCurrentTemperature(String city) {
        try {
            String urlString = String.format("%s?q=%s&appid=%s&units=metric", API_URL, city, API_KEY);
            HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                JSONObject jsonResponse = new JSONObject(response.toString());
                return jsonResponse.getJSONObject("main").getDouble("temp");
            }
        } catch (Exception e) {
            System.err.println("Błąd podczas pobierania danych z API: " + e.getMessage());
            return 0;
        }
    }
}

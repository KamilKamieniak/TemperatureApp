import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/temperature_base";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public void saveTemperatureData(TemperatureData data) throws Exception {
        String query = "INSERT INTO temperature (date, day_of_week, hour, temperature) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            for (TemperatureEntry entry : data.getEntries()) {
                statement.setString(1, entry.getDate());
                statement.setInt(2, entry.getDayOfWeek());
                statement.setString(3, entry.getHour());
                statement.setDouble(4, entry.getTemperature());
                statement.addBatch();
            }

            statement.executeBatch();
        }
    }

    public TemperatureData readTemperatureData() throws Exception {
        String query = "SELECT date, day_of_week, hour, temperature FROM temperature";

        List<TemperatureEntry> entries = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String date = resultSet.getString("date");
                int dayOfWeek = resultSet.getInt("day_of_week");
                String hour = resultSet.getString("hour");
                double temperature = resultSet.getDouble("temperature");

                TemperatureEntry entry = new TemperatureEntry(date, dayOfWeek, hour, temperature);
                entries.add(entry);
            }
        }

        return new TemperatureData(entries);
    }
}

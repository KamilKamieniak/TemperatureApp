import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class CsvReader {

    public TemperatureData readFromFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        List<TemperatureEntry> temperatureEntries = new ArrayList<>();

        for (String line : lines.subList(1, lines.size())) {
            String[] parts = line.split(";");

            String date = parts[0];
            int dayOfWeek = Integer.parseInt(parts[1]);
            String hour = parts[2];
            double temperature = Double.parseDouble(parts[3]);

            temperatureEntries.add(new TemperatureEntry(date, dayOfWeek, hour, temperature));
        }

        return new TemperatureData(temperatureEntries);
    }
}

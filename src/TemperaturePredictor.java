import java.util.ArrayList;
import java.util.List;

public class TemperaturePredictor {

    public List<Double> predictHourlyTemperatures(TemperatureData data) {
        List<TemperatureEntry> entries = data.getEntries();
        List<Double> predictedTemperatures = new ArrayList<>();

        double averageTemperature = data.getAverageTemperature();

        for (int i = 0; i < 24; i++) {
            double sum = 0.0;
            int count = 0;

            for (int j = i; j < entries.size(); j += 24) {
                sum += entries.get(j).getTemperature();
                count++;
            }

            if (count > 0) {
                double hourlyPrediction = sum / count + averageTemperature * 0.1;
                predictedTemperatures.add(hourlyPrediction);
            } else {
                predictedTemperatures.add(averageTemperature);
            }
        }

        return predictedTemperatures;
    }
}

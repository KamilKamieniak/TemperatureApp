import java.util.List;

public class TemperatureData {
    private List<TemperatureEntry> entries;

    public TemperatureData(List<TemperatureEntry> entries) {
        this.entries = entries;
    }

    public List<TemperatureEntry> getEntries() {
        return entries;
    }

    public double getAverageTemperature() {
        double sum = 0.0;
        int count = 0;

        for (TemperatureEntry entry : entries) {
            sum += entry.getTemperature();
            count++;
        }

        if (count > 0) {
            return sum / count;
        } else {
            return 0;
        }
    }
}

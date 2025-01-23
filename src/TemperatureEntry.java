public class TemperatureEntry {
    private String date;
    private int dayOfWeek;
    private String hour;
    private double temperature;

    public TemperatureEntry(String date, int dayOfWeek, String hour, double temperature) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class TemperatureApp {

    public static void main(String[] args) {
        CsvReader csvReader = new CsvReader();
        TemperaturePredictor temperaturePredictor = new TemperaturePredictor();
        DatabaseService databaseService = new DatabaseService();
        WeatherApiClient weatherApiClient = new WeatherApiClient();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTemperatureApp:");
            System.out.println("1. Aktualna pogoda (pobierz z API)");
            System.out.println("2. Szacowanie temperatury na podstawie pliku CSV");
            System.out.println("3. Szacowanie temperatury na podstawie danych z bazy");
            System.out.println("4. Zapisz dane z pliku CSV do bazy");
            System.out.println("5. Wyjście");
            System.out.print("Wybierz opcję (1-5): ");


            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Błąd! Podaj poprawną liczbę.");
                continue;
            }

            if (choice < 1 || choice > 5) {
                System.out.println("Błąd! Wybierz cyfrę od 1 do 5.");
                continue;
            }

            switch (choice) {
                case 1:
                    fetchCurrentWeather(scanner, weatherApiClient);
                    break;
                case 2:
                    predictTemperatureFromCsv(scanner, csvReader, temperaturePredictor);
                    break;
                case 3:
                    predictTemperatureFromDatabase(databaseService, temperaturePredictor);
                    break;
                case 4:
                    saveCsvDataToDatabase(scanner, csvReader, databaseService);
                    break;
                case 5:
                    System.out.println("Zamykanie aplikacji...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Nieznana opcja. Wybierz ponownie.");
                    break;
            }
        }
    }

    private static void fetchCurrentWeather(Scanner scanner, WeatherApiClient weatherApiClient) {
        System.out.print("Podaj nazwę miasta: ");
        String city = scanner.nextLine();
        try {
            double currentTemperature = weatherApiClient.fetchCurrentTemperature(city);
            System.out.printf("Aktualna temperatura w %s: %.2f°C%n", city, currentTemperature);
        } catch (Exception e) {
            System.out.println("Błąd podczas pobierania danych z API: " + e.getMessage());
        }
    }

    private static void predictTemperatureFromCsv(Scanner scanner, CsvReader csvReader, TemperaturePredictor temperaturePredictor) {
        System.out.print("Podaj ścieżkę do pliku CSV: ");
        String filePath = scanner.nextLine();
        try {
            TemperatureData data = csvReader.readFromFile(filePath);
            List<Double> predictedTemperatures = temperaturePredictor.predictHourlyTemperatures(data);

            System.out.println("Prognoza temperatury na kolejną dobę:");
            for (int i = 0; i < predictedTemperatures.size(); i++) {
                System.out.printf("Godzina %02d:00 -> %.2f°C%n", i, predictedTemperatures.get(i));
            }
        } catch (IOException e) {
            System.out.println("Błąd podczas odczytu pliku: " + e.getMessage());
        }
    }

    private static void predictTemperatureFromDatabase(DatabaseService databaseService, TemperaturePredictor temperaturePredictor) {
        try {
            TemperatureData data = databaseService.readTemperatureData();
            List<Double> predictedTemperatures = temperaturePredictor.predictHourlyTemperatures(data);

            System.out.println("Prognoza temperatury na kolejną dobę:");
            for (int i = 0; i < predictedTemperatures.size(); i++) {
                System.out.printf("Godzina %02d:00 -> %.2f°C%n", i, predictedTemperatures.get(i));
            }
        } catch (Exception e) {
            System.out.println("Błąd podczas odczytu danych z bazy: " + e.getMessage());
        }
    }

    private static void saveCsvDataToDatabase(Scanner scanner, CsvReader csvReader, DatabaseService databaseService) {
        System.out.print("Podaj ścieżkę do pliku CSV: ");
        String filePath = scanner.nextLine();
        try {
            TemperatureData data = csvReader.readFromFile(filePath);
            databaseService.saveTemperatureData(data);
            System.out.println("Dane zostały zapisane do bazy danych.");
        } catch (IOException e) {
            System.out.println("Błąd podczas odczytu pliku: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Błąd podczas zapisu danych do bazy: " + e.getMessage());
        }
    }
}

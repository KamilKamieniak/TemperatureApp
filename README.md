# TemperatureApp

## Opis
*TemperatureApp* to aplikacja do analizy i zarządzania danymi temperaturowymi. Oferuje funkcje:
- Odczytu danych z plików CSV,
- Zapisu danych do bazy MySQL,
- Prognozowania temperatur na podstawie istniejących danych,
- Pobierania aktualnej pogody z API OpenWeatherMap.

## Wymagania wstępne
Do uruchomienia aplikacji niezbędne jest:
1. *Java 8 lub nowsza (JDK)*
2. *MySQL* (np. w ramach pakietu XAMPP)
3. *IDE* (np. IntelliJ IDEA)
4. *Klucz API* z OpenWeatherMap (https://openweathermap.org/)
5. *Biblioteka JSON* (org.json)
6. *Biblioteka MySQL Connector/J* (mysql-connector-j-9.2.0)
7. Plik `temperature.sql` zawierający strukturę i dane bazy danych.


## Instalacja i konfiguracja

### 1. Import bazy danych przez phpMyAdmin
1. Uruchom XAMPP.
2. Włącz moduł *MySQL*.
3. Otwórz phpMyAdmin w przeglądarce: http://localhost/phpmyadmin (http://localhost/phpmyadmin).
4. Kliknij *"Nowa"* (po lewej stronie).
5. Nazwij bazę `temperature`.
6. Wybierz kodowanie *utf8_polish_ci*.
7. Kliknij *"Utwórz"*.
8. Przejdź do zakładki *"Import"*.
9. Wybierz plik `temperature.sql` i kliknij *"Rozpocznij"*.

Po imporcie:
- Upewnij się, że tabele zostały utworzone.
- Zweryfikuj, czy dane są widoczne.

### 2. Konfiguracja API OpenWeatherMap
1. Utwórz konto na [OpenWeatherMap](https://openweathermap.org/).
2. Pobierz klucz API.
3. Wprowadź klucz w pliku `WeatherApiClient.java`:
    private static final String API_KEY = "your_api_key";

### 3. Przygotowanie pliku CSV

Aby korzystać z funkcji odczytu danych z pliku CSV, plik powinien mieć następujący format:

date;day_of_week;hour;temperature
2025-01-20;1;00:00;5.0
2025-01-20;1;01:00;4.5

Każdy wiersz powinien zawierać dane temperatury w formacie:

- `date` - data w formacie `yyyy-MM-dd`
- `day_of_week` - dzień tygodnia (0-6, gdzie 0 oznacza niedzielę)
- `hour` - godzina w formacie `HH:mm`
- `temperature` - temperatura w stopniach Celsjusza

### 4. Zapisanie danych z pliku CSV do bazy danych
1. Podaj ścieżkę do pliku CSV w aplikacji.
2. Aplikacja automatycznie odczyta dane z pliku i zapisze je w bazie danych.

### 5. Dodanie biblioteki JSON

#### Pobranie biblioteki

1. Pobierz bibliotekę `org.json` z Maven Repository.
2. Wybierz najnowszą wersję i pobierz plik `.jar`.

#### Dodanie do projektu w IntelliJ IDEA:
1. Otwórz projekt w IntelliJ IDEA.
2. Kliknij prawym przyciskiem myszy na projekt -> `Open Module Settings`.
3. Przejdź do zakładki `Libraries` -> kliknij `+` -> `Java`.
4. Wskaż pobrany plik `.jar` i zatwierdź.

### 6. Dodanie biblioteki MySQL Connector/J

#### Pobranie biblioteki

1. Pobierz bibliotekę `mysql-connector-j-9.2.0` z oficjalnej strony MySQL lub z Maven Repository.
2. Wybierz najnowszą wersję i pobierz plik `.jar`.

#### Dodanie do projektu w IntelliJ IDEA:
1. Otwórz projekt w IntelliJ IDEA.
2. Kliknij prawym przyciskiem myszy na projekt -> `Open Module Settings`.
3. Przejdź do zakładki `Libraries` -> kliknij `+` -> `Java`.
4. Wskaż pobrany plik `mysql-connector-j-9.2.0.jar` i zatwierdź.

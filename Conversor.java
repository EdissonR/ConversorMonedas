import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public class Conversor {

    public static void main(String[] args) throws IOException, InterruptedException {

        //Haciendo conexion con el servidor de la API
        String direction = "https://v6.exchangerate-api.com/v6/613e87f0d07c29a70556a660/latest/USD";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direction))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        Gson gson = new Gson();



        //Imprimiendo en pantalla saludo de bienvenida
        System.out.println("\n******************************************************************************************\n");
        System.out.println("HOLA, BIENVENIDO AL CONVERSOR DE MONEDAS <<<<<<<<<<SOY ESTUDIANTE ALURA>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("\n******************************************************************************************\n");
        Map<String, Double> conversionRates = (Map<String, Double>) gson.fromJson(json, Map.class).get("conversion_rates");
        System.out.println("\nimprimiendo valor de las monedas del mundo ☻☻☻☻\n");
        System.out.println(json);



        //Creando el conversor
        Scanner moneda = new Scanner(System.in);
        boolean alura = false;

        while (!alura) {
            System.out.println("\n Escribe las iniciales ↑↑ de una moneda para convertir (USD, EUR, GBP,COP) o Escribe 'alura' para salir:\n ");
            String selectedCurrency = moneda.nextLine().toUpperCase();

            if (selectedCurrency.equals("ALURA")) {
                alura = true;
                break;
            }

            if (conversionRates.containsKey(selectedCurrency)) {
                System.out.println("Ingrese el valor en dólares a convertir: ");
                double amountInUSD = moneda.nextDouble();

                double conversionRate = conversionRates.get(selectedCurrency);
                double convertedAmount = amountInUSD * conversionRate;

                System.out.println(amountInUSD + " USD equivale a " + convertedAmount + " " + selectedCurrency);
            } else {
                System.out.println("La moneda seleccionada no está disponible.");
            }

                moneda.nextLine(); // Limpiar el buffer del scanner
        }

                System.out.println("¡Desafio Culminado!");
                moneda.close();
    }
}



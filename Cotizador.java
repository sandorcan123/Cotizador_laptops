package elpepe;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cotizador {
    private Map<String, Double> preciosPorSemana;
    private Map<String, Double> preciosPorMes;

    public Cotizador() {
        // Inicializar precios por semana y mes
        preciosPorSemana = Map.of(
            "8GB", 7.0, "16GB", 8.0,
            "Windows", 5.0,
            "Core i5", 6.0, "Core i7", 7.0,
            "1TB Mecánico", 9.0, "256GB Sólido", 11.0, "500GB Sólido", 14.0
        );

        preciosPorMes = Map.of(
            "8GB", 6.0, "16GB", 7.0,
            "Windows", 5.0,
            "Core i5", 7.0, "Core i7", 8.0,
            "1TB Mecánico", 10.0, "256GB Sólido", 12.0, "500GB Sólido", 14.5
        );
    }

    public double calcularCosto(String duracion, String ram, String os, String cpu, String storage, int cantidadLaptops) {
        Map<String, Double> precios = duracion.equals("semana") ? preciosPorSemana : preciosPorMes;
        return (precios.get(ram) + precios.get(os) + precios.get(cpu) + precios.get(storage)) * cantidadLaptops;
    }

    public static void main(String[] args) {
        Cotizador cotizador = new Cotizador();
        Scanner scanner = new Scanner(System.in);

        // Menús para ingresar opciones
        System.out.println("Seleccione la duración: 1. Semana  2. Mes");
        String duracion = scanner.nextLine().equals("1") ? "semana" : "mes";

        System.out.println("Seleccione la RAM: 1. 8GB  2. 16GB");
        String ram = scanner.nextLine().equals("1") ? "8GB" : "16GB";

        System.out.println("Seleccione el procesador: 1. Core i5  2. Core i7");
        String cpu = scanner.nextLine().equals("1") ? "Core i5" : "Core i7";

        System.out.println("Seleccione el almacenamiento: 1. 1TB Mecánico  2. 256GB Sólido  3. 500GB Sólido");
        String storage = switch (scanner.nextLine()) {
            case "1" -> "1TB Mecánico";
            case "2" -> "256GB Sólido";
            case "3" -> "500GB Sólido";
            default -> throw new IllegalArgumentException("Opción no válida");
        };

        System.out.println("Ingrese la cantidad de laptops: ");
        int cantidadLaptops = scanner.nextInt();

        System.out.println("Ingrese la cantidad de duración: ");
        int cantidadDuracion = scanner.nextInt();

        // Calcular costos
        double costoMensual = cotizador.calcularCosto(duracion, ram, "Windows", cpu, storage, cantidadLaptops);
        double costoTotal = costoMensual * cantidadDuracion;

        System.out.printf("Costo por %s: $%.2f%n", duracion.equals("semana") ? "semana" : "mes", costoMensual);
        System.out.printf("Costo total: $%.2f%n", costoTotal);

        scanner.close();
    }
}

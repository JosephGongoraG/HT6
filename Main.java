import java.io.*;
import java.util.*;

public class Main {
    static Map<String, String> inventario;
    static Map<String, Integer> coleccionUsuario = new HashMap<>();
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Seleccione implementación:");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");

        int opcion = sc.nextInt();
        sc.nextLine();
        inventario = MapFactory.getMap(opcion);
        cargarInventario("ListadoProducto.txt");
        int choice;

        do {
            System.out.println("\nMENU");
            System.out.println("1. Agregar producto");
            System.out.println("2. Mostrar categoría de producto");
            System.out.println("3. Mostrar colección usuario");
            System.out.println("4. Mostrar colección ordenada");
            System.out.println("5. Mostrar inventario");
            System.out.println("6. Mostrar inventario ordenado");
            System.out.println("0. Salir");

            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    agregarProducto(sc);
                    break;
                case 2:
                    mostrarCategoria(sc);
                    break;
                case 3:
                    mostrarColeccion(false);
                    break;
                case 4:
                    mostrarColeccion(true);
                    break;
                case 5:
                    mostrarInventario(false);
                    break;
                case 6:
                    mostrarInventario(true);
                    break;
            }

        } while (choice != 0);
    }

    public static void cargarInventario(String archivo) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;

        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split("\\|");
            String categoria = partes[0].trim();
            String producto = partes[1].trim();

            inventario.put(producto, categoria);
        }
        br.close();
        long inicio = System.nanoTime();
        mostrarInventario(false);
        long fin = System.nanoTime();
        System.out.println("Tiempo: " + (fin - inicio) + " ns");

    }

    public static void agregarProducto(Scanner sc) {
        System.out.println("Ingrese producto:");
        String producto = sc.nextLine();

        if (!inventario.containsKey(producto)) {
            System.out.println("Producto no existe.");
            return;
        }

        coleccionUsuario.put(producto,
                coleccionUsuario.getOrDefault(producto, 0) + 1);

        System.out.println("Producto agregado.");
    }

    public static void mostrarCategoria(Scanner sc) {
        System.out.println("Ingrese producto:");
        String producto = sc.nextLine();

        if (inventario.containsKey(producto)) {
            System.out.println("Categoría: " + inventario.get(producto));
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public static void mostrarColeccion(boolean ordenar) {
        Map<String, Integer> mapa = coleccionUsuario;

        if (ordenar) {
            mapa = new TreeMap<>(coleccionUsuario);
        }

        for (String producto : mapa.keySet()) {
            String categoria = inventario.get(producto);
            int cantidad = mapa.get(producto);

            System.out.println(producto + " | " + categoria + " | " + cantidad);
        }
    }

    public static void mostrarInventario(boolean ordenar) {
        Map<String, String> mapa = inventario;

        if (ordenar) {
            mapa = new TreeMap<>(inventario);
        }
        for (String producto : mapa.keySet()) {
            System.out.println(producto + " | " + mapa.get(producto));
        }
    }
}

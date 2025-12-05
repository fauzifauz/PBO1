import java.io.*;
import java.util.List;

public class CSVExporter {
    public static void exportAll(DataStore store) throws IOException {
        exportStations(store.getStations(), "stations.csv");
        exportRoutes(store.getRoutes(), "routes.csv");
        exportUsers(store.getUsers(), "users.csv");
    }

    public static void exportStations(List<Station> stations, String filename) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println("index,name");
            for (int i = 0; i < stations.size(); i++) {
                pw.printf("%d,%s%n", i, escapeCsv(stations.get(i).getName()));
            }
        }
    }

    public static void exportRoutes(List<Route> routes, String filename) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println("index,asal,tujuan,harga");
            for (int i = 0; i < routes.size(); i++) {
                Route r = routes.get(i);
                pw.printf("%d,%s,%s,%.0f%n", i, escapeCsv(r.getAsal()), escapeCsv(r.getTujuan()), r.getHarga());
            }
        }
    }

    public static void exportUsers(List<User> users, String filename) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println("username,role");
            for (User u : users) {
                pw.printf("%s,%s%n", escapeCsv(u.getUsername()), escapeCsv(u.getRole()));
            }
        }
    }

    private static String escapeCsv(String s) {
        if (s.contains(",") || s.contains("\"") || s.contains("\n")) {
            s = s.replace("\"", "\"\"");
            return "\"" + s + "\"";
        }
        return s;
    }
}

import edu.drexel.se211.CSVLib.CSVReader;
import edu.drexel.se211.CSVLib.CSVTable;

public class Main {
    public static void main(String[] args) {
        String filename = "example.csv";
        try {
            CSVReader reader = new CSVReader(filename);
            reader.setHasHeader(true);
            CSVTable table = reader.readTable();
            table.print();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
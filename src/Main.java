import utils.DatabaseConnection;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("✅ Connected3!");
        } catch (Exception e) {
            System.out.println("gg");
            e.printStackTrace();
        }
    }
}

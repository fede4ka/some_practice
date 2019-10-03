import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JavaDBpostgresstest  {

    public static void main(String[] args) {
        try{
             String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
             String user = "postgres";
             String password = "";
            //Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();

            String sqlCommand1 = "DROP TABLE IF EXISTS processors";
            String sqlCommand2 = "CREATE TABLE processors (id   SERIAL,name VARCHAR NOT NULL UNIQUE,price  SMALLINT)";
            String sqlCommand3 = "INSERT INTO processors  (name, price) VALUES ('Core i7-9700K', 374), " +
                    "('Core i7-8700',   303), " +
                    "('Core i7-8700K', 359), " +
                    "('Core i7-9700KF', 374)";

            try (Connection conn = DriverManager.getConnection(url, user, password)){

                Statement statement = conn.createStatement();
                statement.executeUpdate(sqlCommand1);
                statement.executeUpdate(sqlCommand2);
                statement.executeUpdate(sqlCommand3);

                ResultSet resultSet = statement.executeQuery("SELECT * FROM processors");
                int sum = 0;
                var str = new ArrayList<>();
                while(resultSet.next()){
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    str.add(name);
                    int price = resultSet.getInt(3);
                    sum= sum+price;
                    System.out.printf("%d. %s - %d \n", id, name, price);
                }
                System.out.println("Sum = "+ sum);
                System.out.println(str);
                System.out.println("Its alive!");
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
}

package cafe.Utils.db;

import static cafe.Utils.db.Dish.DishUtils.PASSWORD;
import static cafe.Utils.db.Dish.DishUtils.URL;
import static cafe.Utils.db.Dish.DishUtils.USERNAME;
import static cafe.Utils.db.Dish.DishUtils.getCurrentTimeStamp;
import cafe.model.Ingredient;
import cafe.model.User;
import cafe.view.MainForm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author suxarina
 */
public class StorageUtils {

    public static void readStorage() {
        try (Connection connection = DriverManager
                .getConnection(URL, USERNAME, PASSWORD)) {
            String SQL = "SELECT * FROM storage";
            System.out.println(!connection.isClosed() ? "DB connected! readStorage"
                    : "Error DB connecting");
            MainForm.storageList.clear();
            Statement statement = connection.createStatement();
            try (ResultSet rs = statement.executeQuery(SQL)) {
                while (rs.next()) {
                    MainForm.storageList.add(
                            new Ingredient(
                                    rs.getInt("Id"),
                                    rs.getString("title"),
                                    rs.getDouble("count")
                            ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console - readStorage");
        }

    }

    public static void addIngredientToDB(Ingredient ingredient) {
        final String SQL = "INSERT INTO storage(title, count) VALUES(?, ?)";
        try (Connection connection = DriverManager
                .getConnection(URL, USERNAME, PASSWORD)) {

            PreparedStatement pstatement = connection.prepareStatement(SQL);
            pstatement.setString(1, ingredient.getTitle());       
            pstatement.setInt(2, 0);       
            int rowsInserted = pstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new Ingredient was added successfully!");
                
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console - addIngredientToDB");
        }

    }
    
    public static void removeIngredientFromDB(int dbId) {
        final String SQL = "DELETE FROM storage WHERE Id = ?";

        try (Connection connection = DriverManager
                .getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement pst = connection.prepareStatement(SQL);
            pst.setInt(1, dbId);
            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Ingredient was removed successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console - removeIngredientFromDB");
        }

    }
    public static void updateCount(int dbId, double count){
        
        final String SQL = "UPDATE storage SET count = ? WHERE Id = ?";
        try (Connection connection = DriverManager
                .getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement pst = connection.prepareStatement(SQL);
            pst.setDouble(1, count);
            pst.setInt(2, dbId);
            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Storage count was updated successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console - updateCount");
        }
    }
    
    public static Ingredient getIngredientById(int id){
        Ingredient ingredient = new Ingredient();
        try (Connection connection = DriverManager
                .getConnection(URL, USERNAME, PASSWORD)) {
            String SQL = "SELECT * FROM storage WHERE Id =" + id;
            System.out.println(!connection.isClosed() ? "DB connected! getIngredientById"
                    : "Error DB connecting");
            MainForm.storageList.clear();
            Statement statement = connection.createStatement();
            try (ResultSet rs = statement.executeQuery(SQL)) {
                while (rs.next()) {
                    ingredient.setId(rs.getInt("Id"));
                    ingredient.setTitle(rs.getString("title"));
                    ingredient.setCount(rs.getDouble("count"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console - readStorage");
        }
        return ingredient;
        
    }
    
    public static void addRemovedItems(String JsonItems, User user) {
        final String SQL = "INSERT INTO storage_removed(date, operator, removed_ingredients) VALUES(?, ?, ?)";
        try (Connection connection = DriverManager
                .getConnection(URL, USERNAME, PASSWORD)) {

            PreparedStatement pstatement = connection.prepareStatement(SQL);
            pstatement.setTimestamp(1, getCurrentTimeStamp());
            pstatement.setString(2, user.getName());
            pstatement.setString(3, JsonItems);
            int rowsInserted = pstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new RemovedItems was added successfully!");

            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console - addRemovedItems");
        }
    }
    public static void g(String JsonItems, User user) {
        final String SQL = "INSERT INTO storage_removed(date, operator, removed_ingredients) VALUES(?, ?, ?)";
        try (Connection connection = DriverManager
                .getConnection(URL, USERNAME, PASSWORD)) {

            PreparedStatement pstatement = connection.prepareStatement(SQL);
            pstatement.setTimestamp(1, getCurrentTimeStamp());
            pstatement.setString(2, user.getName());
            pstatement.setString(3, JsonItems);
            int rowsInserted = pstatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new RemovedItems was added successfully!");

            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console - addRemovedItems");
        }
    }
    
    
    
    
    

}

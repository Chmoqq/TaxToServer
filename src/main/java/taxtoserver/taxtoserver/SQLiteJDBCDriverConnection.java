package taxtoserver.taxtoserver;


import java.sql.*;

public class SQLiteJDBCDriverConnection {

    Connection s1 = null;
    Connection s2 = null;
    Statement st1 = null;
    Statement st2 = null;
    public static long time = 0;

    public SQLiteJDBCDriverConnection() {
        connectDB("jdbc:sqlite:/home/minecraft/laguna_network/s1/plugins/YourAuction/data/database.db", "jdbc:sqlite:/home/minecraft/laguna_network/s2/plugins/YourAuction/data/database.db");
    }

    private void connectDB(String s1, String s2) {
        try {
            this.s1 = DriverManager.getConnection(s1);
            this.s2 = DriverManager.getConnection(s2);
            st1 = this.s1.createStatement();
            st2 = this.s2.createStatement();
            time = System.currentTimeMillis();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    int taxCalculate() {
        int tax = 0;
        while (System.currentTimeMillis() < time + 360) {
            continue;
        }
        try {
            String query = "select sum(tax_abs) from (select (price / 100 * tax) as tax_abs  from YOURAUCTION_ITEM_SOLD WHERE date>" + time + ") d;";
            ResultSet resultSet = st1.executeQuery(query);
            while (resultSet.next()) {
                tax = resultSet.getInt("sum(tax_abs)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tax;
    }
}

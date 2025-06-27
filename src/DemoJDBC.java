import java.sql.*;

public class DemoJDBC {
    public static void main(String[] args) throws Exception {
        /*

        Import packages (import java.sql.*;)
        Load drivers
        Register drivers
        Create connection
        Create statement
        Execute statement
        Close

         */

        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/myFirstDB";
        String uName = "postgres";
        String pass = "Hulkbuster#008";
        String query = "select * from student ";

        Connection con = DriverManager.getConnection(url,uName,pass);
        System.out.println("Connection Established");


        Statement st = con.createStatement();
        ResultSet rt = st.executeQuery(query);
//        rt.next();
//        System.out.println(rt.next());
//        String name = rt.getString(1);
//        System.out.println("the username is "+name);
        System.out.println("===================");

        while (rt.next()){
            System.out.print(rt.getInt("sid") + "-");
            System.out.print(rt.getString(2) + "-");
            System.out.println(rt.getInt(3) );
        }
        con.close();
        System.out.println("Connection Closed");
    }
}

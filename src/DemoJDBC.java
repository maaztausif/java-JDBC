import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String queryInsert = "insert into student values (10, 'dilawar', 90)";

        String queryUpdate = "update student set sname = 'maaz is updated' where sid = 1  ";

        String queryDelete = "delete from student where  sid = 3 ";
        DemoJDBC myClass = new DemoJDBC();

        Connection con = DriverManager.getConnection(url, uName, pass);
        System.out.println("Connection Established");


        Statement st = con.createStatement();
        ResultSet rt = st.executeQuery(query);

        System.out.println("===================");
        //getting values
        myClass.printResultSetValues(rt);

        //Insert Values
        boolean insertresult = st.execute(queryInsert);
        System.out.println("After inserting");

        //getting values
        System.out.println("===================");
        myClass.printResultSetValues(st.executeQuery(query));

        //update values
        st.execute(queryUpdate);

        //getting values
        System.out.println("===================");
        myClass.printResultSetValues(st.executeQuery(query));

        //delete values
        boolean deleteResult = st.execute(queryDelete);

        //getting values
        System.out.println("===================");
        myClass.printResultSetValues(st.executeQuery(query));


        con.close();
        System.out.println("Connection Closed");

    }

    public void printResultSetValues(ResultSet rt) {
        try {
            while (rt.next()) {
                System.out.print(rt.getInt("sid") + "-");
                System.out.print(rt.getString(2) + "-");
                System.out.println(rt.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

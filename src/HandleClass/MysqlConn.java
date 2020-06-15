/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HandleClass;

/**
 *
 * @author asus
 */
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author asus
 */
public class MysqlConn {

    private static Connection conn = null;

    public static Connection getCon() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            String user = "root";
            String passwd = "";
            String url = "jdbc:mysql://localhost:3306/software?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
            conn = DriverManager.getConnection(url, user, passwd);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;

    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HandleClass;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import Sponsorsystem.ShowRunnerinfo;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class AdminQuery {

    public static void query(String sql, DefaultTableModel ta) {

        String num = null;
        String name = null;
        String unit = null;
        String signday = null;
        String runtype = null;
        String status = null;
        String sex = null;
        String age = null;
        String experience = null;
        int flag = 0;
        Connection con = MysqlConn.getCon();
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {
                flag++;
                num = rs.getString("runner_id");
                name = rs.getString("runner_name");
                unit = rs.getString("runner_unit");
                signday = rs.getString("signday");
                runtype = rs.getString("runtype");
                status = rs.getString("status");
                if ("0".equals(status)) {
                    status = "待审核";
                } else if ("1".equals(status)) {
                    status = "审核通过";
                } else {
                    status = "审核未通过";
                }
                sex = rs.getString("runner_sex");
                age = rs.getString("runner_age");
                experience = rs.getString("experience");
                Vector v = new Vector();

                v.add(num);
                v.add(name);
                v.add(sex);
                v.add(age);
                v.add(unit);
                v.add(signday);
                v.add(runtype);
                v.add(experience);
                v.add(status);
                ta.addRow(v);

            }
            if (flag == 0) {
                Vector v = new Vector();

                ta.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShowRunnerinfo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void salaryquery(String sql, String depay, DefaultTableModel ta) {

        String num = null;
        String num2 = null;
        String name = null;
        String name2 = null;
        String time = null;
        String salary = null;
        String sql2 = null;

        Connection con = MysqlConn.getCon();
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            //ResultSet rs2 = stat.executeQuery(sql2);

            while (rs.next()) {
                num = rs.getString("staffid");
                name = rs.getString("staffname");
                salary = rs.getString("pay");
                time = find(num);
                Vector v = new Vector();
                v.add(num);
                v.add(name);
                v.add(salary);
                v.add(time);
                int money = Integer.parseInt(salary) + Integer.parseInt(time) * Integer.parseInt(depay);
                v.add(money);
                ta.addRow(v);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ShowRunnerinfo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String find(String num) {
        String time = null;
        try {
            String sql2 = null;
            sql2 = "select * from signtable where staffid='" + num + "'";
            ResultSet rs2 = null;
            Connection con = MysqlConn.getCon();
            Statement stat2 = con.createStatement();
            rs2 = stat2.executeQuery(sql2);
            if (rs2.first()) {

                time = rs2.getString("signnum");
            } else {
                time = "0";
            }
            //time=Integer.parseInt(ti);
        } catch (SQLException ex) {
            Logger.getLogger(AdminQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return time;
    }

    public static void leavequery() {
        String num[] = null;
        String name[] = null;
        String content[] = null;
        String time[] = null;
        int state[];
        try {
            String sql = "select * from leavetable where state=0";
            Connection con = MysqlConn.getCon();
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                num[i] = rs.getString("staffid");
                name[i] = rs.getString("staffname");
                content[i] = rs.getString("content");
                time[i] = rs.getString("time");
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void buquery(String sql, DefaultTableModel ta) {

        String num = null;
        String name = null;
        String timeString = null;
        String salary = null;

        Connection con = MysqlConn.getCon();
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                num = rs.getString("businessid");
                name = rs.getString("businessname");
                salary = rs.getString("bumoney");
                timeString = rs.getString("butime");

                Vector v = new Vector();
                v.add(num);
                v.add(name);
                v.add(timeString);
                v.add(salary);

                ta.addRow(v);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ShowRunnerinfo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ArrayList<Runner> Checkapply() {
        String num = null;
        String name = null;
        String unit = null;
        String signday = null;
        String runtype = null;
        String status = null;
        String sex = null;
        String age = null;
        String experience = null;
        ArrayList<Runner> list = new ArrayList<Runner>();
        try {
            Connection con = MysqlConn.getCon();
            String sql = "select * from runnerinfo where status=0";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                Runner runner = new Runner();
                num = rs.getString("runner_id");
                name = rs.getString("runner_name");
                unit = rs.getString("runner_unit");
                signday = rs.getString("signday");
                runtype = rs.getString("runtype");
                status = rs.getString("status");
                if ("0".equals(status)) {
                    status = "待审核";
                } else if ("1".equals(status)) {
                    status = "审核通过";
                } else {
                    status = "审核未通过";
                }
                sex = rs.getString("runner_sex");
                age = rs.getString("runner_age");
                experience = rs.getString("experience");
                runner.setNum(num);
                runner.setName(name);
                runner.setUnit(unit);
                runner.setSignday(signday);
                runner.setRuntype(runtype);
                runner.setExperience(experience);
                runner.setStatus(status);
                runner.setAge(age);
                runner.setSex(sex);
                list.add(runner);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    return list;}
    public static void Update(String sql ){
        try {
            Connection con = MysqlConn.getCon();
            Statement stat = con.createStatement();
            stat.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AdminQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

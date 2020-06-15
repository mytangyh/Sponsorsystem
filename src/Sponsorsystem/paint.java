/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sponsorsystem;

import HandleClass.MysqlConn;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Tang
 */
public class paint {

    ChartPanel frame1;

    public paint(int flag) {

        CategoryDataset dataset = getDataSet(flag);
        JFreeChart chart = ChartFactory.createBarChart3D(
                "信息统计", // 图表标题
                "赛事种类", // 目录轴的显示标签
                "人数", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true, // 是否显示图例(对于简单的柱状图必须是false)
                false, // 是否生成工具
                false // 是否生成URL链接
        );

        //从这里开始
        CategoryPlot plot = chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis = plot.getDomainAxis();         //水平底部列表
        domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14));         //水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12));  //垂直标题
        ValueAxis rangeAxis = plot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));//设置标题字体

        //到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题
        frame1 = new ChartPanel(chart, true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame

    }

    private static CategoryDataset getDataSet(int flag) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //数据库读数据
        String type = null;
        int a = 0, b = 0, c = 0;

        String sql = "select * from runnerinfo ";
        Connection con = MysqlConn.getCon();
        Statement stat;
        try {
            stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                type = rs.getString("runtype");
                if (type.equals("全程")) {
                    a++;
                } else if (type.equals("半程")) {
                    b++;
                } else {
                    c++;
                }

            }
            dataset.addValue(a, "全程", "全程");
            dataset.addValue(b, "半程", "半程");
            dataset.addValue(c, "四分", "四分");
        } catch (SQLException ex) {
            Logger.getLogger(paint.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dataset;
    }

    public ChartPanel getChartPanel() {
        return frame1;

    }
}

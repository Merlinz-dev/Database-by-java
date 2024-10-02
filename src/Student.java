/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labnerd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NONT
 */
public class Student {
    public void insert(char operation,Integer id,String fname,String lname,String sex,String bdate,String course){
        
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        
        
        if(operation == 'i'){   //i for insert สมัครสมาชิก
            try {
                ps = con.prepareStatement("INSERT INTO student(first_name,last_name,sex,birthdate,course) VALUES (?,?,?,?,?)");
                ps.setString(1, fname);
                ps.setString(2, lname);
                ps.setString(3, sex);
                ps.setString(4, bdate);
                ps.setString(5, course);
                
                if(ps.executeUpdate() > 0){
                    JOptionPane.showMessageDialog(null, "New Student Added");
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(operation == 'd'){   //d for delete ลบสมาชิก
            try {
                ps = con.prepareStatement("DELETE FROM `student` WHERE `id` = ?");
                ps.setInt(1, id);
                
                
                if(ps.executeUpdate() > 0){
                    JOptionPane.showMessageDialog(null, "Student Deleted");
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        

        
}
    
    
    //ดึงข้อมูลจาก phpmysql
    public void fillStudentJtable(JTable table, String valueToSearch){
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `student` WHERE CONCAT(`first_name`,`last_name`,`sex`,`birthdate`)LIKE ?");
            ps.setString(1, "%"+valueToSearch+"%");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            Object[]row;
            
            while(rs.next()){
                row = new Object[6];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);

                

                
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


    void insert(char c, int id, String course) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

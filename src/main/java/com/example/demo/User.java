package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class User {
    private Connection connect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid_db?useSSL=false", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }


    //*************************read users***********************************************
    public String readUsers() {
        String output = "";
        try {
            Connection con = connect();
            if (con == null) {
                return "Error while connecting to the database for reading.";
            }
// Prepare the html table to be displayed
            output = "<table class='table table-striped'><tr><th style=width:100px; >Account Number</th>"
                    + "<th style=width:100px; >User Name</th>"
                    + "<th style=width:100px; >Email</th>"
                    + "<th style=width:100px; >Mobile No</th>"
                    + "<th style=width:100px; >Address</th>"
                    + "<th style=width:100px; >Units</th>"
                    + "<th style=width:50px; >Update</th>"
                    + "<th style=width:50px; >Remove</th></tr>";


            String query = "SELECT * FROM electrogrid_db.users";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
            while (rs.next()) {
                String id = Integer.toString(rs.getInt("id"));
                String accountNo = rs.getString("accountNo");
                String userName = rs.getString("userName");
                String email = rs.getString("email");
                String mobileNo = rs.getString("mobileNo");
                String address = rs.getString("address");
                String units = rs.getString("units");

// Add into the html table
                output += "<tr><td><input id='hidUserIDUpdate'name='hidUserIDUpdate'type='hidden' value='" + id
                        + "'>" + accountNo + "</td>";
                output += "<td>" + userName + "</td>";
                output += "<td>" + email + "</td>";
                output += "<td>" + mobileNo + "</td>";
                output += "<td>" + address + "</td>";
                output += "<td>" + units + "</td>";
// buttons
                output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'>"
                        + "</td><td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-id='"+id+"'></td></tr>";
            }
            con.close();
// Complete the html table
            output += "</table>";
        } catch (Exception e) {
            output = "Error while reading Users.";
            System.err.println(e.getMessage());
        }
        return output;
    }


    //***********************************insert users****************************************
    public String insertUser(String accountNo, String userName, String email, String mobileNo, String address, String units)
    {
        String output = "";
        try
        {
            Connection con = connect();
            if (con == null)
            {
                return "Error while connecting to the database for inserting.";
            }
// create a prepared statement
            String query = "insert into electrogrid_db.users (id, accountNo, userName, email, mobileNo, address, units) values (?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
// users values
            preparedStmt.setInt(1, 0);
            preparedStmt.setString(2, accountNo);
            preparedStmt.setString(3, userName);
            preparedStmt.setString(4, email);
            preparedStmt.setString(5, mobileNo);
            preparedStmt.setString(6, address);
            preparedStmt.setString(7, units);
// execute the statement
            preparedStmt.execute();
            con.close();
            String newUsers = readUsers();
            output = "{\"status\":\"success\", \"data\": \"" +
                    newUsers + "\"}";
        }
        catch (Exception e)
        {
            output = "status:error ,data:Error while inserting user.";
            System.err.println(e.getMessage());
        }
        return output;
    }


    //***********************************update users****************************************
    public String updateUser(String id, String accountNo, String userName, String email, String mobileNo, String address, String units)
    {
        String output = "";
        try
        {
            Connection con = connect();
            if (con == null)
            {
                return "Error while connecting to the database for updating.";
            }
// create a prepared statement
            String query = "UPDATE electrogrid_db.users SET accountNo=?, userName=?, email=?, mobileNo=?, address=?, units=? WHERE id =?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
            preparedStmt.setString(1, accountNo);
            preparedStmt.setString(2, userName);
            preparedStmt.setString(3, email);
            preparedStmt.setString(4, mobileNo);
            preparedStmt.setString(5, address);
            preparedStmt.setString(6, units);
            preparedStmt.setInt(7, Integer.parseInt(id));
// execute the statement
            preparedStmt.execute();
            con.close();
            String newUsers = readUsers();
            output = "{\"status\":\"success\", \"data\": \"" +
                    newUsers + "\"}";
        }
        catch (Exception e)
        {
            output = "status:error data Error while updating the bill.";
            System.err.println(e.getMessage());
        }
        return output;
    }



//***********************************delete users****************************************



    public String deleteUser(String id)
    {
        String output = "";
        try
        {
            Connection con = connect();
            if (con == null)
            {
                return "Error while connecting to the database for deleting.";
            }
// create a prepared statement
            String query = "DELETE FROM electrogrid_db.users WHERE id =?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
            preparedStmt.setInt(1, Integer.parseInt(id));
// execute the statement
            preparedStmt.execute();
            con.close();
            String newUsers = readUsers();
            output = "{\"status\":\"success\", \"data\": \"" +
                    newUsers + "\"}";
        }
        catch (Exception e)
        {
            output = "status:error data :Error while deleting user.";
            System.err.println(e.getMessage());
        }
        return output;
    }
}

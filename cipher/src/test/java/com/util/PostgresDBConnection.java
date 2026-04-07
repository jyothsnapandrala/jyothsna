package com.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
 
public class PostgresDBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5433/login dB";
    private static final String USER = "loginuser";
    private static final String PASSWORD = "jyothsna";
	//private static final String query = null;
 
    public static List<List<String>>getLoginTestData(String query) throws SQLException {
        List<List<String>> data = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            int cols = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                List<String> row = new ArrayList<>();
                for (int i = 1; i <= cols; i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
        }
        return data;
    }}

 
	

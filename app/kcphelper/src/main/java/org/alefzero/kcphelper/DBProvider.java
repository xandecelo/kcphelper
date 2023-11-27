package org.alefzero.kcphelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBProvider {
    protected static final Logger logger = LogManager.getLogger();

    private String jdbcUrl;
    private String jdbcUsername;
    private String jdbcPassword;
    private String sqlQuery;

    private BasicDataSource bds = null;

    public DBProvider(String jdbcUrl, String jdbcUsername, String jdbcPassword, String sqlQuery) {
        this.jdbcUrl = jdbcUrl;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
        this.sqlQuery = sqlQuery;
    }

    public String getPhoneFor(String username) {
        String phone = "";
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                phone = rs.getString(1);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            logger.error("Error running SQL: {}. Return phone number as empty.", e);
        }
        return phone;
    }

    private Connection getConnection() throws SQLException {
        if (bds == null) {
            logger.info("Create datasource.");
            bds = new BasicDataSource();
            bds.setUrl(jdbcUrl);
            bds.setUsername(jdbcUsername);
            bds.setPassword(jdbcPassword);
            bds.setMaxTotal(5);
            bds.setMinIdle(1);
            bds.setCacheState(false);
        }
        return bds.getConnection();
    }

}

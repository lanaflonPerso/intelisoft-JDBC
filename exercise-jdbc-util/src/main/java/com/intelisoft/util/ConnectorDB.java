package com.intelisoft.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public final class ConnectorDB {

	private static final Logger log = Logger.getLogger(ConnectorDB.class);

	private static ConnectorDB connectorDB;

	private Connection jdbcConnection;

	public static ConnectorDB getConnectorDBInstance() {

		if (connectorDB == null) {
			connectorDB = new ConnectorDB();
		}
		return connectorDB;
	}

	public Connection getConnection() {

		if (jdbcConnection == null) {
			initConnection();
		}
		return jdbcConnection;
	}

	private void initConnection() {

		Properties properties = getDataBaseProperties();

		String driverName = properties.getProperty("db.jdbc_driver_name");
		String url = properties.getProperty("db.url");
		String user = properties.getProperty("db.user");
		String password = properties.getProperty("db.password");

		try {
			Class.forName(driverName);
			jdbcConnection = DriverManager.getConnection(url, user, password);

			jdbcConnection.setAutoCommit(false);
			
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e.getMessage(), e);
		}

	}

	public void closeConnection() {

		try {

			jdbcConnection.close();

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}

	}

	private Properties getDataBaseProperties() {

		Properties properties = new Properties();

		try (FileInputStream fis = new FileInputStream("classes/jdbc.properties")) {

			properties.load(fis);

		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}

		return properties;
	}
}

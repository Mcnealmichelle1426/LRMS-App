package org.vetech.lrms.core.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created by alex on 2/3/15.
 */
public class DatabaseManager {
		// Figure out a way to populate these variables somehow...
		static final String databaseIP = "localhost";
		static final String databasePort = "3306";
		static final String databaseName = "coreLRMS";
		static final String databaseUsername = "root";
		static final String databasePassword = "";

		static Connection connection = null;

		private DatabaseManager()
		{
		}

	public static DatabaseManager getInstance()
	{
		if(connection == null)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(
						"jdbc:mysql://" + databaseIP + ":" + databasePort + "/" + databaseName + "?user="
								+ databaseUsername + "&password=" + databasePassword);
			}
			catch (ClassNotFoundException | SQLException ex)
			{
				Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return DatabaseManagerHolder.INSTANCE;
	}

	private static class DatabaseManagerHolder
	{
		private static final DatabaseManager INSTANCE = new DatabaseManager();
	}

	public Connection getConnection()
	{
		return connection;
	}

	public ResultSet getData(String query)
	{
		ResultSet resultSet = null;
		try
		{
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
		}
		catch (SQLException ex)
		{
			Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
		}

		return resultSet;
	}

	public void executeSQL(String query)
	{
		ResultSet resultSet = null;
		try
		{
			Statement statement = connection.createStatement();
			statement.execute(query);
		}
		catch (SQLException ex)
		{
			Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public int insertReturnID(String query)
	{
		int id = 0;
		try
		{
			Statement statement = connection.createStatement();
			statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet resultSet = statement.getGeneratedKeys();
			while(resultSet.next())
			{
				id = resultSet.getInt(1);
			}
		}
		catch (SQLException ex)
		{
			Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
		}

		return id;
	}

	public boolean checkIfExists(String query)
	{
		boolean exists = false;
		try
		{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			if(resultSet.next())
			{
				exists = true;
			}
		}
		catch (SQLException ex)
		{
			Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
		}

		return exists;
	}
}


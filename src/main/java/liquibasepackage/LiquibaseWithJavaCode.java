package liquibasepackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.changelog.ChangeLogParameters;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.parser.ChangeLogParser;
import liquibase.parser.ChangeLogParserFactory;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.FileSystemResourceAccessor;
import liquibase.resource.ResourceAccessor;

public class LiquibaseWithJavaCode {

	private static ResourceAccessor resourceAccessor = new FileSystemResourceAccessor();

	static void executeUpdateForMSSQL() throws SQLException, LiquibaseException {
		ChangeLogParser parser = ChangeLogParserFactory
				.getInstance()
				.getParser(
						"src/main/resources/liquibase/mssql/sequenceProcessMSSQL.xml",
						resourceAccessor);

		DatabaseChangeLog changeLog = parser.parse(
				"src/main/resources/liquibase/mssql/sequenceProcessMSSQL.xml",
				new ChangeLogParameters(), resourceAccessor);

		java.sql.Connection connection = getConnectionForMSSQL();
		Database database = DatabaseFactory.getInstance()
				.findCorrectDatabaseImplementation(
						new JdbcConnection(connection));
		Liquibase liquibase = new liquibase.Liquibase(changeLog,
				new ClassLoaderResourceAccessor(), database);
		liquibase.update(new Contexts(), new LabelExpression());
	}

	static void executeUpdateForDB2() throws SQLException, LiquibaseException {
		ChangeLogParser parser = ChangeLogParserFactory
				.getInstance()
				.getParser(
						"src/main/resources/liquibase/db2/sequenceProcessDB2.xml",
						resourceAccessor);

		DatabaseChangeLog changeLog = parser.parse(
				"src/main/resources/liquibase/db2/sequenceProcessDB2.xml",
				new ChangeLogParameters(), resourceAccessor);

		java.sql.Connection connection = getConnectionForDB2();
		Database database = DatabaseFactory.getInstance()
				.findCorrectDatabaseImplementation(
						new JdbcConnection(connection));
		Liquibase liquibase = new liquibase.Liquibase(changeLog,
				new ClassLoaderResourceAccessor(), database);
		liquibase.update(new Contexts(), new LabelExpression());
	}

	public static Connection getConnectionForMSSQL() throws SQLException {

		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", "liquibase");
		connectionProps.put("password", "password");

		conn = DriverManager
				.getConnection(
						"jdbc:sqlserver://192.168.2.38:1433;databaseName=liquibase;SelectMethod=cursor",
						connectionProps);
		return conn;
	}

	public static Connection getConnectionForDB2() throws SQLException {

		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", "Administrator");
		connectionProps.put("password", "@lm0stD0ne");

		conn = DriverManager.getConnection(
				"jdbc:db2://192.168.2.43:50000/test", connectionProps);
		return conn;
	}

	public static Connection getConnectionForOracle() throws SQLException {

		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", "liquibase");
		connectionProps.put("password", "password");

		conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@vmbiumft3:1521:orcl", connectionProps);
		return conn;
	}

	static void executeUpdateForOracle() throws SQLException,
			LiquibaseException {
		ChangeLogParser parser = ChangeLogParserFactory
				.getInstance()
				.getParser(
						"src/main/resources/liquibase/oracle/sequenceProcess.xml",
						resourceAccessor);

		DatabaseChangeLog changeLog = parser.parse(
				"src/main/resources/liquibase/oracle/sequenceProcess.xml",
				new ChangeLogParameters(), resourceAccessor);

		java.sql.Connection connection = getConnectionForOracle();
		Database database = DatabaseFactory.getInstance()
				.findCorrectDatabaseImplementation(
						new JdbcConnection(connection));
		Liquibase liquibase = new liquibase.Liquibase(changeLog,
				new ClassLoaderResourceAccessor(), database);
		liquibase.update(new Contexts(), new LabelExpression());
	}

	public static void main(String[] args) throws SQLException,
			LiquibaseException {
		executeUpdateForOracle();
	}

}

package oracle.fsgbu.customer.test.steps;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class GetDatabaseConnectionImpl {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GetDatabaseConnectionImpl.class);
	
	private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static Connection conn = null;
	
 	
	/**
	 * Save jdbc connection.
	 * 
	 * @param table jdbc connection connection information.
	 */

	@Given("^the jdbc connection for connecting to Customer db as :$")
	public void the_jdbc_connection_for_connecting_to_Invoicing_db_as(DataTable table)
			throws MalformedURLException, SQLException, ClassNotFoundException {
		final List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		assertTrue("JDBC connection information not found.", !rows.isEmpty());
		final Map<String, String> row = rows.get(0);

		String url = row.get("url");
/*		String dbHost = System.getProperty("dbHost").toString();
		String dbPort = System.getProperty("dbPort").toString();

		if (dbHost != null && dbPort != null) {
			url = url.replace("$(dbHost)", dbHost);
			url = url.replace("$(dbPort)", dbPort);
		}
		LOGGER.debug("Database URL: " + url);*/

		final String username = row.get("username");
		final String password = row.get("password");

		LOGGER.debug("jdbc connection username=[{}]", username);
		LOGGER.debug("jdbc connection password=[{}]", password);
		LOGGER.debug("jdbc connection url=[{}]", url);
		try {
			// Load Oracle driver
			Class.forName(JDBC_DRIVER);
			// Connect to the local database
			conn = DriverManager.getConnection(url, username, password);
			LOGGER.debug("jdbc connection was made successfully.");
		} catch (SQLException | ClassNotFoundException ex1) {
			LOGGER.error("jdbc connection not made:{}", ex1);
			throw ex1;
		}
	}

	@Then("^execute sql statements for CustomerAPITest event from \"([^\"]*)\"$")
	public void execute_sql_statements_for_CustomerAPITest_event_from(final String file)
			throws IOException, SQLException {
		LOGGER.debug("conn=" + conn);
		// execute each sql in the list
		LOGGER.debug("executeSqlFromFile: [{}]", file);
		final BufferedReader buf = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), Charset.defaultCharset()));
		final Statement stmt = conn.createStatement();
		try {
			String sql = buf.readLine();
			while (sql != null) {
				stmt.addBatch(sql);
				LOGGER.debug("Added sql=" + sql + " to batch");
				sql = buf.readLine();
			}
			stmt.executeBatch();
		} catch (IOException ioex) {
			LOGGER.error("Unable to process file " + file, ioex);
			throw ioex;
		} catch (SQLException sqlex) {
			LOGGER.error("Unable to executeSql ", sqlex);
			throw sqlex;
		} finally {
			buf.close();
			stmt.close();
		}
		LOGGER.debug("executed all batched sqls from file: [{}]", file);
	}

	@Then("^close the jdbc connection for CustomerAPITest event$")
	public void close_the_jdbc_connection_for_CustomerAPITest_event() throws Throwable {
		if (conn != null) {
			conn.close();
		}
	}
}
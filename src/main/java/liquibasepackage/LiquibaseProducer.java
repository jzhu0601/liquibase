//package liquibasepackage;
//
//import java.sql.SQLException;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.inject.Produces;
//import javax.sql.DataSource;
//
//import liquibase.integration.cdi.CDILiquibaseConfig;
//import liquibase.integration.cdi.annotations.LiquibaseType;
//import liquibase.resource.ClassLoaderResourceAccessor;
//import liquibase.resource.ResourceAccessor;
//
//import org.hsqldb.jdbc.JDBCDataSource;
//
//@ApplicationScoped
//public class LiquibaseProducer {
//
//	// @Resource
//	//private DataSource ds;
//
//	@Produces
//	@LiquibaseType
//	public CDILiquibaseConfig createConfig() {
//		CDILiquibaseConfig config = new CDILiquibaseConfig();
//		config.setChangeLog("src/main/resources/liquibase/mssql/sequenceProcessMSSQL.xml");
//		return config;
//	}
//
//	@Produces
//	@LiquibaseType
//	public DataSource createDataSource() throws SQLException {
//		org.hsqldb.jdbc.JDBCDataSource ds = new JDBCDataSource();
//		ds.setDatabase("jdbc:sqlserver://192.168.2.38:1433;databaseName=liquibase;SelectMethod=cursor");
//		ds.setUser("liquibase");
//		ds.setPassword("password");
//		return ds;
//	}
//
//	@Produces
//	@LiquibaseType
//	public ResourceAccessor create() {
//		return new ClassLoaderResourceAccessor(getClass().getClassLoader());
//	}
//	
//	public static void main(String[] args){
//		LiquibaseProducer lp = new LiquibaseProducer();
//		
//	}
//}

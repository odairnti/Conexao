import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/** 
* Classe ConexaoBancoDados 
*/ 


public class ConexaoBancoDados {
	private static ConexaoBancoDados instance = null;
	private static String abrirconfig = "config.ini";
	private String jdbcDriver;
	private String url;
	private String usuario;
	private String senha;

	/** metodo ConexaoBancoDados*/ 
	private ConexaoBancoDados(){
		initDataSource();
	}
	
	/** metodo getInstance
	 * para instanciar o metodo ConexaoBancoDados();
	 * */ 
	public static ConexaoBancoDados getInstance(){
		if(instance == null)
			instance = new ConexaoBancoDados();
		return instance;
	}

	/** metodo getInstance
	 * Busca as propriedades da conexao no arquivo config.ini
	 * esse método seta as proprietades para conectar ao banco. */
	private void initDataSource(){
		Properties config = buscaconexaobd();

		setJdbcDriver(config.getProperty("driver"));
		setUrl(config.getProperty("url"));
		setusuario(config.getProperty("usuario"));
		setsenha(config.getProperty("senha"));
	}

	
	/** metodo buscaconexaobd
	 * abre as propriedades da conexao no arquivo config.ini */
	private Properties buscaconexaobd(){
		Properties properties = new Properties();

		try{
			properties.load(getClass().getResourceAsStream(getabrirbd()));
		}catch(IOException e){
			e.printStackTrace();
		}catch(NullPointerException e){
			e.printStackTrace();
		}

		return properties;
	}

	/** metodo getConexao */
	public Connection getConexao(){
		Connection conn = null;

		try {
			Class.forName(getJdbcDriver()).newInstance();
			conn = DriverManager.getConnection(getUrl(), getusuario(), getsenha());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	/** metodo getabrirbd() @return abrirconfig */
	public static String getabrirbd() {
		return abrirconfig;
	}
 
	/** metodo setabrirbd @param filePathConnectionDB */
	public static void setabrirbd(String filePathConnectionDB) {
		ConexaoBancoDados.abrirconfig = filePathConnectionDB;
	}

	/** metodo getJdbcDriver() retorna o tipo de driver para conexão com o banco */
	public String getJdbcDriver() {
		return jdbcDriver;
	}
	
	/** metodo setJdbcDriver @param jdbcDriver @return this */
	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	/** metodo getUrl() busca o parametro url*/
	public String getUrl() {
		return url;
	}
	/** metodo setUrl @param url @return this */
	public void setUrl(String url) {
		this.url = url;
	}
	/** metodo getusuario() busca o tipo de usuário*/
	public String getusuario() {
		return usuario;
	}

	/** metodo setusuario @param usuario @return this */
	public void setusuario(String usuario) {
		this.usuario = usuario;
	}

	/** metodo getsenha() busca a senha do usuário*/
	public String getsenha() {
		return senha;
	}

	/** metodo setsenha @param senha @return this */
	public void setsenha(String senha) {
		this.senha = senha;
	}	
}

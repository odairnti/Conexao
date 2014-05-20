import java.sql.Connection;
import java.sql.SQLException;

/** Programa: conexao com banco usando padrão singleton feita em Java 
@author Odair 
@version 1.0
*
*/
public class principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection myConnection = ConexaoBancoDados.getInstance().getConexao();

		try {
			myConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

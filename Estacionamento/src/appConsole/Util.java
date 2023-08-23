/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

package appconsole;

import java.util.List;

import javax.management.Query;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import estacionamento.Registro;
import estacionamento.TipoVeiculo;
import estacionamento.Veiculo;



public class Util {
	private static ObjectContainer manager;
	
	public static ObjectContainer conectarBanco(){
		if (manager != null)
			return manager;		//ja tem uma conexao
		
		//---------------------------------------------------------------
		//configurar, criar e conectar banco local (na pasta do projeto
		//---------------------------------------------------------------
		
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);  // mensagens na tela 0(desliga),1,2,3...
		
		// habilitar cascata na altera��o, remo��o e leitura
		config.common().objectClass(Registro.class).cascadeOnDelete(false);;
		config.common().objectClass(Registro.class).cascadeOnUpdate(true);;
		config.common().objectClass(Registro.class).cascadeOnActivate(true);
		config.common().objectClass(TipoVeiculo.class).cascadeOnDelete(false);;
		config.common().objectClass(TipoVeiculo.class).cascadeOnUpdate(true);;
		config.common().objectClass(TipoVeiculo.class).cascadeOnActivate(true);
		config.common().objectClass(Veiculo.class).cascadeOnDelete(false);;
		config.common().objectClass(Veiculo.class).cascadeOnUpdate(true);;
		config.common().objectClass(Veiculo.class).cascadeOnActivate(true);
		
		//conexao local
		manager = Db4oEmbedded.openFile(config, "banco.db4o");
		return manager;
	}
	
	public static void desconectar() {
		if(manager!=null) {
			manager.close();
			manager=null;
		}
	}
	
	
	public static int gerarIdAluguel() {
		if(manager.query(Aluguel.class).size()==0) 
			//classe nao registrada no banco
			return 1;
		
		Query q = manager.query();
		q.constrain(Registro.class);
		q.descend("id").orderDescending();
		List<Registro> resultados = q.execute();

		if(resultados.size()>0) {
			Registro registro = resultados.get(0);    //max
			return registro.getId() + 1;
		}
		else
			return 1; 	//nenhum objeto Registro encontrado
	}

}

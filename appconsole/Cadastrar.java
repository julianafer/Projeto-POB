package appconsole;

import com.db4o.ObjectContainer;

import modelo.Registro;
import modelo.Veiculo;
import modelo.TipoVeiculo;

public class Cadastrar {
	protected ObjectContainer manager;

	public Cadastrar() {
		try {
			manager = Util.conectarBanco();

			System.out.println("cadastrando...");
			
			TipoVeiculo moto = new TipoVeiculo("moto");
			manager.store(moto);
			manager.commit();
			
			TipoVeiculo carro = new TipoVeiculo("carro");
			manager.store(carro);
			manager.commit();
			
			TipoVeiculo caminhao = new TipoVeiculo("caminhao");
			manager.store(caminhao);
			manager.commit();
			
			Veiculo veiculo1 = new Veiculo("AAA1000", moto);
			manager.store(veiculo1);
			manager.commit();
			
			Veiculo veiculo2 = new Veiculo("AAB1001", carro);
			manager.store(veiculo2);
			manager.commit();
			
			Veiculo veiculo3 = new Veiculo("ABC1002", caminhao);
			manager.store(veiculo3);
			manager.commit();
			
			Veiculo veiculo4 = new Veiculo("BCD1012", moto);
			manager.store(veiculo4);
			manager.commit();
			
			
			Registro registro1 = new Registro("11/02/2023 11:55",  veiculo1 , "saida");
			manager.store(registro1);
			manager.commit();
			
			Registro registro2 = new Registro("11/02/2023 11:55",  veiculo3 , "entrada");
			manager.store(registro2);
			manager.commit();
			
/*
			Cliente cli1 = new Cliente("joao", "1111");
			manager.store(cli1);
			manager.commit();
			
			Cliente cli2 = new Cliente("maria", "2222");
			manager.store(cli2);
			manager.commit();
			
			Cliente cli3 = new Cliente("jose", "3333");
			manager.store(cli3);
			manager.commit();
*/	


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Util.desconectar();
		System.out.println("\nfim do programa !");
	}

	


	public static void main(String[] args) {
		new Cadastrar();
	}
}

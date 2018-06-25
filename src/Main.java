import java.util.List;

import br.com.ufabc.sistemabancariohibernate.dao.ContaCorrenteDao;
import br.com.ufabc.sistemabancariohibernate.model.ContaCorrente;

public class Main {
public static void main(String[] args){
		
	ContaCorrente cc = new ContaCorrente();
	ContaCorrente cc2;
	ContaCorrente c3 = new ContaCorrente();
	ContaCorrenteDao dao = new ContaCorrenteDao();

	cc.setAgencia("6548");
	cc.setNumero("1354-8");
	cc.setDescricao("BRADESCO123");
	cc.setAtiva(true);
	cc.setVariacao(0);

	// gravando registro
	dao.save(cc);

	//atualizando registro
	cc = dao.buscaPeloNumero("1354-8");
	cc.setAgencia("1234");
	cc.setNumero("1235-9");
	cc.setDescricao("ITAU UNIBANCO");
	dao.save(cc);

/*
	//removendo registro
	cc2 = dao.buscaPeloNumero("65465-8");
	dao.remove(cc2);
	System.out.println("removido!");

//		//alterando registro
	c3 = dao.buscaPeloNumero("1123-5");
	c3.setNumero("9875-5");
	c3.setAgencia("4321");
	c3.setDescricao("mexeu");
	dao.altera(cc);
	System.out.println("alterado");

	cc2 = dao.buscaPeloNumeroEAgencia("1123-5", "123");
	System.out.println("Id: "+ cc2.getId());
	System.out.println("Agencia " + cc2.getAgencia());
	System.out.println("Numero " + cc2.getNumero());
	System.out.println("Descricao " + cc2.getDescricao());

	cc2 = dao.buscaPorDescricao("ITAU");
	System.out.println("Id: "+ cc2.getId());
	System.out.println("Agencia " + cc2.getAgencia());
	System.out.println("Numero " + cc2.getNumero());
	System.out.println("Descricao " + cc2.getDescricao());
*/


/*		//exibindo todos os registros
		List<ContaCorrente> contas = dao.consultaBanco();

		for(int i = 0; i < contas.size(); i++){
			cc = contas.get(i);
			System.out.println("Id: "+ cc.getId());
			System.out.println("Agencia " + cc.getAgencia());
			System.out.println("Numero " + cc.getNumero());
			System.out.println("Descricao " + cc.getDescricao());
			System.out.println("------------------------");
		}*/
	}
}

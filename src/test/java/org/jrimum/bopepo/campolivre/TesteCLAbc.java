package org.jrimum.bopepo.campolivre;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.parametro.ParametroABC;
import org.jrimum.domkee.financeiro.banco.ParametrosBancariosMap;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.junit.Before;

/**
 * The Class TesteCLAbc.
 *
 * @author Eduardo José Santos Junior
 * @since 21/07/2017
 */
public class TesteCLAbc extends AbstractCampoLivreBaseTest<CLAbc> {

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {

		titulo.getContaBancaria().setBanco(BancosSuportados.BANCO_ABC.create());
		titulo.getContaBancaria().setNumeroDaConta(new NumeroDaConta(02214350));
		titulo.getContaBancaria().setAgencia(new Agencia(1));
		titulo.getContaBancaria().setCarteira(new Carteira(110));
		titulo.setParametrosBancarios(new ParametrosBancariosMap(ParametroABC.NUMERO_DA_OPERACAO, 5020286));
		titulo.setNossoNumero("0003171103");
		titulo.setDigitoDoNossoNumero("5");

		createCampoLivreToTest();

		setCampoLivreEsperadoComoString("0001110502028600031711035");

	}

}

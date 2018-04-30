/*
 * Copyright 2008 JRimum Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * Created at: 30/03/2008 - 18:12:17
 *
 * =============================================================================
 * ===
 *
 * Direitos autorais 2008 JRimum Project
 *
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode usar
 * esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que
 * haja exigência legal ou acordo por escrito, a distribuição de software sob
 * esta LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER
 * TIPO, sejam expressas ou tácitas. Veja a LICENÇA para a redação específica a
 * reger permissões e limitações sob esta LICENÇA.
 *
 * Criado em: 30/03/2008 - 18:12:17
 *
 */

package org.jrimum.bopepo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.jrimum.bopepo.campolivre.CampoLivre;
import org.jrimum.bopepo.campolivre.CampoLivreFactory;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeCobranca;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeMoeda;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.utilix.text.DateFormat;
import org.junit.Before;
import org.junit.Test;

/**
 * The Class TestLinhaDigitavelABC.
 *
 * @author Eduardo José Santos Junior
 * @since 21/07/2017
 */
public class TestLinhaDigitavelBanrisul {

	private CampoLivre clABC;

	private Titulo titulo;

	private CodigoDeBarras codigoDeBarras;

	private LinhaDigitavel linhaDigitavel;

	private Date VENCIMENTO = DateFormat.DDMMYYYY_B.parse("15/07/2018");

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {

		Sacado sacado = new Sacado("Sacado");
		Cedente cedente = new Cedente("Cedente");

		ContaBancaria contaBancaria = new ContaBancaria();
		contaBancaria.setBanco(BancosSuportados.BANCO_DO_ESTADO_DO_RIO_GRANDE_DO_SUL.create());

		Agencia agencia = new Agencia(243);
		contaBancaria.setAgencia(agencia);

		NumeroDaConta numeroDaConta = new NumeroDaConta(96622,"35");

		
		contaBancaria.setNumeroDaConta(numeroDaConta);
		contaBancaria.setCarteira(new Carteira(5,TipoDeCobranca.SEM_REGISTRO));

		titulo = new Titulo(contaBancaria, sacado, cedente);
		titulo.setNossoNumero("50051847");
		titulo.setDigitoDoNossoNumero("72");
		titulo.setTipoDeMoeda(TipoDeMoeda.REAL);
		titulo.setValorCobrado(new BigDecimal(3996.10));
		titulo.setValor(BigDecimal.valueOf(3996.10));
		titulo.setDataDoVencimento(VENCIMENTO);



		clABC = CampoLivreFactory.create(titulo);

		codigoDeBarras = new CodigoDeBarras(titulo, clABC);

		linhaDigitavel = new LinhaDigitavel(codigoDeBarras);

	}

	/**
	 * Test method for {@link org.jrimum.bopepo.LinhaDigitavel#toString()}.
	 */
	@Test
	public void testWrite() {
		System.out.println(linhaDigitavel.write());
		assertEquals("04192.10240 30096.622508 05184.740289 9 75860000399610", linhaDigitavel.write());

	}

}

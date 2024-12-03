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
 * Created at: 30/03/2008 - 18:09:27
 * 
 * ================================================================================
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
 * Criado em: 30/03/2008 - 18:09:27
 * 
 */


package org.jrimum.bopepo.campolivre;

import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.texgit.type.component.Fillers;
import org.jrimum.texgit.type.component.FixedField;

/**
 * <p>
 * O campo livre do BNK deve seguir esta forma:
 * </p>
 * 
 * <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="campolivre">
 * <thead bgcolor="#DEDEDE">
 * <tr>
 * <th>Posição</th>
 * <th>Tamanho</th>
 * <th>Picture</th>
 * <th>Conteúdo (terminologia padrão)</th>
 * <th>Conteúdo (terminologia do banco)</th>
 * </tr>
 * </thead> <tbody style="text-align:center">
 * <tr>
 * <td >01-12</td>
 * <td >7</td>
 * <td >&nbsp;9(12)</td>
 * <td style="text-align:left;padding-left:10">Conta do Cedente</td>
 * <td style="text-align:left;padding-left:10">Conta do Cedente</td>
 * </tr>
 * <tr>
 * <tr>
 * <td >13-22</td>
 * <td >11</td>
 * <td >&nbsp;9(10)</td>
 * <td style="text-align:left;padding-left:10">Número do Nosso Número (Sem o dígito)</td>
 * <td style="text-align:left;padding-left:10">Nosso Número (Sem o dígito)</td>
 * </tr>
 * <tr>
 * <td >23-24</td>
 * <td >2</td>
 * <td >9(2)</td>
 * <td style="text-align:left;padding-left:10">Código da Carteira</td>
 * <td style="text-align:left;padding-left:10">Código da Carteira</td>
 * </tr>
 * <td >25-25</td>
 * <td >1</td>
 * <td >9(1)</td>
 * <td style="text-align:left;padding-left:10">Constante "3"</td>
 * <td style="text-align:left;padding-left:10">Três Fixo</td>
 * </tr>
 * </table>
 * 
 * 
 * @see org.jrimum.bopepo.campolivre.AbstractCampoLivre
 * 
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">Rômulo Augusto</a>
 * @author <a href="http://www.nordestefomento.com.br">Nordeste Fomento
 *         Mercantil</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
class CLBNK extends AbstractCLBNK {
	
	private static final long serialVersionUID = 6042347626907056584L;

	/**
	 * Número de campos = 4.
	 */
	private static final Integer FIELDS_LENGTH = Integer.valueOf(3);

	/**
	 * Tamanho do campo Conta = 12. 
	 */
	private static final Integer CONTA_LENGTH = Integer.valueOf(12);
	
	/**
	 * Tamanho do campo Nosso Número = 10. 
	 */
	private static final Integer NOSSO_NUMERO_LENGTH = Integer.valueOf(8);
	
	/**
	 * Tamanho do campo Carteira = 2. 
	 */
	private static final Integer CARTEIRA_LENGTH = Integer.valueOf(5);
	
	/**
	 * Tamanho do campo Constante = 1. 
	 */
	private static final Integer CONSTANT_LENGTH = Integer.valueOf(1);
	
	/**
	 * Cria um campo livre instanciando o número de fields ({@code FIELDS_LENGTH}) deste campo.
	 * 
	 * @since 0.2
	 */
	protected CLBNK() {
		
		super(FIELDS_LENGTH);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @see org.jrimum.bopepo.campolivre.AbstractCampoLivre#checkValues(org.jrimum.domkee.financeiro.banco.febraban.Titulo)
	 */
	@Override
	protected void checkValues(Titulo titulo){
		
		checkCarteiraNotNull(titulo);
		checkCodigoDaCarteira(titulo);
		checkNossoNumero(titulo);
		checkNumeroDaContaNotNull(titulo);
		checkCodigoDoNumeroDaConta(titulo);
		
	}
	
	/**
	 *  {@inheritDoc}
	 *  
	 * @see org.jrimum.bopepo.campolivre.AbstractCampoLivre#addFields(org.jrimum.domkee.financeiro.banco.febraban.Titulo)
	 */
	@Override
	protected void addFields(Titulo titulo) {
		
		this.add(new FixedField<String>(titulo.getContaBancaria().getNumeroDaConta().getCodigoDaConta().toString(), CONTA_LENGTH, Fillers.ZERO_LEFT));
		this.add(new FixedField<String>(titulo.getNossoNumero().toString(), NOSSO_NUMERO_LENGTH, Fillers.ZERO_LEFT));
		this.add(new FixedField<String>("00000", CARTEIRA_LENGTH, Fillers.ZERO_LEFT));
//		this.add(new FixedField<Integer>(titulo.getContaBancaria().getCarteira().getCodigo(), CARTEIRA_LENGTH, Fillers.ZERO_LEFT));
//		this.add(new FixedField<Integer>(3, CONSTANT_LENGTH, Fillers.ZERO_LEFT));
	
	}
}

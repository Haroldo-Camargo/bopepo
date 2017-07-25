package org.jrimum.bopepo.campolivre;

import org.jrimum.bopepo.parametro.ParametroABC;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.texgit.type.component.Fillers;
import org.jrimum.texgit.type.component.FixedField;
import org.jrimum.utilix.text.Strings;

/**
 * The Class CLAbc.
 *
 * @author Eduardo José Santos Junior
 * @since 21/07/2017
 */
public class CLAbc extends AbstractCLAbc {

	private static final long serialVersionUID = 3788164020842904816L;

	private static final Integer FIELDS_LENGTH = Integer.valueOf(4);

	/**
	 * Instantiates a new CL abc.
	 */
	protected CLAbc() {
		super(FIELDS_LENGTH);
	}

	/**
	 * Instantiates a new CL abc.
	 *
	 * @param fieldsLength
	 *            the fields length
	 */
	protected CLAbc(Integer fieldsLength) {
		super(fieldsLength);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.jrimum.bopepo.campolivre.AbstractCampoLivre#checkValues(org.jrimum.
	 * domkee.financeiro.banco.febraban.Titulo)
	 */
	@Override
	protected void checkValues(Titulo titulo) {
		checkAgenciaNotNull(titulo);
		checkCodigoDaAgencia(titulo);
		checkCodigoDaAgenciaMenorOuIgualQue(titulo, 9999);
		checkCarteiraNotNull(titulo);
		checkCodigoDaCarteira(titulo);
		checkCodigoDaCarteiraMenorOuIgualQue(titulo, 999);
		checkNossoNumero(titulo);
		checkTamanhoDoNossoNumero(titulo, NN10);
		checkNumeroDaContaNotNull(titulo);
		checkCodigoDoNumeroDaConta(titulo);
		checkCodigoDoNumeroDaContaMenorOuIgualQue(titulo, 9999999);
		checkParametroBancario(titulo, ParametroABC.NUMERO_DA_OPERACAO);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.jrimum.bopepo.campolivre.AbstractCampoLivre#addFields(org.jrimum.
	 * domkee.financeiro.banco.febraban.Titulo)
	 */
	@Override
	protected void addFields(Titulo titulo) {

		Integer numeroDaOperacao = null;

		this.add(new FixedField<Integer>(titulo.getContaBancaria().getAgencia().getCodigo(), 4, Fillers.ZERO_LEFT));
		this.add(new FixedField<Integer>(titulo.getContaBancaria().getCarteira().getCodigo(), 3, Fillers.ZERO_LEFT));

		if (titulo.getParametrosBancarios().contemComNome(ParametroABC.NUMERO_DA_OPERACAO)) {
			numeroDaOperacao = titulo.getParametrosBancarios().getValor(ParametroABC.NUMERO_DA_OPERACAO);
		}

		this.add(new FixedField<Integer>(numeroDaOperacao, 7, Fillers.ZERO_LEFT));
		this.add(new FixedField<String>(
				Strings.eliminateSymbols(titulo.getNossoNumero().concat(titulo.getDigitoDoNossoNumero())), 11,
				Fillers.ZERO_LEFT));

	}

}

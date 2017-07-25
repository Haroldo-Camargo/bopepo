
package org.jrimum.bopepo.campolivre;

import org.jrimum.domkee.financeiro.banco.febraban.Titulo;

/**
 * The Class AbstractCLAbc.
 *
 * @author Eduardo José Santos Junior
 * @since 18/07/2017
 */
public abstract class AbstractCLAbc extends AbstractCampoLivre {

	private static final long serialVersionUID = -1541058951026875781L;

	/**
	 * Instantiates a new abstract CL abc.
	 *
	 * @param fieldsLength
	 *            the fields length
	 */
	protected AbstractCLAbc(Integer fieldsLength) {
		super(fieldsLength);
	}

	/**
	 * Creates the.
	 *
	 * @param titulo
	 *            the titulo
	 * @return the campo livre
	 */
	protected static CampoLivre create(Titulo titulo) {
		return new CLAbc().build(titulo);
	}

}

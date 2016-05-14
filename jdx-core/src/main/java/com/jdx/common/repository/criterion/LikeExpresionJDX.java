package com.jdx.common.repository.criterion;

import org.hibernate.criterion.LikeExpression;
import org.hibernate.criterion.MatchMode;

public class LikeExpresionJDX extends LikeExpression {

	private static final long serialVersionUID = 3461069252096689259L;
	
	public static final Character ESCAPE_CHARACTER = '@';

	public LikeExpresionJDX(
			String propertyName,
			String value,
			MatchMode matchMode) {
		
		super(propertyName, value, matchMode, ESCAPE_CHARACTER, true);
		
	}
}

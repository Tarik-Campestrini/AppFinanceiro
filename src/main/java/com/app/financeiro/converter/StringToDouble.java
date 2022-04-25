package com.app.financeiro.converter;

import org.springframework.core.convert.converter.Converter;

public class StringToDouble implements Converter<String, Double> {

	@Override
	public Double convert(String source) {
		source = source.trim();
		if(source.length()>0){
			source = source.replace(".", "").replace(",", ".");
			return Double.parseDouble(source);
		}
		return 0.;
	}

}

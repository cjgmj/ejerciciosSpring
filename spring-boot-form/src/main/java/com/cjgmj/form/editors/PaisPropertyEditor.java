package com.cjgmj.form.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cjgmj.form.services.PaisService;

@Component
public class PaisPropertyEditor extends PropertyEditorSupport {

	@Autowired
	private PaisService paisService;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			this.setValue(this.paisService.obtenerPorId(Integer.parseInt(text)));
		} catch (final NumberFormatException e) {
			this.setValue(null);
		}
	}

}

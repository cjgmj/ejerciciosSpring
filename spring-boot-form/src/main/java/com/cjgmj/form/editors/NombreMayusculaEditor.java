package com.cjgmj.form.editors;

import java.beans.PropertyEditorSupport;

public class NombreMayusculaEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		this.setValue(text.toUpperCase().trim());
	}

}

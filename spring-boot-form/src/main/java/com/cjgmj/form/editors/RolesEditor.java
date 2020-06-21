package com.cjgmj.form.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cjgmj.form.services.RoleService;

@Component
public class RolesEditor extends PropertyEditorSupport {

	@Autowired
	private RoleService roleService;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			this.setValue(this.roleService.obtenerPorId(Integer.parseInt(text)));
		} catch (final NumberFormatException e) {
			this.setValue(null);
		}
	}

}

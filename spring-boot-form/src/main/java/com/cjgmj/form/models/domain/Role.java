package com.cjgmj.form.models.domain;

public class Role {

	private Integer id;
	private String nombre;
	private String role;

	public Role() {
	}

	public Role(Integer id, String nombre, String role) {
		this.id = id;
		this.nombre = nombre;
		this.role = role;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Role)) {
			return false;
		}

		final Role role = (Role) obj;

		return this.id != null && this.id.equals(role.getId());
	}

}

package model.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private ArrayList<AttributeBean> specialAttributes;
	public CategoryBean() {
		super();
	}
	public CategoryBean(Long id, String name,  ArrayList<AttributeBean> specialAttributes) {
		super();
		this.id = id;
		this.name = name;
		this.specialAttributes = specialAttributes;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<AttributeBean> getSpecialAttributes() {
		return specialAttributes;
	}
	public void setSpecialAttributes(ArrayList<AttributeBean> specialAttributes) {
		this.specialAttributes = specialAttributes;
	}
	

}

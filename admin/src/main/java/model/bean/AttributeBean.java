package model.bean;

import java.io.Serializable;

public class AttributeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String value;
	private String unit;
	private String possibleValues;
	private Long categoryId;
	public AttributeBean() {
		super();
	}
	public AttributeBean(String name, String value, String unit, String possibleValues,Long category_id) {
		super();
		this.name = name;
		this.value = value;
		this.unit = unit;
		this.possibleValues = possibleValues;
		this.categoryId=category_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPossibleValues() {
		return possibleValues;
	}
	public void setPossibleValues(String possibleValues) {
		this.possibleValues = possibleValues;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long category_id) {
		this.categoryId = category_id;
	}
	
}

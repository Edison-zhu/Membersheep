package cn.membersheep.entity;

import java.io.Serializable;

/**
 * 地区信息实体类
 * @author tarena
 *
 */
public class District implements Serializable {

	private static final long serialVersionUID = -2777570570541589252L;
	private Integer id;
	private String parent;
	private String code;
	private String name;
	public District(){
		super();
	}
	public District(Integer id, String parent, String code, String name) {
		this.id = id;
		this.parent = parent;
		this.code = code;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "District{" +
				"id=" + id +
				", parent='" + parent + '\'' +
				", code='" + code + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}

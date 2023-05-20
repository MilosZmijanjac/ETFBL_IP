package model.bean;

import java.io.Serializable;
import java.time.Instant;

public class CommentBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long parent_id;
	private Instant timestamp;
	private String text;
	private Long user_id;
	private Long product_id;

	public CommentBean() {
		super();
	}

	public CommentBean(Long id, Long parent_id, Instant timestamp, String text, Long user_id, Long product_id) {
		super();
		this.id = id;
		this.parent_id = parent_id;
		this.timestamp = timestamp;
		this.text = text;
		this.user_id = user_id;
		this.product_id = product_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

}

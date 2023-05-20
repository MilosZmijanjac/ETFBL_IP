package model.bean;

import java.io.Serializable;
import java.time.Instant;

public class MessageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String text;
	private Boolean read;
	private Instant sentTime;
	private Long userId;
	private String username;
	private String userMail;

	public MessageBean() {
		super();
	}

	public MessageBean(long id, String text, boolean read, Instant sentTime, long userId, String username,
			String userMail) {
		super();
		this.id = id;
		this.text = text;
		this.read = read;
		this.sentTime = sentTime;
		this.userId = userId;
		this.username = username;
		this.userMail = userMail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public Instant getSentTime() {
		return sentTime;
	}

	public void setSentTime(Instant sentTime) {
		this.sentTime = sentTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

}

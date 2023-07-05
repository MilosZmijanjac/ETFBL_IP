package model.bean;

import java.io.Serializable;
import java.time.Instant;

import model.enumeration.LogType;

public class LogBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
    private LogType type;
    private String path;
    private String message;
    private Instant timestamp;
    
	public LogBean() {
		super();
	}

	public LogBean(Long id,  LogType type, String path, String message,
			Instant timestamp) {
		super();
		this.id = id;
		this.type = type;
		this.path = path;
		this.message = message;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public LogType getType() {
		return type;
	}

	public void setType(LogType type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
	
}

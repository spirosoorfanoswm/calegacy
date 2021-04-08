package eu.ark.creditark.services.creditarkservices.dto;

import java.io.Serializable;
import java.util.Date;

public class ExportFileEmptyResponse implements Serializable {
    private String user;
    private Date date;
    private String message;
    private boolean exists;
    private String fileName;
    private boolean pending;

    public ExportFileEmptyResponse(String user, Date date) {
        this.user = user;
        this.date = date;
    }

    public ExportFileEmptyResponse() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }
}

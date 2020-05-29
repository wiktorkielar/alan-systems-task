package com.wiktorkielar.alansystemstask.model;

public class AlertEntity {

    private int id;
    private boolean replicated;
    private String message;
    private int code;

    public AlertEntity() {
    }

    public AlertEntity(int id, boolean replicated, String message, int code) {
        this.id = id;
        this.replicated = replicated;
        this.message = message;
        this.code = code;
    }

    public AlertEntity(AlertEntity alertEntity) {
        this.id = alertEntity.getId();
        this.replicated = alertEntity.isReplicated();
        this.message = alertEntity.getMessage();
        this.code = alertEntity.getCode();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isReplicated() {
        return replicated;
    }

    public void setReplicated(boolean replicated) {
        this.replicated = replicated;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "AlertEntity{" +
                "id=" + id +
                ", replicated=" + replicated +
                ", message='" + message + '\'' +
                ", code=" + code +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlertEntity that = (AlertEntity) o;

        if (id != that.id) return false;
        if (replicated != that.replicated) return false;
        if (code != that.code) return false;
        return message != null ? message.equals(that.message) : that.message == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (replicated ? 1 : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + code;
        return result;
    }
}

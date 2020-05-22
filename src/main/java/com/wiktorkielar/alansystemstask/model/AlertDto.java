package com.wiktorkielar.alansystemstask.model;

public class AlertDto {

    private int id;
    private boolean replicated;
    private String message;
    private int code;

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
        return "AlertDto{" +
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

        AlertDto alertDto = (AlertDto) o;

        if (id != alertDto.id) return false;
        if (replicated != alertDto.replicated) return false;
        if (code != alertDto.code) return false;
        return message != null ? message.equals(alertDto.message) : alertDto.message == null;
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

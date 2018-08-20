package com.capgemini.starterkit.to;

public class AbstractDto {

    private Long id;

    private int version;

    private String persistingHistory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getPersistingHistory() {
        return persistingHistory;
    }

    public void setPersistingHistory(String persistingHistory) {
        this.persistingHistory = persistingHistory;
    }
}

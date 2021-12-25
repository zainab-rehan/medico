package com.example.medico;

import java.util.ArrayList;

public class History {
    private String id;
    private String patId;
    private String history;

    public History(){}
    public History(String id,String patId, String history) {
        this.id = id;
        this.patId = patId;
        this.history = history;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }



}

package com.example.mobile_app;

public class Volumen {
    private String issue_id;
    private String volume;
    private String year; // Añadimos el campo year (si existe en el JSON)

    public String getIssueId() { return issue_id; }
    public void setIssueId(String issue_id) { this.issue_id = issue_id; }
    public String getVolume() { return volume; }
    public void setVolume(String volume) { this.volume = volume; }
    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
}
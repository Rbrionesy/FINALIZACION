package com.example.mobile_app;

import java.util.List;

public class Paper {
    private String publication_id;
    private String title;
    private String doi;
    private String abstractText; // Renombrado para evitar conflicto con la palabra reservada "abstract"
    private List<Galley> galeys;
    private List<Keyword> keywords;
    private List<Author> authors;

    // Getters y setters
    public String getPublicationId() { return publication_id; }
    public void setPublicationId(String publication_id) { this.publication_id = publication_id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDoi() { return doi; }
    public void setDoi(String doi) { this.doi = doi; }
    public String getAbstractText() { return abstractText; }
    public void setAbstractText(String abstractText) { this.abstractText = abstractText; }
    public List<Galley> getGaleys() { return galeys; }
    public void setGaleys(List<Galley> galeys) { this.galeys = galeys; }
    public List<Keyword> getKeywords() { return keywords; }
    public void setKeywords(List<Keyword> keywords) { this.keywords = keywords; }
    public List<Author> getAuthors() { return authors; }
    public void setAuthors(List<Author> authors) { this.authors = authors; }

    // Clase interna para Galley
    public static class Galley {
        private String galley_id;
        private String label;
        private String file_id;
        private String UrlViewGalley;

        public String getGalleyId() { return galley_id; }
        public void setGalleyId(String galley_id) { this.galley_id = galley_id; }
        public String getLabel() { return label; }
        public void setLabel(String label) { this.label = label; }
        public String getFileId() { return file_id; }
        public void setFileId(String file_id) { this.file_id = file_id; }
        public String getUrlViewGalley() { return UrlViewGalley; }
        public void setUrlViewGalley(String urlViewGalley) { this.UrlViewGalley = urlViewGalley; }
    }

    // Clase interna para Keyword
    public static class Keyword {
        private String keyword;

        public String getKeyword() { return keyword; }
        public void setKeyword(String keyword) { this.keyword = keyword; }
    }

    // Clase interna para Author
    public static class Author {
        private String nombres;
        private String filiacion;
        private String email;

        public String getNombres() { return nombres; }
        public void setNombres(String nombres) { this.nombres = nombres; }
        public String getFiliacion() { return filiacion; }
        public void setFiliacion(String filiacion) { this.filiacion = filiacion; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
}
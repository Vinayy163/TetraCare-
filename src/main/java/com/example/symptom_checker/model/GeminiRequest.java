package com.example.symptom_checker.model;


import java.util.List;

public class GeminiRequest {
    private List<Content> contents;

    public static class Content {
        private List<Part> parts;

        public static class Part {
            private String text;

            // Getters and Setters
            public String getText() { return text; }
            public void setText(String text) { this.text = text; }
        }

        // Getters and Setters
        public List<Part> getParts() { return parts; }
        public void setParts(List<Part> parts) { this.parts = parts; }
    }

    // Getters and Setters
    public List<Content> getContents() { return contents; }
    public void setContents(List<Content> contents) { this.contents = contents; }
    public class Part {

        public void setText(String string) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setText'");
        }
    }
}


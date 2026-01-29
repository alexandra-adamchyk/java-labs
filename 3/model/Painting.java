package model;

public class Painting extends Exhibit {
    private String technique;
    private String style;

    public Painting(String id, String name, String author, java.time.LocalDate acquisitionDate, 
                   String description, String technique, String style) {
        super(id, name, author, acquisitionDate, description);
        this.technique = technique;
        this.style = style;
    }

    public String getTechnique() {
        return technique;
    }

    public String getStyle() {
        return style;
    }

    public void setTechnique(String technique) {
        this.technique = technique;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public String getType() {
        return "Painting";
    }

    @Override
    public String toString() {
        return String.format("%s, Technique: %s, Style: %s", 
                super.toString(), technique, style);
    }
}

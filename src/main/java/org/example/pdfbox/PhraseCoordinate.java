package org.example.pdfbox;

public class PhraseCoordinate {
    private String phrase;

    private Float xCoordinate;
    private Float yCoordinate;

    public PhraseCoordinate(String phraze, Float xCoordinate, Float yCoordinate) {
        this.phrase = phraze;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public PhraseCoordinate() {
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public Float getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Float xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Float getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Float yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    @Override
    public String toString() {
        return "PhraseCoordinate{" +
                "phrase='" + phrase + '\'' +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                '}';
    }
}

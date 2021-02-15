package me.smartquiz.api.model;

public class Sentence {

    private double probablity;
    private String sentence;

    public Sentence(double probablity, String sentence) {
        this.probablity = probablity;
        this.sentence = sentence;
    }

    public double getProbablity() {
        return probablity;
    }

    public void setProbablity(double probablity) {
        this.probablity = probablity;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "probablity=" + probablity +
                ", sentence='" + sentence + '\'' +
                '}';
    }
}

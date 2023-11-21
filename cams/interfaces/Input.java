package cams.interfaces;

public interface Input extends IntInput, StringInput, DateInput {
    int confirmOrDiscard(String title);
}

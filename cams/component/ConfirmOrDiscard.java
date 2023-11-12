package cams.component;

public class ConfirmOrDiscard extends TwoOptionsInput {
    public ConfirmOrDiscard(String title) {
        super("Confirm " + title + "?", "Confirm", "Discard and go back");
    }
}

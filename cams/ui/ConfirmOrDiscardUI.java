package cams.ui;

public class ConfirmOrDiscardUI extends ChooseBetweenTwoOptionsUI {
    public ConfirmOrDiscardUI(String title) {
        super("Confirm " + title + "?", "Confirm", "Discard and go back");
    }
}

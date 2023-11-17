package cams.ui;

public class ConfirmOrDiscardUI extends ChooseBetweenTwoOptionsUI {

    public ConfirmOrDiscardUI() {
        super("Confirm", "Discard and go back");
    }

    @Override
    public int getValidInt(String title) {
        return super.getValidInt(title);
    }
}

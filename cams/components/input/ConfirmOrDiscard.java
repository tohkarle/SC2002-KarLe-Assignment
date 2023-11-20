package cams.components.input;

public class ConfirmOrDiscard extends ChooseBetweenTwoOptions {

    public ConfirmOrDiscard() {
        super("Confirm", "Discard and go back");
    }

    @Override
    public int getValidInt(String title) {
        return super.getValidInt(title);
    }
}

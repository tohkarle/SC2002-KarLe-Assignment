package cams.components.option;

public abstract class TableOptions extends Options {

    @Override
    public void printOptions() {
        if (super.getOptions() == null || super.getOptions().isEmpty()) {
            System.out.println("No super.getOptions() available.");
            return;
        }
    
        int maxOptionLength = super.getOptions().stream().mapToInt(String::length).max().orElse(0);
    
        System.out.println("+" + "-".repeat(4) + "+" + "-".repeat(maxOptionLength + 2) + "+");

        for (int i = 0; i < super.getOptionsSize(); i++) {
            String option = super.getOption(i);
            System.out.printf("| %-2d | %-" + maxOptionLength + "s |\n", (i + 1), option);
            System.out.println("+" + "-".repeat(4) + "+" + "-".repeat(maxOptionLength + 2) + "+");
        }
    }
    
    public abstract void display(String title);
    public abstract int selection();
}

public class PrintMethodPicker {

    PrintingMethod printingMethod;

    public void setPrintingMethod(PrintingMethod printingMethod) {
        this.printingMethod = printingMethod;
    }

    public void print(String message) {
        printingMethod.print(message);
    }
}

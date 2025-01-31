package model;

public class Computer extends Product {

    private Processor processorModel;
    private Ram ramType;

    public Computer(String id, String name, double price, int availableCount, Processor processorModel, Ram ramType) {
        super(id, name, price, availableCount);
        this.processorModel = processorModel;
        this.ramType = ramType;
    }

    @Override
    public String toString() {
        return super.toString() + "Model procesora: " + processorModel.getDescription() + " Ilość RAM: " + ramType.getDescription();
    }

    public Processor getProcessorModel() {
        return processorModel;
    }

    public void setProcessorModel(Processor processorModel) {
        this.processorModel = processorModel;
    }

    public Ram getRamType() {
        return ramType;
    }

    public void setRamType(Ram ramType) {
        this.ramType = ramType;
    }
}

package model;

public enum Processor {
    AMD_RYZEN5600("AMD Ryzen 5 5600"), INTEL_I5("Intel Core i5-12400F"), INTEL_I7("Intel Core i7-14700K"),
    INTEL_I9("Intel Core i9-14900K"), AMD_RYZEN7900X("AMD Ryzen 9 7900X");
    private String description;

    Processor(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

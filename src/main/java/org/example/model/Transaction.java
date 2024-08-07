package org.example.model;

public class Transaction {
    private Type type;
    private String name;
    private int sum;

    public Transaction(Type type, String name, int sum) {
        this.type = type;
        this.name = name;
        this.sum = sum;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", sum=" + sum +
                '}';
    }
}

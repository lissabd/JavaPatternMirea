package org.example;


// Класс, представляющий объект "Автомобиль"
class Car {
    private String model;
    private String color;
    private String engineType;


    public Car(String model, String color, String engineType) {
        this.model = model;
        this.color = color;
        this.engineType = engineType;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getEngineType() {
        return engineType;
    }
}

// Интерфейс строителя
interface CarBuilder {
    CarBuilder setModel(String model);
    CarBuilder setColor(String color);
    CarBuilder setEngineType(String engineType);
    Car build();
}

// Конкретная реализация строителя
class CarBuilderImpl implements CarBuilder {
    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getEngineType() {
        return engineType;
    }

    private String model;
    private String color;
    private String engineType;

    @Override
    public CarBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    @Override
    public CarBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    @Override
    public CarBuilder setEngineType(String engineType) {
        this.engineType = engineType;
        return this;
    }

    @Override
    public Car build() {
        return new Car(model, color, engineType);
    }
}

public class BuilderPatternExample {
    public static void main(String[] args) {
        // Создание объекта строителя
        CarBuilder builder = new CarBuilderImpl();

        // Построение объекта "Автомобиль" пошагово
        Car car = builder.setModel("Toyota")
                .setColor("Red")
                .setEngineType("Gasoline")
                .build();


        System.out.println("Model: " + car.getModel());
        System.out.println("Color: " + car.getColor());
        System.out.println("Engine Type: " + car.getEngineType());
    }
}

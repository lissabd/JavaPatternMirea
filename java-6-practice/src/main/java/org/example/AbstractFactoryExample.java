package org.example;

// Интерфейс продукта А
interface ProductA {
    void operationA();
}

// Интерфейс продукта B
interface ProductB {
    void operationB();
}

// Конкретные реализации продукта А
class ConcreteProductA1 implements ProductA {
    @Override
    public void operationA() {
        System.out.println("ConcreteProductA1 operation");
    }
}

class ConcreteProductA2 implements ProductA {
    @Override
    public void operationA() {
        System.out.println("ConcreteProductA2 operation");
    }
}

// Конкретные реализации продукта B
class ConcreteProductB1 implements ProductB {
    @Override
    public void operationB() {
        System.out.println("ConcreteProductB1 operation");
    }
}

class ConcreteProductB2 implements ProductB {
    @Override
    public void operationB() {
        System.out.println("ConcreteProductB2 operation");
    }
}

// Интерфейс абстрактной фабрики
interface AbstractFactory {
    ProductA createProductA();
    ProductB createProductB();
}

// Конкретные реализации абстрактной фабрики
class ConcreteFactory1 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ConcreteProductA1();
    }

    @Override
    public ProductB createProductB() {
        return new ConcreteProductB1();
    }
}

class ConcreteFactory2 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ConcreteProductA2();
    }

    @Override
    public ProductB createProductB() {
        return new ConcreteProductB2();
    }
}

// Класс для демонстрации работы
public class AbstractFactoryExample {
    public static void main(String[] args) {
        AbstractFactory factory1 = new ConcreteFactory1();
        ProductA productA1 = factory1.createProductA();
        ProductB productB1 = factory1.createProductB();
        productA1.operationA(); // Вывод: ConcreteProductA1 operation
        productB1.operationB(); // Вывод: ConcreteProductB1 operation

        AbstractFactory factory2 = new ConcreteFactory2();
        ProductA productA2 = factory2.createProductA();
        ProductB productB2 = factory2.createProductB();
        productA2.operationA(); // Вывод: ConcreteProductA2 operation
        productB2.operationB(); // Вывод: ConcreteProductB2 operation
    }
}

package org.example;


// Интерфейс продукта
interface Product {
    void operation();
}

// Конкретная реализация продукта
class ConcreteProduct implements Product {
    @Override
    public void operation() {
        System.out.println("ConcreteProduct operation");
    }
}

// Интерфейс фабрики
interface Creator {
    Product factoryMethod();
}

// Конкретная реализация фабрики
class ConcreteCreator implements Creator {
    @Override
    public Product factoryMethod() {
        return new ConcreteProduct();
    }
}


public class FactoryMethodExample {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product = creator.factoryMethod();
        product.operation();
    }
}



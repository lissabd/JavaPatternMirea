package org.example;

// прдставляет собой обработчик запроса имеющий ссылку на следующий обработчик в цепочк
abstract class Handler {
    protected Handler successor;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public abstract void handleRequest(int request);
    // обрабатывает запрос
}
//  реализуют метод и опрееляют условия при котором либо обработают либо передают следующему
class ConcreteHandler1 extends Handler {
    public void handleRequest(int request) {
        if (request >= 0 && request < 10) {
            System.out.println("Обработан ConcreteHandler1");
        } else if (successor != null) {
            successor.handleRequest(request);
        }
    }
}

class ConcreteHandler2 extends Handler {
    public void handleRequest(int request) {
        if (request >= 10 && request < 20) {
            System.out.println("Обработан ConcreteHandler2");
        } else if (successor != null) {
            successor.handleRequest(request);
        }
    }
}
// Интерфейс команды
interface Command {
    void execute();
}

// Конкретная команда: Включить свет
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.turnOn();
    }
}

// Конкретная команда: Выключить свет
class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.turnOff();
    }
}

// Получатель: Свет
class Light {
    public void turnOn() {
        System.out.println("Light is on");
    }

    public void turnOff() {
        System.out.println("Light is off");
    }
}

// Инициатор
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}


public class Client {
    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();

        handler1.setSuccessor(handler2);

        handler1.handleRequest(5); // Обработка запроса цепочкой
        handler1.handleRequest(15);
        handler1.handleRequest(25);

        Light light = new Light();
        Command turnOn = new LightOnCommand(light);
        Command turnOff = new LightOffCommand(light);

        RemoteControl remote = new RemoteControl();
        remote.setCommand(turnOn);
        remote.pressButton(); // Включить свет

        remote.setCommand(turnOff);
        remote.pressButton(); // Выключить свет
    }
}
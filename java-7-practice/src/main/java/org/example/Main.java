package org.example;


class OldService {
    public void oldRequest() {
        System.out.println("Old service request called");
    }
}


interface NewService {
    void newRequest();
}

// Адаптер, преобразующий вызов метода oldRequest() в вызов метода newRequest()
class Adapter implements NewService {
    private final OldService oldService;

    public Adapter(OldService oldService) {
        this.oldService = oldService;
    }

    @Override
    public void newRequest() {
        oldService.oldRequest();
    }
}

// Класс, использующий новый сервис
class Client {
    private final NewService newService;

    public Client(NewService newService) {
        this.newService = newService;
    }

    public void performRequest() {
        newService.newRequest();
    }
}
// Интерфейс для реализации механизма рисования
interface DrawingAPI {
    void draw();
}

// Конкретная реализация механизма рисования - рисование на экране
class ScreenDrawing implements DrawingAPI {
    @Override
    public void draw() {
        System.out.println("Drawing on Screen");
    }
}

// Конкретная реализация механизма рисования - рисование в файле
class FileDrawing implements DrawingAPI {
    @Override
    public void draw() {
        System.out.println("Drawing in File");
    }
}

// Абстракция фигуры
abstract class Shape {
    protected DrawingAPI drawingAPI;

    protected Shape(DrawingAPI drawingAPI) {
        this.drawingAPI = drawingAPI;
    }

    public abstract void draw();
}

// Конкретная реализация абстракции - круг
class Circle extends Shape {
    public Circle(DrawingAPI drawingAPI) {
        super(drawingAPI);
    }

    @Override
    public void draw() {
        drawingAPI.draw();
        System.out.println("Drawing Circle");
    }
}

// Конкретная реализация абстракции - квадрат
class Square extends Shape {
    public Square(DrawingAPI drawingAPI) {
        super(drawingAPI);
    }

    @Override
    public void draw() {
        drawingAPI.draw();
        System.out.println("Drawing Square");
    }
}





public class Main {
    public static void main(String[] args) {
        OldService oldService = new OldService();
        Adapter adapter = new Adapter(oldService);
        Client client = new Client(adapter);

        client.performRequest();

        DrawingAPI screenDrawing = new ScreenDrawing();
        Shape circle = new Circle(screenDrawing);
        circle.draw();

        DrawingAPI fileDrawing = new FileDrawing();
        Shape square = new Square(fileDrawing);
        square.draw();

    }
}

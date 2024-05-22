import java.io.File;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;
class FileHasher {
    private final String inputFile;
    private final String outputFile;

    public FileHasher(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public void hashAndWriteToFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String data = reader.readLine();
            reader.close();

            if (data == null) {
                data = "null";
            }

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(bytesToHex(hashedBytes));
            writer.close();
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
@Component
// чтобы спринг мог управлять его жизненным циклом
public class Main {
    private String inputFile;
    private String outputFile;
    private FileHasher fileHasher;

    @PostConstruct // чтобы выполнить инициализацию
    public void init() {
        fileHasher = new FileHasher(inputFile, outputFile);
        fileHasher.hashAndWriteToFile();
    } // для автоматизации
// для очистки ресурсов
    @PreDestroy
    public void cleanup() {
        File file = new File(inputFile);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void main(String[] args) {
        // Проверка наличия аргументов
        if (args.length != 2) {
            System.out.println("Usage: java Main <inputFileName> <outputFileName>");
            return;
        }

        // Замена имен файлов на переданные аргументы
        Main main = new Main();
        main.inputFile = args[0];
        main.outputFile = args[1];

        // Вручную вызываем метод init(), так как Spring не управляет главным методом main
        main.init();
    }
}
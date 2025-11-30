import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    private static final int MAX_LINE_SIZE = 1024;

    public static void main(String[] args) {
    /*    System.out.println("Введите текст и нажмите <Enter>: " );
        String text = new Scanner(System.in).nextLine();
        System.out.println("Длинна текста: " + text.length());
*/
        int n = 0;

        while (true) {

            System.out.println("Укажите путь к файлу или введите q для выхода");
            System.out.print("Путь к файлу: ");

            String path = new Scanner(System.in).nextLine();
            if ("q".equals(path)) {
                return;
            }
            File file = new File(path);

            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();

            if (isDirectory == true) {
                System.out.println("Это путь к папке");
                continue;
            } else if (fileExists == true) {
                n++;
                System.out.println("Путь указан верно");
                System.out.println("Это файл номер" + n);

                try {
                    FileReader fileReader = new FileReader(path);
                    BufferedReader reader =
                            new BufferedReader(fileReader);
                    int linesCount = 0;
                    int maxLength = 0;
                    int minLength = -1;
                    String line;
                    while ((line = reader.readLine()) != null) {
                        int length = line.length();
                        linesCount++;
                        if (length > MAX_LINE_SIZE) {
                            throw new InvalidLineException(linesCount, length, MAX_LINE_SIZE);
                        }
                        if (length > maxLength) {
                            maxLength = length;
                        }
                        if (length < minLength || minLength == -1) {
                            minLength = length;
                        }
                    }

                    System.out.println("Общее число строк в файле: " + linesCount);
                    System.out.println("Длина самой короткой строки: " + minLength);
                    System.out.println("Длина самой длинной строки: " + maxLength);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Файла не существует");
                continue;
            }
        }
    }
}

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    /*    System.out.println("Введите текст и нажмите <Enter>: " );
        String text = new Scanner(System.in).nextLine();
        System.out.println("Длинна текста: " + text.length());
*/
        int n = 0;

        while (true) {

            System.out.println("Укажите путь к файлу");
            String path = new Scanner(System.in).nextLine();
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
            } else {
                System.out.println("Файла не существует");
                continue;
            }
        }
    }
}

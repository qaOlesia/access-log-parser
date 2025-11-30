import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    private static final int MAX_LINE_SIZE = 1024;
    private static final String YANDEX_BOT = "YandexBot";
    private static final String GOOGLE_BOT = "Googlebot";

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
                    int yandexBots = 0;
                    int googleBots = 0;
                    String line;
                    while ((line = reader.readLine()) != null) {
                        int length = line.length();
                        linesCount++;
                        if (length > MAX_LINE_SIZE) {
                            throw new InvalidLineException(linesCount, length, MAX_LINE_SIZE);
                        }
                        String userAgent = extractUserAgent(line);
                        String agentName = extractUserAgentName(userAgent);

                        if(YANDEX_BOT.equals(agentName))
                        {
                            yandexBots++;
                        }
                        else if(GOOGLE_BOT.equals(agentName))
                        {
                            googleBots++;
                        }
                    }

                    System.out.println("Общее число строк в файле: " + linesCount);
                    System.out.println("Количество запросов от YandexBot: " + yandexBots);
                    System.out.println("Количество запросов от GoogleBot: " + googleBots);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Файла не существует");
                continue;
            }
        }
    }

    public static String extractUserAgent(String line) {
        int lastQuoteStart = line.lastIndexOf('"');
        if(lastQuoteStart >= 0)
        {
            int secondLastQuoteStart = line.lastIndexOf('"', lastQuoteStart - 1);
            if(secondLastQuoteStart >= 0)
            {
                return line.substring(secondLastQuoteStart + 1, lastQuoteStart);
            }
        }
        return "";
    }

    public static String extractUserAgentName(String userAgent) {

        String[] parts = userAgent.split(";");
        String targetPart;

        if (parts.length >= 2) {
            targetPart = parts[parts.length-2].trim();
        } else {
            return "";
        }

        int slashIndex = targetPart.indexOf('/');
        if (slashIndex != -1) {
            return targetPart.substring(0, slashIndex);
        } else {
            return targetPart;
        }
    }

}

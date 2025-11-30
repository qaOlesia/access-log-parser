public class InvalidLineException extends RuntimeException {
    public InvalidLineException(int lineNumber, int length, int max) {
        this("Файл невалиден. Строка #" + lineNumber + " слшиком большая и содержит " + length + " символов. Ограничение " + max + " символов");
    }

    public InvalidLineException(String message) {
        super(message);
    }
}

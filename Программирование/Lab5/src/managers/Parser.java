package managers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для парсинга csv файлов
 */
public final class Parser {

    /**
     * @param csvFile
     * @return список списков элементов csv файла
     */
    public static List<List<String>> readCSV(File csvFile) {
        List<List<String>> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                List<String> row = parseCsvLine(line);
                result.add(row);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return result;
    }

    /**
     * @param line
     * @return строку csv файла
     */
    private static List<String> parseCsvLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    currentField.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                fields.add(currentField.toString());
                currentField.setLength(0);
            } else {
                currentField.append(c);
            }
        }

        fields.add(currentField.toString());
        return fields;
    }

    /**
     * Запись в csv файл
     * @param data
     * @param filePath
     * @param outputManager
     */
    public static void writeCsv(List<List<String>> data, String filePath, OutputManager outputManager) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (List<String> row : data) {
                String line = String.join(",", row);
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            outputManager.printError("Не удалось конвертировать java объект в csv");
        }
    }
}


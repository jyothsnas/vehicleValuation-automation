package FileUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputAndOutputFileExtractor {
    private static Map<String, String> carInputData = new ConcurrentHashMap<>();
    private static Map<String, String> carOutPutData = new ConcurrentHashMap<>();
    public static List<String> extractRegistrationNumbers(String filePath) {
        List<String> registrationNumbers = new ArrayList<>();
        String line;
        String regex = "[A-Z]{2}\\d{2}\\s?[A-Z]{3}";
        Pattern pattern = Pattern.compile(regex);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
           while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String registrationNumber = matcher.group();
                    registrationNumbers.add(registrationNumber);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registrationNumbers;
    }
    public static Map<String, String> readCarOutputFileAndPerformEvaluation(String filePath, String registrationNo) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (int i=0; i< lines.size(); i++) {
                if(lines.get(i).contains(registrationNo)) {
                    String[] value = lines.get(i ).split(",");
                    String[] key = lines.get(0).split(",");
                    for (int j = 0; j < value.length; j++) {
                        carOutPutData.put(key[j], value[j]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carOutPutData;
    }
    public static void main(String[] args) {
        String inputFilePath = "car_input.txt";
        String line;
        String regex = "[A-Z]{2}\\d{2}\\s?[A-Z]{3}";
        Pattern pattern = Pattern.compile(regex);
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String registrationNumber = matcher.group();
                    carInputData.put("RegistrationNo", registrationNumber);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
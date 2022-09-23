import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Stream;

public class ParseJson {

    private List<Company> company = new ArrayList<>();
    private Map<Securities, Company> companyMap = new LinkedHashMap<>();
    private Map<Securities, String> resultSecurities = new LinkedHashMap<>();
    private Map<Integer, List<String>> resultIdCompaniesAndCodeCurrency = new LinkedHashMap<>();
    private List<String> promotions = new ArrayList<>();


    public void parse(String stringPath) {
        try (FileReader fileWriter = new FileReader(Path.of(stringPath).toAbsolutePath().toString())) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
            Gson gson = gsonBuilder.setPrettyPrinting().create();
            Companies companies = gson.fromJson(fileWriter, Companies.class);
            company = companies.getCompanies();
            for (int i = 0; i < company.size(); i++) {
                for (int j = 0; j < companies.getCompanies().get(i).getSecurities().size(); j++) {
                    companyMap.put(companies.getCompanies().get(i).getSecurities().get(j), companies.getCompanies().get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getCompanies() {
        company.stream().flatMap(x -> Stream.of("название компании: "+ x.getName() + " - "+ " дата основания: " + getConvertDate(x.getFounded())))
                .forEach(System.out::println);
    }

    public void getSecurities() {
        System.out.println("___________________");
        LocalDate today = LocalDate.now();
        for (Map.Entry<Securities, Company> pair : companyMap.entrySet()) {
            if (pair.getKey().getDate().isBefore(today)) {
                resultSecurities.put(pair.getKey(), pair.getValue().getName());
            }
        }
        resultSecurities.forEach((x, y) -> System.out.println("код: " + x.getCode() + " дата истечения: " + getConvertDate(x.getDate()) + " название организации-владельца: " + y));
        System.out.println("Суммарное число просроченных ценных бумаг: " + resultSecurities.size());
    }

    public void getCompaniesNameAndDate() {
        System.out.println("___________________");
        System.out.println("Введите дату в формате «ДД.ММ.ГГГГ», «ДД.ММ.ГГ», «ДД/ММ/ГГГГ», «ДД/ММ/ГГ»:");
        Scanner input = new Scanner(System.in);
        String result = input.nextLine().replaceAll("/", ".");
        LocalDate resultDate;
        DateTimeFormatter dateTimeFormatter;
        try {
            if (result.length() < 10) {
                dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
                resultDate = LocalDate.from(dateTimeFormatter.parse(result)).minusYears(100);
            } else {
                dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                resultDate = LocalDate.from(dateTimeFormatter.parse(result));
            }
            for (Company value : company) {
                if (value.getFounded().isAfter(resultDate)) {
                    System.out.println(value.getName() + " - " + getConvertDate(value.getFounded()));
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Вы ввели дату не соответствующую формату");
        }
    }

    public void getIdCompaniesAndCodeCurrency() {
        System.out.println("___________________");
        System.out.println("Введите валюту EU, USD, RUB:");
        Scanner input = new Scanner(System.in);
        String currency = input.nextLine();
        for (Company value : company) {
            for (int j = 0; j < value.getSecurities().size(); j++) {
                for (int k = 0; k < value.getSecurities().get(j).getCurrency().size(); k++) {
                    if (value.getSecurities().get(j).getCurrency().get(k).equals(currency)) {
                        promotions.add(value.getSecurities().get(j).getCode());
                    }
                }
            }
            if (promotions.size() > 0) {
                resultIdCompaniesAndCodeCurrency.put(value.getId(), promotions);
                resultIdCompaniesAndCodeCurrency.forEach((x, y) -> System.out.println("id: " + x + " - "+ " коды ценных бумаг: " + y));
                promotions.clear();
                resultIdCompaniesAndCodeCurrency.clear();
            }
        }
    }

    private String getConvertDate(LocalDate date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dtf.format(date);
    }
}

public class Main {
    public static void main(String[] args) {
        ParseJson parseJson = new ParseJson();
        parseJson.parse("src/main/resources/Shares.json");
        parseJson.getCompanies();
        parseJson.getSecurities();
        parseJson.getCompaniesNameAndDate();
        parseJson.getIdCompaniesAndCodeCurrency();
    }
}

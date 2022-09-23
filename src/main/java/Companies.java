import java.util.List;

public class Companies {
   private List<Company> companies;

    public Companies() {
    }

    public Companies(List<Company> companies) {
        this.companies = companies;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "companies: " + companies;
    }
}

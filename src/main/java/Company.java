import java.time.LocalDate;
import java.util.List;

public class Company {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String inn;
    private LocalDate founded;
    private List <Securities> securities;

    public Company() {
    }

    public Company(int id, String name, String address, String phoneNumber, String inn, LocalDate founded, List<Securities> securities) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.inn = inn;
        this.founded = founded;
        this.securities = securities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public LocalDate getFounded() {
        return founded;
    }

    public void setFounded(LocalDate founded) {
        this.founded = founded;
    }

    public List<Securities> getSecurities() {
        return securities;
    }

    public void setSecurities(List <Securities> securities) {
        this.securities = securities;
    }

    @Override
    public String toString() {
        return "id: " + id + ", name: " + name + ", address: " + address + ", phoneNumber: " + phoneNumber + ", inn: " + inn +
                ", founded: " + founded + ", securities: " + securities;
    }
}

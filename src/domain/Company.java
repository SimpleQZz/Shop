package domain;

public class Company {
    private int companyId;
    private String companyName;
    private int credit;

    public Company() {
    }

    public Company(int companyId, String companyName, int credit) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.credit = credit;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", credit=" + credit +
                '}';
    }
}

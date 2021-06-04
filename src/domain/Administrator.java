package domain;

public class Administrator {
    private Integer aid;
    private String adname;
    private String password;

    public Administrator() {
    }

    public Administrator(Integer aid, String adname, String password) {
        this.aid = aid;
        this.adname = adname;
        this.password = password;
    }

    public String getAdname() {
        return adname;
    }

    public void setAdname(String adname) {
        this.adname = adname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

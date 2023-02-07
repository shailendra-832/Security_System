package com.example.security_system;

public class newfile {
    Integer Tserial,Tage;
    String Tidtype,TidNumber,TName,Tentrydate,Texitdate;

    public newfile(Integer tserial, String tidNumber, String tidtype, String TName,Integer tage, String tentrydate, String texitdate) {
        this.Tserial = tserial;
        this.Tidtype = tidtype;
        this.TidNumber = tidNumber;
        this.TName = TName;
        this.Tage = tage;
        this.Tentrydate = tentrydate;
        this.Texitdate = texitdate;
    }

    public Integer getTserial() {
        return Tserial;
    }

    public void setTserial(Integer tserial) {
        Tserial = tserial;
    }

    public Integer getTage() {
        return Tage;
    }

    public void setTage(Integer tage) {
        Tage = tage;
    }

    public String getTidtype() {
        return Tidtype;
    }

    public void setTidtype(String tidtype) {
        Tidtype = tidtype;
    }

    public String getTidNumber() {
        return TidNumber;
    }

    public void setTidNumber(String tidNumber) {
        TidNumber = tidNumber;
    }

    public String getTName() {
        return TName;
    }

    public void setTName(String TName) {
        this.TName = TName;
    }

    public String getTentrydate() {
        return Tentrydate;
    }

    public void setTentrydate(String tentrydate) {
        Tentrydate = tentrydate;
    }

    public String getTexitdate() {
        return Texitdate;
    }

    public void setTexitdate(String texitdate) {
        Texitdate = texitdate;
    }
}

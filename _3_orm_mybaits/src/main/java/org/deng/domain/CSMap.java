package org.deng.domain;

public class CSMap {
    @Override
    public String toString() {
        return "CSMap{" +
                "sName='" + sName + '\'' +
                ", cName='" + cName + '\'' +
                '}';
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    private String sName;
    private String cName;
}

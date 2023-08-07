package org.adtado.katfun.effective.chapter02.item02;

public class Item02JavaBeansResponse {
    private Boolean isBocho;
    private Boolean isKatfun;
    private Boolean isWuki;
    private Boolean isChak;
    private Boolean isBruceHan;
    private Boolean isHarryPatta;
    private Boolean isIvai;
    private Boolean isBachikalCode;
    private Boolean isPangyo = false;
    private Boolean isJeongja = false;
    private Boolean isRemote = false;
    private Boolean isOffice = false;
    private int accessCount = 0;

    public Boolean getBocho() {
        return isBocho;
    }

    public Boolean getKatfun() {
        return isKatfun;
    }

    public Boolean getWuki() {
        return isWuki;
    }

    public Boolean getChak() {
        return isChak;
    }

    public Boolean getBruceHan() {
        return isBruceHan;
    }

    public Boolean getHarryPatta() {
        return isHarryPatta;
    }

    public Boolean getIvai() {
        return isIvai;
    }

    public Boolean getBachikalCode() {
        return isBachikalCode;
    }

    public Boolean getPangyo() {
        return isPangyo;
    }

    public Boolean getJeongja() {
        return isJeongja;
    }

    public Boolean getRemote() {
        return isRemote;
    }

    public Boolean getOffice() {
        return isOffice;
    }

    public int getAccessCount() {
        return accessCount;
    }

    public Item02JavaBeansResponse() {
    }

    public void setBocho(Boolean bocho) {
        isBocho = bocho;
    }

    public void setKatfun(Boolean katfun) {
        isKatfun = katfun;
    }

    public void setWuki(Boolean wuki) {
        isWuki = wuki;
    }

    public void setChak(Boolean chak) {
        isChak = chak;
    }

    public void setBruceHan(Boolean bruceHan) {
        isBruceHan = bruceHan;
    }

    public void setHarryPatta(Boolean harryPatta) {
        isHarryPatta = harryPatta;
    }

    public void setIvai(Boolean ivai) {
        isIvai = ivai;
    }

    public void setBachikalCode(Boolean bachikalCode) {
        isBachikalCode = bachikalCode;
    }

    public void setPangyo(Boolean pangyo) {
        isPangyo = pangyo;
    }

    public void setJeongja(Boolean jeongja) {
        isJeongja = jeongja;
    }

    public void setRemote(Boolean remote) {
        isRemote = remote;
    }

    public void setOffice(Boolean office) {
        isOffice = office;
    }

    public void setAccessCount(int target) {
        accessCount = target;
    }
}

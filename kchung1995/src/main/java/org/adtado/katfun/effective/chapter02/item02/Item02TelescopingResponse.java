package org.adtado.katfun.effective.chapter02.item02;

public class Item02TelescopingResponse {
    private final Boolean isBocho;
    private final Boolean isKatfun;
    private final Boolean isWuki;
    private final Boolean isChak;
    private final Boolean isBruceHan;
    private final Boolean isHarryPatta;
    private final Boolean isIvai;
    private final Boolean isBachikalCode;
    private final Boolean isPangyo;
    private final Boolean isJeongja;
    private final Boolean isRemote;
    private final Boolean isOffice;

    public Item02TelescopingResponse(Boolean isBocho, Boolean isKatfun, Boolean isWuki, Boolean isChak, Boolean isBruceHan, Boolean isHarryPatta, Boolean isIvai, Boolean isBachikalCode, Boolean isPangyo, Boolean isJeongja, Boolean isRemote, Boolean isOffice) {
        this.isBocho = isBocho;
        this.isKatfun = isKatfun;
        this.isWuki = isWuki;
        this.isChak = isChak;
        this.isBruceHan = isBruceHan;
        this.isHarryPatta = isHarryPatta;
        this.isIvai = isIvai;
        this.isBachikalCode = isBachikalCode;
        this.isPangyo = isPangyo;
        this.isJeongja = isJeongja;
        this.isRemote = isRemote;
        this.isOffice = isOffice;
    }

    public Item02TelescopingResponse(Boolean isBocho, Boolean isKatfun, Boolean isWuki, Boolean isChak, Boolean isBruceHan, Boolean isHarryPatta, Boolean isIvai, Boolean isBachikalCode) {
        this.isBocho = isBocho;
        this.isKatfun = isKatfun;
        this.isWuki = isWuki;
        this.isChak = isChak;
        this.isBruceHan = isBruceHan;
        this.isHarryPatta = isHarryPatta;
        this.isIvai = isIvai;
        this.isBachikalCode = isBachikalCode;
        this.isPangyo = false;
        this.isJeongja = false;
        this.isRemote = false;
        this.isOffice = false;
    }

    public Item02TelescopingResponse(Boolean isBocho, Boolean isKatfun, Boolean isWuki, Boolean isChak, Boolean isBruceHan, Boolean isHarryPatta, Boolean isIvai, Boolean isBachikalCode, Boolean isPangyo, Boolean isJeongja) {
        this.isBocho = isBocho;
        this.isKatfun = isKatfun;
        this.isWuki = isWuki;
        this.isChak = isChak;
        this.isBruceHan = isBruceHan;
        this.isHarryPatta = isHarryPatta;
        this.isIvai = isIvai;
        this.isBachikalCode = isBachikalCode;
        this.isPangyo = isPangyo;
        this.isJeongja = isJeongja;
        this.isRemote = false;
        this.isOffice = false;
    }

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
}

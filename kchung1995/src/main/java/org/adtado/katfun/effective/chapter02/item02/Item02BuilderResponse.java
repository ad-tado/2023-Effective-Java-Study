package org.adtado.katfun.effective.chapter02.item02;

public class Item02BuilderResponse {
    private final Boolean isBocho;
    private final Boolean isKatfun;
    private final Boolean isWuki;
    private final Boolean isChak;
    private final Boolean isBruceHan;
    private final Boolean isHarryPatta;
    private final Boolean isIvai;
    private final Boolean isBachikalCode;
    private final Boolean isPangyo;

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

    private final Boolean isJeongja;
    private final Boolean isRemote;
    private final Boolean isOffice;

    public static class Builder {
        private final Boolean isBocho;
        private final Boolean isKatfun;
        private final Boolean isWuki;
        private final Boolean isChak;
        private final Boolean isBruceHan;
        private final Boolean isHarryPatta;
        private final Boolean isIvai;
        private final Boolean isBachikalCode;

        private Boolean isPangyo = false;
        private Boolean isJeongja = false;
        private Boolean isRemote = false;
        private Boolean isOffice = false;

        public Builder(Boolean isBocho, Boolean isKatfun, Boolean isWuki, Boolean isChak, Boolean isBruceHan, Boolean isHarryPatta, Boolean isIvai, Boolean isBachikalCode) {
            this.isBocho = isBocho;
            this.isKatfun = isKatfun;
            this.isWuki = isWuki;
            this.isChak = isChak;
            this.isBruceHan = isBruceHan;
            this.isHarryPatta = isHarryPatta;
            this.isIvai = isIvai;
            this.isBachikalCode = isBachikalCode;
        }

        public Builder isPangyo(boolean val) {
            isPangyo = val;
            return this;
        }

        public Builder isJeongja(boolean val) {
            isJeongja = val;
            return this;
        }

        public Builder isRemote(boolean val) {
            isRemote = val;
            return this;
        }

        public Builder isOffice(boolean val) {
            isOffice = val;
            return this;
        }

        public Item02BuilderResponse build() {
            return new Item02BuilderResponse(this);
        }
    }

    public Item02BuilderResponse(Builder builder) {
        this.isBocho = builder.isBocho;
        this.isKatfun = builder.isKatfun;
        this.isWuki = builder.isWuki;
        this.isChak = builder.isChak;
        this.isBruceHan = builder.isBruceHan;
        this.isHarryPatta = builder.isHarryPatta;
        this.isIvai = builder.isIvai;
        this.isBachikalCode = builder.isBachikalCode;
        this.isPangyo = builder.isPangyo;
        this.isJeongja = builder.isJeongja;
        this.isRemote = builder.isRemote;
        this.isOffice = builder.isOffice;
    }
}

package com.lld.parkinglot.services.console;

public class LotConfig {
    static private String lotId = "PR1234";

    public static void setLotId(String lotId) {
        LotConfig.lotId = lotId;
    }

    public static String getLotId() {
        return lotId;
    }
}

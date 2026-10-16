package com.lastma.data.models;

public enum ViolationType {
    OVERSPEEDING(50_000),
    DUI(100_000),
    EXPIRED_PAPERS(30_000),
    TRAFFIC_LIGHT_OFFENCE(50_000),
    ONE_WAY(30_000),
    ;

    ViolationType(int paymentAmount) {
    }
}

// TransactionRequest.java
package com.example.pay.dto;

public class TransactionRequest {
    private String userId;
    private Integer amount;

    public TransactionRequest(String userId, Integer amount) {
        this.userId = userId;
        this.amount = amount;
    }

    // Getter와 Setter를 추가합니다.
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

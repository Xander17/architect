package ru.geekbrains.atmclient.operation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.atmclient.model.Bill;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardOperationResponse {

    private Boolean status;
    private Integer amount;
    private List<Bill> billList;

    public CardOperationResponse(Boolean status) {
        this.status = status;
    }

    public CardOperationResponse(Boolean status, Integer amount) {
        this.status = status;
        this.amount = amount;
    }

    public CardOperationResponse(List<Bill> billList) {
        this.billList = billList;
    }
}

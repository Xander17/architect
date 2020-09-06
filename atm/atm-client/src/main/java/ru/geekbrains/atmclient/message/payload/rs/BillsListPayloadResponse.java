package ru.geekbrains.atmclient.message.payload.rs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.atmclient.model.Bill;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillsListPayloadResponse {
    private List<Bill> bills;
}

package ru.geekbrains.atmclient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.atmclient.handler.AuthHandler;
import ru.geekbrains.atmclient.handler.CardOperationHandler;
import ru.geekbrains.atmclient.model.Bill;
import ru.geekbrains.atmclient.operation.CardOperationRequest;

import java.util.List;

import static ru.geekbrains.atmclient.operation.CardOperationType.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final AuthHandler authHandler;
    private final CardOperationHandler cardOperationHandler;

    @GetMapping
    public String mainPage() {
        return "main";
    }

    @GetMapping("/auth")
    public String authPage() {
        return "auth";
    }

    @PostMapping("/authorization")
    public String auth(String pinCode) {
        authHandler.authByToken(pinCode);
        return "redirect:/user";
    }

    @GetMapping("/close")
    public String endSession() {
        authHandler.endCardSession();
        return "redirect:/user/auth";
    }

    @GetMapping("/cashIn")
    public String cashInPage() {
        authHandler.checkAuthorized();
        cardOperationHandler.handle(CASH_IN_PREPARE);
        return "cashIn";
    }

    @PostMapping("/cashIn")
    public String cashIn(Model model) {
        authHandler.checkAuthorized();
        Integer amount = cardOperationHandler.handle(CASH_IN).getAmount();
        model.addAttribute("cashAmount", amount);
        return "redirect:/user";
    }

    @GetMapping("/cashOut")
    public String cashOutPage() {
        authHandler.checkAuthorized();
        return "cashOut";
    }

    @PostMapping("/cashOut")
    public String cashOut(Model model, Integer amount) {
        authHandler.checkAuthorized();
        Boolean status = cardOperationHandler.handle(CASH_OUT, CardOperationRequest.builder()
                .amount(amount)
                .build())
                .getStatus();
        model.addAttribute("operationStatus", status);
        return "redirect:/user";
    }

    @GetMapping("/balance")
    public String balancePage(Model model) {
        authHandler.checkAuthorized();
        Integer balance = cardOperationHandler.handle(GET_BALANCE).getAmount();
        model.addAttribute("balance", balance);
        return "balance";
    }

    @GetMapping("/bills")
    public String payBillPage(Model model) {
        authHandler.checkAuthorized();
        List<Bill> billsList = cardOperationHandler.handle(GET_BILLS_LIST).getBillList();
        model.addAttribute("billsList", billsList);
        return "bills";
    }

    @PostMapping("/bill")
    public String payBill(Model model, Bill bill) {
        authHandler.checkAuthorized();
        Boolean status = cardOperationHandler.handle(PAY_BILL, CardOperationRequest.builder()
                .bill(bill)
                .build())
                .getStatus();
        model.addAttribute("operationStatus", status);
        return "redirect:/user";
    }
}

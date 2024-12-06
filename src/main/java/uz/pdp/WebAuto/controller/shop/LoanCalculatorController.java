package uz.pdp.WebAuto.controller.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.WebAuto.entity.shop.LoanHistory;
import uz.pdp.WebAuto.service.shop.LoanCalculatorService;

@RestController
@RequestMapping("/api/loan")
public class LoanCalculatorController {
    @Autowired
    private LoanCalculatorService loanCalculatorService;

    @PostMapping("/calculate")
    public ResponseEntity<LoanHistory> calculateLoan(
            @RequestParam Double amount,
            @RequestParam Integer termInMonths,
            @RequestParam Double annualRate) {
        return ResponseEntity.ok(loanCalculatorService.calculateAndSaveLoan(amount, termInMonths, annualRate));
    }
}


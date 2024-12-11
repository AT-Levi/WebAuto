package uz.pdp.WebAuto.controller.shop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.entity.shop.LoanHistory;
import uz.pdp.WebAuto.service.shop.LoanCalculatorService;

import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
public class LoanCalculatorController {

    @Autowired
    private LoanCalculatorService loanCalculatorService;

    @PostMapping("/calculate")
    public ResponseEntity<LoanHistory> calculateLoan(@RequestParam Double amount,
                                                     @RequestParam Integer termInMonths,
                                                     @RequestParam Double annualRate) {
        try {
            LoanHistory loanHistory = loanCalculatorService.calculateAndSaveLoan(amount, termInMonths, annualRate);
            return ResponseEntity.ok(loanHistory);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanHistory> getLoanById(@PathVariable Long id) {
        Optional<LoanHistory> loanHistory = loanCalculatorService.findLoanById(id);
        return loanHistory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}


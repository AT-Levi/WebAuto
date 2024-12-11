package uz.pdp.WebAuto.controller.shop;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(
            summary = "Kreditni hisoblash",
            description = "Bu metod berilgan miqdor, muddat va yillik foiz stavkasi asosida kreditni hisoblaydi va saqlaydi."
    )
    @PostMapping("/calculate")
    public ResponseEntity<LoanHistory> calculateLoan(
            @Parameter(description = "Kredit miqdori") @RequestParam Double amount,
            @Parameter(description = "Kredit muddatini oyda kiritish") @RequestParam Integer termInMonths,
            @Parameter(description = "Yillik foiz stavkasi") @RequestParam Double annualRate
    ) {
        try {
            LoanHistory loanHistory = loanCalculatorService.calculateAndSaveLoan(amount, termInMonths, annualRate);
            return ResponseEntity.ok(loanHistory);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Operation(
            summary = "Kredit tarixini ID bo'yicha olish",
            description = "Bu metod ID bo'yicha kredit tarixini topib, uni qaytaradi."
    )
    @GetMapping("/{id}")
    public ResponseEntity<LoanHistory> getLoanById(@PathVariable Long id) {
        Optional<LoanHistory> loanHistory = loanCalculatorService.findLoanById(id);
        return loanHistory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
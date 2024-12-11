package uz.pdp.WebAuto.service.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.entity.shop.LoanHistory;
import uz.pdp.WebAuto.repository.shop.LoanHistoryRepository;

import java.util.Optional;

@Service
public class LoanCalculatorService {

    @Autowired
    private LoanHistoryRepository loanHistoryRepository;

    public LoanHistory calculateAndSaveLoan(Double amount, Integer termInMonths, Double annualRate) {
        validateInputs(amount, termInMonths, annualRate);

        Double monthlyRate = annualRate / 12 / 100;
        Double denominator = Math.pow(1 + monthlyRate, termInMonths) - 1;
        if (denominator == 0) {
            throw new IllegalArgumentException("Invalid inputs causing denominator to be zero!");
        }
        Double monthlyPayment = (amount * monthlyRate * Math.pow(1 + monthlyRate, termInMonths)) / denominator;

        LoanHistory loanHistory = new LoanHistory();
        loanHistory.setAmount(amount);
        loanHistory.setTermInMonths(termInMonths);
        loanHistory.setAnnualRate(annualRate);
        loanHistory.setMonthlyPayment(monthlyPayment);

        return loanHistoryRepository.save(loanHistory);
    }

    public Optional<LoanHistory> findLoanById(Long id) {
        return loanHistoryRepository.findById(id);
    }

    private void validateInputs(Double amount, Integer termInMonths, Double annualRate) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero!");
        }
        if (termInMonths == null || termInMonths <= 0) {
            throw new IllegalArgumentException("Term in months must be greater than zero!");
        }
        if (annualRate == null || annualRate <= 0) {
            throw new IllegalArgumentException("Annual rate must be greater than zero!");
        }
    }
}

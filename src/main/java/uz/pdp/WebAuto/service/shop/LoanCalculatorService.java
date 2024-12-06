package uz.pdp.WebAuto.service.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.entity.shop.LoanHistory;
import uz.pdp.WebAuto.repository.shop.LoanHistoryRepository;

@Service
public class LoanCalculatorService {
    @Autowired
    private LoanHistoryRepository loanHistoryRepository;

    public LoanHistory calculateAndSaveLoan(Double amount, Integer termInMonths, Double annualRate) {
        // Parametrlarni tekshirish
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero!");
        }
        if (termInMonths == null || termInMonths <= 0) {
            throw new IllegalArgumentException("Term in months must be greater than zero!");
        }
        if (annualRate == null || annualRate <= 0) {
            throw new IllegalArgumentException("Annual rate must be greater than zero!");
        }

        // Hisoblash
        Double monthlyRate = annualRate / 12 / 100;
        Double denominator = Math.pow(1 + monthlyRate, termInMonths) - 1;
        if (denominator == 0) {
            throw new IllegalArgumentException("Invalid inputs causing denominator to be zero!");
        }
        Double monthlyPayment = (amount * monthlyRate * Math.pow(1 + monthlyRate, termInMonths)) / denominator;

        // LoanHistory obyektini saqlash
        LoanHistory loanHistory = new LoanHistory();
        loanHistory.setAmount(amount);
        loanHistory.setTermInMonths(termInMonths);
        loanHistory.setAnnualRate(annualRate);
        loanHistory.setMonthlyPayment(monthlyPayment);

        // Ma'lumotlar bazasiga saqlash
        return loanHistoryRepository.save(loanHistory);
    }
}


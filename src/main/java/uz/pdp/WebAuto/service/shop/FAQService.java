package uz.pdp.WebAuto.service.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.entity.shop.FAQ;
import uz.pdp.WebAuto.repository.shop.FAQRepository;

import java.util.List;

@Service
public class FAQService {
    @Autowired
    private FAQRepository faqRepository;

    public List<FAQ> getAllFAQs() {
        return faqRepository.findAll();
    }

    public List<FAQ> getFAQsByCategory(String category) {
        return faqRepository.findByCategory(category);
    }

    public FAQ addFAQ(FAQ faq) {
        return faqRepository.save(faq);
    }

    public FAQ updateFAQ(Long id, FAQ updatedFAQ) {
        FAQ faq = faqRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FAQ not found"));
        faq.setQuestion(updatedFAQ.getQuestion());
        faq.setAnswer(updatedFAQ.getAnswer());
        faq.setCategory(updatedFAQ.getCategory());
        return faqRepository.save(faq);
    }
}

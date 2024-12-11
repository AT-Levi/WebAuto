package uz.pdp.WebAuto.service.shop;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.entity.shop.FAQ;
import uz.pdp.WebAuto.repository.shop.FAQRepository;

import java.util.List;
@Service
public class FAQService {

    private final FAQRepository faqRepository;

    @Autowired
    public FAQService(FAQRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    public List<FAQ> getAllFAQs() {
        return faqRepository.findAll();
    }

    public FAQ getFAQById(Long id) {
        return faqRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FAQ with ID " + id + " not found"));
    }

    public List<FAQ> getFAQsByCategory(String category) {
        return faqRepository.findByCategory(category);
    }

    public FAQ addFAQ(FAQ faq) {
        return faqRepository.save(faq);
    }

    public FAQ updateFAQ(Long id, FAQ updatedFAQ) {
        FAQ existingFAQ = faqRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FAQ with ID " + id + " not found"));
        existingFAQ.setQuestion(updatedFAQ.getQuestion());
        existingFAQ.setAnswer(updatedFAQ.getAnswer());
        existingFAQ.setCategory(updatedFAQ.getCategory());
        return faqRepository.save(existingFAQ);
    }

    public void deleteFAQ(Long id) {
        FAQ faq = faqRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FAQ with ID " + id + " not found"));
        faqRepository.delete(faq);
    }
}


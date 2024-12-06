package uz.pdp.WebAuto.controller.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.entity.shop.FAQ;
import uz.pdp.WebAuto.service.shop.FAQService;

import java.util.List;

@RestController
@RequestMapping("/api/faqs")
public class FAQController {
    @Autowired
    private FAQService faqService;

    @GetMapping
    public ResponseEntity<List<FAQ>> getAllFAQs() {
        return ResponseEntity.ok(faqService.getAllFAQs());
    }

    @GetMapping("/category")
    public ResponseEntity<List<FAQ>> getFAQsByCategory(@RequestParam String category) {
        return ResponseEntity.ok(faqService.getFAQsByCategory(category));
    }

    @PostMapping
    public ResponseEntity<FAQ> addFAQ(@RequestBody FAQ faq) {
        return ResponseEntity.ok(faqService.addFAQ(faq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FAQ> updateFAQ(@PathVariable Long id, @RequestBody FAQ updatedFAQ) {
        return ResponseEntity.ok(faqService.updateFAQ(id, updatedFAQ));
    }
}


package uz.pdp.WebAuto.controller.shop;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.entity.shop.FAQ;
import uz.pdp.WebAuto.service.shop.FAQService;

import java.util.List;

@RestController
@RequestMapping("/api/faqs")

public class FAQController {

    private final FAQService faqService;

    @Autowired
    public FAQController(FAQService faqService) {
        this.faqService = faqService;
    }

    @Operation(summary = "Barcha FAQ'larni olish", description = "Bu metod barcha FAQ'larni qaytaradi.")
    @GetMapping
    public ResponseEntity<List<FAQ>> getAllFAQs() {
        return ResponseEntity.ok(faqService.getAllFAQs());
    }

    @Operation(summary = "ID orqali FAQ'ni olish", description = "Bu metod berilgan IDga ega FAQ'ni qaytaradi.")
    @GetMapping("/{id}")
    public ResponseEntity<FAQ> getFAQById(@PathVariable Long id) {
        return ResponseEntity.ok(faqService.getFAQById(id));
    }

    @Operation(summary = "Kategoriyaga ko'ra FAQ'larni olish", description = "Bu metod berilgan kategoriyadagi FAQ'larni qaytaradi.")
    @GetMapping("/category/{category}")
    public ResponseEntity<List<FAQ>> getFAQsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(faqService.getFAQsByCategory(category));
    }

    @Operation(summary = "Yangi FAQ qo'shish", description = "Bu metod yangi FAQ yaratib, uni qaytaradi.")
    @PostMapping
    public ResponseEntity<FAQ> addFAQ(@RequestBody FAQ faq) {
        FAQ createdFAQ = faqService.addFAQ(faq);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFAQ);
    }

    @Operation(summary = "FAQ'ni yangilash", description = "Bu metod mavjud FAQ'ni yangilab, yangilangan FAQ'ni qaytaradi.")
    @PutMapping("/{id}")
    public ResponseEntity<FAQ> updateFAQ(@PathVariable Long id, @RequestBody FAQ updatedFAQ) {
        FAQ updated = faqService.updateFAQ(id, updatedFAQ);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "FAQ'ni o'chirish", description = "Bu metod berilgan IDga ega FAQ'ni o'chirib yuboradi.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFAQ(@PathVariable Long id) {
        faqService.deleteFAQ(id);
        return ResponseEntity.noContent().build();
    }
}

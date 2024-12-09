package uz.pdp.WebAuto.service;

import org.springframework.web.multipart.MultipartFile;
import uz.pdp.WebAuto.dtos.image.ImageDataDTO;
import uz.pdp.WebAuto.dtos.image.ImageRequestDTO;
import uz.pdp.WebAuto.dtos.image.ImageResponseDTO;
import uz.pdp.WebAuto.entity.Image;

public interface ImageService {

    Image save(MultipartFile file);

    ImageDataDTO getImageData(Long id);

    ImageResponseDTO findById(Long id);

    Image refresh(Long entityId, MultipartFile file);

    Image findByCompanyId(Long companyId);

    void deleteById(Long id);
}

package uz.pdp.WebAuto.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.WebAuto.config.service.StorageService;
import uz.pdp.WebAuto.dtos.image.ImageDataDTO;
import uz.pdp.WebAuto.dtos.image.ImageRequestDTO;
import uz.pdp.WebAuto.dtos.image.ImageResponseDTO;
import uz.pdp.WebAuto.entity.Image;
import uz.pdp.WebAuto.exception.NotFoundException;
import uz.pdp.WebAuto.mapper.ImageMapper;
import uz.pdp.WebAuto.repository.ImageRepository;
import uz.pdp.WebAuto.service.ImageService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImp implements ImageService {

    private final StorageService storageServiceImp;
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    @Override
    public Image save(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extension = getFilenameExtension(file);
        String fileName = UUID.randomUUID() + "." + extension;
        String mimeType = file.getContentType();

        String imageUrl = storageServiceImp.uploadFile(file, "images", fileName);

        Image image = Image.builder()
                .url(imageUrl)
                .fileName(fileName)
                .mimeType(mimeType)
                .extension(extension)
                .originalName(originalFilename)
                .build();

        return imageRepository.save(image);
    }

    @Override
    public List<Image> saveImages(List<ImageRequestDTO> carImages) {
        return carImages.stream()
                .map(imageRequestDTO -> save(imageRequestDTO.getImage()))
                .toList();
    }


    @Override
    public ImageDataDTO getImageData(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Image "));
        return ImageDataDTO.builder()
                .id(image.getId())
                .fileName(image.getFileName())
                .originalName(image.getOriginalName())
                .extension(image.getExtension())
                .mimeType(image.getMimeType())
                .url(image.getUrl())
                .build();
    }

    @Override
    public ImageResponseDTO findById(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Image "));
        return imageMapper.toDto(image);
    }

    @Override
    public Image refresh(Long entityId, MultipartFile file) {
        return null;
    }

    @Override
    public Image findByCompanyId(Long companyId) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        imageRepository.deleteByImageId(id);
    }

    private static String getFilenameExtension(MultipartFile file) {
        return StringUtils.getFilenameExtension(file.getOriginalFilename());
    }
}

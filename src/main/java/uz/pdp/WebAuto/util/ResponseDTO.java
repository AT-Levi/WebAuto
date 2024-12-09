package uz.pdp.WebAuto.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uz.pdp.WebAuto.exception.ErrorDataResponse;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> {
    private boolean success;
    private Integer status;
    private T data;
    private String message;
    private ErrorDataResponse errorData;
    private PageableResponse pageableResponse;

    public ResponseDTO(boolean success, Integer status, T data) {
        this.success = success;
        this.data = data;
        this.status = status;
    }

    public ResponseDTO(boolean success, Integer status, T data, ErrorDataResponse errorData, String message) {
        this.success = success;
        this.data = data;
        this.status = status;
        this.message = message;
        this.errorData = errorData;
    }

    public ResponseDTO(boolean success, Integer status, T data, String message, PageableResponse pageableResponse) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.status = status;
        this.pageableResponse = pageableResponse;
    }

    public static <T> ResponseEntity<ResponseDTO<T>> ok(T data) {
        return ResponseEntity.ok(new ResponseDTO<>(true, HttpStatus.OK.value(), data));
    }

    public static <T> ResponseEntity<ResponseDTO<T>> ok(String message) {
        return ResponseEntity.ok(new ResponseDTO<>(true, HttpStatus.OK.value(), null));
    }

    public static <T> ResponseEntity<ResponseDTO<T>> error(ErrorDataResponse errorData) {
        return ResponseEntity.badRequest().body(new ResponseDTO<>(false, HttpStatus.BAD_REQUEST.value(), null, errorData, errorData.getErrorMessage()));
    }

    public static <T> ResponseEntity<ResponseDTO<T>> error(String message) {
        return ResponseEntity.badRequest().body(new ResponseDTO<>(false, HttpStatus.BAD_REQUEST.value(), null, message, null));
    }

    public static <T> ResponseEntity<ResponseDTO<T>> error(String message, HttpStatus status) {
        return ResponseEntity.badRequest().body(new ResponseDTO<>(false, status.value(), null, message, null));
    }

    public static <T> ResponseEntity<ResponseDTO<T>> error(ErrorDataResponse errorData, HttpStatus status) {
        return ResponseEntity.badRequest().body(new ResponseDTO<>(false, status.value(), null, errorData, errorData.getErrorMessage()));
    }

    public static <T> ResponseEntity<ResponseDTO<List<T>>> page(Page<T> pageContent){
        return ResponseEntity.ok(
                new ResponseDTO<>(
                        true,
                        HttpStatus.OK.value(),
                        pageContent.getContent(),
                        "Successfully",
                        new PageableResponse(
                                pageContent.getTotalElements(),
                                pageContent.getNumber(),
                                pageContent.getTotalPages(),
                                pageContent.getPageable().getPageSize()
                        )
                )
        );
    }
}
package uz.pdp.WebAuto.exception;

import uz.pdp.WebAuto.dtos.error.ErrorResDTO;
import uz.pdp.WebAuto.enums.ErrorCode;
import uz.pdp.WebAuto.util.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalException {



    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseDTO<Void>> handleNotFound(NotFoundException ex) {
        if (Objects.nonNull(ex.getTableName())) {
            return ResponseDTO.error(new ErrorDataResponse(
                    ErrorCode.DATA_NOT_FOUND.getCode(), ErrorCode.DATA_NOT_FOUND.getErrorKey(),
                    ex.getTableName()
            ));
        }
        return ResponseDTO.error(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

//
//    @ExceptionHandler(GeneralException.class)
//    public ResponseEntity<ResponseDTO<Void>> handleGeneral(GeneralException ex) {
//        if (Objects.nonNull(ex.getErrorCode())) {
//            return ResponseDTO.error(new ErrorDataResponse());
//        }
//
//        return
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<Void>> handle(Exception ex) {
        ex.printStackTrace();
        return ResponseDTO.error(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
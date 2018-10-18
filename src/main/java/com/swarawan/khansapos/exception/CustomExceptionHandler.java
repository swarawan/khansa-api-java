package com.swarawan.khansapos.exception;

import com.swarawan.khansapos.utils.StringUtils;
import com.swarawan.khansapos.vo.ErrorVO;
import com.swarawan.khansapos.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorVO errorVO = new ErrorVO(ex.getLocalizedMessage(), status.value());
        ResultVO resultVO = new ResultVO();
        resultVO.status = status.name();
        resultVO.error = errorVO;
        return new ResponseEntity<>(resultVO, status);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ResultVO> badRequest(AppException ex) {
        ErrorVO errorVO = new ErrorVO(ex.getLocalizedMessage(), ex.code.value());
        ResultVO resultVO = new ResultVO();
        resultVO.status = HttpStatus.BAD_REQUEST.name();
        resultVO.error = errorVO;
        return new ResponseEntity<>(resultVO, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultVO> generalError(Exception ex) {
        ErrorVO errorVO = new ErrorVO(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        ResultVO resultVO = new ResultVO();
        resultVO.status = HttpStatus.INTERNAL_SERVER_ERROR.name();
        resultVO.error = errorVO;
        return new ResponseEntity<>(resultVO, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ResultVO> badRequest(EmptyResultDataAccessException ex) {
        ErrorVO errorVO = new ErrorVO(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND.value());
        ResultVO resultVO = new ResultVO();
        resultVO.status = HttpStatus.NOT_FOUND.name();
        resultVO.error = errorVO;
        return new ResponseEntity<>(resultVO, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(NoSuchMessageException.class)
    public ResponseEntity<ResultVO> badRequest(NoSuchMessageException ex) {
        ErrorVO errorVO = new ErrorVO(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND.value());
        ResultVO resultVO = new ResultVO();
        resultVO.status = HttpStatus.NOT_FOUND.name();
        resultVO.error = errorVO;
        return new ResponseEntity<>(resultVO, HttpStatus.NOT_FOUND);
    }


}

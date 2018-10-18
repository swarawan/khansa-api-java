package com.swarawan.khansapos.handler;

import com.swarawan.khansapos.costant.StatusCode;
import com.swarawan.khansapos.exception.AppException;
import com.swarawan.khansapos.vo.ErrorVO;
import com.swarawan.khansapos.vo.ResultVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public abstract class AbstractResponseHandler {

    public ResponseEntity<ResultVO> getResult() {
        ResultVO resultVO = new ResultVO();
        Object processResponse = processResponse();
        if (processResponse instanceof AppException) {
            resultVO.status = StatusCode.ERROR.name();
            resultVO.error = new ErrorVO(
                    ((AppException) processResponse).errorMessage,
                    ((AppException) processResponse).code.value());
            return generateResponseEntity(resultVO, HttpStatus.BAD_REQUEST);
        } else {
            resultVO.status = StatusCode.OK.name();
            resultVO.data = processResponse;
            return generateResponseEntity(resultVO, HttpStatus.OK);
        }
    }

    private ResponseEntity<ResultVO> generateResponseEntity(ResultVO result, HttpStatus code) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<>(result, headers, code);
    }

    protected abstract Object processResponse();
}

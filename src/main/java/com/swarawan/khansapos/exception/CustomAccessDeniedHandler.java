package com.swarawan.khansapos.exception;

import com.swarawan.khansapos.vo.ErrorVO;
import com.swarawan.khansapos.vo.ResultVO;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ErrorVO errorVO = new ErrorVO(e.getLocalizedMessage(), HttpStatus.FORBIDDEN.value());
        ResultVO resultVO = new ResultVO();
        resultVO.status = HttpStatus.FORBIDDEN.name();
        resultVO.error = errorVO;

        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(httpServletResponse.getOutputStream(), generateBody(resultVO));
    }

    private Map<String, Object> generateBody(ResultVO resultVO) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", resultVO.status);
        result.put("data", resultVO.data);
        result.put("error", resultVO.error);
        return result;
    }
}

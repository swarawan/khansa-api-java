package com.swarawan.khansapos.exception;

import com.swarawan.khansapos.vo.ErrorVO;
import com.swarawan.khansapos.vo.ResultVO;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ErrorVO errorVO = new ErrorVO(e.getLocalizedMessage(), HttpStatus.UNAUTHORIZED.value());
        ResultVO resultVO = new ResultVO();
        resultVO.status = HttpStatus.UNAUTHORIZED.name();
        resultVO.error = errorVO;

        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

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

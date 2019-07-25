package com.htsc.zcgl.util.exception;

import com.htsc.zcgl.util.response.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 统一异常处理
 *
 * @author - 011354
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {
    private static final int simpleStackTraceLines = 5;
    private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    public static void printSimpleStackTrace(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        sb.append(ex.toString()).append("\n");

        StackTraceElement[] stackElements = ex.getStackTrace();
            if (stackElements.length > simpleStackTraceLines)
                sb.append("---ignore ").append(stackElements.length - simpleStackTraceLines).append(" lines---\n");

            logger.error(sb.toString());
        }
    }

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception e) {
        ModelAndView mv = new ModelAndView();

        //设置状态码
        response.setStatus(HttpStatus.OK.value());
        //设置ContentType
        response.setContentType(String.valueOf(MediaType.APPLICATION_JSON));
        //避免乱码
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");

        try {
            String ret;

            if (e instanceof BaseRuntimeException) {
                logger.debug(e.getMessage(), e);
                printSimpleStackTrace(e);

                int code = ((BaseRuntimeException) e).getCode();
                String msg = e.getMessage();

                ret = ResultUtil.error(code, msg).toString();
            } else {
                if(logger.isDebugEnabled())
                  logger.debug(e.getMessage(), e);
                else
                    printSimpleStackTrace(e);

//                ret = ResultUtil.error(-1, e.getLocalizedMessage()).toString();
                ret = ResultUtil.error(-1, e.toString()).toString();
            }

            response.getWriter().write(ret);
        } catch (IOException ioe) {
            logger.debug(ioe.getMessage(), ioe);
            printSimpleStackTrace(e);
        }

        return mv;
    }
}

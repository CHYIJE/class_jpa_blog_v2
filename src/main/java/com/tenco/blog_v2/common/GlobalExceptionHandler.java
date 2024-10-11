package com.tenco.blog_v2.common;

import com.tenco.blog_v2.common.errors.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice    // IoC대상 (뷰 렌더링)
public class GlobalExceptionHandler {

    /**
     * 400 Band Requset 예외처리
     * @param ex
     * @param model
     * @return
     */
    @ExceptionHandler(Exception400.class)
    public ModelAndView handelException(Exception400 ex, Model model) {
        // templates/err/400.mustache
        ModelAndView mav = new ModelAndView("err/400");
        mav.addObject("msg", ex.getMessage());
        return mav;
    }

    /**
     * 400 Band Requset 예외처리
     * @param ex
     * @param model
     * @return
     */
    @ExceptionHandler(Exception401.class)
    public ModelAndView handelException(Exception401 ex, Model model) {

        ModelAndView mav = new ModelAndView("err/401");
        mav.addObject("msg", ex.getMessage());
        return mav;
    }

    /**
     * 400 Band Requset 예외처리
     * @param ex
     * @param model
     * @return
     */
    @ExceptionHandler(Exception403.class)
    public ModelAndView handelException(Exception403 ex, Model model) {

        ModelAndView mav = new ModelAndView("err/403");
        mav.addObject("msg", ex.getMessage());
        return mav;
    }

    /**
     * 400 Band Requset 예외처리
     * @param ex
     * @param model
     * @return
     */
    @ExceptionHandler(Exception404.class)
    public ModelAndView handelException(Exception404 ex, Model model) {

        ModelAndView mav = new ModelAndView("err/404");
        mav.addObject("msg", ex.getMessage());
        return mav;
    }

    /**
     * 400 Band Requset 예외처리
     * @param ex
     * @param model
     * @return
     */
    @ExceptionHandler(Exception500.class)
    public ModelAndView handelException(Exception500 ex, Model model) {

        ModelAndView mav = new ModelAndView("err/500");
        mav.addObject("msg", ex.getMessage());
        return mav;
    }

}

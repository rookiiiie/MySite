package com.MySiteTest.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 2020/6/29
 */
public interface HomeAndAboutMeService {
    public void returnQuotes(HttpServletResponse httpServletResponse,String type) throws IOException;
}

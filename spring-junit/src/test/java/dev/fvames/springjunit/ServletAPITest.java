package dev.fvames.springjunit;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @version 2019/5/27 17:28
 */

public class ServletAPITest {

    @Test
    public void testHttpServletRequestInStaticMock() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("name", "廖毅");

        // 获取请求参数
        String value = request.getParameter("name");
        Assert.assertEquals("廖毅", value);
    }

    @Test
    public void testHttpServletRequestInDynamicMock() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("name")).thenReturn("廖毅");

        String value = request.getParameter("name");
        Assert.assertEquals("廖毅", value);

    }
}

package edu.udacity.java.nano;

import edu.udacity.java.nano.controllers.ChatController;
import edu.udacity.java.nano.controllers.LogoutController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@RunWith(SpringRunner.class)
public class LogoutControllerTests {

    @Test
    public void testNoSessionRedirectToLogin(){
        LogoutController controller = new LogoutController();
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        ModelAndView actualMaV = controller.logout(mockRequest);

        Assert.assertTrue(((RedirectView)actualMaV.getView()).isRedirectView());
        Assert.assertEquals("/login", ((RedirectView)actualMaV.getView()).getUrl());
    }

    @Test
    public void testValidSessionInvalidateIsCalled(){
        LogoutController controller = new LogoutController();
        String expectedUsername = "John Doe";
        MockHttpSession mockSession = new MockHttpSession();
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.setSession(mockSession);
        ModelAndView actualMaV = controller.logout(mockRequest);

        Assert.assertTrue(mockSession.isInvalid());
        Assert.assertEquals("/login", ((RedirectView)actualMaV.getView()).getUrl());
    }

}

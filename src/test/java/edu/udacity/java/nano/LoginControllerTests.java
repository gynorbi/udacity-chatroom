package edu.udacity.java.nano;

import edu.udacity.java.nano.controllers.LoginController;
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
public class LoginControllerTests {

    @Test
    public void testGetRequest(){
        LoginController controller = new LoginController();
        ModelAndView actualMaV = controller.login();

        Assert.assertEquals("login", actualMaV.getViewName());
    }

    @Test
    public void testPostRequestNullUsername(){
        LoginController controller = new LoginController();
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        ModelAndView actualMaV = controller.loginSetUsername(null, mockRequest);

        Assert.assertEquals("login", actualMaV.getViewName());
    }

    @Test
    public void testPostRequestEmptyUsername(){
        LoginController controller = new LoginController();
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        ModelAndView actualMaV = controller.loginSetUsername("", mockRequest);

        Assert.assertEquals("login", actualMaV.getViewName());
    }

    @Test
    public void testPostRequestValidUsername(){
        LoginController controller = new LoginController();
        String expectedUsername = "John Doe";
        MockHttpSession oldMockSession = new MockHttpSession();
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.setSession(oldMockSession);
        ModelAndView actualMaV = controller.loginSetUsername(expectedUsername, mockRequest);

        Assert.assertTrue("Old session should be invalid", oldMockSession.isInvalid());
        Assert.assertEquals("Session should contain the username", expectedUsername,mockRequest.getSession(false).getAttribute("username"));
        Assert.assertEquals("Should be redirected to the main page","/", ((RedirectView)actualMaV.getView()).getUrl());
    }
}

package edu.udacity.java.nano;

import edu.udacity.java.nano.controllers.ChatController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@RunWith(SpringRunner.class)
//@SpringBootTest
public class ChatControllerTests {

    @Test
    public void testNoSessionRedirectToLogin(){
        ChatController controller = new ChatController();
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        ModelAndView actualMaV = controller.index(mockRequest);

        Assert.assertTrue(((RedirectView)actualMaV.getView()).isRedirectView());
        Assert.assertEquals("/login", ((RedirectView)actualMaV.getView()).getUrl());
    }

    @Test
    public void testValidSessionGoToChatRoom(){
        ChatController controller = new ChatController();
        String expectedUsername = "John Doe";
        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute("USERNAME",expectedUsername);
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.setSession(mockSession);
        ModelAndView actualMaV = controller.index(mockRequest);

        Assert.assertEquals("chat", actualMaV.getViewName());
        Assert.assertEquals(expectedUsername,actualMaV.getModel().get("USERNAME"));

    }

}

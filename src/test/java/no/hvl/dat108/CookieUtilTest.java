package no.hvl.dat108;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


import javax.servlet.http.Cookie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CookieUtilTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    public void setup() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    /*
     * Denne testen ble lagt til etter timen der jeg prøvde å
     * bruke streams, men fikk NullPointerException.
     */
    @Test
    public void manglendeCookieSkalGiNull() {
        String cookie = CookieUtil.getCookieFromRequest(request, "User");
        assertNull(cookie);
    }

    @Test
    public void cookieSkalKunneHentesUtDekodetFraRequest() {

        //Arrange
        request.setCookies(new Cookie("User", "Atle+Patle"));
        //Act
        String cookie = CookieUtil.getCookieFromRequest(request, "User");
        //Assert
        assertEquals("Atle Patle", cookie);
    }

    @Test
    public void cookieBlirLagretKodetOgTidsmerket() {

        //Act
        CookieUtil.addCookieToResponse(response, "User", "Atle Patle");
        //Assert
        Cookie cookie = response.getCookies()[0];
        assertEquals("User", cookie.getName());
        assertEquals("Atle+Patle", cookie.getValue());
        assertEquals(30, cookie.getMaxAge());
    }
}

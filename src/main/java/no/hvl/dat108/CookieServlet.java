package no.hvl.dat108;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieServlet")
public class CookieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Sjekk om det er kommet cookies
        String besoktCookie = CookieUtil.getCookieFromRequest(request, "besokt");

        //Sende med en cookie uansett
        CookieUtil.addCookieToResponse(response, "besokt", "sdfsfnf");

        PrintWriter out = response.getWriter();

        if (besoktCookie != null) {
            out.println("Velkommen tilbake");
        } else {
            out.println("Det var et nytt fjes");
        }
    }
}

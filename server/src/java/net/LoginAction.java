/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import business.UserManager;
import common.ObjectSerialization;
import dto.ReturnDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import common.ActionResult;
import dto.UserDTO;
import dto.UserReturnDTO;

/**
 *
 * @author frederico
 */
public class LoginAction extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        ReturnDTO returnDTO = null;
        try {
            String id   = request.getParameter("id");
            String pass = request.getParameter("pass");
            String ip   = request.getRemoteAddr();
            
            UserDTO user = new UserManager().login(id, pass, ip);
            ActionResult result = user != null ? ActionResult.SUCCESS : ActionResult.ERROR;
            returnDTO = new UserReturnDTO(user, result);
        } catch (Exception ex) {
            returnDTO = new UserReturnDTO(null, ActionResult.ERROR, ex.getMessage());
        } finally {          
            out.print(ObjectSerialization.toString(returnDTO));  
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

package org.example.servlet;

import org.example.dao.ContactDao;
import org.example.dao.ContactDaoImpl;
import org.example.model.Contact;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/ContactServlet.do")
public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String LIST_CONTACT = "/listContacts.jsp";
    public static final String INSERT_OR_EDIT = "/contact.jsp";
    private ContactDao dao = new ContactDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            forward = LIST_CONTACT;
            int contactId = Integer.parseInt(request.getParameter("Id"));
            dao.deleteContact(contactId);
            request.setAttribute("contacts", dao.getAllContact());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int contactId = Integer.parseInt(request.getParameter("Id"));
            Contact contact = dao.getContactById(contactId);
            request.setAttribute("contact", contact);
        } else if (action.equalsIgnoreCase("insert")) {
            forward = INSERT_OR_EDIT;
        } else {
            forward = LIST_CONTACT;
            request.setAttribute("contact", dao.getAllContact());
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        Contact contact = new Contact();
        contact.setName(request.getParameter("Name"));
        contact.setFamily(request.getParameter("Family"));
        contact.setPhoneNumber(request.getParameter("Phone"));
        String contactId = request.getParameter("Id");

        if (contactId == null || contactId.isEmpty())
            dao.addContact(contact);
        else {
            contact.setContactId(Integer.parseInt(contactId));
            dao.updateContact(contact);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_CONTACT);
        request.setAttribute("contact", dao.getAllContact());
        view.forward(request, response);
    }
}




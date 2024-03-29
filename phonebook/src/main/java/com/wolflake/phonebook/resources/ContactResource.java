package com.wolflake.phonebook.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.*;
import com.wolflake.phonebook.representations.Contact;
import org.skife.jdbi.v2.DBI;
import com.wolflake.phonebook.dao.ContactDAO;

@Path("/contact")
@Produces(MediaType.APPLICATION_JSON)
public class ContactResource {
  // code...

private final ContactDAO contactDao;

public ContactResource(DBI jdbi) {
	contactDao = jdbi.onDemand(ContactDAO.class);
}

@GET
@Path("/{id}")
public Response getContact(@PathParam("id") int id) {
    // retrieve information about the contact with theprovided id
    // ...
    Contact contact = contactDao.getContactById(id);
    return Response
        .ok(contact)
        .build();
}

@POST
public Response createContact(Contact contact) throws URISyntaxException {
    // store the new contact
    int newContactId = contactDao.createContact(contact.getFirstName(), contact.getLastName(), contact.getPhone());
    return Response.created(new URI(String.valueOf(newContactId))).build();
}

@DELETE
@Path("/{id}")
public Response deleteContact(@PathParam("id") int id) {
    // delete the contact with the provided id
    contactDao.deleteContact(id);
    return Response.noContent().build();
}

@PUT
@Path("/{id}")
public Response updateContact(@PathParam("id") int id, Contact contact) {
    // update the contact with the provided ID
    contactDao.updateContact(id, contact.getFirstName(),
      contact.getLastName(), contact.getPhone());
    return Response.ok(
      new Contact(id, contact.getFirstName(), contact.getLastName(),
          contact.getPhone())).build();
}

}

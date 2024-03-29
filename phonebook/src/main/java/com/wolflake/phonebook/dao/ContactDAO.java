package com.wolflake.phonebook.dao;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import com.wolflake.phonebook.representations.Contact;
import com.wolflake.phonebook.dao.mappers.ContactMapper;

public interface ContactDAO {

    @Mapper(ContactMapper.class)
    @SqlQuery("select * from contact where id= :id")
    Contact getContactById(@Bind("id") int id);

    @GetGeneratedKeys
    @SqlUpdate("insert into contact (id, firstName, lastName, phone) values (NULL, :firstName, :lastName, :phone)")
    int createContact(@Bind("firstName") String firstName, @Bind("lastName") String lastName, @Bind("phone") String phone);

    @SqlUpdate("update contact set firstName = :firstName, lastName = :lastName, phone = :phone where id = :id")
    void updateContact(@Bind("id") int id, @Bind("firstName") String firstName, @Bind("lastName") String lastName, @Bind("phone") String phone);

    @SqlUpdate("delete from contact where id = :id")
    void deleteContact(@Bind("id") int id);
}

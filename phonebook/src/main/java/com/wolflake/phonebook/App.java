package com.wolflake.phonebook;

import com.wolflake.phonebook.resources.ContactResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import io.dropwizard.jdbi.DBIFactory;

/**
 * com.wolflake.phonebook.App
 */
public class App extends Application<PhonebookConfiguration> 
{
  private static final Logger LOGGER =LoggerFactory.getLogger(App.class);

  @Override
  public void initialize(Bootstrap<PhonebookConfiguration> b) {}

  @Override
  public void run(PhonebookConfiguration c, Environment e) throws Exception {

    System.out.println( "Hello world, by Dropwizard!" );
    LOGGER.info("Method App#run() called");

    for(int i=0; i < c.getMessageRepetitions(); i++) 
    {
	System.out.println(c.getMessage());
    }
	System.out.println(c.getAdditionalMessage());

    final DBIFactory factory = new DBIFactory();
    final DBI jdbi = factory.build(e, c.getDataSourceFactory(), "mysql");

    // ...
    // Add the resource to the environment
    e.jersey().register(new ContactResource(jdbi));

  }

    public static void main( String[] args ) throws Exception
    {
	new App().run(args);
    }
}

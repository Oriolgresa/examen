package edu.upc.eetac.dsa;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple Main.
 */
public class MainTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MainTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( MainTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        User user = new User("oriol","1234");
        Etakemon etakemon =new Etakemon("dito",1);
        Controller.getInstance().addUser(user);
        Controller.getInstance().addEtakemonToUser(etakemon,user);

        assertTrue(etakemon.getName().equals("dito"));
    }
}

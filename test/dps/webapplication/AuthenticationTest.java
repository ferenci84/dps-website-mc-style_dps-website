package dps.webapplication;

import dps.authentication.AuthenticableUser;
import dps.authentication.UserDataProvider;
import dps.authentication.utils.PasswordAuthentication;
import dps.logging.HasLogger;
import dps.webapplication.authentication.AuthenticableUserProvider;
import dps.webapplication.entities.ApplicationUser;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.nio.file.Paths;

@RunWith(Arquillian.class)
public class AuthenticationTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class,"test.war")
            .addClasses(ApplicationUser.class)
            .addPackage(HasLogger.class.getPackage())
            .addPackage(UserDataProvider.class.getPackage())
            .addPackage(AuthenticableUserProvider.class.getPackage())
            .addPackage(ApplicationUser.class.getPackage())
            .addPackage(PasswordAuthentication.class.getPackage())
            .addAsResource("persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    UserDataProvider userDataProvider;

    @Test
    public void testDefaultLogin()
    {
        System.out.println("Running Test");
        AuthenticableUser userByCredentials = userDataProvider.getUserByCredentials("admin", "admin");
        Assert.assertNotNull(userByCredentials);

        userByCredentials = userDataProvider.getUserByCredentials("admin", "wrong");
        Assert.assertNull(userByCredentials);

        System.out.println("Finished Test");
    }
}

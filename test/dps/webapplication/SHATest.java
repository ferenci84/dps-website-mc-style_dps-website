package dps.webapplication;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

@RunWith(Arquillian.class)
public class SHATest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class,"test.war")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testSHA()
    {
        byte[] salt = new byte[0];
        try {
            salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(32);
            System.out.println(hash("valami",salt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String hash(String password, byte[] salt) throws Exception {
        if (password != null && password.length() != 0) {
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            SecretKey key = f.generateSecret(new PBEKeySpec(password.toCharArray(), salt, 20000, 256));
            return Base64.getEncoder().encode(key.getEncoded()).toString();
        } else {
            throw new IllegalArgumentException("Empty passwords are not supported.");
        }
    }
}

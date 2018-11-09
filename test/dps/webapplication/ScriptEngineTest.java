package dps.webapplication;

import org.junit.Assert;
import org.junit.Test;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptEngineTest {


    @Test
    public void scriptEngineTest() {
        Assert.assertEquals(true,true);

        ScriptEngine javascript = new ScriptEngineManager().getEngineByName("javascript");
        Assert.assertNotNull(javascript);
        javascript.put("name1","value1");
        javascript.put("name2","value2");
        try {
            javascript.eval("name2 = 'value3'; name3 = 'value4'");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        System.out.println(javascript.get("name2"));
    }
}

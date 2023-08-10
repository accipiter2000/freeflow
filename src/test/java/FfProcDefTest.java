import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opendynamic.OdUtils;
import com.opendynamic.ff.service.FfService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class FfProcDefTest {
    @Autowired
    private FfService ffService;

    @Test
    public void deployProcDef() throws Exception {
        InputStream inputStream;
        // inputStream = this.getClass().getResource("/procdef/demo.json").openStream();
        // inputStream = this.getClass().getResource("/procdef/stageDemo.json").openStream();
        // inputStream = this.getClass().getResource("/procdef/completeReturnDemo.json").openStream();
        // inputStream = this.getClass().getResource("/procdef/centerForwardDemo.json").openStream();
        // inputStream = this.getClass().getResource("/procdef/subProcDemo1.json").openStream();
        // inputStream = this.getClass().getResource("/procdef/subProcDemo2.json").openStream();
        // inputStream = this.getClass().getResource("/procdef/subProcDemo3.json").openStream();
        // inputStream = this.getClass().getResource("/procdef/subProcDemo.json").openStream();
        // inputStream = this.getClass().getResource("/procdef/isolateSubProcDemo.json").openStream();
        // inputStream = this.getClass().getResource("/procdef/gatewayDemo.json").openStream();
        inputStream = this.getClass().getResource("/procdef/serviceTaskDemo.json").openStream();
        // inputStream = this.getClass().getResource("/procdef/endDemo.json").openStream();

        String procDef = OdUtils.inputStreamToString(inputStream);
        inputStream.close();

        String procDefId = OdUtils.getUuid();
        ffService.deployProcDef(procDefId, procDef, null, null, 0, null, null);
        System.out.println(procDefId);
    }
}
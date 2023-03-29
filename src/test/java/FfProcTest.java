import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opendynamic.ff.service.FfService;
import com.opendynamic.ff.vo.FfResult;
import com.opendynamic.ff.vo.Proc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class FfProcTest {
    @Autowired
    private FfService ffService;

    @Test
    public void loadProc() throws Exception {
        String procId = "266dd28c8eff43409596f5324f8ca99d";
        Proc proc = ffService.loadProc(procId);
        System.out.println(proc.getCreationDate());
    }

    @Test
    public void createProcQuery() throws Exception {
        List<Proc> procList = ffService.createProcQuery().setProcStatusList(Arrays.asList(FfService.PROC_STATUS_COMPLETE)).queryForObjectList();
        for (Proc proc : procList) {
            System.out.println(proc.getCreationDate());
        }

        System.out.println(ffService.createProcQuery().setProcStatusList(Arrays.asList(FfService.PROC_STATUS_COMPLETE)).count());
    }

    @Test
    public void createInvolvedProcQuery() throws Exception {
        List<Proc> procList = ffService.createInvolvedProcQuery().setAssigneeList(Arrays.asList("z34")).setProcStatusList(Arrays.asList(FfService.PROC_STATUS_COMPLETE)).queryForObjectList();
        for (Proc proc : procList) {
            System.out.println(proc.getCreationDate());
        }

        System.out.println(ffService.createInvolvedProcQuery().setAssigneeList(Arrays.asList("z34")).setProcStatusList(Arrays.asList(FfService.PROC_STATUS_COMPLETE)).count());
    }

    @Test
    public void selectProcByIdList() throws Exception {
        List<Proc> procList = ffService.selectProcByIdList(Arrays.asList("266dd28c8eff43409596f5324f8ca99d", "3017bc554299420d97e91a811c63eef4"));
        for (Proc proc : procList) {
            System.out.println(proc.getCreationDate());
        }
    }

    @Test
    public void updateProcBizInfo() throws Exception {
        String procId = "266dd28c8eff43409596f5324f8ca99d";
        ffService.updateProcBizInfo(procId, "bizId4", "bizType2", "bizCode3", "bizName2");
    }

    @Test
    public void suspendProc() throws Exception {
        String procId = "266dd28c8eff43409596f5324f8ca99d";
        FfResult ffResult = ffService.suspendProc(procId, null, "e");
        System.out.println(ffResult);
    }

    @Test
    public void activateProc() throws Exception {
        String procId = "266dd28c8eff43409596f5324f8ca99d";
        FfResult ffResult = ffService.activateProc(procId, null, "e");
        System.out.println(ffResult);
    }

    @Test
    public void completeProc() throws Exception {
        String procId = "266dd28c8eff43409596f5324f8ca99d";
        FfResult ffResult = ffService.completeProc(procId, null, "e");
        System.out.println(ffResult);
    }

    @Test
    public void terminateProc() throws Exception {
        String procId = "266dd28c8eff43409596f5324f8ca99d";
        FfResult ffResult = ffService.terminateProc(procId, null, "e");
        System.out.println(ffResult);
    }

    @Test
    public void deleteProc() throws Exception {
        String procId = "8bb3abfa0bbe46c0af0e07b6b4420c76";
        FfResult ffResult = ffService.deleteProc(procId, "e");
        System.out.println(ffResult);
    }
}
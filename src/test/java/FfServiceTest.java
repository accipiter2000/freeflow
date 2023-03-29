import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opendynamic.OdUtils;
import com.opendynamic.ff.service.FfService;
import com.opendynamic.ff.vo.FfResult;
import com.opendynamic.ff.vo.ProcDef;
import com.opendynamic.ff.vo.RunningNodeDef;
import com.opendynamic.ff.vo.RunningProcDef;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class FfServiceTest {
    @Autowired
    private FfService ffService;

    @Test
    public void startProcByProcDefCode() throws Exception {
        Map<String, Object> nodeVarMap = new HashMap<>();
        nodeVarMap.put("assignee", "zhang");
        nodeVarMap.put("INIT_COM_ID_", "259");
        nodeVarMap.put("initPosiEmpId", "abcde12345");

        FfResult ffResult = ffService.startProcByProcDefCode("stageDemo", "bizId", "bizType", "bizCode", "bizName", "li", nodeVarMap);

        System.out.println(ffResult);
    }

    @Test
    public void startIsolateSubProc() throws Exception {
        Map<String, Object> nodeVarMap = new HashMap<>();
        nodeVarMap.put("assignee", "zhang");
        nodeVarMap.put("INIT_COM_ID_", "259");
        nodeVarMap.put("initPosiEmpId", "abcde12345");

        FfResult ffResult = ffService.startIsolateSubProc("b90fc95467e34eb78048bb882446d898", "bizId", "bizType", "bizCode", "bizName", "li", nodeVarMap);

        System.out.println(ffResult);
    }

    @Test
    public void completeTask() throws Exception {
        String taskId = "d317059b70fa4bf3acee92ce560667cb";
        Map<String, Object> branchNodeVar = new HashMap<>();
        branchNodeVar.put("assignee", "z3");
        // branchNodeVar.put("STEP", "3");
        FfResult ffResult = ffService.completeTask(taskId, "SYSADMIN", branchNodeVar);

        System.out.println(ffResult);
    }

    @Test
    public void claimTask() throws Exception {
        String taskId = "917650025cff4310849514e6f4e47410";
        FfResult ffResult = ffService.claimTask(taskId, "SYSADMIN");

        System.out.println(ffResult);
    }

    @Test
    public void forwardTask() throws Exception {
        String taskId = "67df3be3993d4e7082a63de9afe4460e";
        List<String> assigneeList = Arrays.asList("z3", "z4");
        // List<String> assigneeList = Arrays.asList("z5");
        String action = "handle${proc.getBizType()}.do?BIZ_ID_=${proc.getBizId()}&TASK_ID_=${task.getTaskId()}";
        Date dueDate = null;
        Integer priority = 5;
        boolean forwarable = false;
        String executor = "SYSADMIN";

        FfResult ffResult = ffService.forwardTask(taskId, assigneeList, action, dueDate, priority, forwarable, executor);
        System.out.println(ffResult);
    }

    @Test
    public void rejectTask() throws Exception {
        String taskId = "52d0bef1873842728bd8afe199c0ddb6";
        String executor = "SYSADMIN";
        FfResult ffResult = ffService.rejectTask(taskId, executor);
        System.out.println(ffResult);
    }

    @Test
    public void rejectTaskToNode() throws Exception {
        String fromTaskId = "fd05099b4204440db98fcc1ae45e77aa";
        String toNodeId = "f7ce8bda91854b96947ed2a042af33da";
        String executor = "SYSADMIN";
        FfResult ffResult = ffService.rejectTaskToNode(fromTaskId, toNodeId, executor);
        System.out.println(ffResult);
    }

    @Test
    public void activateNode() throws Exception {
        String nodeId = "f33fbfe3937747f2ae7f580a4b750a2d";
        String executor = "SYSADMIN";
        FfResult ffResult = ffService.activateNode(nodeId, executor);
        System.out.println(ffResult);
    }

    @Test
    public void insertSubProc() throws Exception {
        String nodeId = "8711b60fba344869b271f12d8e576032";
        String subProcDefCode = "subProcDemo3";
        String executor = "SYSADMIN";
        FfResult ffResult = ffService.insertSubProc(nodeId, subProcDefCode, executor);
        System.out.println(ffResult);
    }

    @Test
    public void deleteSubProc() throws Exception {
        String nodeId = "5fd2114ab76e4b89bdd85ca40e596feb";
        String executor = "SYSADMIN";
        FfResult ffResult = ffService.deleteSubProc(nodeId, executor);
        System.out.println(ffResult);
    }

    @Test
    public void deleteProc() throws Exception {
        String procId = "150d2bbeb51c409b8e6b9685382caed7";
        String executor = "SYSADMIN";
        FfResult ffResult = ffService.deleteProc(procId, executor);
        System.out.println(ffResult);
    }

    @Test
    public void undo() throws Exception {
        String operationId = "e91d72679898468db3e8c1f582e65740";
        FfResult ffResult = ffService.undo(operationId);
        System.out.println(ffResult);
    }

    @Test
    public void getStartRunningNodeDefList() throws Exception {
        ProcDef procDef = ffService.loadProcDefByCode("centerForwardDemo");
        Map<String, Object> nodeVarMap = new HashMap<>();
        nodeVarMap.put("assignee", "assi");
        nodeVarMap.put("initPosiEmpId", "initer");

        List<RunningNodeDef> runningNodeDefList = ffService.getStartRunningNodeDefList(procDef, nodeVarMap);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(runningNodeDefList));
    }

    @Test
    public void getRunningProcDef() throws Exception {
        long startTime = System.currentTimeMillis();
        String procId = "0fc78eb1c2fa4a9fb8b39a7605d202c9";
        String taskId = "67bbb9c1de92425d9a3a878eccdddb66";
        boolean drawOptional = true;
        RunningProcDef runningProcDef = ffService.getRunningProcDef(procId, taskId, drawOptional);

        // gson
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(runningProcDef);

        // jackson
        // ObjectMapper objectMapper = new ObjectMapper();
        // String json = objectMapper.writeValueAsString(runningProcDef);

        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);

        System.out.println(json);
    }

    @Test
    public void getNextRunningNodeDefList() throws Exception {
        String taskId = "e8828c0d205a4630a4c0c5d7cb169521";
        Map<String, Object> nodeVarMap = new HashMap<>();
        nodeVarMap.put("assignee", "assi");
        nodeVarMap.put("initPosiEmpId", "initer");
        nodeVarMap.put("STEP", "3");

        List<RunningNodeDef> runningNodeDefList = ffService.getNextRunningNodeDefList(taskId, nodeVarMap);
        System.out.println(new Gson().toJson(runningNodeDefList));
    }

    @Test
    public void getRunningNodeDef() throws Exception {
        String nodeId = "3799d6fb94994c959e7780f6fe461d54";

        RunningNodeDef runningNodeDef = ffService.getRunningNodeDef(nodeId);
        System.out.println(new Gson().toJson(runningNodeDef));
    }

    @Test
    public void insertDelegate() {
        ffService.insertDelegate(OdUtils.getUuid(), null, null, "SYSADMIN", "系统管理员", null, null);
    }
}
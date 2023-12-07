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
import com.opendynamic.ff.vo.Candidate;
import com.opendynamic.ff.vo.CandidateList;
import com.opendynamic.ff.vo.FfResult;
import com.opendynamic.ff.vo.Operation;
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
        nodeVarMap.put("INIT_COM_ID_", "259");
        nodeVarMap.put("initPosiEmpId", "abcde12345");
        nodeVarMap.put("amountOfMoney", "2000");
        nodeVarMap.put("assignee", "zhang");

        CandidateList candidateList = new CandidateList();
        candidateList.add(new Candidate(null, "departmentLeader", "zhang"));

        FfResult ffResult = ffService.startProcByProcDefCode("nestSubProcDemo", "bizId", "bizType", "bizCode", "bizName", "bizDesc", "li", nodeVarMap, candidateList);

        System.out.println(ffResult);
    }

    @Test
    public void startIsolateSubProc() throws Exception {
        Map<String, Object> nodeVarMap = new HashMap<>();
        nodeVarMap.put("assignee", "zhang");
        nodeVarMap.put("INIT_COM_ID_", "259");
        nodeVarMap.put("initPosiEmpId", "abcde12345");

        FfResult ffResult = ffService.startIsolateSubProc("04ddee031d394751846296963316ba01", "bizId", "bizType", "bizCode", "bizName", "bizDesc", "li", nodeVarMap, null);

        System.out.println(ffResult);
    }

    @Test
    public void startProcToNodeByProcDefCode() throws Exception {
        Map<String, Object> nodeVarMap = new HashMap<>();
        nodeVarMap.put("INIT_COM_ID_", "259");
        nodeVarMap.put("initPosiEmpId", "abcde12345");
        nodeVarMap.put("amountOfMoney", "2000");
        nodeVarMap.put("assignee", "zhang");

        CandidateList candidateList = new CandidateList();
        candidateList.add(new Candidate(null, "departmentLeader", "zhang"));
        candidateList.add(new Candidate(null, "hrLeader", "hrLeader1"));
        candidateList.add(new Candidate(null, "财务审计专员", "se"));

        FfResult ffResult = ffService.startProcToNodeByProcDefCode("subProcDemo", "厂矿内部审批子流程:subProcDemo1", "A厂长审批", "bizId", "bizType", "bizCode", "bizName", "bizDesc", "li", nodeVarMap, candidateList);

        System.out.println(ffResult);
    }

    @Test
    public void completeTask() throws Exception {
        String taskId = "5867aa7b4a384b6798d671cb350c42e4";
        Map<String, Object> branchNodeVar = new HashMap<>();
        // branchNodeVar.put("STEP", "3");

        CandidateList candidateList = new CandidateList();
        // candidateList.add(new Candidate("厂矿内部审批子流程:subProcDemo1", "subDepartmentLeader", "zhang1"));
        // candidateList.add(new Candidate("厂矿内部审批子流程:subProcDemo2", "subDepartmentLeader", "zhang2"));
        // candidateList.add(new Candidate("厂矿内部审批子流程:subProcDemo2", "B厂长审批", "B厂长"));

        //
        // candidateList.add(new Candidate(null, "厂矿内部审批子流程", "subProcDemo1,subProcDemo2"));
        //
        // candidateList.add(new Candidate(null, "departmentLeader", "dl"));
        // candidateList.add(new Candidate(null, "factorys", "subProcDemo1"));
        // candidateList.add(new Candidate(null, "人力资源审批", "wang"));
        // candidateList.add(new Candidate(null, "factory1", "f1"));
        // candidateList.add(new Candidate(null, "factory2", "f2,f22"));
        // candidateList.add(new Candidate(null, "hrLeader", "hr"));
        // candidateList.add(new Candidate(null, "hqLeader", "hq"));
        // candidateList.add(new Candidate(null, "subHrLeader", "subHrLeader22"));
        // candidateList.add(new Candidate(null, "文书", "S2"));
        candidateList.add(new Candidate(null, "集团领导审批", "T"));

        FfResult ffResult = ffService.completeTask(taskId, branchNodeVar, candidateList, "SYSADMIN");

        System.out.println(ffResult);
    }

    @Test
    public void completeTaskToNode() throws Exception {
        String taskId = "aee4259e42a44831b55e0df9af962241";
        Map<String, Object> branchNodeVar = new HashMap<>();
        // branchNodeVar.put("STEP", "3");

        CandidateList candidateList = new CandidateList();
        // candidateList.add(new Candidate("厂矿内部审批子流程:subProcDemo1", "subDepartmentLeader", "zhang1"));
        // candidateList.add(new Candidate("厂矿内部审批子流程:subProcDemo2", "subDepartmentLeader", "zhang2"));
        // candidateList.add(new Candidate("厂矿内部审批子流程:subProcDemo2", "B厂长审批", "B厂长"));

        //
        // candidateList.add(new Candidate(null, "厂矿内部审批子流程", "subProcDemo1,subProcDemo2"));
        //
        // candidateList.add(new Candidate(null, "departmentLeader", "dl"));
        // candidateList.add(new Candidate(null, "factorys", "subProcDemo1"));
        // candidateList.add(new Candidate(null, "人力资源审批", "wang"));
        // candidateList.add(new Candidate(null, "factory1", "f1"));
        // candidateList.add(new Candidate(null, "factory2", "f2,f22"));
        // candidateList.add(new Candidate(null, "hrLeader", "hr"));
        // candidateList.add(new Candidate(null, "hqLeader", "hq"));
        // candidateList.add(new Candidate(null, "subHrLeader", "subHrLeader22"));
        // candidateList.add(new Candidate(null, "文书", "S2"));
        candidateList.add(new Candidate(null, "财务审计专员", "T"));

        FfResult ffResult = ffService.completeTaskToNode(taskId, "厂矿内部审批子流程:subProcDemo3.厂矿内部审批子流程:subProcDemo1", "A设备部长审批", branchNodeVar, candidateList, "SYSADMIN");

        System.out.println(ffResult);
    }

    @Test
    public void appendCandidate() {
        String nodeId = "7cf7eb6289c54a58aba1c3763694cc54";

        CandidateList candidateList = new CandidateList();
        candidateList.add(new Candidate("null", "departmentLeader", "zhang1"));
        candidateList.add(new Candidate(null, "factorys", "subProcDemo2"));
        candidateList.add(new Candidate("subProcDemo2", "subDepartmentLeader", "zhang2"));
        candidateList.add(new Candidate(null, "人力资源审批", "wang2"));

        FfResult ffResult = ffService.appendCandidate(nodeId, candidateList, "SYSADMIN");

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
        String claim = FfService.BOOLEAN_FALSE;
        String forwarable = FfService.BOOLEAN_FALSE;
        Integer priority = 5;
        String executor = "SYSADMIN";

        FfResult ffResult = ffService.forwardTask(taskId, assigneeList, action, dueDate, claim, forwarable, priority, executor);
        System.out.println(ffResult);
    }

    @Test
    public void rejectTask() throws Exception {
        String taskId = "52d0bef1873842728bd8afe199c0ddb6";
        String executor = "SYSADMIN";
        FfResult ffResult = ffService.rejectTask(taskId, null, executor);
        System.out.println(ffResult);
    }

    @Test
    public void rejectTaskToNode() throws Exception {
        String taskId = "f0ee46cff9fb45cfbce45136b5b6e53d";
        // branchNodeVar.put("STEP", "3");

        CandidateList candidateList = new CandidateList();

        candidateList.add(new Candidate(null, "财务审计专员", "T"));

        FfResult ffResult = ffService.rejectTaskToNode(taskId, "厂矿内部审批子流程:subProcDemo3.厂矿内部审批子流程:subProcDemo2", "B财务部长审批", candidateList, "SYSADMIN");

        System.out.println(ffResult);
    }

    @Test
    public void activateNode() throws Exception {
        String nodeId = "f33fbfe3937747f2ae7f580a4b750a2d";
        String executor = "SYSADMIN";
        FfResult ffResult = ffService.activateNode(nodeId, null, executor);
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
        String operationId = "9393267ad50040d78cd96d2a63299d8c";
        FfResult ffResult = ffService.undo(operationId);
        System.out.println(ffResult);
    }

    @Test
    public void getStartRunningNodeDefList() throws Exception {
        ProcDef procDef = ffService.loadProcDefByCode("demo");
        Map<String, Object> nodeVarMap = new HashMap<>();
        nodeVarMap.put("assignee", "assi");
        nodeVarMap.put("initPosiEmpId", "initer");

        List<RunningNodeDef> runningNodeDefList = ffService.getStartRunningNodeDefList(null, procDef, nodeVarMap);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(runningNodeDefList));
    }

    @Test
    public void getRunningProcDef() throws Exception {
        long startTime = System.currentTimeMillis();
        String procId = "44ebfcc770fb4f3e806af87280534f03";
        String taskId = "7ea88adc2ab74151b5eecf09839c4701";
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
        String taskId = "72aa1659f1634ffc93e51dc0470bc720";
        Map<String, Object> nodeVarMap = new HashMap<>();
        nodeVarMap.put("assignee", "assi");
        nodeVarMap.put("initPosiEmpId", "initer");
        nodeVarMap.put("STEP", "3");

        List<RunningNodeDef> runningNodeDefList = ffService.getNextRunningNodeDefList(taskId, nodeVarMap);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(runningNodeDefList));
    }

    @Test
    public void insertDelegate() {
        ffService.insertDelegate(OdUtils.getUuid(), null, null, "SYSADMIN", "系统管理员", null, null);
    }

    @Test
    public void createOperationQuery() throws Exception {
        String procId = "e277901ee99b43469678a7cc6ef7afe0";
        List<Operation> operationList = ffService.createOperationQuery().setProcId(procId).queryForObjectList();

        for (Operation operation : operationList) {
            System.out.println(operation.getNodeId());
        }
    }

}
import java.util.ArrayList;
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
import com.opendynamic.ff.vo.FfUser;
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
        candidateList.add(new Candidate(null, "departmentLeader", Arrays.asList(new FfUser("zhang", null)), null));

        FfResult ffResult = ffService.startProcByProcDefCode("nodeConfigsDemo", "bizId", "bizType", "bizCode", "bizName", "bizDesc", new FfUser("li", "liname"), nodeVarMap, candidateList);

        System.out.println(ffResult);
    }

    @Test
    public void startIsolateSubProc() throws Exception {
        Map<String, Object> nodeVarMap = new HashMap<>();
        nodeVarMap.put("assignee", "zhang");
        nodeVarMap.put("INIT_COM_ID_", "259");
        nodeVarMap.put("initPosiEmpId", "abcde12345");

        FfResult ffResult = ffService.startIsolateSubProc("04ddee031d394751846296963316ba01", "bizId", "bizType", "bizCode", "bizName", "bizDesc", new FfUser("li", null), nodeVarMap, null);

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
        candidateList.add(new Candidate(null, "departmentLeader", Arrays.asList(new FfUser("zhang", null)), null));
        candidateList.add(new Candidate(null, "hrLeader", Arrays.asList(new FfUser("hrLeader1", null)), null));
        candidateList.add(new Candidate(null, "财务审计专员", Arrays.asList(new FfUser("se", null)), null));

        FfResult ffResult = ffService.startProcToNodeByProcDefCode("subProcDemo", "厂矿内部审批子流程:subProcDemo1", "A厂长审批", "bizId", "bizType", "bizCode", "bizName", "bizDesc", new FfUser("li", null), nodeVarMap, candidateList);

        System.out.println(ffResult);
    }

    @Test
    public void completeTask() throws Exception {
        String taskId = "05bf54b844fa4f82be7a2b95252d27f8";
        Map<String, Object> branchNodeVar = new HashMap<>();
        // branchNodeVar.put("STEP", "3");

        CandidateList candidateList = new CandidateList();
        // candidateList.add(new Candidate("厂矿内部审批子流程:subProcDemo1", "subDepartmentLeader", Arrays.asList(new FfUser("zhang1", null)), null));
        // candidateList.add(new Candidate("厂矿内部审批子流程:subProcDemo2", "subDepartmentLeader", Arrays.asList(new FfUser("zhang2", null)), null));
        // candidateList.add(new Candidate("厂矿内部审批子流程:subProcDemo2", "B厂长审批", Arrays.asList(new FfUser("B厂长", null)), null));

        // candidateList.add(new Candidate(null, "厂矿内部审批子流程", null, Arrays.asList("subProcDemo1,subProcDemo2")));

        candidateList.add(new Candidate(null, "候选人", Arrays.asList(new FfUser("b58656cfc219487b86283e7644e58dec", null)), null));
        // candidateList.add(new Candidate(null, "departmentLeader", Arrays.asList(new FfUser("dl", null)), null));
        // candidateList.add(new Candidate(null, "factorys",null, Arrays.asList("subProcDemo1")));
        // candidateList.add(new Candidate(null, "人力资源审批", Arrays.asList(new FfUser("wang", null)), null));
        // candidateList.add(new Candidate(null, "factory1", Arrays.asList(new FfUser("f1", null)), null));
        // candidateList.add(new Candidate(null, "factory2", Arrays.asList(new FfUser("f2", null),new FfUser("f22", null)), null);
        candidateList.add(new Candidate(null, "hrLeader", Arrays.asList(new FfUser("hr", "hrname")), null));
        candidateList.add(new Candidate(null, "companyLeader", Arrays.asList(new FfUser("cl", null)), null));
        // candidateList.add(new Candidate(null, "hqLeader", Arrays.asList(new FfUser("hq", null)), null));
        // candidateList.add(new Candidate(null, "subHrLeader", Arrays.asList(new FfUser("subHrLeader22", null)), null));
        candidateList.add(new Candidate(null, "文书", Arrays.asList(new FfUser("S2", null)), null));
        candidateList.add(new Candidate(null, "集团领导审批", Arrays.asList(new FfUser("T", null)), null));
        candidateList.add(new Candidate(null, "财务审计专员", Arrays.asList(new FfUser("F1", null)), null));

        FfResult ffResult = ffService.completeTask(taskId, branchNodeVar, candidateList, new FfUser("SYSADMIN", "SYSADMIN"));

        System.out.println(ffResult);
    }

    @Test
    public void completeTaskToNode() throws Exception {
        String taskId = "aee4259e42a44831b55e0df9af962241";
        Map<String, Object> branchNodeVar = new HashMap<>();
        // branchNodeVar.put("STEP", "3");

        CandidateList candidateList = new CandidateList();
        // candidateList.add(new Candidate("厂矿内部审批子流程:subProcDemo1", "subDepartmentLeader", Arrays.asList(new FfUser("zhang1", null)), null));
        // candidateList.add(new Candidate("厂矿内部审批子流程:subProcDemo2", "subDepartmentLeader", Arrays.asList(new FfUser("zhang2", null)), null));
        // candidateList.add(new Candidate("厂矿内部审批子流程:subProcDemo2", "B厂长审批", Arrays.asList(new FfUser("B厂长", null)), null));

        //
        // candidateList.add(new Candidate(null, "厂矿内部审批子流程", null, Arrays.asList("subProcDemo1", "subProcDemo2")));
        //
        // candidateList.add(new Candidate(null, "departmentLeader", Arrays.asList(new FfUser("dl", null)), null));
        // candidateList.add(new Candidate(null, "factorys", null, Arrays.asList("subProcDemo1")));
        // candidateList.add(new Candidate(null, "人力资源审批", Arrays.asList(new FfUser("wang", null)), null));
        // candidateList.add(new Candidate(null, "factory1", Arrays.asList(new FfUser("f1", null)), null));
        // candidateList.add(new Candidate(null, "factory2", Arrays.asList(new FfUser("f2", null), new FfUser("f22", null)), null));
        // candidateList.add(new Candidate(null, "hrLeader", Arrays.asList(new FfUser("hr", null)), null));
        // candidateList.add(new Candidate(null, "hqLeader", Arrays.asList(new FfUser("hq", null)), null));
        // candidateList.add(new Candidate(null, "subHrLeader", Arrays.asList(new FfUser("subHrLeader22", null)), null));
        // candidateList.add(new Candidate(null, "文书", Arrays.asList(new FfUser("S2", null)), null));
        candidateList.add(new Candidate(null, "财务审计专员", Arrays.asList(new FfUser("T", null)), null));

        FfResult ffResult = ffService.completeTaskToNode(taskId, "厂矿内部审批子流程:subProcDemo3.厂矿内部审批子流程:subProcDemo1", "A设备部长审批", branchNodeVar, candidateList, new FfUser("SYSADMIN", "SYSADMIN"));

        System.out.println(ffResult);
    }

    @Test
    public void appendCandidate() {
        String nodeId = "7cf7eb6289c54a58aba1c3763694cc54";

        CandidateList candidateList = new CandidateList();
        // candidateList.add(new Candidate("null", "departmentLeader", Arrays.asList(new FfUser("zhang1", null)), null));
        // candidateList.add(new Candidate(null, "factorys", null, Arrays.asList("subProcDemo2")));
        // candidateList.add(new Candidate("subProcDemo2", "subDepartmentLeader", Arrays.asList(new FfUser("zhang2", null)), null));
        candidateList.add(new Candidate(null, "人力资源审批", Arrays.asList(new FfUser("wang2", null)), null));

        FfResult ffResult = ffService.appendCandidate(nodeId, candidateList, new FfUser("SYSADMIN", "SYSADMIN"));

        System.out.println(ffResult);
    }

    @Test
    public void claimTask() throws Exception {
        String taskId = "917650025cff4310849514e6f4e47410";
        FfResult ffResult = ffService.claimTask(taskId, new FfUser("SYSADMIN", "SYSADMIN"));

        System.out.println(ffResult);
    }

    @Test
    public void forwardTask() throws Exception {
        String taskId = "67df3be3993d4e7082a63de9afe4460e";
        List<FfUser> assigneeList = new ArrayList<FfUser>();
        assigneeList.add(new FfUser("z3", "z3"));
        assigneeList.add(new FfUser("z4", "z4"));
        // assigneeList.add(new FfUser("z5", "z5"));
        String action = "handle${proc.getBizType()}.do?BIZ_ID_=${proc.getBizId()}&TASK_ID_=${task.getTaskId()}";
        Date dueDate = null;
        String claim = FfService.BOOLEAN_FALSE;
        String forwarable = FfService.BOOLEAN_FALSE;
        Integer priority = 5;

        FfResult ffResult = ffService.forwardTask(taskId, assigneeList, action, dueDate, claim, forwarable, priority, new FfUser("SYSADMIN", "SYSADMIN"));
        System.out.println(ffResult);
    }

    @Test
    public void rejectTask() throws Exception {
        String taskId = "32baaab0ef3a41de885e8c7ab227b314";
        FfResult ffResult = ffService.rejectTask(taskId, null, new FfUser("SYSADMIN", "SYSADMIN"));
        System.out.println(ffResult);
    }

    @Test
    public void rejectTaskToNode() throws Exception {
        String taskId = "f0ee46cff9fb45cfbce45136b5b6e53d";
        // branchNodeVar.put("STEP", "3");

        CandidateList candidateList = new CandidateList();

        candidateList.add(new Candidate(null, "财务审计专员", Arrays.asList(new FfUser("T", null)), null));

        FfResult ffResult = ffService.rejectTaskToNode(taskId, "厂矿内部审批子流程:subProcDemo3.厂矿内部审批子流程:subProcDemo2", "B财务部长审批", candidateList, new FfUser("SYSADMIN", "SYSADMIN"));

        System.out.println(ffResult);
    }

    @Test
    public void activateNode() throws Exception {
        String nodeId = "f33fbfe3937747f2ae7f580a4b750a2d";
        FfResult ffResult = ffService.activateNode(nodeId, null, new FfUser("SYSADMIN", "SYSADMIN"));
        System.out.println(ffResult);
    }

    @Test
    public void deleteProc() throws Exception {
        String procId = "150d2bbeb51c409b8e6b9685382caed7";
        FfResult ffResult = ffService.deleteProc(procId, new FfUser("SYSADMIN", "SYSADMIN"));
        System.out.println(ffResult);
    }

    @Test
    public void undo() throws Exception {
        String operationId = "be76b982f7f24de29a353301ba846c44";
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

    @Test
    public void createProcDefQuery() throws Exception {
        List<ProcDef> procDefQueryList = ffService.createProcDefQuery().setExtAttr1("aa").queryForObjectList();

        for (ProcDef procDef : procDefQueryList) {
            System.out.println(procDef.getProcDefCode());
            System.out.println(procDef.getVersion());
        }
    }
}
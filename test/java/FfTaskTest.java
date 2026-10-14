import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opendynamic.OdUtils;
import com.opendynamic.ff.service.FfService;
import com.opendynamic.ff.vo.FfResult;
import com.opendynamic.ff.vo.FfUser;
import com.opendynamic.ff.vo.Task;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class FfTaskTest {
    @Autowired
    private FfService ffService;

    @Test
    public void loadTask() throws Exception {
        String taskId = "e2390b0f76284e45b9f1d1a3f3c55e3f";
        Task task = ffService.loadTask(taskId);
        System.out.println(task.getCreationDate());
    }

    @Test
    public void createTaskQuery() throws Exception {
        List<Task> taskList = ffService.createTaskQuery().setProcId("43cf7729c2524288862a4fa5d31905fe").setTaskStatusList(Arrays.asList(FfService.TASK_STATUS_ACTIVE, FfService.TASK_STATUS_SUSPEND)).setDataScope(FfService.DATA_SCOPE_PROC).queryForObjectList();
        for (Task task : taskList) {
            System.out.println(task.getCreationDate());
        }

        System.out.println(ffService.createTaskQuery().setTaskStatusList(Arrays.asList(FfService.TASK_STATUS_ACTIVE)).count());
    }

    @Test
    public void insertTask() throws Exception {
        String taskId = OdUtils.getUuid();

        Task task = ffService.loadTask("9b77793374584e4294f8358c454166f6");
        task.setTaskId(taskId);
        task.setAssignee("plus");
        task.setCreationDate(new Date());
        ffService.insertTask(task, new FfUser("exec", null));
    }

    @Test
    public void updateTaskAssignee() throws Exception {
        String taskId = "b02f63275fa14dbea97e33a042c3a6a5";
        ffService.updateTaskAssignee(taskId, new FfUser("e", "力"), new FfUser("exec", null));
    }

    @Test
    public void suspendTask() throws Exception {
        String taskId = "60d2e03c41404e3ebfdb7fc731eedf10";
        FfResult ffResult = ffService.suspendTask(taskId, new FfUser("e", null));
        System.out.println(ffResult);
    }

    @Test
    public void activateTask() throws Exception {
        String taskId = "60d2e03c41404e3ebfdb7fc731eedf10";
        FfResult ffResult = ffService.activateTask(taskId, new FfUser("e", null));
        System.out.println(ffResult);
    }

    @Test
    public void terminateTask() throws Exception {
        String taskId = "60d2e03c41404e3ebfdb7fc731eedf10";
        FfResult ffResult = ffService.terminateTask(taskId, new FfUser("e", null));
        System.out.println(ffResult);
    }

    @Test
    public void deleteTask() throws Exception {
        String taskId = "60d2e03c41404e3ebfdb7fc731eedf10";
        FfResult ffResult = ffService.deleteTask(taskId, new FfUser("e", null));
        System.out.println(ffResult);
    }

    @Test
    public void completeTask() throws Exception {
        String taskId = "569e82bd9a004f10ab7690fd1c6f9a25";
        FfResult ffResult = ffService.completeTask(taskId, null, null, new FfUser("e", null));
        System.out.println(ffResult);
    }

    @Test
    public void forwardTask() throws Exception {
        String taskId = "ab7399d31d5646eeb702d27c79c0d7e7";
        List<FfUser> assigneeList = new ArrayList<FfUser>();
        assigneeList.add(new FfUser("1", "1"));
        assigneeList.add(new FfUser("2", "2"));
        FfResult ffResult = ffService.forwardTask(taskId, assigneeList, "action", null, FfService.BOOLEAN_FALSE, FfService.BOOLEAN_FALSE, 5, new FfUser("exec", null));
        System.out.println(ffResult);
    }

    @Test
    public void rejectTask() throws Exception {
        String taskId = "68db8c1015444e4d9d6586e22441c14d";
        FfResult ffResult = ffService.rejectTask(taskId, null, new FfUser("e", null));
        System.out.println(ffResult);
    }

    @Test
    public void test() throws Exception {
        String taskId = "";
        String[] split = taskId.split(",");
        System.out.println(split.length);
    }
}
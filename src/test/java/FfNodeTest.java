import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opendynamic.ff.query.ChildNodeQuery;
import com.opendynamic.ff.query.ParentNodeQuery;
import com.opendynamic.ff.service.FfService;
import com.opendynamic.ff.vo.FfResult;
import com.opendynamic.ff.vo.Node;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class FfNodeTest {
    @Autowired
    private FfService ffService;

    @Test
    public void loadNode() throws Exception {
        String nodeId = "ff6909635aed4f0781cdb1597c4a7a26";
        Node node = ffService.loadNode(nodeId);
        System.out.println(node.getCreationDate());
    }

    @Test
    public void createNodeQuery() throws Exception {
        List<Node> nodeList = ffService.createNodeQuery().setProcId("42648e28e9414965b36a1f5727b5fb0c").queryForObjectList();
        for (Node node : nodeList) {
            System.out.println(node.getCreationDate());
        }
        System.out.println(ffService.createNodeQuery().setProcId("42648e28e9414965b36a1f5727b5fb0c").count());

        Node node = ffService.createNodeQuery().setProcId("42648e28e9414965b36a1f5727b5fb0c").setNodeTypeList(Arrays.asList(FfService.NODE_TYPE_BRANCH)).queryForObject();
        System.out.println(node.getCreationDate());
    }

    @Test
    public void createParentNodeQuery() throws Exception {
        String nodeId = "b98c2598b5c4479e8bb4cbc4bdd1156f";
        ParentNodeQuery parentNodeQuery = ffService.createParentNodeQuery().setNodeId(nodeId);
        System.out.println(parentNodeQuery.queryForObject().getNodeId());

        parentNodeQuery.setIncludeSelf(true);
        parentNodeQuery.setRecursive(true);
        List<Node> nodeList = parentNodeQuery.queryForObjectList();
        for (Node node : nodeList) {
            System.out.println(node.getNodeId());
        }

        System.out.println(parentNodeQuery.count());
    }

    @Test
    public void createChildNodeQuery() throws Exception {
        String nodeId = "3017bc554299420d97e91a811c63eef4";
        ChildNodeQuery childNodeQuery = ffService.createChildNodeQuery().setNodeId(nodeId).setNodeTypeList(Arrays.asList(FfService.NODE_TYPE_SUB_PROC));
        System.out.println(childNodeQuery.queryForObject().getNodeId());

        childNodeQuery.setNodeTypeList(null);
        childNodeQuery.setIncludeSelf(true);
        childNodeQuery.setRecursive(true);
        List<Node> nodeList = childNodeQuery.queryForObjectList();
        for (Node node : nodeList) {
            System.out.println(node.getNodeId());
        }

        System.out.println(childNodeQuery.count());
    }

    @Test
    public void selectNodeByIdList() throws Exception {
        List<Node> nodeList = ffService.selectNodeByIdList(Arrays.asList("00e04b46546d4e46983a16e0d4684608", "a190f57a2ec74e8a9eb0e64384172050"));
        for (Node node : nodeList) {
            System.out.println(node.getCreationDate());
        }
    }

    @Test
    public void activateNode() throws Exception {
        String nodeId = "0e825f821ebe4faeb3ac36f082a0bf2d";
        FfResult ffResult = ffService.activateNode(nodeId, "exe");
        System.out.println(ffResult);
    }

    @Test
    public void completeNode() throws Exception {
        String nodeId = "0e825f821ebe4faeb3ac36f082a0bf2d";
        FfResult ffResult = ffService.completeNode(nodeId, "exe");
        System.out.println(ffResult);
    }

    @Test
    public void terminateNode() throws Exception {
        String nodeId = "3bb986b35e1e4ae29291681e6fe0a0d1";
        FfResult ffResult = ffService.terminateNode(nodeId, "exe");
        System.out.println(ffResult);
    }
}

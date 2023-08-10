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
        List<Node> nodeList = ffService.createNodeQuery().setProcId("a8303781041e44c0b18d7d248e29b529").queryForObjectList();
        for (Node node : nodeList) {
            System.out.println(node.getCreationDate());
        }
        System.out.println(ffService.createNodeQuery().setProcId("a8303781041e44c0b18d7d248e29b529").count());

        Node node = ffService.createNodeQuery().setProcId("a8303781041e44c0b18d7d248e29b529").setNodeType(FfService.NODE_TYPE_BRANCH).queryForObject();
        System.out.println(node.getCreationDate());
    }

    @Test
    public void createParentNodeQuery() throws Exception {
        String nodeId = "d7e4800ea6f948c7bac57fdd9e4cbfee";
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
        String nodeId = "d7e4800ea6f948c7bac57fdd9e4cbfee";
        ChildNodeQuery childNodeQuery = ffService.createChildNodeQuery().setNodeId(nodeId).setNodeTypeList(Arrays.asList(FfService.NODE_TYPE_SUB_PROC));
        System.out.println(childNodeQuery.queryForObject());

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

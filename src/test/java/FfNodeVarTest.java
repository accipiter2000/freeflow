import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opendynamic.OdUtils;
import com.opendynamic.ff.query.NodeVarQuery;
import com.opendynamic.ff.service.FfService;
import com.opendynamic.ff.vo.NodeVar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class FfNodeVarTest {
    @Autowired
    private FfService ffService;

    @Test
    public void loadNodeVar() throws Exception {
        String nodeVarId = "ddf92d2fadad4bf4b37deb698677e000";
        NodeVar nodeVar = ffService.loadNodeVar(nodeVarId);
        System.out.println(nodeVar.getCreationDate());
    }

    @Test
    public void createNodeVarQuery() throws Exception {
        NodeVarQuery nodeVarQuery = ffService.createNodeVarQuery().setNodeId("18f6467de2e549d1be91ef83fdd0fa0c");

        for (NodeVar nodeVar : nodeVarQuery.queryForObjectList()) {
            System.out.println(nodeVar.getCreationDate());
        }
        System.out.println(nodeVarQuery.count());
        System.out.println(nodeVarQuery.queryForMap());
    }

    @Test
    public void selectNodeVarByIdList() throws Exception {
        List<NodeVar> nodeVarList = ffService.selectNodeVarByIdList(Arrays.asList("c8455c9652894d4b8d7f7721d897efd8", "905ebc951f2d479c94b3c5755a4f6bc5"));
        for (NodeVar nodeVar : nodeVarList) {
            System.out.println(nodeVar.getCreationDate());
        }
    }

    @Test
    public void insertNodeVar() throws Exception {
        String nodeVarId = "c8455c9652894d4b8d7f7721d897efd8";

        NodeVar nodeVar = ffService.loadNodeVar(nodeVarId);
        nodeVar.setNodeVarId(OdUtils.getUuid());
        nodeVar.setVarType(FfService.VAR_TYPE_OBJECT);
        nodeVar.setValue(null);
        nodeVar.setObj(new Integer(5));

        ffService.insertNodeVar(nodeVar);
    }

    @Test
    public void updateNodeVar() throws Exception {
        String nodeVarId = "46ca50de19d9400d82c97b6106e509fa";

        NodeVar nodeVar = ffService.loadNodeVar(nodeVarId);
        nodeVar.setObj(new Integer(9));

        ffService.updateNodeVar(nodeVar);
    }

    @Test
    public void updateNodeVar2() throws Exception {
        String nodeId = "18f6467de2e549d1be91ef83fdd0fa0c";

        Map<String, Object> nodeVarMap = new HashMap<String, Object>();
        nodeVarMap.put("STEP", "3");
        nodeVarMap.put("new", "vv");
        List<String> list = ffService.updateNodeVar(nodeId, nodeVarMap);
        for (String string : list) {
            System.out.println(string);
        }
    }

    @Test
    public void deleteNodeVar() throws Exception {
        String nodeVarId = "5a1be188428444fa8f150a03a4541980";

        ffService.deleteNodeVar(nodeVarId);
    }

    @Test
    public void deleteNodeVarByNodeId() throws Exception {
        String nodeId = "0c77ed60a3734a11bcd2a1cdf3f898ea";

        ffService.deleteNodeVarByNodeId(nodeId);
    }
}

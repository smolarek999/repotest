/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.jsf;

import edu.agh.repotest.dao.TestGroup;
import edu.agh.repotest.session.TestGroupFacade;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "testTreeController")
@ViewScoped
public class TestTreeController implements Serializable {

    private TreeNode root;
    private TreeNode selectedNode;
    private static final TestGroup DUMMY_CHILD = new TestGroup();
    private static final NodeDecorator NOTHING_TODO_DECORATOR = new NodeDecorator() {
        @Override
        public void decorateNode(TreeNode treeNode) {
            // intentionally left empty
        }
    };
    private static final NodeDecorator ADD_DUMMY_NODE_DECORATOR = new NodeDecorator() {
        @Override
        public void decorateNode(TreeNode treeNode) {
            new DefaultTreeNode(DUMMY_CHILD, treeNode);
        }
    };
    @EJB
    private TestGroupFacade ejbFacade;

    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("Root", null);

        Collection<TestGroup> firstLevelNodes = ejbFacade.getAllFirstLevel();
        createChildrenOfNode(ejbFacade.getAllFirstLevel(), root, ADD_DUMMY_NODE_DECORATOR);


    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void onNodeExpand(NodeExpandEvent event) {
        final TreeNode treeNode = event.getTreeNode();
        if (haveOnlyDummyChildren(treeNode)) {
            TestGroup group = (TestGroup) treeNode.getData();
            treeNode.getChildren().clear();
            createChildrenOfNode(ejbFacade.getByParent(group.getIdTestGroup()), treeNode, ADD_DUMMY_NODE_DECORATOR);
            

        }else{
            System.err.print("No only dummy");
        }
    }

    public void onNodeCollapse(NodeCollapseEvent event) {
    }

    public boolean haveOnlyDummyChildren(TreeNode node) {
        return node.getChildCount() == 1 && node.getChildren().get(0).getData() == DUMMY_CHILD;
    }

    public void createChildrenOfNode(Collection<TestGroup> children, TreeNode treeNode) {
        createChildrenOfNode(children, treeNode, NOTHING_TODO_DECORATOR);
    }

    public void createChildrenOfNode(Collection<TestGroup> children, TreeNode treeNode, NodeDecorator decorator) {
        for (TestGroup child : children) {
            DefaultTreeNode childNode = new DefaultTreeNode(child, treeNode);
            decorator.decorateNode(childNode);
        }
    }

    interface NodeDecorator {
        void decorateNode(TreeNode treeNode);
    }
}

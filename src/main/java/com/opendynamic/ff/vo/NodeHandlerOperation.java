package com.opendynamic.ff.vo;

import java.io.Serializable;

/**
 * 节点处理器操作。
 */
public class NodeHandlerOperation implements Serializable {
    private static final long serialVersionUID = 1L;

    private String operation;// 操作。
    private Node node;// 节点。

    public NodeHandlerOperation() {
        super();
    }

    public NodeHandlerOperation(String operation, Node node) {
        super();
        this.operation = operation;
        this.node = node;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
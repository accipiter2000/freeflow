package com.opendynamic.ff.vo;

public class ThreadOperation {
    private String operationId;
    private int order;

    public ThreadOperation(String operationId) {
        this.operationId = operationId;
    }

    /**
     * 获取本线程内操作ID。
     * 
     * @return
     */
    public String getOperationId() {
        return operationId;
    }

    /**
     * 获取本线程内下一个操作步骤序号。
     * 
     * @return
     */
    public int getNextOrder() {
        return order++;
    }
}

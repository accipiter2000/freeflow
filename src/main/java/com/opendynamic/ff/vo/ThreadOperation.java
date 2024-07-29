package com.opendynamic.ff.vo;

/**
 * 线程操作。记录每个线程的流程操作，收集必要的历史数据用于操作取消。
 */
public class ThreadOperation {
    private final String operationId;// 操作ID。
    private int order;// 操作顺序。

    public ThreadOperation(String operationId) {
        this.operationId = operationId;
    }

    /**
     * 获取本线程内的操作ID。
     * 
     * @return 操作ID。
     */
    public String getOperationId() {
        return operationId;
    }

    /**
     * 获取本线程内的下一个操作顺序。
     * 
     * @return 下一个操作顺序。
     */
    public int getNextOrder() {
        return order++;
    }
}
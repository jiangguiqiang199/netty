package com.carrot.pool.obj;

import lombok.Data;

/**
 * 需要池化的对象
 * @Author: Created by carrot
 * 2020/11/24 10:15
 */
@Data
public class Resource {

    private static int id;
    private int rid;

    public Resource() {
        synchronized (this) {
            this.rid = id++;
        }
    }

    public int getRid() {
        return this.rid;
    }

    @Override
    public String toString() {
        return "id:" + this.rid;
    }
}

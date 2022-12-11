package com.tianxing.dr.byteBuddy;

/**
 * @author tianxing
 */
public class AgentImpl implements Agent{
    @Override
    public String hello(String s) {
        return "AgentImpl";
    }
}

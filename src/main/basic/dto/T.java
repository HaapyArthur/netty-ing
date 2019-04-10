package main.basic.dto;

import org.apache.tools.ant.taskdefs.Echo;

/**
 * @author wangyoucai
 * @date 2019/2/27
 */
public class T {

    public static void main(String[] args) {
        EchoRequest request = new EchoRequest();
        request.setEmail("123");
        request.setMessage("message");
    }
}

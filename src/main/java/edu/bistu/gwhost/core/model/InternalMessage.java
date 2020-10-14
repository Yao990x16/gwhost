package edu.bistu.gwhost.core.model;

public class InternalMessage
{
    /**
     * 服务器内部消息类
     * 各线程服务接收并处理此类消息
     */

    private Integer type;   //消息类型（规定此变量为INT最小值时表示服务终止信号）

    private ClientMessage clientMessage;    //客户端消息

    private Object attachment;  //消息附件，是否使用及类型由各线程服务决定

    private static InternalMessage shutdownSignal;  //终止信号（只创建一个对象）

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public ClientMessage getClientMessage() {
        return clientMessage;
    }

    public void setClientMessage(ClientMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    public Object getAttachment() {
        return attachment;
    }

    public void setAttachment(Object attachment) {
        this.attachment = attachment;
    }

    public static InternalMessage getShutdownSignal()
    {
        if(shutdownSignal == null)
        {
            shutdownSignal = new InternalMessage();
            shutdownSignal.setType(Integer.MIN_VALUE);
        }
        return shutdownSignal;
    }
}

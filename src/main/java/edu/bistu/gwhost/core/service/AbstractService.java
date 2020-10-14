package edu.bistu.gwhost.core.service;

import edu.bistu.gwhost.core.GuessWhat;
import edu.bistu.gwhost.core.model.ClientMessage;
import edu.bistu.gwhost.core.model.InternalMessage;

import java.nio.channels.SocketChannel;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public abstract class AbstractService implements Runnable
{
    /**
     * 线程服务抽象类
     * 在此类中定义服务类的通用结构
     */

    private GuessWhat master;   //管理类

    private Integer serviceNumber;

    private LinkedBlockingQueue<InternalMessage> messageQueue;  //消息队列，接收内部消息对象

    private boolean keepRunning;

    protected AbstractService(GuessWhat master, Integer serviceNumber)
    {
        this.master = master;
        this.serviceNumber = serviceNumber;
        messageQueue = new LinkedBlockingQueue<>();
        keepRunning = true;
    }

    public void receiveMessage(InternalMessage message)
    {
        if(message == null)
        {
            System.out.println("线程服务【" + getClass().getName() + "】接收到一条空消息" );
            return;
        }
        messageQueue.add(message);
    }

    protected abstract void initialize();   //初始化线程服务

    protected void handleMessage(InternalMessage internalMessage)
    {
        /**
         *  在此处理各种消息，是线程服务的核心部分
         */

        /* 判断终止信号，由于全局仅一个终止信号对象，因此直接调用equals()方法进行比对 */
        if(internalMessage.equals(InternalMessage.getShutdownSignal()))
            keepRunning = false;
    }

    protected abstract void onStop();   //线程服务结束时调用此方法

    /* 由消息接收器器调用此方法，用来根据不同服务的需求封装客户端消息 */
    protected abstract InternalMessage wrapMessage(ClientMessage clientMessage, SocketChannel socketChannel);

    @Override
    public void run()
    {
        System.out.println("线程服务【" + getClass().getName() +"】启动");

        initialize();

        while(keepRunning)
        {
            try
            {
                InternalMessage internalMessage = messageQueue.poll(2, TimeUnit.SECONDS);   //阻塞2秒
                if(internalMessage != null)
                    handleMessage(internalMessage);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
                keepRunning = false;
                master.serviceCrash(this);
            }
        }

        onStop();
        System.out.println("线程服务【" + getClass().getName() +"】结束");
    }
}

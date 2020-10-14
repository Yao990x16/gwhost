package edu.bistu.gwhost.core;

import edu.bistu.gwhost.core.service.AbstractService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuessWhat
{
    /**
     * GuessWhat对战系统的总管理类
     * 初始化系统各子线程及对象
     * 线程池的创建及管理
     */

    private ExecutorService serviceThreadPool;

    private final int serviceCount = 1; //线程服务的数量

    public void initialize()
    {
        /**
         * 初始化GuessWhat对战系统
         */

        serviceThreadPool = Executors.newFixedThreadPool(serviceCount);
    }

    public synchronized void serviceCrash(AbstractService service)
    {
        /**
         * 当线程服务抛出不可完全避免的异常时（如InterruptedException）调用此方法重启服务
         * 由各服务线程同步调用此方法
         */

        System.out.println("线程服务【" + getClass().getName() +"】崩溃，需要重启");
    }
}

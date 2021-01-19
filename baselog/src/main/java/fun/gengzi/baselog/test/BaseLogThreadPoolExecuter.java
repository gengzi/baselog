package fun.gengzi.baselog.test;

import fun.gengzi.baselog.instrument.execute.MDCInheritableThreadLocal;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class BaseLogThreadPoolExecuter  extends ThreadPoolExecutor {


//    public BaseLogThreadPoolExecuter(ThreadPoolExecutor threadPoolExecutor){
//        threadPoolExecutor.
//        super(threadPoolExecutor.getCorePoolSize(),threadPoolExecutor.getMaximumPoolSize(),threadPoolExecutor.getKeepAliveTime());
//
//
//    }

    public BaseLogThreadPoolExecuter(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {

        MDC.setContextMap((Map<String, String>) MDCInheritableThreadLocal.get());
        super.beforeExecute(t, r);
    }


    @Override
    public void execute(Runnable command) {
        super.execute(command);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
    }

    @Override
    protected void terminated() {
        super.terminated();
    }
}

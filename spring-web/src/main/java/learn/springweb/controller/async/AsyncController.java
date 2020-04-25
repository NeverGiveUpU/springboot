package learn.springweb.controller.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

/**
 * controller，同一时间有多少个请求，就有多少个nio线程启动，响应速度不变，只是返回异步结果。
 * Task只会启动一个线程来进行异步任务。防止请求数过多导致启动太多线程。
 */
@Slf4j
@RestController
public class AsyncController {
    @Autowired
    RequestQueue queue;
    @Autowired
    AsyncTask asyncTask;


    /**
     * 异步调用restful
     * 当controller返回值是Callable的时候，springmvc就会启动一个线程将Callable交给TaskExecutor去处理
     * 然后DispatcherServlet还有所有的spring拦截器都退出主线程，然后把response保持打开的状态
     * 当Callable执行结束之后，springmvc就会重新启动分配一个request请求，然后DispatcherServlet就重新
     * 调用和处理Callable异步执行的返回结果， 然后返回视图
     *
     * @return
     */
    @GetMapping("/callable")
    public Callable<String> callable() {
        log.info(Thread.currentThread().getName() + "收到请求");
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info(Thread.currentThread().getName() + "进入call方法");
                //模拟耗时的业务
                Thread.sleep(2000);
                //在这里调用service方法实现业务，然后返回给前端数据
                return "业务返回结果";
            }
        };
        log.info(Thread.currentThread().getName() + "退出controller");
        return callable;
    }

    /**
     * 这种方式和上面的callable方式最大的区别就是，WebAsyncTask支持超时，
     * 并且还提供了两个回调函数，分别是onCompletion和onTimeout，
     * 顾名思义，这两个回调函数分别在执行完成和超时的时候回调。
     * @return
     */
    @GetMapping("/webasynctask")
    public WebAsyncTask<String> webAsyncTask() {
        log.info(Thread.currentThread().getName() + "收到请求");
        WebAsyncTask<String> webAsyncTask = new WebAsyncTask<>(3000, new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info(Thread.currentThread().getName() + "进入call方法");
                //模拟耗时的业务
                Thread.sleep(2000);
                //在这里调用service方法实现业务，然后返回给前端数据
                return "业务返回结果";
            }
        });
        webAsyncTask.onCompletion(new Runnable() {
            @Override
            public void run() {
                log.info(Thread.currentThread().getName() + "执行完毕");
            }
        });
        webAsyncTask.onTimeout(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info(Thread.currentThread().getName() + "执行超时");
                // 超时的时候，直接抛异常，让外层统一处理超时异常
                throw new TimeoutException("执行超时");
            }
        });
        return webAsyncTask;
    }

    @GetMapping("/deferredresult")
    public DeferredResult<String> deferredResult() {
        log.info(Thread.currentThread().getName() + "收到请求");
        DeferredResult<String> deferredResult = new DeferredResult<>();
        asyncTask.execute(deferredResult);
        //处理完成回调，无论是成功还是超时都会进入
        deferredResult.onCompletion(new Runnable() {
            @Override
            public void run() {
                log.info(Thread.currentThread().getName() + "DeferredResult完成");
            }
        });
        //超时回调
        deferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                log.info(Thread.currentThread().getName() + "DeferredResult超时");
            }
        });
        return deferredResult;
    }

    @GetMapping("/deferredresult/queue")
    public DeferredResult<Object> order(String number) throws InterruptedException {
        log.info(Thread.currentThread().getName() + "收到请求");
        log.info("当前待处理请求数: " + queue.getQueue().size());

        AsyncVO<String, Object> vo = new AsyncVO<>();
        DeferredResult<Object> result = new DeferredResult<>();
        vo.setParams(number);
        vo.setResult(result);

        queue.getQueue().put(vo);
        log.info(Thread.currentThread().getName() + "响应请求");
        return result;
    }
}

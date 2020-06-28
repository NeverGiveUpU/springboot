package learn.springweb.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class QueueListener {
    @Autowired
    private Task task;

    /**
     * 初始化容器时，启动监听请求队列
     */
    @PostConstruct
    public void init() {
        task.start();
    }

    /**
     * 销毁容器时，停止监听任务
     */
    @PreDestroy
    public void destroy() {
        task.setRunning(false);
    }
}

package learn.springweb.async;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Task extends Thread {

    @Autowired
    private RequestQueue queue;
    @Setter
    private boolean running = true;

    @Override
    public void run() {
        while (running) {
            try {
                AsyncVO<String, Object> vo = queue.getQueue().take();
                System.out.println(Thread.currentThread().getName() + "开始处理Task");
                String params = vo.getParams();
                //模拟该线程运行3秒
                Thread.sleep(3000);
                //处理结束，将结果存储到DeferredResult
                Map<String, Object> map = new HashMap<>();
                map.put("params", params);
                map.put("time", System.currentTimeMillis());
                vo.getResult().setResult(map);
                System.out.println(Thread.currentThread().getName() + "完成处理Task");
            } catch (InterruptedException e) {
                e.printStackTrace();
                running = false;
            }
        }
    }
}

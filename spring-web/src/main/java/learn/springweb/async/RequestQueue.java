package learn.springweb.async;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Component
public class RequestQueue {
    @Getter
    private BlockingQueue<AsyncVO<String, Object>> queue = new LinkedBlockingDeque<>(5);
}

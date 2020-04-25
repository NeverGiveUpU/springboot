package learn.springcommon.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class AutowireDemo {

    @Autowired
    DemoService serviceImpl1;

    public void show() {
        serviceImpl1.display();
    }

    static interface DemoService {
        void display();
    }

    @Service("demoService")
    static class ServiceImpl implements DemoService {
        @Override
        public void display() {
            System.out.println("demoService");
        }
    }

    @Service("serviceImpl1")
    static class ServiceImpl1 implements DemoService {
        @Override
        public void display() {
            System.out.println("serviceImpl1");
        }
    }

    @Service("serviceImpl2")
    static class ServiceImpl2 implements DemoService {
        @Override
        public void display() {
            System.out.println("serviceImpl2");
        }
    }
}

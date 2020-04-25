package learn.nosql;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * 测试Ehcache缓存
 */
@SpringBootTest
public class EhcacheTest {
    
    @Test
    public void test1() throws IOException {
        // 1. 创建缓存管理器
        Resource resource = new ClassPathResource("ehcache.xml");
        CacheManager cacheManager = CacheManager.create(resource.getURL());
        
        // 2. 获取缓存对象
        Cache cache = cacheManager.getCache("ehCache");
        
        // 3. 创建元素
        Element element = new Element("key1", "value1");
        
        // 4. 将元素添加到缓存
        cache.put(element);
        
        // 5. 获取缓存
        Element value = cache.get("key1");
        System.out.println(value);
        System.out.println(value.getObjectValue());
        
        // 6. 删除元素
        cache.remove("key1");
        
        Element pelement = new Element("xm", "小明");
        cache.put(pelement);
        Element pelement2 = cache.get("xm");
        System.out.println(pelement2.getObjectValue());
        
        System.out.println(cache.getSize());
        
        // 7. 刷新缓存
        cache.flush();
        
        // 8. 关闭缓存管理器
        cacheManager.shutdown();

    }

}

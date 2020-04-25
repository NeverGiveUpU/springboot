package learn.nosql.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author 陈濛
 * @date 2020/4/16 12:15 上午
 */
@Service
public class CacheService {

    @Cacheable(value = "user", key = "#userId")
    public String getUser(String userId) {
        return userId + "#" + System.currentTimeMillis();
    }

    @CachePut(value = "user", key = "#userId")
    public String updateUser(String userId) {
        return userId + "#" + System.currentTimeMillis();
    }

    @CacheEvict(value = "user", key = "#userId")
    public void deleteUser(String userId) {
    }

    //TODO spring-cache配置的ehcache没有生效，但是直接使用ehcache有效果
    @Cacheable(value = "ehCache", key = "#userId",
            cacheManager = CacheConfig.CacheManagerName.EHCACHE_CACHE_MANAGER)
    public String getUserByLocal(String userId) {
        return userId + "#" + System.currentTimeMillis();
    }


}

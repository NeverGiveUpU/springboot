package learn.nosql.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {

    // =============================common============================
    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    boolean expire(String key, long time);

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    long getExpire(String key);

    /**
     * key是否存在
     *
     * @param key
     * @return
     */
    boolean hasKey(String key);

    /**
     * 删除key
     * @param keys
     */
    void delete(String... keys);

    /**
     * 正则匹配删除key
     * @param pattern
     */
    void deleteByPattern(String pattern);

    // ============================String=============================
    /**
     * 根据k，读取v
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 新增kv
     * @param key
     * @param value
     * @return
     */
    boolean set(String key, Object value);

    /**
     * 新增带过期时间的kv
     * @param key
     * @param value
     * @param expireTime
     */
    boolean set(String key, Object value, long expireTime);

    /**
     * 递增
     * @param key
     * @param delta
     * @return
     */
    long increment(String key, long delta);

    /**
     * 递减
     * @param key
     * @param delta
     * @return
     */
    long decrement(String key, long delta);

    // ================================Map=================================
    /**
     * 哈希表中放入数据
     * @param key
     * @param hashKey
     * @param value
     */
    boolean hmSet(String key, Object hashKey, Object value);

    /**
     * 哈希表中放入数据
     * @param key
     * @param hashKey
     * @param value
     * @param expireTime
     */
    boolean hmSet(String key, Object hashKey, Object value, long expireTime);

    /**
     * 多个kv存入哈希对象
     * @param key
     * @param map
     * @return
     */
    boolean hmSet(String key, Map<Object, Object> map);

    /**
     * 多个kv存入哈希对象
     * @param key
     * @param map
     * @return
     */
    boolean hmSet(String key, Map<Object, Object> map, long expireTime);

    /**
     * 读哈希对象的指定kv
     * @param key
     * @param hashKey
     * @return
     */
    Object hmGet(String key, Object hashKey);

    /**
     * 读取哈希对象的所有ky
     * @param key
     * @return
     */
    Map<Object, Object> hmGet(String key);

    /**
     * 删除哈希表中的指定kv
     * @param key
     * @param hashKey
     * @return
     */
    boolean hmDelete(String key, Object... hashKey);

    /**
     * hash表是否包含k
     * @param key
     * @param hashKey
     * @return
     */
    boolean hmHasKey(String key, Object hashKey);




    // ===============================list=================================
    /**
     * 存入列表对象
     * @param key
     * @param value
     */
    boolean lSet(String key, Object value);

    boolean lSet(String key, Object value, long expireTime);

    boolean lSet(String key, List<Object> values);

    boolean lSet(String key, List<Object> values, long expireTime);

    /**
     * 读取列表对象
     * @param key
     * @param start 列表开始位置
     * @param end 列表结束位置
     * @return
     */
    List<Object> lGet(String key, long start, long end);

    /**
     * 指定位置的列表元素
     * @param key
     * @param index
     * @return
     */
    Object lGetIndex(String key, long index);

    /**
     * 更新指定位置的元素
     * @param key
     * @param index
     * @param value
     * @return
     */
    boolean lUpdateIndex(String key, long index, Object value);

    /**
     * 列表长度
     * @param key
     * @return
     */
    long lSize(String key);

    /**
     * 删除N个value
     * @param key
     * @param count
     * @param value
     * @return
     */
    long lRemove(String key, long count, Object value);




    // ============================set=============================
    /**
     * 存入集合对象
     * @param key
     * @param values
     */
    boolean sSet(String key, Object... values);

    boolean sSet(String key, long expireTime, Object... values);

    /**
     * 读取集合对象
     * @param key
     * @return
     */
    Set<Object> sGet(String key);

    /**
     * 集合是否有某个value
     * @param key
     * @param value
     * @return
     */
    boolean sHas(String key, Object value);

    long sSize(String key);

    long sDelete(String key, Object... values);


    // ============================有序set=============================
    /**
     * 存入有序集合对象
     * @param key
     * @param value
     * @param score 用来排序的分数
     */
    boolean zSet(String key, Object value, double score);

    /**
     * 读取有序集合对象
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<Object> zGet(String key, long start, long end);
}

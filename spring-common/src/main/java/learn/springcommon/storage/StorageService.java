package learn.springcommon.storage;

import lombok.Data;

/**
 * 提供存储服务类，所有存储服务均由该类对外提供
 *
 * @author chenmeng
 * @date 2020/06/30 14:31
 */
@Data
public class StorageService {
    private String active;
    private Storage storage;
}

package learn.springcommon.storage.config;

import learn.springcommon.storage.AliyunStorage;
import learn.springcommon.storage.LocalStorage;
import learn.springcommon.storage.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenmeng
 * @date 2020/06/30 14:25
 */
@Slf4j
@Configuration
public class StorageAutoConfiguration {
    private final StorageProperties properties;

    public StorageAutoConfiguration(StorageProperties properties) {
        this.properties = properties;
    }

    @Bean
    public StorageService storageService() {
        StorageService storageService = new StorageService();
        String active = this.properties.getActive();
        storageService.setActive(active);

        if (active.equals("local")) {
            storageService.setStorage(localStorage());
        }
        switch (active) {
            case "local":
                storageService.setStorage(localStorage());
                break;
            case "aliyun":
                storageService.setStorage(aliyunStorage());
                break;
            default:
                throw new RuntimeException("当前存储模式不支持" + active);
        }
        return storageService;
    }

    @Bean
    @ConditionalOnProperty("storage.local.address")
    public LocalStorage localStorage() {
        log.info("正在初始化本地存储服务");
        StorageProperties.Local local = properties.getLocal();
        return new LocalStorage(local.getStoragePath(), local.getAddress());
    }

    @Bean
    @ConditionalOnProperty("storage.aliyun")
    public AliyunStorage aliyunStorage() {
        log.info("正在初始化阿里云存储服务");
        StorageProperties.Aliyun aliyun = properties.getAliyun();
        return new AliyunStorage(
                aliyun.getEndpoint(),
                aliyun.getAccessKeyId(),
                aliyun.getAccessKeySecret(),
                aliyun.getBucketName()
        );
    }
}

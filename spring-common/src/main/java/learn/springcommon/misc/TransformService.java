package learn.springcommon.misc;

import org.springframework.stereotype.Service;

@Service
public interface TransformService extends TransformTypeSupport {
    void doTransform(TransformType transformType);
}

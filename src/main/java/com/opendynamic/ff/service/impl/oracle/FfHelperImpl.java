package com.opendynamic.ff.service.impl.oracle;

import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opendynamic.ff.service.FfHelper;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FfHelperImpl implements FfHelper {
    @Override
    public String getUserName(String userId) {
        return null;
    }

    @Override
    public List<String> getAllUserIdList(String userId) {
        return Collections.singletonList(userId);
    }
}
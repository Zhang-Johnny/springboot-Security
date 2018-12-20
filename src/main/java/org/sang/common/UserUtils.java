package org.sang.common;

import org.sang.bean.XhxGroupUser;
import org.sang.bean.XhxUser;
import org.sang.entity.ModuleEntity;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {
    public static ModuleEntity getCurrentUser() {
        return (ModuleEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

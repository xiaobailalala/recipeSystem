/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/30 10:02
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service;

import com.smxy.recipe.entity.Process;
import com.smxy.recipe.utils.ResApi;

public interface ProcessService {
    ResApi<Object> produceVoiceForId(Process process);
}

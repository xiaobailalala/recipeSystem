/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/18 20:53
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.Process;

import java.util.List;

public interface ProcessDao {
    Integer saveInfo(Process process);
    List<Process> getInfoByRid(Integer fRid);
    Integer deleteInfoByRid(Integer fRid);
    Integer updateVoiceById(Process process);
}

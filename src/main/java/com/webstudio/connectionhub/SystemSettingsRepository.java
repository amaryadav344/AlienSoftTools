package com.webstudio.connectionhub;

import com.webstudio.connectionhub.Models.SystemSetting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemSettingsRepository extends CrudRepository<SystemSetting, Long> {
    List<SystemSetting> findFirstBySettingName(String SettingName);

}

package com.webstudio.connectionhub.repositories;


import com.webstudio.connectionhub.common.SystemSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemSettingsRepository extends JpaRepository<SystemSetting, Long> {
    List<SystemSetting> findFirstBySettingName(String SettingName);

}

package com.webstudio.connectionhub;

import com.webstudio.connectionhub.Models.SystemSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemSettingsRepository extends JpaRepository<SystemSetting, Long> {
    List<SystemSetting> findFirstBySettingName(String SettingName);

}

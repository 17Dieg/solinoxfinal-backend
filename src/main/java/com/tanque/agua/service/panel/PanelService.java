package com.tanque.agua.service.panel;

import com.tanque.agua.dto.PanelDto;

public interface PanelService {
    PanelDto buscarInfoPorEmail(String email);
}

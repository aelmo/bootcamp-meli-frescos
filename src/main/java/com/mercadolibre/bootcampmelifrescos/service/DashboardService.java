package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.request.GetDashBoardDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.DashboardDTO;

public interface DashboardService {
    DashboardDTO getDashboardByPeriod(GetDashBoardDTO getDashBoardDTO) throws Exception;
}

package com.mercadolibre.bootcampmelifrescos.controller;

import com.mercadolibre.bootcampmelifrescos.dtos.request.GetDashBoardDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.DashboardDTO;
import com.mercadolibre.bootcampmelifrescos.service.DashboardService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/dashboard/")
@AllArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_AGENT')")
    public ResponseEntity getDashboardByPeriod( @Valid @RequestBody GetDashBoardDTO getDashBoardDTO) {
        try {
            return new ResponseEntity<DashboardDTO>(dashboardService.getDashboardByPeriod(getDashBoardDTO), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

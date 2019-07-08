package com.crud.library.controller;

import com.crud.library.domain.TitleDto;
import com.crud.library.mapper.TitleMapper;
import com.crud.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v1/library")
public class TitleController {
    @Autowired
    private DbService service;
    @Autowired
    private TitleMapper titleMapper;

    @RequestMapping(method = RequestMethod.POST, value = "addTitle", consumes = APPLICATION_JSON_VALUE)
    public void addTitle(@RequestBody TitleDto titleDto) {
        service.addTitle(titleMapper.mapToTitle(titleDto));

    }

    @RequestMapping(method = RequestMethod.GET, value = "getTitle")
    public TitleDto getTitle(@RequestParam String title){
        return titleMapper.mapToTitleDto(service.getTitle(title));
    }
}

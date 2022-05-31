package com.test.pims.service;

import com.test.pims.common.Mapper;
import com.test.pims.dao.service.ProjectService;
import com.test.pims.service.impl.WebProjectServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * Created by SafinaAA on 30.05.2022
 */
public class WebProjectServiceImplTest {

    @Mock
    private ProjectService projectService;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private WebProjectServiceImpl webProjectService;


}

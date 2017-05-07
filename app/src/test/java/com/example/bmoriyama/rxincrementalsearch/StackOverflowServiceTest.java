package com.example.bmoriyama.rxincrementalsearch;

import com.example.bmoriyama.rxincrementalsearch.service.StackOverflowAPI;
import com.example.bmoriyama.rxincrementalsearch.service.StackOverflowService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class StackOverflowServiceTest {

    StackOverflowService subject;

    @Mock
    StackOverflowAPI mockStackOverflowAPI;

    @Before
    public void setUp() {
        initMocks(this);

        subject = new StackOverflowService(mockStackOverflowAPI);
    }

    @Test
    public void searchQuestions_performsNetworkRequest() throws UnsupportedEncodingException {
        subject.searchQuestions("test query");

        verify(mockStackOverflowAPI).searchQuestions(URLEncoder.encode("test query", "UTF-8"));
    }
}

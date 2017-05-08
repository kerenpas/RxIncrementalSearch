package com.example.bmoriyama.rxincrementalsearch;

import com.example.bmoriyama.rxincrementalsearch.model.Item;
import com.example.bmoriyama.rxincrementalsearch.model.SearchResponse;
import com.example.bmoriyama.rxincrementalsearch.service.StackOverflowService;
import com.example.bmoriyama.rxincrementalsearch.ui.activity.SearchActivity;
import com.example.bmoriyama.rxincrementalsearch.viewmodel.action.SearchActionStateModel;
import com.example.bmoriyama.rxincrementalsearch.viewmodel.SearchViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/*
Only tests logic within the viewmodel's control. RxJava2 logic is tested/maintained by the library author.
RxSearchView query text change functionality can be tested in instrumentation tests.
 */
public class SearchViewModelTest {

    SearchViewModel subject;

    @Mock
    SearchActivity mockActivity;

    @Mock
    StackOverflowService mockService;

    @Mock
    SearchActionStateModel mockActionStateModel;

    @Mock
    SearchResponse mockResponse;

    @Mock
    Throwable mockThrowable;

    @Mock
    List<Item> mockList;

    @Before
    public void setUp() {
        initMocks(this);

        subject = new SearchViewModel(mockActivity, mockService, mockActionStateModel);
    }

    @Test
    public void onSuccess_setsActionStateToSuccess() {
        subject.onSuccess(mockResponse);

        verify(mockActionStateModel).success();
    }

    @Test
    public void onFailure_setsActionStateToFailure() {
        subject.onFailure(mockThrowable);

        verify(mockActionStateModel).failure(mockThrowable.getMessage());
    }

    @Test
    public void updateSearchResults_updatesSearchResultsOnView() {
        subject.updateSearchResults(mockList);

        verify(mockActivity).updateSearchResults(mockList);
    }

    @Test
    public void showError_showsErrorOnView() {
        subject.showError();

        verify(mockActivity).showError();
    }
}

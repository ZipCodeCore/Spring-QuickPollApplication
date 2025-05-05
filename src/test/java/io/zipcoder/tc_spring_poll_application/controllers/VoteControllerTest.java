package io.zipcoder.tc_spring_poll_application.controllers;

import io.zipcoder.tc_spring_poll_application.domain.Vote;
import io.zipcoder.tc_spring_poll_application.repositories.VoteRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * project: spring-demo
 * package: io.zipcoder.tc_spring_poll_application.controllers
 * author: https://github.com/vvmk
 * date: 4/6/18
 */
public class VoteControllerTest {

    @InjectMocks
    private VoteController voteCtrl;

    @Mock
    private VoteRepository voteRepo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        //create vote
        when(voteRepo.save(any(Vote.class))).thenAnswer(inv -> inv.getArgument(0));
        when(voteRepo.findAll()).thenReturn((Iterable<Vote>) mock(List.class));
        when(voteRepo.findVotesByPoll(anyLong())).thenReturn((Iterable<Vote>) mock(List.class));
    }

    @Test
    public void createVote() {
        Vote v = new Vote();
        long expected = 1;
        v.setId(expected);

        long actual = voteCtrl.createVote(1L, v).getBody().getId();

        verify(voteRepo).save(any(Vote.class));
        assertEquals(expected, actual);
    }

    @Test
    public void getVotesByPoll() {
        HttpStatus expected = HttpStatus.OK;
        HttpStatus actual = voteCtrl.getVotesForPoll(anyLong()).getStatusCode();

        verify(voteRepo).findVotesByPoll(anyLong());
        assertEquals(expected, actual);
    }

    @Test
    public void getAllVotes() {
        HttpStatus expected = HttpStatus.OK;
        HttpStatus actual = voteCtrl.getVotes().getStatusCode();

        verify(voteRepo).findAll();
        assertEquals(expected, actual);
    }
}
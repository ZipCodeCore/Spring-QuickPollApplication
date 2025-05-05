package io.zipcoder.tc_spring_poll_application.controllers;

import io.zipcoder.tc_spring_poll_application.domain.Poll;
import io.zipcoder.tc_spring_poll_application.exception.ResourceNotFoundException;
import io.zipcoder.tc_spring_poll_application.repositories.PollRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * project: spring-demo
 * package: io.zipcoder.tc_spring_poll_application.controllers
 * author: https://github.com/vvmk
 * date: 4/5/18
 */
public class PollControllerTest {

    @InjectMocks
    private PollController pollCtrl;

    @Mock
    private PollRepository pollRepo;

    private List<Poll> polls = new ArrayList<>();
    private Long exists = 10L;
    private Long dne = 999L;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        // GET all polls
        when(pollRepo.findAll()).thenReturn(polls);

        // GET one poll
        when(pollRepo.findById(anyLong())).thenReturn(Optional.of(new Poll()));

        // POST create poll
        // PUT update poll
        when(pollRepo.save(any(Poll.class))).thenAnswer(inv -> inv.getArgument(0));

        when(pollRepo.existsById(10L)).thenReturn(true);
        when(pollRepo.existsById(999L)).thenReturn(false);

//        when(pollRepo::save)
//                .onSuccess(HttpStatus.CREATED)
//                .onFail(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void getAllPollsReturnsIterable() {
        Class<?> expected = Iterable.class;
        Class<?> actual = pollCtrl.getAllPolls().getBody().getClass();

        verify(pollRepo).findAll();
        Assert.isAssignable(expected, actual);
    }

    @Test
    public void getAllPollsReturnsPolls() {
        polls.add(mock(Poll.class));

        long expected = 1;
        long actual = pollCtrl.getAllPolls().getBody().spliterator().getExactSizeIfKnown();

        verify(pollRepo).findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void createPollSavesPoll() {
        Poll expected = new Poll();
        expected.setId(1L);

        Poll actual = pollCtrl.createPoll(expected).getBody();

        verify(pollRepo).save(any(Poll.class));
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    public void createPollReturnsLocation() {
        Poll p = new Poll();
        p.setId(1L);

        Pattern expected = Pattern.compile("/polls/1");
        String actual = pollCtrl.createPoll(p).getHeaders().getLocation().toString();

        verify(pollRepo).save(any(Poll.class));
        assertTrue(expected.matcher(actual).find());
    }

    @Test
    public void getPollReturnsPoll() {
        HttpStatus expected = HttpStatus.OK;
        HttpStatus actual = pollCtrl.getPoll(anyLong()).getStatusCode();

        verify(pollRepo).findById(anyLong());
        assertEquals(expected, actual);
    }

    @Test
    public void updatesPollExists() {
        HttpStatus expected = HttpStatus.OK;
        HttpStatus actual = pollCtrl.updatePoll(mock(Poll.class), exists).getStatusCode();

        verify(pollRepo).save(any(Poll.class));
        assertEquals(expected, actual);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updatePollFails404() {
        HttpStatus expected = HttpStatus.OK;
        HttpStatus actual = pollCtrl.updatePoll(mock(Poll.class), dne).getStatusCode();

        verify(pollRepo).save(any(Poll.class));
    }

    @Test
    public void deletePoll() {
        HttpStatus expected = HttpStatus.OK;
        HttpStatus actual = pollCtrl.deletePoll(anyLong()).getStatusCode();

        verify(pollRepo).deleteById(anyLong());
        assertEquals(expected, actual);
    }

    @Test
    public void testVerifyPoll() {
        try {
            pollCtrl.verifyPoll(exists);
        } catch (ResourceNotFoundException rnfe) {
            fail();
        }
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testVerifyPollNotFound() {
        pollCtrl.verifyPoll(dne);
    }
}
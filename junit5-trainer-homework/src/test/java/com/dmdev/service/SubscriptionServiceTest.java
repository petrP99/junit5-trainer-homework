package com.dmdev.service;

import com.dmdev.dao.SubscriptionDao;
import com.dmdev.dto.CreateSubscriptionDto;
import com.dmdev.entity.Provider;
import com.dmdev.entity.Status;
import com.dmdev.entity.Subscription;
import com.dmdev.mapper.CreateSubscriptionMapper;
import com.dmdev.validator.CreateSubscriptionValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;


@ExtendWith(MockitoExtension.class)
class SubscriptionServiceTest {
    @Mock
    private CreateSubscriptionValidator subValidator;
    @Mock
    private SubscriptionDao subDao;
    @Mock
    private Subscription subscription;

    @Mock
    private CreateSubscriptionMapper createSubMapper;
    @Mock
    private Clock clock;
    @InjectMocks
    private SubscriptionService subService;

    @BeforeEach
    void init() {
        clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

//    @Test
//    void upsert() {
//
//        CreateSubscriptionDto createSubscriptionDto = getSubscriptionDto();
//        Subscription sub = getSubscription();
//
//        doReturn(new ValidationResult()).when(subValidator).validate(createSubscriptionDto);
//        doReturn(sub).when(subDao).findByUserId(subscription.getUserId());
//
//        Subscription actualResult = subService.upsert(createSubscriptionDto);
//        assertThat(actualResult).isEqualTo(createSubscriptionDto);
//        verify(subDao).upsert(subscription);
//    }

    //    @Test
//    void cancel() {
//        Subscription sub = getSubscription();
//        doReturn(sub).when(subDao.findById(sub.getId()));
//        subService.cancel(sub.getId());
//        assertThat(sub.getStatus()).isEqualTo(Status.CANCELED);
//    }
//
    private CreateSubscriptionDto getSubscriptionDto() {
        return CreateSubscriptionDto.builder()
                .userId(1)
                .name("Ivan")
                .provider(Provider.APPLE.name())
                .expirationDate(Instant.ofEpochMilli(1000L))
                .build();
    }

    private Subscription getSubscription() {
        return Subscription.builder()
                .userId(1)
                .name("Ivan")
                .provider(Provider.APPLE)
                .expirationDate(Instant.ofEpochMilli(1000L))
                .status(Status.ACTIVE)
                .build();
    }
}

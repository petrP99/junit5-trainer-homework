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
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@ExtendWith(MockitoExtension.class)
public class SubscriptionServiceIT extends SubscriptionServiceTest {

    private SubscriptionService subService;
    private SubscriptionDao subDao;

    @BeforeEach
    void init() {
        subDao = SubscriptionDao.getInstance();
        subService = new SubscriptionService(
                subDao,
                CreateSubscriptionMapper.getInstance(),
                CreateSubscriptionValidator.getInstance(),
                Clock.fixed(Instant.now(), ZoneId.systemDefault())
        );
    }

//    @Test
//    void upsert() {
//        CreateSubscriptionDto subDto = getSubscriptionDto();
//        Subscription actualResult = subService.upsert(subDto);
//        assertNotNull(actualResult.getUserId());
//    }
//
//    @Test
//    void cancel() {
//        Subscription sub = subDao.insert(getSub(11, "Pave"));
//        sub = subService.upsert(getSubscriptionDto());
//        subService.cancel(sub.getId());
//        assertThat(sub.getStatus()).isEqualTo(Status.CANCELED);
//    }

    private Subscription getSub(Integer userId, String name) {
        return Subscription.builder()
                .userId(userId)
                .name(name)
                .provider(Provider.APPLE)
                .expirationDate(Instant.now())
                .status(Status.ACTIVE)
                .build();
    }

    private CreateSubscriptionDto getSubscriptionDto() {
        return CreateSubscriptionDto.builder()
                .userId(1)
                .name("Ivan")
                .provider(Provider.APPLE.name())
                .expirationDate(Instant.ofEpochMilli(1000L))
                .build();
    }
}




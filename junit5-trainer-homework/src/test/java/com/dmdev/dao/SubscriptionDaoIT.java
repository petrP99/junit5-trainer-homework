package com.dmdev.dao;

import com.dmdev.entity.Provider;
import com.dmdev.entity.Status;
import com.dmdev.entity.Subscription;
import com.dmdev.integration.IntegrationTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SubscriptionDaoIT extends IntegrationTestBase {

    SubscriptionDao subDao = SubscriptionDao.getInstance();

    @Order(1)
    @Test
    void findAll() {
        Subscription sub1 = subDao.insert(getSub(1, "ivan"));
        Subscription sub2 = subDao.insert(getSub(2, "petr"));
        Subscription sub3 = subDao.insert(getSub(3, "pavel"));

        List<Subscription> actualResult = subDao.findAll();

        assertThat(actualResult).hasSize(3);
        List<Integer> subIds = actualResult.stream()
                .map(Subscription::getUserId)
                .toList();
        List<String> subNames = actualResult.stream()
                .map(Subscription::getName)
                .toList();

        assertThat(subIds).contains(sub1.getId(), sub2.getId(), sub3.getId());
        assertThat(subNames).contains(sub1.getName(), sub2.getName(), sub3.getName());
    }

    @Test
    void findById() {
        Subscription sub1 = subDao.insert(getSub(1, "ivan"));

        Optional<Subscription> actualResult = subDao.findById(sub1.getId());

        assertThat(actualResult).isPresent();
        assertThat(actualResult.get().equals(sub1));
    }

    @Test
    void deleteExistingEntity() {
        Subscription sub = subDao.insert(getSub(4, "Petr"));

        boolean actualResult = subDao.delete(sub.getUserId());

        assertTrue(actualResult);
    }

    @Test
    void deleteNotExistingEntity() {
        Subscription sub = subDao.insert(getSub(1, "ivan"));

        boolean actualResult = subDao.delete(1000);

        assertFalse(actualResult);
    }

    @Test
    void update() {
        Subscription sub = getSub(5, "Valera");
        subDao.insert(sub);
        sub.setName("Pavel");
        sub.setProvider(Provider.GOOGLE);
        subDao.update(sub);

        Subscription subUpdate = subDao.findById(sub.getId()).get();

        assertThat(subUpdate).isEqualTo(sub);
    }

    @Test
    void insert() {
        Subscription sub1 = getSub(1, "ivan");

        Subscription actualResult = subDao.insert(sub1);

        Assertions.assertNotNull(actualResult);
    }

    @Test
    void findByUserId() {
        Subscription sub1 = subDao.insert(getSub(1, "ivan"));

        List<Subscription> actualResult = subDao.findByUserId(sub1.getUserId());
        List<Integer> subUserId = actualResult.stream()
                .map(Subscription::getUserId)
                .toList();

        assertThat(subUserId).contains(sub1.getUserId());
    }

    private Subscription getSub(Integer userId, String name) {
        return Subscription.builder()
                .userId(userId)
                .name(name)
                .provider(Provider.APPLE)
                .expirationDate(Instant.ofEpochMilli(1000L))
                .status(Status.ACTIVE)
                .build();
    }
}